-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-12-2018 a las 21:09:01
-- Versión del servidor: 10.1.28-MariaDB
-- Versión de PHP: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ficheros`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresas`
--

CREATE TABLE `empresas` (
  `id_Empresa` int(11) NOT NULL,
  `Nombre` varchar(45) DEFAULT NULL,
  `Tamanno` varchar(45) DEFAULT NULL,
  `Pais` varchar(45) DEFAULT NULL,
  `Capital` int(25) DEFAULT NULL,
  `Director` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `empresas`
--

INSERT INTO `empresas` (`id_Empresa`, `Nombre`, `Tamanno`, `Pais`, `Capital`, `Director`) VALUES
(1, 'Naughty Dog', 'AAA', 'Noruega', 323200, 'Noruego'),
(2, 'GuerrillaGames', 'AAA', 'PaisesBajos', 130000, 'HermenHulst'),
(3, 'StudioMDHR', 'Indie', 'EEUU', 10000, 'JaredMoldenhauer'),
(4, 'fads', 'fdas', 'fasd', 432, 'fasd');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ficheritos`
--

CREATE TABLE `ficheritos` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  `Empresa` int(45) DEFAULT NULL,
  `Creacion` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `ficheritos`
--

INSERT INTO `ficheritos` (`id`, `nombre`, `tipo`, `Empresa`, `Creacion`) VALUES
(1, 'The Last of Us', 'AAA', 1, '2000-10-10'),
(2, 'HorizonZeroDawn', 'Aventura', 2, '2017-02-28'),
(3, 'Uncharted4', 'AAA', 1, '2000-10-10'),
(4, 'CupHead', 'Arcade', 3, '2017-09-29'),
(5, 'fasd', 'AAA', 2, '2000-10-10');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `empresas`
--
ALTER TABLE `empresas`
  ADD PRIMARY KEY (`id_Empresa`);

--
-- Indices de la tabla `ficheritos`
--
ALTER TABLE `ficheritos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `Empresa` (`Empresa`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `empresas`
--
ALTER TABLE `empresas`
  MODIFY `id_Empresa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `ficheritos`
--
ALTER TABLE `ficheritos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `ficheritos`
--
ALTER TABLE `ficheritos`
  ADD CONSTRAINT `ficheritos_ibfk_1` FOREIGN KEY (`Empresa`) REFERENCES `empresas` (`id_Empresa`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
