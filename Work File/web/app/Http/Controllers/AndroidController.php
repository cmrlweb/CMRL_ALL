<?php

namespace CMRL\Http\Controllers;

use Illuminate\Http\Request;
use Input;
use Hash;
use Illuminate\Support\Facades\Redirect;
use CMRL\User;
use CMRL\apiuser;
use Auth;
use CMRL\Http\Requests;
use CMRL\Http\Controllers\Controller;

class AndroidController extends Controller
{
    public function login()
    {
        return Input::all();
    }

    public function register()
    {
        return view('androidregister');
    }
    public function storeuser()
    {
        $user = new apiuser;
        $user->name = Input::get('name');
        $user->email =Input::get('email');
        $user->password=Input::get('password');

        $user->save();
        return Redirect::to('/');
    }

    public function assetcode()
    {
        $assetcode = Input::get('ASSETCODE');

        $AssetDet = AssetCodes::where('assetcode', $assetcode)->first();
        $Ecode = $AssetDet->Ecode;

        $MachineDet = Equipment::where('Ecode',$Ecode)->first();
        $MachineName = $MachineDet->Name;
        $Maintain = Maintainence::where('Ecode',$Ecode)->get();
        
        foreach($Maintain as $index => $maintain)
        {
            $MachineDesc[] = $maintain->Name;
        }

        $query = "SELECT * FROM ".$MachineName." WHERE AssetCode ='".$assetcode."'";
        $Details = DB::connection('mysql2')->select($query);

        foreach($Maintain as $i => $maintain){
            $desc = $MachineDesc[$i];
            $Machvalue[] = $Details[0]->$desc;
        }
    }
}
