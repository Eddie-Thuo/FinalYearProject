<?php
header('Content-Type: application/json');
include('config.php');

if($_SERVER['REQUEST_METHOD'] == 'POST'){

  $studentNo1 = $_POST['studentNumber1'];
  $query = "SELECT moduleID, mtitle, venue, examDate, examTime FROM Modules
  NATURAL JOIN Exam NATURAL JOIN StudentModules WHERE studentID = ?";

  $stmt = $con->prepare($query);
  $stmt->bind_param("s", $student_number1);
  $student_number1 = $studentNo1;
  $stmt->execute();
  $result = $stmt->get_result();

  // $result = mysqli_query($con,$query);
  // $number_of_rows = mysqli_num_rows($result);
  $number_of_rows = $result->num_rows;
  $temp_array = array();
  if($number_of_rows > 0){
    while($row = $result ->fetch_assoc()){
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
