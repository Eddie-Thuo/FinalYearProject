<?php

  include 'config.php';
  $response = '';

  if($_SERVER['REQUEST_METHOD']=='POST'){

    $studentNo = $_POST['studentNo'];
    $password = $_POST['password'];


    if($studentNo == '' || $password == '') {
      $response = "Cannot Sign In.";
      echo $response;
    }
    else{
      // $query = "SELECT * FROM StudentLogin WHERE studentID = '$studentNo' AND password = '$password'";
      $query2 = "SELECT * FROM StudentLogin WHERE studentID = ? AND password = ? ";
      $stmt = $con-> prepare($query2);
      $stmt->bind_param('ss', $studentNo, $password);
      $stmt->execute();
      //$result = mysqli_query($con, $query);
    }
    if($stmt){
      $response = "Working";
      mysqli_close($con);

    }
    else{
      $response = "Problem";
      mysqli_close($con);

    }
    echo $response;
  }
 ?>
