<?php

include('config.php');
header('Content-Type: application/json');

if($_SERVER['REQUEST_METHOD'] == 'POST'){

  $staffID = $_POST['staffID'];


  $query = "select * from StaffPhotos where staffID = '$staffID';";
  $result = mysqli_query($con, $query);
  $row = mysqli_fetch_array($result);

  $yo = base64_encode($row['image']);
  echo json_encode($yo);


mysqli_close($con);
}

?>
