<?php
header('Content-Type: application/json');
include('config.php');

if($_SERVER['REQUEST_METHOD'] == 'POST'){

  $studentNo1 = $_POST['studentNumber1'];
  $query = "select moduleID, mtitle, venue, examDate, examTime from Modules
  natural join Exam natural join StudentModules where studentID = '$studentNo1';";

  $result = mysqli_query($con,$query);

  $number_of_rows = mysqli_num_rows($result);
  $temp_array = array();
  if($number_of_rows > 0){
    while($row = mysqli_fetch_assoc($result)){
    // $temp_array[] = $row;
    $temp_array[] = array(
      'moduleID' => $row['moduleID'],
      'mtitle' => $row['mtitle'],
      'venue' => $row['venue'],
      'examDate' => $row['examDate'],
      'examTime' => $row['examTime']

    );
    }
  }
// echo json_encode(array("ExamDetails" => $temp_array));
echo json_encode($temp_array);
mysqli_close($con);

}
 ?>
