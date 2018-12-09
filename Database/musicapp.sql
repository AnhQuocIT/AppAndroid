-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 09, 2018 at 05:55 AM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.1.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `musicapp`
--

-- --------------------------------------------------------

--
-- Table structure for table `album`
--

CREATE TABLE `album` (
  `idAlbum` int(11) NOT NULL,
  `TenAlbum` varchar(255) NOT NULL,
  `TenCaSiAlbum` varchar(255) NOT NULL,
  `HinhAlbum` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `album`
--

INSERT INTO `album` (`idAlbum`, `TenAlbum`, `TenCaSiAlbum`, `HinhAlbum`) VALUES
(1, 'Phía Sau Một Cô Gái', 'Soobin Hoàng Sơn', 'http://localhost:8080/musicapp/img/album/Phiasaumotcogai-SoobinHoangSon.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `baihat`
--

CREATE TABLE `baihat` (
  `idBaiHat` int(11) NOT NULL,
  `idAlbum` varchar(255) NOT NULL,
  `idTheLoai` varchar(255) NOT NULL,
  `idPlayList` varchar(255) NOT NULL,
  `TenBaiHat` varchar(255) NOT NULL,
  `HinhBaiHat` varchar(255) NOT NULL,
  `CaSi` varchar(255) NOT NULL,
  `LinkBaiHat` varchar(255) NOT NULL,
  `LuotThich` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `baihat`
--

INSERT INTO `baihat` (`idBaiHat`, `idAlbum`, `idTheLoai`, `idPlayList`, `TenBaiHat`, `HinhBaiHat`, `CaSi`, `LinkBaiHat`, `LuotThich`) VALUES
(1, '1', '0', '0', 'Phía Sau Một Cô Gái', 'http://localhost:8080/musicapp/img/album/Phiasaumotcogai-SoobinHoangSon.jpg', 'Soobin Hoàng Sơn', 'http://localhost:8080/musicapp/music/Phia-Sau-Mot-Co-Gai-Soobin-Hoang-Son.mp3', 0),
(2, '0', '1', '0', 'Eastside (Acoustic)', 'http://localhost:8080/musicapp/img/baihat/Eastside(Acoustic).jpg', 'Benny Blanco, Halsey, Khalid', 'http://localhost:8080/musicapp/music/Eastside-Acoustic-Benny-Blanco-Halsey-Khalid.mp3', 0),
(3, '0', '1', '0', 'We Are the Night (Acoustic Mix)', 'http://localhost:8080/musicapp/img/baihat/WeAretheNight(AcousticMix).jpg', '\r\nMadison Mars, Sanjana Ghosh', 'http://localhost:8080/musicapp/music/We-Are-the-Night-Acoustic-Mix-Madison-Mars-Sanjana-Ghosh_128.mp3', 0),
(4, '0', '0', '1', 'Anh Đang Ở Đâu Đấy Anh', 'http://localhost:8080/musicapp/img/baihat/anhdangodaudayanh.jpg', 'Hương Giang', 'http://localhost:8080/musicapp/music/Anh-Dang-O-Dau-Day-Anh-Huong-Giang.mp3', 0),
(5, '0', '0', '0', 'Việt Nam Việt Nam', 'http://localhost:8080/musicapp/img/baihat/vietnamvietnam.jpg', 'MTV , Nguyễn Dân', 'http://localhost:8080/musicapp/music/vietnamvietnam.mp3', 0),
(6, '0', '0', '0', 'Khi Em Già Đi', 'http://localhost:8080/musicapp/img/baihat/khiemgiadi.jpg', 'Phan Duy Anh , KayDee , Phuc.Pin', 'http://localhost:8080/musicapp/music/khi-em-gia-di.mp3', 0);

-- --------------------------------------------------------

--
-- Table structure for table `chude`
--

CREATE TABLE `chude` (
  `idChuDe` int(11) NOT NULL,
  `TenChuDe` varchar(255) NOT NULL,
  `HinhChuDe` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `chude`
--

INSERT INTO `chude` (`idChuDe`, `TenChuDe`, `HinhChuDe`) VALUES
(1, 'ACOUSTIC', 'http://localhost:8080/musicapp/img/chude/acoustic.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `playlist`
--

CREATE TABLE `playlist` (
  `idPlayList` int(11) NOT NULL,
  `Ten` varchar(255) NOT NULL,
  `HinhNen` varchar(255) NOT NULL,
  `HinhIcon` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `playlist`
--

INSERT INTO `playlist` (`idPlayList`, `Ten`, `HinhNen`, `HinhIcon`) VALUES
(1, 'TOP 100 NHẠC NHẠC TRẺ', '', 'http://localhost:8080/musicapp/img/playlist/top100popicon.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `quangcao`
--

CREATE TABLE `quangcao` (
  `id` int(11) NOT NULL,
  `HinhAnh` varchar(255) NOT NULL,
  `NoiDung` varchar(255) NOT NULL,
  `idBaiHat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `quangcao`
--

INSERT INTO `quangcao` (`id`, `HinhAnh`, `NoiDung`, `idBaiHat`) VALUES
(1, 'http://localhost:8080/musicapp/img/quangcao/vnvn.jpg', 'Việt Nam Việt Nam - MTV, Nguyễn Dân', 5),
(2, 'http://localhost:8080/musicapp/img/quangcao/khiemgiadi.jpg', 'Khi Em Già Đi - Phan Duy Anh , KayDee , Phuc.Pin', 6);

-- --------------------------------------------------------

--
-- Table structure for table `theloai`
--

CREATE TABLE `theloai` (
  `idTheLoai` int(11) NOT NULL,
  `idChuDe` varchar(255) NOT NULL,
  `TenTheLoai` varchar(255) NOT NULL,
  `HinhTheLoai` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `theloai`
--

INSERT INTO `theloai` (`idTheLoai`, `idChuDe`, `TenTheLoai`, `HinhTheLoai`) VALUES
(1, '1', 'Acoustic EDM', 'http://localhost:8080/musicapp/img/theloai/acousticEDM.jpg'),
(2, '1', 'Acoustic Pop', 'http://localhost:8080/musicapp/img/theloai/acousticpop.jpg'),
(3, '1', 'K-Pop Acoustics', 'http://localhost:8080/musicapp/img/theloai/acoustick-pop.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `album`
--
ALTER TABLE `album`
  ADD PRIMARY KEY (`idAlbum`);

--
-- Indexes for table `baihat`
--
ALTER TABLE `baihat`
  ADD PRIMARY KEY (`idBaiHat`);

--
-- Indexes for table `chude`
--
ALTER TABLE `chude`
  ADD PRIMARY KEY (`idChuDe`);

--
-- Indexes for table `playlist`
--
ALTER TABLE `playlist`
  ADD PRIMARY KEY (`idPlayList`);

--
-- Indexes for table `quangcao`
--
ALTER TABLE `quangcao`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `theloai`
--
ALTER TABLE `theloai`
  ADD PRIMARY KEY (`idTheLoai`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `album`
--
ALTER TABLE `album`
  MODIFY `idAlbum` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `baihat`
--
ALTER TABLE `baihat`
  MODIFY `idBaiHat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `chude`
--
ALTER TABLE `chude`
  MODIFY `idChuDe` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `playlist`
--
ALTER TABLE `playlist`
  MODIFY `idPlayList` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `quangcao`
--
ALTER TABLE `quangcao`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `theloai`
--
ALTER TABLE `theloai`
  MODIFY `idTheLoai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
