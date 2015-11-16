<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\AssetCodes;
use App\Equipment;
use Input;
use Illuminate\Support\Facades\Redirect;

use App\Http\Requests;
use App\Http\Controllers\Controller;

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
 //       $acode->save();
 
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

        return view('asset.edit',compact('AC'));
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        //
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
        
        // $Equip->save();

        return Redirect::to('/');

    }
}
