<?php
//Created by Kauta Sentio
//Restful PHP API to insert data into a remote database and for this demo we using our local host

require_once('connect.php');
// array for JSON response
// check for required fields

if (isset($_REQUEST['data']) ) {
   
    $vid= $_REQUEST['data'];
   
//store all JSON files in an array
$response = array();
	
	//Assign values to the of incoming data to be stored into the database
	$signal = $_REQUEST['data'];	
		$sensor= $_REQUEST['sensor'];	
	


    //  inserting a new row
   $insertip = mysql_query("INSERT INTO farm_info(signal,sensor) VALUES ('$signal','$sensor')");

    if ($insertip ) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "successfull inserted into the database.";

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
    $response["message"] = "Required you can only vote once";

    // echoing JSON response
    echo json_encode($response);
}
	//
?>
