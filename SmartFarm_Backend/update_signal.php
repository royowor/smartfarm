<?php

/*
 * Following code will update a signal information
 * A signal is identified by signal id (pid)
 */

// array for JSON response
$response = array();

// check for required fields
if (isset($_GET["pid"])) {
    $pid = $_POST['pid'];
 $signal= $_POST['signal'];

    // include db connect class
    require_once __DIR__ . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();

    // mysql update row with matched pid
    $result = mysql_query("UPDATE farm_info SET  signal= '$signal' WHERE pid = $pid");

    // check if row inserted or not
    if ($result) {
        // successfully updated
        $response["success"] = 1;
        $response["message"] = "signal successfully updated.";
        
        // echoing JSON response
        echo json_encode($response);
    } else {
        
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>
