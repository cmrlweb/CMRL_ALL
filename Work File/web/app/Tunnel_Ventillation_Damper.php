<?php

namespace CMRL;

use Illuminate\Database\Eloquent\Model;

class Tunnel_Ventillation_Damper extends Model
{
    protected $connection = 'mysql2';

    protected $table ="Tunnel_Ventilation_Damper";
    protected $primaryKey = "id";
}
