<?php

namespace CMRL\Http\Controllers;

use Illuminate\Http\Request;
use Input;
use Hash;
use Illuminate\Support\Facades\Redirect;
use CMRL\User;
use CMRL\AssetCodes;
use DB;
use CMRL\Equipment;
use CMRL\Maintainence;
use CMRL\apiuser;
use CMRL\history;
use Auth;
use CMRL\Http\Requests;
use CMRL\Http\Controllers\Controller;

class AndroidController extends Controller
{
    public function login()
    {
        $email = Input::get('email');
        $user = apiuser::where('email',$email)->first();

        $pass = $user->password;

        $password = Input::get('password');

        if($pass == $password)
        {    
            $response["error"] = "false";
            $response["id"] = $user->id;
            $response["name"] = $user->name;
            $response["email"] = $user->email;
            $responser["created_at"] = $user->created_at;
        }
        else
        {
            $response["error"] = "true";
            $response["error_msg"] = "Login Credentials were wrong";
        }
        return json_encode($response);
    }

    public function register()
    {
        return view('androidregister');
    }
    public function storeuser()
    {
        $user = new apiuser;
        $user->name = Input::get("name");
        $user->email = Input::get("email");
        $user->password = Input::get("password");
        $user->save();
       return Redirect::to('/');
    }

    public function assetcode()
    {
        $assetcode = Input::get('ASSETCODE');
        $emailid = Input::get('email');

        $AssetDet = AssetCodes::where('assetcode', $assetcode)->first();
        $Ecode = $AssetDet->Ecode;

        $MachineDet = Equipment::where('Ecode',$Ecode)->first();
        $MachineName = $MachineDet->Name;
        $Maintain = Maintainence::where('Ecode',$Ecode)->get();
        
        foreach($Maintain as $index => $maintain)
        {
            $MachineDesc[] = $maintain->Name;
            $MachineDesc1[$index] = $maintain->Name;
        }

        $query = "SELECT * FROM ".$MachineName." WHERE AssetCode ='".$assetcode."' ORDER BY id DESC";
        $Details = DB::connection('mysql')->select($query);

        foreach($Maintain as $i => $maintain){
            $desc = $MachineDesc[$i];
            $Machvalue[] = $Details[0]->$desc;
            $Machvalue1[$i] = $Details[0]->$desc;
        }
        if(isset($assetcode))
        {
            $response["error"] = "false";
            foreach($Maintain as $index => $maintain)
            {
                $response["MachineDesc"][$index]["name"] = $MachineDesc1[$index];
            }
            foreach($Maintain as $index => $maintain){
                $response["Machvalue"][$index]["name"] = $Machvalue1[$index];
            }
        }
        else
            $response["error"] = "true";

        $apiuser = apiuser::where('email',$emailid)->first();
        $status = "QRFETCH";

        $historyuser = new history;
        $historyuser->assetcode = $assetcode;
        $historyuser->username = $apiuser->name;
        $historyuser->status = $status;

        $historyuser->save();

        return json_encode($response);
    }

    public function postdata()
    {
        $assetcode = Input::get('ASSETCODE');
        $emailid = Input::get('email');
        $checkboxsize = Input::get('Size');

        for($i=0;$i<$checkboxsize;$i++)
        {
            $value = "CheckBox_".$i;
            $MachCBValue[$i]=Input::get($value);
        }
        //Assetcode and its Retreival
        $AssetDet = AssetCodes::where('assetcode', $assetcode)->first();
        $Ecode = $AssetDet->Ecode;

        $MachineDet = Equipment::where('Ecode',$Ecode)->first();
        $MachineName = $MachineDet->Name;
        $Maintain = Maintainence::where('Ecode',$Ecode)->get();
        
        foreach($Maintain as $index => $maintain)
        {
            $MachineDesc[] = $maintain->Name;
            $Machinetimer[] = $maintain->timer;
        }

        $query = "SELECT * FROM ".$MachineName." WHERE AssetCode ='".$assetcode."'";
        $Details = DB::connection('mysql')->select($query);

        foreach($Maintain as $i => $maintain){
            $desc = $MachineDesc[$i];
            $Machvalue[] = $Details[0]->$desc;
        }
        
        //UNDERSTANDING this part is pretty hard
        //The main this is that i am constructing an insert query to be executed
        //Since everything is dynamic .First thing im creating is
        //INSERT INTO TABLENAME () , What comes inside that Brackets. and inside VALUES();
        //I use implode function to actually do this process of converting an array into string.
        $i=0;
        $j=0;
        while($j<count($MachineDesc))
        {
            $MachineValueforinsert[$i]=$MachCBValue[$j];
            $MachineValueforinsert[$i+1]=$Machinetimer[$j];
            //Values() will have the checkbox value and a timer . ex : 0,91,1,182 etc where 91 , 182 are timer days and 0,1
            //all are checkbox value.
            $i = $i + 2;
            $j = $j + 1;
        }

        $i=0;
        $j=0;
        while($j<count($MachineDesc))
        {
            $MachineDescforinsert[$i]=$MachineDesc[$j];
            $MachineDescforinsert[$i+1]=$MachineDesc[$j]."_d";
            //Inside table_name () , There will be a list of items we need to insert , That is what being done in this area.
            $i = $i + 2;
            $j = $j + 1;
        }

        $INMachineValue = implode(",",$MachineValueforinsert);
        $INMachineDescription = implode(",",$MachineDescforinsert);

        //End of Fetch. Now. Insert a new data int the Fan
        $query = "INSERT INTO ".$MachineName." (AssetCode,".$INMachineDescription.")"." VALUES("."'".$assetcode."'".",".$INMachineValue.")";
        
        $Success = DB::connection('mysql')->insert($query,[]);

        if($Success)
            $response["error"]="false";
        else
            $response["error"]="true";
        return json_encode($response);


        $apiuser = apiuser::where('email',$emailid)->first();
        $status = "CHANGED";

        $historyuser = new history;
        $historyuser->assetcode = $assetcode;
        $historyuser->username = $apiuser->name;
        $historyuser->status = $status;

        $historyuser->save();
    }

    public function syncdata()
    {
        $sync = Input::get('Sync');

        if($sync)
        {
            $response["error"]="false";
            $EquipmentDetails = Equipment::all();

            foreach($EquipmentDetails as $index => $equip){
                $response["Equipment"][$index]["Ecode"] = $equip->Ecode;
                $response["Equipment"][$index]["Value"] = $equip->Name;
            }

            $MaintainenceDetails = Maintainence::all();

            foreach ($MaintainenceDetails as $index => $Maintain) {
                $response["Maintainence"][$index]["Ecode"] = $Maintain->Ecode;
                $response["Maintainence"][$index]["Value"] = $Maintain->Name; 
            } 
            return json_encode($response);
        }
    }
}
