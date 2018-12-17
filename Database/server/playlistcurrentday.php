<?php
	require "connect.php";

	$query  = "SELECT DISTINCT  * FROM playlist ORDER BY rand(" . date("Ymd") . ") LIMIT 3";

	class PlaylistToday{
		function  PlaylistToday($idplayList, $ten, $hinhnen, $Icon){
			$this->idPlayList = $idplayList;
			$this->Ten = $ten;
			$this->HinhNen = $hinhnen;
			$this->HinhIcon = $Icon;

		}
	}
	
	$arrayplaylistfortoday = array();
	$data = mysqli_query($con,$query);
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($arrayplaylistfortoday, new PlaylistToday($row['idPlayList']
															,$row['Ten']
															,$row['HinhNen']
															,$row['HinhIcon']));	
		
	}
	echo json_encode($arrayplaylistfortoday);

	
?>