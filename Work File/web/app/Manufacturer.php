<?php

namespace CMRL;

use Illuminate\Database\Eloquent\Model;

class Manufacturer extends Model
{
    protected $table ="Manu_det";
    protected $primaryKey = "Mid";
    public $timestamps = "false";
}
