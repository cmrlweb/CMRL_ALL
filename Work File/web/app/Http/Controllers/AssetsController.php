<?php

namespace CMRL\Http\Controllers;

use Illuminate\Http\Request;
use CMRL\AssetCodes;
use CMRL\Maintainence;
use CMRL\Equipment;
use CMRL\Tunnel_Ventillation_Fan;
use CMRL\Tunnel_Ventillation_Damper;
use Input;
use Illuminate\Support\Facades\Redirect;
use DB;
use CMRL\Http\Requests;
use CMRL\Http\Controllers\Controller;

class AssetsController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */

    public function index()
    {
        $AC = AssetCodes::all();

        return view('asset.index',compact('AC'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
    
        $acode = new AssetCodes;
        $acode->assetcode = Input::get('assetcode');
        $Eval = Input::get('selector');
        $acode->Ecode = $Eval;
        $acode->save();

        $AssetDet = AssetCodes::where('assetcode',Input::get('assetcode'))->first();
        $Ecode = $AssetDet->Ecode;
        $MachineDet = Equipment::where('Ecode',$Ecode)->first();
        $MachineName = $MachineDet->Name;
        $Maintain = Maintainence::where('Ecode',$Ecode)->get();
        
        foreach($Maintain as $index => $maintain)
        {
            $MachineDesc[] = $maintain->Name;
            $Machinetimer[] = $maintain->timer;
        }

        $i=0;
        $j=0;
        while($j<count($MachineDesc))
        {
            $MachineValueforinsert[$i]="0";
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

        $query = "INSERT INTO ".$MachineName." (AssetCode,".$INMachineDescription.")"." VALUES("."'".Input::get('assetcode')."'".",".$INMachineValue.")";
        $Details = DB::connection('mysql')->insert($query,[]);
 
        return Redirect::to('/');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        //
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $AC = AssetCodes::find($id);
        $Enames = Equipment::all();

        return view('asset.edit',compact('AC','Enames'));
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update()
    {
        $aid = Input::get('ID');

        $asset = AssetCodes::find($aid);

        $asset->assetcode = Input::get('assetcode');

        $asset->Ecode = Input::get('selector');

        $mod = Input::get('modify');

        if($mod == 'Edit')
        {
            $asset->save();
        }
        elseif($mod == 'Delete')
        {
            $asset->delete();
        }
         return Redirect::to('/');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
    }

    public function addpage()
    {
        $Enames = Equipment::all();

        return view('asset.add',compact('Enames'));
    }

    public function equipment()
    {
        $Equip = Equipment::all();

        return view('asset.equipment',compact('Equip'));
    }

    public function addequip()
    {
        $Equip = new Equipment;
        $Equip->Ecode = Input::get('Ecode');
        $Equip->Name = Input::get('Ename');

        $Equip->save();
        
        return Redirect::to('/');

    }

    public function report()
    {
        $assetcode = Input::get('assetcode');
        $operator = Input::get('username');

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

        return view('report',compact('assetcode','operator','Maintain','AssetDet','MachineDesc','Machvalue'));

    }
}
