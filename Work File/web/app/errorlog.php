<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class errorlog extends Model
{
    protected $table ="errorlog";
    protected $primaryKey = "id";
    
    public $timestamps = false;
}
