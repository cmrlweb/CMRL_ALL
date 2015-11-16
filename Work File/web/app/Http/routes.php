<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It's a breeze. Simply tell Laravel the URIs it should respond to
| and give it the controller to call when that URI is requested.
|
*/

Route::get('/',['middleware' => 'auth','uses' => 'HomeController@index']);


//Maintaining the Asset Codes
Route::get('/assets/list',['middleware' => 'auth','uses' => 'AssetsController@index']);
Route::get('/assets/add',['middleware' => 'auth','uses' => 'AssetsController@addpage']);
Route::post('/assets/add',['middleware' => 'auth','uses' => 'AssetsController@create']);
Route::get('/assets/equipment',['middleware' => 'auth','uses' => 'AssetsController@equipment']);
Route::post('/assets/equipment',['middleware' => 'auth','uses' => 'AssetsController@addequip']);
Route::post('/assets/modify',['middleware' => 'auth','uses' => 'AssetsController@update']);
Route::get('/assets/edit/{id}',['middleware' => 'auth','uses' => 'AssetsController@edit']);
Route::get('/assets/delete/{id}',['middleware' => 'auth','uses' => 'AssetsController@destroy']);


//Maintaining the Manufacturers
Route::get('/manufacturer/list',['middleware' => 'auth','uses' => 'ManufacturerController@index']);
Route::get('/manufacturer/add',['middleware' => 'auth','uses' => 'ManufacturerController@addpage']);
Route::post('/manufacturer/add',['middleware' => 'auth','uses' => 'ManufacturerController@create']);
Route::get('/manufacturer/edit/{id}',['middleware' => 'auth','uses' => 'ManufacturerController@edit']);
Route::get('/manufacturer/delete/{id}',['middleware' => 'auth','uses' => 'ManufacturerController@destroy']);

//Maintaining the Maintainence details
Route::get('/maintainence/list',['middleware' => 'auth','uses' => 'MaintainenceController@index']);
Route::get('/maintainence/add',['middleware' => 'auth','uses' => 'MaintainenceController@addpage']);
Route::post('/maintainence/add',['middleware' => 'auth','uses' => 'MaintainenceController@create']);
Route::get('/maintainence/edit/{id}',['middleware' => 'auth','uses' => 'MaintainenceController@edit']);
Route::get('/maintainence/delete/{id}',['middleware' => 'auth','uses' => 'MaintainenceController@destroy']);



Route::controllers([
    'auth' => 'Auth\AuthController',
    'password' => 'Auth\PasswordController',
]);