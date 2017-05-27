<?php

/*
 * Following code will display all the signals sent to DB
 */

// array for JSON response
$response = array();


// include db connect class
require_once __DIR__ . '/db_connect.php';

// connecting to db
$db = new DB_CONNECT();

// get all signal info from table
$result = mysql_query("SELECT *FROM farm_info") or die(mysql_error());

// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // signal node
    $response["signal"] = array();
    
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $product = array();
        $product["signal"] = $row["signal"];
        $product["sensor"] = $row["sensor"];
        


        // push single product into final response array
        array_push($response["signal"], $product);
    }
    // success
    $response["success"] = 1;

    // echoing JSON response
    echo json_encode($response);
} else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "No signal found";

    // echo no users JSON
    echo json_encode($response);
}
?>
