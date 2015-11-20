<?php

namespace CMRL\Http\Controllers;

use Illuminate\Http\Request;
use Input;
use CMRL\errorlog;
use CMRL\Role;
use Illuminate\Support\Facades\Redirect;
use CMRL\User;
use Auth;
use CMRL\history;
use CMRL\Http\Requests;
use CMRL\Http\Controllers\Controller;

class HomeController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $currentuserid = Auth::user()->id;
        $user = User::where('id', '=', $currentuserid)->first();
        $adminid=0;
        
        if($user->hasRole('admin'))
            $adminid=1; 
        
        $err = errorlog::all();
        return view('home',compact('adminid','err'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
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
    public function show()
    {
        return view('history');
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

    public function history()
    {
        $searchcontent = Input::get('searchcontent');

        $createdtime = Input::get('registration_date');

        $data = History::where('assetcode',$searchcontent)->where('created_at','>',$createdtime)->get();

        return view('history',compact('data'));
    }
    public function onetime()
    {
        $currentuserid = Auth::user()->id;
        $user = User::where('id', '=', $currentuserid)->first();
        echo $user;
    }

    public function errors()
    {
        $arch = Input::get('archiver');

        foreach($arch as $index => $archiver)
        {
            $value = $archiver[$index];

            $error = errorlog::where('id',$value)->first();

            $error->archive = 1;

            $error->save();
        }
        return Redirect::to('/');
        
    }

    public function roles()
    {
        $allusers = User::all();

        return view('roles.users',compact('allusers'));
    }
}
