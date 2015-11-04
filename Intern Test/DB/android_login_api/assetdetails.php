<?php

error_reporting(-1);
ini_set('display_errors', 'On');


require_once 'include/DB_Functions.php';
$db = new DB_Functions();

// json response array
$response = array("error" => FALSE);

if (isset($_POST['ASSETCODE']) ){

	$ASSETCODE = $_POST['ASSETCODE'];
	
	$assetdetail = db->assetdetails($ASSETCODE);


	$response["error"] = FALSE;
    $response["AssetCODE"] = $assetdetail['AssetCODE'];
    $response["AssetName"] = $assetdetail['AssetName'];
    $response["AssetLocation"] = $assetdetail['AssetLocation'];
    $response["AssetInstallDate"] = $assetdetail['AssetInstallDate'];
    echo json_encode($response);
	
}
else{
	// required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters ASSETCODE is missing!";
    echo json_encode($response);
}
?>