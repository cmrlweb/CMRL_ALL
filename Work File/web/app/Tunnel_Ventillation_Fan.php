<?php

namespace CMRL;

use Illuminate\Database\Eloquent\Model;

class Tunnel_Ventillation_Fan extends Model
{
    protected $connection = 'mysql2';

    protected $table ="Tunnel_Ventilation_Fan";
    protected $primaryKey = "id";
}
