<?php
header('Content-Type: application/json');
include('config.php');

if($_SERVER['REQUEST_METHOD'] == 'POST'){

  $studentNo = $_POST['studentNo2'];

  $query2 = "SELECT moduleID, mtitle, firstname, lastname, email, telephone, image FROM Staff NATURAL JOIN StudentModules
  NATURAL JOIN StaffContact NATURAL JOIN Modules NATURAL JOIN StaffPhotos WHERE studentID = ? ";

  $stmt = $con->prepare($query2);
  $stmt->bind_param("s", $student_number2);
  $student_number2 = $studentNo2;
  $stmt->execute();
  $result = $stmt->get_result();

  // $result = mysqli_query($con,$query2);
  // $number_of_rows = mysqli_num_rows($result);
  $number_of_rows = $result->num_rows;
  $temp_array = array();
  if($number_of_rows > 0){
    while($row = $result ->fetch_assoc()){
    // $temp_array[] = $row;
    $temp_array[] = array(
      'moduleID' => $row['moduleID'],
      'mtitle' => $row['mtitle'],
      'firstname' => $row['firstname'],
      'lastname' => $row['lastname'],
      'email' => $row['email'],
      'telephone' => $row['telephone'],
      'image' => base64_encode($row['image'])
    );
    }
  }
echo json_encode($temp_array);
mysqli_close($con);
}

?>
