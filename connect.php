<?php
//String connection to the database
$connect = mysql_connect("localhost","root","") or die ('<h2>Failed to connect to the web server. Please Check your web server!</h2>');
mysql_select_db("smartfarm",$connect) or die ('<h2>Failed to connect to the database!</h2>');
?>