-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 07, 2021 at 10:36 AM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `database`
--

-- --------------------------------------------------------

--
-- Table structure for table `catalog`
--

CREATE TABLE `catalog` (
  `id` int(11) NOT NULL,
  `CodSt` int(11) NOT NULL,
  `Sesiune` varchar(50) NOT NULL,
  `IP` varchar(11) DEFAULT NULL,
  `BD` varchar(11) DEFAULT NULL,
  `PAW` varchar(11) DEFAULT NULL,
  `PLF` varchar(11) DEFAULT NULL,
  `POO` varchar(11) DEFAULT NULL,
  `AM` varchar(11) DEFAULT NULL,
  `LFT` varchar(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `domeniu`
--

CREATE TABLE `domeniu` (
  `CodD` int(11) NOT NULL,
  `DenD` varchar(50) NOT NULL,
  `CodF` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `facultate`
--

CREATE TABLE `facultate` (
  `CodF` int(11) NOT NULL,
  `DenF` varchar(50) NOT NULL,
  `Locatie` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `inmatriculare`
--

CREATE TABLE `inmatriculare` (
  `CodM` int(11) NOT NULL,
  `An` int(11) NOT NULL,
  `Grupa` varchar(50) NOT NULL,
  `Medie` varchar(50) NOT NULL,
  `Bursa` tinyint(1) DEFAULT NULL,
  `FormaInv` varchar(50) NOT NULL,
  `CodSt` int(11) NOT NULL,
  `CodS` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `inmatriculare`
--

INSERT INTO `inmatriculare` (`CodM`, `An`, `Grupa`, `Medie`, `Bursa`, `FormaInv`, `CodSt`, `CodS`) VALUES
(1, 3, '22c21', '6', 1, 'frec', 1, 1),
(2, 4, '22c31', '7', 1, 'fara', 2, 1),
(3, 2, '22c11', '8', 0, 'fara', 3, 1),
(4, 4, '22c41', '10', 0, 'frec', 4, 1),
(5, 2, '22c21', '10', 1, 'frec', 5, 1);

-- --------------------------------------------------------

--
-- Table structure for table `logs`
--

CREATE TABLE `logs` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `table_name` varchar(255) NOT NULL,
  `table_row` int(11) NOT NULL,
  `command` varchar(255) NOT NULL,
  `created_at` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `specializare`
--

CREATE TABLE `specializare` (
  `CodS` int(11) NOT NULL,
  `DenS` varchar(50) NOT NULL,
  `CodD` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `CodSt` int(11) NOT NULL,
  `Nume` varchar(50) NOT NULL,
  `Prenume` varchar(50) NOT NULL,
  `Cetatenie` varchar(50) NOT NULL,
  `DataN` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`CodSt`, `Nume`, `Prenume`, `Cetatenie`, `DataN`) VALUES
(1, 'Alex', 'Panaite', 'roman', '2021-01-19'),
(2, 'Mihai', 'Nistor', 'roman', '2021-01-13'),
(3, 'Bercea', 'Claudiu', 'roman', '2021-01-29'),
(4, 'Pandela', 'Moraci', 'roman', '2021-01-14'),
(5, 'Tudor', 'Chirila', 'roman', '2021-01-20');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  `role` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `created_at`, `role`) VALUES
(1, 'secretar', 'secretar', '2021-01-06 20:48:59', 'secretar'),
(2, 'student', 'student', '2021-01-06 20:48:02', 'student'),
(3, 'admin', 'admin', '2021-01-06 20:48:20', 'admin'),
(4, 'profesor', 'profesor', '2021-01-06 20:48:38', 'profesor');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `catalog`
--
ALTER TABLE `catalog`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `domeniu`
--
ALTER TABLE `domeniu`
  ADD PRIMARY KEY (`CodD`);

--
-- Indexes for table `facultate`
--
ALTER TABLE `facultate`
  ADD PRIMARY KEY (`CodF`);

--
-- Indexes for table `inmatriculare`
--
ALTER TABLE `inmatriculare`
  ADD PRIMARY KEY (`CodM`);

--
-- Indexes for table `logs`
--
ALTER TABLE `logs`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `specializare`
--
ALTER TABLE `specializare`
  ADD PRIMARY KEY (`CodS`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`CodSt`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `catalog`
--
ALTER TABLE `catalog`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `domeniu`
--
ALTER TABLE `domeniu`
  MODIFY `CodD` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `facultate`
--
ALTER TABLE `facultate`
  MODIFY `CodF` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `inmatriculare`
--
ALTER TABLE `inmatriculare`
  MODIFY `CodM` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100;

--
-- AUTO_INCREMENT for table `logs`
--
ALTER TABLE `logs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `specializare`
--
ALTER TABLE `specializare`
  MODIFY `CodS` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `CodSt` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
