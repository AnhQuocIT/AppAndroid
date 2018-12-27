<?php
	require "connect.php";

	class Album{
		function Album($idalbum, $tenalbum, $tencasi, $hinhalbum){
			$this->idAlbum = $idalbum;
			$this->TenAlbum = $tenalbum;
			$this->TenCaSiAlbum = $tencasi;
			$this->HinhAlbum = $hinhalbum;
		}
	}

	$arrayalbum = array();
	$query = "SELECT * FROM album";

	$data = mysqli_query($con, $query);

	while ($row = mysqli_fetch_assoc($data)) {
		array_push($arrayalbum, new Album($row['idAlbum']
										,$row['TenAlbum']
										,$row['TenCaSiAlbum']
										,$row['HinhAlbum']));
		# code...
	}
	echo json_encode($arrayalbum);
?>