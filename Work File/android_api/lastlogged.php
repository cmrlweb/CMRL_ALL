<?php



require_once 'include/DB_Functions.php';
$db = new DB_Functions();

// json response array
$response = array("error" => FALSE);

if (isset($_POST['timelogged']) && isset($_POST['email'])) {

	$timelogged = $_POST['timelogged'];
    $email = $_POST['email'];

    $succ = $db->lastlogged($timelogged,$email);

    if ($succ != false) {
        // use is found
        $response["error"] = FALSE;
        $response["message"] = "Success in Posting Last Logged in.";
        echo json_encode($response);
    } else {
        // user is not found with the credentials
        $response["error"] = TRUE;
        $response["error_msg"] = "Couldnt Post Data . Something Wrong.";
        echo json_encode($response);
    }

} else {
    // required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters Time and Email are missing!";
    echo json_encode($response);
}

?>
