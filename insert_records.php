<?php
require_once('connect.php');
// array for JSON response
// check for required fields

if (isset($_REQUEST['data']) ) {
   
    $vid= $_REQUEST['data'];
   

$response = array();
	$id = $_REQUEST['name'];	
	$ip = $_SERVER['REMOTE_ADDR'];
	$time = time();	
	$timeout = 1;  //1 day or 24 hours;
	$out = $time - $timeout;
	$check = mysql_query("SELECT * FROM tblip WHERE ip='$ip' AND time > '$out' ");
	
	
	
	
	if(mysql_num_rows($check) > 0 ){
		  $response["success"] = 0;
        $response["message"] = "You can only vote once a day.";

		//header("Location:index.php?error=1");
	}//else{
		//$insertip = mysql_query("INSERT INTO tblip(ip,time) VALUES ('$ip','$time')");
		//updatevote = mysql_query("UPDATE tblvotes SET vpoints = vpoints + 1 WHERE vid = '$id'") or die(mysql_error());
		//("Location:index.php?success=1");
		// array for JSON response


// check for required fields
if (isset($_REQUEST['name']) ) {
    
    $id=sprintf( $_REQUEST['name']);
    

    // include db connect class
    require_once __DIR__ . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();

    // mysql inserting a new row
   $insertip = mysql_query("INSERT INTO tblip(ip,time) VALUES ('$ip','$time')");
$updatevote = mysql_query("UPDATE accomodation SET Status = Status+1 WHERE name = '$id'");
    // check if row inserted or not
    if ($insertip ) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Thank you for your vote.";

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
}}
	//
?>