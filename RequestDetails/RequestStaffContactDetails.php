<?php
header('Content-Type: application/json');
include('config.php');

if($_SERVER['REQUEST_METHOD'] == 'POST'){

  $studentNo = $_POST['studentNumber2'];

  $query = "select moduleID, mtitle, firstname, lastname, email, telephone, image from Staff natural join StudentModules
  natural join StaffContact natural join Modules natural join StaffPhotos where studentID = '$studentNo';";

  $result = mysqli_query($con,$query);

  $number_of_rows = mysqli_num_rows($result);
  $temp_array = array();
  if($number_of_rows > 0){
    while($row = mysqli_fetch_assoc($result)){
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
