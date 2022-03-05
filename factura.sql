-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 05-03-2022 a las 14:56:17
-- Versión del servidor: 5.7.33
-- Versión de PHP: 7.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `factura`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id` bigint(20) NOT NULL,
  `email` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id`, `email`, `apellido`, `nombre`) VALUES
(3, 'juanjoalanccay@gmail.com', 'aaa', 'osbaldo'),
(8, 'mendezeduardaeva@gmail.com', 'aaa', 'micaela'),
(11, 'qq@gmail.com', 'alancay', 'gonza');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle`
--

CREATE TABLE `detalle` (
  `id` bigint(20) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `factura_id` bigint(20) NOT NULL,
  `producto_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `detalle`
--

INSERT INTO `detalle` (`id`, `cantidad`, `factura_id`, `producto_id`) VALUES
(1, 3, 3, 1),
(3, 2, 8, 5),
(4, 3, 8, 9),
(7, 3, 13, 12),
(23, 1, 11, 29),
(28, 2, 11, 34),
(29, 4, 11, 35);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `id` bigint(20) NOT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `fecha` date NOT NULL,
  `folio` bigint(20) NOT NULL,
  `observacion` varchar(45) DEFAULT NULL,
  `cliente_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`id`, `descripcion`, `fecha`, `folio`, `observacion`, `cliente_id`) VALUES
(3, 'fasf', '2022-03-04', 123, 'obs', 3),
(8, 'a', '2022-03-16', 123, 'a', 8),
(11, 'g', '2022-03-24', 123, 'g', 11),
(13, 'asfasf', '2022-03-15', 123, 'obs', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `precio` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id`, `nombre`, `precio`) VALUES
(1, 'zapatilla', 200),
(4, 'zapatilla', 200),
(5, 'silla', 499),
(9, 'zapatilla', 200),
(10, 'parlante', 200),
(11, 'zapatilla', 200),
(12, 'zapatilla', 200),
(13, 'zapatilla', 200),
(14, 'manzana', 400),
(15, 'lapiz', 300),
(16, 'mortadela', 222),
(18, 'MANZANA', 200),
(19, 'mortadela', 200),
(20, 'zapatilla', 200),
(21, 'parlante', 200),
(22, 'sabana', 233),
(23, 'zapatilla', 200),
(24, 'MANZANA', 200),
(25, 'zapatilla', 200),
(26, 'papa', 200),
(27, 'MANZANA', 200),
(28, 'MANZANA', 200),
(29, 'ropero', 200),
(30, 'MANZANA', 200),
(31, 'zapatilla', 200),
(32, 'MANZANA', 200),
(33, 'pera', 200),
(34, 'caramelo', 20),
(35, 'lapiz', 40);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `detalle`
--
ALTER TABLE `detalle`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKleiy4pcyo8dmses60x3idbjsx` (`factura_id`),
  ADD KEY `FKsgl7nr9hbueh77knqra50w4pt` (`producto_id`);

--
-- Indices de la tabla `factura`
--
ALTER TABLE `factura`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK2602efsrpmevi8yxg464stfn5` (`cliente_id`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `detalle`
--
ALTER TABLE `detalle`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT de la tabla `factura`
--
ALTER TABLE `factura`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalle`
--
ALTER TABLE `detalle`
  ADD CONSTRAINT `FKleiy4pcyo8dmses60x3idbjsx` FOREIGN KEY (`factura_id`) REFERENCES `factura` (`id`),
  ADD CONSTRAINT `FKsgl7nr9hbueh77knqra50w4pt` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`);

--
-- Filtros para la tabla `factura`
--
ALTER TABLE `factura`
  ADD CONSTRAINT `FK2602efsrpmevi8yxg464stfn5` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
