<?php

/*
 * Following code will get single signal details
 * A signal is identified by signal id (pid)
 */

// array for JSON response
$response = array();


// include db connect class
require_once __DIR__ . '/db_connect.php';

// connecting to db
$db = new DB_CONNECT();

// check for post data
if (isset($_GET["pid"])) {
    $pid = $_GET['pid'];

    // get a signal from signals table
    $result = mysql_query("SELECT *FROM farm_info WHERE pid = $pid");

    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) {

            $result = mysql_fetch_array($result);

            $signal = array();
            $signal["pid"] = $result["pid"];
            $signal["signal"] = $result["signal"];
           
            // success
            $response["success"] = 1;

            // user node
            $response["signal"] = array();

            array_push($response["signal"], $signal);

            // echoing JSON response
            echo json_encode($response);
        } else {
            // no signal found
            $response["success"] = 0;
            $response["message"] = "No signal found";

            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // no signal found
        $response["success"] = 0;
        $response["message"] = "No signal found";

        // echo no users JSON
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>