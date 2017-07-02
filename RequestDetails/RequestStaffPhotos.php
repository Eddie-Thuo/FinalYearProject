<?php

include('config.php');
header('Content-Type: application/json');

if($_SERVER['REQUEST_METHOD'] == 'POST'){

  $staffID = $_POST['staffID'];
  $query3 = "SELECT * FROM StaffPhotos WHERE staffID = ? ";

  $stmt = $con->prepare($query3);
  $stmt->bind_param("s", $staff_number);
  $staff_number = $staffID;
  $stmt->execute();
  $result = $stmt->get_result();
  $row = mysqli_fetch_array($result);

  $yo = base64_encode($row['image']);
  echo json_encode($yo);


mysqli_close($con);
}

?>
