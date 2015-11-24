<?php

error_reporting(-1);
ini_set('display_errors', 'On');


require_once 'assets/Asset_Functions.php';
$assf = new Asset_Functions();

// json response array
$response = array("error" => FALSE);

if (isset($_POST['ASSETCODE']) ){

	$ASSETCODE = $_POST['ASSETCODE'];
	
	$assetdetail = $assf->assetdetails($ASSETCODE);


	$response["error"] = FALSE;
    $response["ASSETCODE"] = $ASSETCODE;
    $response["AssetName"] = $assetdetail["AssetName"];
    $response["AssetLocation"] = $assetdetail["AssetLocation"];
    $response["AssetInstallDate"] = $assetdetail["AssetInstallDate"];
    echo json_encode($response);
	
}
else{
	// required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters ASSETCODE is missing!";
    echo json_encode($response);
}
?>