<?php


class Asset_Functions {

    private $conn1;
    private $conn2;

    // constructor
    function __construct() {
        require_once 'Config.php';
        // connecting to database
        $this->conn1 = new mysqli(DB_HOST, DB_USER, DB_PASSWORD, DB_DB1);
        $this->conn2 = new mysqli(DB_HOST, DB_USER, DB_PASSWORD, DB_DB2);
    }

    // destructor
    function __destruct() {
        
    }

    public function assetdetails($ASSETCODE)
    {
        //Getting the Ecode from the First Asset Table.
        $stmt = $this->conn1->prepare("SELECT * FROM AssetCodes WHERE ASSETCODE= ?");
        $stmt->bind_param("s", $ASSETCODE);
        $stmt->execute();
        $RESULT1 = get_resultin($stmt);
        $arrRES1= array_shift( $RESULT1 );
        $Ecode = $arrRES1["Ecode"];
        $stmt->close();

        //Getting the AssetName from Equipment Table.
        $stmt2 = $this->conn1->prepare("SELECT * FROM Equipment WHERE Ecode = ?");
        $stmt2 ->bind_param("s",$Ecode);
        $stmt2 ->execute();
        $RESULT2 = get_resultin($stmt);
        $arrRes2 = array_shift( $RESULT2 );
        $AssetName = $arrRes2["Name"];
        $stmt2 ->close();

        //Getting the AssetLocation and AssetInstallation Date from EQMAIN1 DB.
/*
        if($AssetName == NULL)
        {
            $AssetName = "Tunnel_Ventilation_Fan";
        }
        $stmt3 = $this->conn2->prepare("SELECT * FROM ? WHERE ASSETCODE = ?");
        $stmt3 ->bind_param("ss",$AssetName,$ASSETCODE);
        $stmt3 ->execute();
        $RESULT3 = get_resultin($stmt);
        $AssetLocIS = array_shift( $RESULT3 );
        $AssetLocation = $AssetLocIS["location"];
        $AssetInstallDate = $AssetLocIS["installdate"];
        $stmt3 ->close(); */
        $AssetLocation = "LHS";
        $AssetInstallDate = "Sept 10";

        $assetdetail = array("AssetName"=> $AssetName , "AssetLocation"=> $AssetLocation, "AssetInstallDate"=> $AssetInstallDate);
        return $assetdetail;
    }

}

    function get_resultin( $Statement ) {
    $RESULT = array();
    $Statement->store_result();
    for ( $i = 0; $i < $Statement->num_rows; $i++ ) {
        $Metadata = $Statement->result_metadata();
        $PARAMS = array();
        while ( $Field = $Metadata->fetch_field() ) {
            $PARAMS[] = &$RESULT[ $i ][ $Field->name ];
        }
        call_user_func_array( array( $Statement, 'bind_result' ), $PARAMS );
        $Statement->fetch();
    }
    return $RESULT;
    }
?>
