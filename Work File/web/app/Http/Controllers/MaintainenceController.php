<?php

namespace CMRL\Http\Controllers;

use CMRL\AssetCodes;
use CMRL\Equipment;
use Input;

use Illuminate\Http\Request;
use CMRL\Maintainence;
use CMRL\Http\Requests;
use Illuminate\Support\Facades\Redirect;

use CMRL\Http\Controllers\Controller;

class MaintainenceController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $Maintainence = Maintainence::all();

        return view('maintainence.index',compact('Maintainence'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        $main = new Maintainence;
        $Mval = Input::all();

        $main->mnid = Input::get('mnid');
        $main->Ecode = Input::get('selector');
        $main->Name = Input::get('mnname');
        $main->timer = Input::get('timer');
        $main->save();

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
        //
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
        $Equip = Equipment::all();
        $lastmain = Maintainence::orderBy('mnid', 'desc')->first();
        return view('maintainence.add',compact('Equip','lastmain'));
    
    }
}
