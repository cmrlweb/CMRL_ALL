<?php

namespace CMRL;

use Illuminate\Database\Eloquent\Model;

class Equipment extends Model
{
    protected $table ="Equipment";
    protected $primaryKey = "Ecode";
    
    public $timestamps = false;

    public static $rules = [
    'Name' => 'required'
    ];
}
