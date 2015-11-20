<?php

namespace CMRL;

use Illuminate\Database\Eloquent\Model;

class AssetCodes extends Model
{
    protected $table ="AssetCodes";
    protected $primaryKey = "ID";
    
    public $timestamps = false;


    public static $rules = [
    'assetcodes' => 'required',
    'Ecode' => 'required'
    ];

}
