<?php

error_reporting(-1);
ini_set('display_errors', 'On');


require_once 'assets/Config.php';

    $conn1 = new mysqli(DB_HOST, DB_USER, DB_PASSWORD, DB_DB1);

// Check connection
if ($conn1->connect_error) {
    die("Connection failed: " . $conn1->connect_error);
} 

// json response array
$response = array("error" => FALSE);

if (isset($_POST['ASSETCODE']) ){

	$ASSETCODE = $_POST['ASSETCODE'];
	   
    $stmt = $conn1->prepare("SELECT Ecode FROM cmrlmain WHERE ASSETCODE = ?");
    $stmt->bind_param("s", $ASSETCODE);
    $stmt->execute();
    $RESULT = get_resultin($stmt);
    $Ecode = array_shift( $RESULT );

    if($Ecode == 1)
        $ASSETNAME = "Tunnel_Ventilation_Fan";
    else
        $ASSETNAME = "Tunnel_Ventilation_Damper";

	$response["error"] = FALSE;
    $response["ASSETCODE"] = $ASSETCODE;
    $response["AssetName"] = $ASSETNAME;
    echo json_encode($response);
	
}
else{
	// required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters ASSETCODE is missing!";
    echo json_encode($response);
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

$conn1->close();
?>