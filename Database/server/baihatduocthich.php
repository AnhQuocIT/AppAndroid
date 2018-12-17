<?php
	require "connect.php";
	class Baihat{
		function Baihat($idBaiHat,$TenBaiHat,$HinhBaiHat,$CaSi,$LinkBaiHat,$LuotThich){
			$this->idBaiHat = $idBaiHat;
			$this->TenBaiHat = $TenBaiHat;
			$this->HinhBaiHat = $HinhBaiHat;
			$this->CaSi = $CaSi;
			$this->LinkBaiHat = $LinkBaiHat;
			$this->LuotThich = $LuotThich;
		}
	}
	$arraycasi = array();
	$query = "SELECT * FROM baihat ORDER BY LuotThich DESC LIMIT 5";
	$data = mysqli_query($con,$query);
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($arraycasi, new Baihat($row['idBaiHat']
					,$row['TenBaiHat']
					,$row['HinhBaiHat']
					,$row['CaSi']
					,$row['LinkBaiHat']
					,$row['LuotThich']));
	}
	echo json_encode($arraycasi);
?>