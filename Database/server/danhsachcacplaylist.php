<?php
	require "connect.php";

	$query = "SELECT * FROM playlist";
	

	class Danhsachplaylist{
		function Danhsachplaylist($idplaylist,$ten,$hinhnen,$hinhicon){
			$this->idPlayList = $idplaylist;
			$this->Ten = $ten;
            $this->HinhNen = $hinhnen;
            $this->HinhIcon = $hinhicon;
        }
	}
	$data = mysqli_query($con,$query);
	$arrayplaylist = array();
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($arrayplaylist, new Danhsachplaylist($row['idPlayList']
														,$row['Ten']
														,$row['HinhNen']
														,$row['HinhIcon']));
	}
	echo json_encode($arrayplaylist);

?>