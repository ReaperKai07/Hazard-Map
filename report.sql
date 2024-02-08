-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 08, 2024 at 04:55 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sampledb3`
--

-- --------------------------------------------------------

--
-- Table structure for table `report`
--

CREATE TABLE `report` (
  `id` int(11) NOT NULL,
  `title` varchar(30) NOT NULL,
  `type` varchar(10) NOT NULL,
  `reporter` varchar(50) NOT NULL,
  `lat` decimal(10,4) NOT NULL,
  `lng` decimal(10,4) NOT NULL,
  `hour` varchar(2) NOT NULL,
  `minute` varchar(2) NOT NULL,
  `tod` varchar(2) NOT NULL,
  `day` varchar(2) NOT NULL,
  `month` varchar(2) NOT NULL,
  `year` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `report`
--

INSERT INTO `report` (`id`, `title`, `type`, `reporter`, `lat`, `lng`, `hour`, `minute`, `tod`, `day`, `month`, `year`) VALUES
(1, 'Road Block', 'Accident', 'Khairul Amin', 6.4533, 100.2752, '8', '25', 'PM', '15', '2', '2024'),
(2, 'Heavy Rain', 'Weather', 'Waliudin', 6.4379, 100.2682, '3', '21', 'PM', '29', '1', '2024'),
(3, 'Sinkhole', 'Terrain', 'Amirul', 6.4295, 100.2699, '7', '01', 'AM', '17', '12', '2023'),
(4, 'UFO Sight', 'Accident', 'Muhd Badri', 6.4474, 100.2253, '9', '41', 'PM', '21', '11', '2023'),
(5, 'Landslide', 'Terrain', 'Zulhilmi', 6.5473, 100.2689, '9', '01', 'AM', '27', '1', '2024'),
(6, 'Waterspout', 'Weather', 'Ahmad Aidil', 6.4065, 100.1249, '6', '32', 'PM', '21', '1', '2024'),
(7, 'Road Accident', 'Accident', 'Aiman Razi', 6.4059, 100.1313, '8', '07', 'PM', '21', '1', '2024'),
(8, 'Thunderstorm', 'Weather', 'Danial', 6.4456, 100.2912, '2', '07', 'PM', '25', '1', '2024'),
(9, 'Earthquake', 'Terrain', 'Haziq', 6.4269, 100.2727, '1', '34', 'PM', '3', '1', '2024'),
(10, 'Boar Sight', 'Accident', 'Nazrin Hakimi', 6.4461, 100.2825, '5', '12', 'PM', '29', '1', '2024'),
(11, 'Car Crash', 'accident', 'Sarah', 6.4428, 100.2658, '11', '49', 'AM', '5', '2', '2024');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `report`
--
ALTER TABLE `report`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `report`
--
ALTER TABLE `report`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
