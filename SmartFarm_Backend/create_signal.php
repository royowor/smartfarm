<?php

/*
 * Following code will create a new signal row
 * All signal details are read from HTTP Post Request
 */

// array for JSON response
$response = array();

// check for required fields

if (isset($_REQUEST['data']) ) {    
	
	//Assign values to the of incoming data to be stored into the database
	$signal = $_REQUEST['data'];	
		$sensor= $_REQUEST['sensor'];	

    // include db connect class
    require_once __DIR__ . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();

    // mysql inserting a new row
    $result = mysql_query("INSERT INTO farm_info(signal,sensor) VALUES ('$signal','$sensor')");

    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "signal successfully created.";

        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
        
        // echoing JSON response
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