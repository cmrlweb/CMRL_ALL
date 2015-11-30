<?php

namespace CMRL\Http\Controllers;

use Illuminate\Http\Request;
use Input;
use CMRL\User;
use Auth;
use CMRL\Http\Requests;
use CMRL\Http\Controllers\Controller;

class AndroidController extends Controller
{
    public function login()
    {

    	//Getting Login Request from app.
    	$email = Input::get('email');
    	$password = Input::get('password');

    	if($success)
    	{
        	$user = User::where('id', '=', $currentuserid)->first();
    		
    		// user is found
        	$response["error"] = FALSE;
        	$response["id"] = $user->id;
        	$response["message"] = "Success in Posting Last Logged in.";
        	$response["user"]["name"] = $user->name;
        	$response["user"]["email"] = $user->email;
        	$response["user"]["created_at"] = $user->created_at;
        	echo json_encode($response);
    	}
    	else
    	{
    		$response["error"] = TRUE;
        	$response["error_msg"] = "Login credentials are wrong. Please try again!";
        	echo json_encode($response);
    	}
    }
    public function register()
    {
        return view('androidregister');
    }
    public function store()
    {
        return Input::all();
    }
}
