-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-06-2021 a las 04:06:33
-- Versión del servidor: 10.4.19-MariaDB
-- Versión de PHP: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dbproyecto`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `departamento`
--

CREATE TABLE `departamento` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `departamento`
--

INSERT INTO `departamento` (`id`, `nombre`) VALUES
(1, 'Finanzas'),
(2, 'Recursos Humanos'),
(3, 'Almacen'),
(4, 'Calidad'),
(5, 'Diseño'),
(7, 'Sistemas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallepedido`
--

CREATE TABLE `detallepedido` (
  `id` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `id_pedido` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `id_estatus` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `detallepedido`
--

INSERT INTO `detallepedido` (`id`, `cantidad`, `id_pedido`, `id_producto`, `id_estatus`) VALUES
(609, 3, 45, 22, 3),
(610, 5, 45, 8, 3),
(611, 5, 46, 42, 3),
(612, 10, 46, 42, 3),
(613, 5, 46, 43, 3),
(614, 7, 46, 43, 3),
(615, 5, 46, 8, 3),
(616, 10, 47, 27, 3),
(617, 7, 47, 27, 3),
(618, 10, 47, 28, 3),
(619, 10, 48, 26, 3),
(620, 10, 48, 26, 3),
(621, 10, 48, 28, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entrada`
--

CREATE TABLE `entrada` (
  `id` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `id_producto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `entrada`
--

INSERT INTO `entrada` (`id`, `cantidad`, `fecha`, `id_producto`) VALUES
(26, 10, '2021-05-31', 42),
(27, 10, '2021-05-31', 43),
(28, 5, '2021-05-31', 42),
(29, 2, '2021-05-31', 43),
(30, 10, '2021-05-31', 27),
(31, 10, '2021-05-31', 28),
(32, 7, '2021-05-31', 16),
(33, 7, '2021-05-31', 27),
(34, 7, '2021-05-31', 27),
(35, 10, '2021-05-31', 42),
(36, 15, '2021-05-31', 26);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estatus`
--

CREATE TABLE `estatus` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `estatus`
--

INSERT INTO `estatus` (`id`, `nombre`) VALUES
(3, 'entregado'),
(4, 'pendiente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `id` int(11) NOT NULL,
  `horaCreacion` datetime NOT NULL,
  `id_usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`id`, `horaCreacion`, `id_usuario`) VALUES
(45, '2021-05-31 18:46:53', 3),
(46, '2021-05-31 18:51:25', 18),
(47, '2021-05-31 19:03:47', 6),
(48, '2021-05-31 19:58:22', 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id` int(11) NOT NULL,
  `clave` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `precio` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id`, `clave`, `nombre`, `descripcion`, `precio`) VALUES
(8, 1000, 'Paquete de Hojas Blancas', 'Paquete con 100 hojas blancas', 79.99),
(9, 1001, 'Etiqueta Blanca 10 x 10', 'Edicion 3000', 19.99),
(11, 1002, 'Etiqueta Verde 10 x 5', 'Edicion 500', 9.99),
(12, 1003, 'Etiqueta Verde 10 x 10', 'Edicion 3000', 19.99),
(13, 1004, 'Etiqueta Narajana 10 x 5', 'Edicion 500', 9.99),
(14, 1005, 'Etiqueta Narajana 10 x 10', 'Edicion 3000', 19.99),
(15, 1006, 'Etiqueta Verde Opaco 10 x 5', 'Edicion 500', 9.99),
(16, 1007, 'Etiqueta Amarilla 10 x 5', 'Edicion 500', 9.99),
(17, 1008, 'Etiqueta Azul Claro 10 x 5', 'Edicion 500', 9.99),
(18, 1009, 'Etiqueta Rosa 10 x 5', 'Edicion 500', 9.99),
(19, 1010, 'Etiqueta Roja 10 x 5', 'Edicion 500', 9.99),
(20, 1011, 'Toner M402', '(CF226A)', 2599.99),
(22, 1012, 'Toner M607', '(CF237A)', 4199.99),
(23, 1013, 'Toner M4072', '(MLD-D203L)', 1950),
(24, 1014, 'Toner MEX ', '(217Ax)', 1999.99),
(25, 1015, 'Toner M5360RX', '(MLT-D358S)', 2025),
(26, 1016, 'Tinta Negro ligth', '150ml para plotter (T8247)', 2899.99),
(27, 1017, 'Tinta Amarillo', '150ml para plotter (T8244)', 2899.99),
(28, 1018, 'Tinta Naranja', '150ml para plotter (T824A)', 2899.99),
(30, 1019, 'Tinta Cyan ligth', '150ml para plotter (T8245)', 2899.99),
(31, 1020, 'Tinta Rosa', '150ml para plotter (T8246)', 2899.99),
(32, 1021, 'Tinta Negro Foto', '150ml para plotter (T8241)', 2899.99),
(33, 1022, 'Ribbon 110 x 300', '110mm x 300m DE RESINA-CERA AZUL INSIDE INK', 999.99),
(34, 1023, 'Ribbon 110 x 75', '110mm x 75m DE RESINA-CERA AZUL INSIDE INK', 320),
(35, 1024, 'Rollo de tela azul', '2m x 25m', 599.99),
(36, 1025, 'Rollo de tela rojo', '2m x 25m', 599.99),
(37, 1026, 'Rollo de tela verde', '2m x 25m', 599.99),
(38, 1027, 'Rollo de tela amarillo', '2m x 25m', 599.99),
(39, 1028, 'Rollo de tela naranja', '2m x 25m', 599.99),
(40, 1029, 'Rollo de tela negro', '2m x 25m', 599.99),
(41, 1030, 'Rollo de tela blanco', '2m x 25m', 599.99),
(42, 1031, 'HDD', 'disco duro de un terabyte', 999.99),
(43, 1032, 'SSD', 'disco de estado solido 500GB', 2499.99);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto_departamento`
--

CREATE TABLE `producto_departamento` (
  `id` int(11) NOT NULL,
  `id_departamento` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `producto_departamento`
--

INSERT INTO `producto_departamento` (`id`, `id_departamento`, `id_producto`) VALUES
(23, 1, 8),
(24, 1, 20),
(25, 1, 22),
(26, 2, 8),
(27, 2, 23),
(28, 3, 9),
(30, 3, 12),
(31, 3, 13),
(32, 3, 14),
(33, 3, 15),
(34, 3, 16),
(35, 3, 17),
(36, 3, 18),
(37, 3, 19),
(38, 4, 8),
(39, 4, 24),
(40, 4, 25),
(41, 5, 8),
(42, 5, 20),
(43, 3, 33),
(44, 3, 34),
(45, 5, 26),
(46, 5, 27),
(47, 5, 28),
(48, 5, 30),
(49, 5, 31),
(50, 5, 32),
(51, 5, 35),
(52, 5, 36),
(53, 5, 37),
(54, 5, 38),
(55, 5, 39),
(56, 5, 40),
(57, 5, 41),
(58, 7, 8),
(59, 7, 22),
(60, 7, 23),
(61, 7, 42),
(62, 7, 43);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `stock`
--

CREATE TABLE `stock` (
  `id` int(11) NOT NULL,
  `cantidadActual` int(11) NOT NULL,
  `cantidadMinima` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `stock`
--

INSERT INTO `stock` (`id`, `cantidadActual`, `cantidadMinima`, `id_producto`) VALUES
(6, 40, 20, 8),
(7, 100, 30, 9),
(9, 100, 30, 11),
(10, 100, 30, 12),
(11, 100, 30, 13),
(12, 100, 30, 14),
(13, 100, 30, 15),
(14, 107, 30, 16),
(15, 100, 30, 17),
(16, 100, 30, 18),
(17, 100, 30, 19),
(18, 15, 5, 20),
(19, 12, 5, 22),
(20, 15, 5, 23),
(21, 15, 5, 24),
(22, 15, 5, 25),
(23, 5, 3, 26),
(24, 3, 3, 27),
(26, 0, 3, 28),
(27, 10, 3, 30),
(28, 10, 3, 31),
(29, 10, 3, 32),
(30, 20, 5, 33),
(31, 20, 5, 34),
(32, 50, 20, 35),
(33, 50, 20, 36),
(34, 50, 20, 37),
(35, 50, 20, 38),
(36, 50, 20, 39),
(37, 50, 20, 40),
(38, 50, 20, 41),
(39, 15, 2, 42),
(40, 5, 2, 43);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `nombreUsuario` varchar(45) DEFAULT NULL,
  `contrasena` varchar(45) DEFAULT NULL,
  `privilegio` int(11) DEFAULT NULL,
  `id_departamento` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombreUsuario`, `contrasena`, `privilegio`, `id_departamento`) VALUES
(3, 'Fi_01', 'root', 1, 1),
(4, 'Al_01', 'root', 1, 3),
(6, 'Di_01', 'root', 1, 5),
(7, 'Rh_01', 'root', 1, 2),
(14, 'admin', 'admin', 2, 3),
(18, 'Si_01', 'root', 1, 7),
(19, 'Ca_01', 'root', 1, 4);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `departamento`
--
ALTER TABLE `departamento`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `detallepedido`
--
ALTER TABLE `detallepedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_pedido` (`id_pedido`),
  ADD KEY `id_producto` (`id_producto`),
  ADD KEY `id_estatus` (`id_estatus`);

--
-- Indices de la tabla `entrada`
--
ALTER TABLE `entrada`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_producto` (`id_producto`);

--
-- Indices de la tabla `estatus`
--
ALTER TABLE `estatus`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `producto_departamento`
--
ALTER TABLE `producto_departamento`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_departamento` (`id_departamento`),
  ADD KEY `id_producto` (`id_producto`);

--
-- Indices de la tabla `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_producto` (`id_producto`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_departamento` (`id_departamento`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `departamento`
--
ALTER TABLE `departamento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `detallepedido`
--
ALTER TABLE `detallepedido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=622;

--
-- AUTO_INCREMENT de la tabla `entrada`
--
ALTER TABLE `entrada`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT de la tabla `estatus`
--
ALTER TABLE `estatus`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT de la tabla `producto_departamento`
--
ALTER TABLE `producto_departamento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- AUTO_INCREMENT de la tabla `stock`
--
ALTER TABLE `stock`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detallepedido`
--
ALTER TABLE `detallepedido`
  ADD CONSTRAINT `detallepedido_ibfk_1` FOREIGN KEY (`id_pedido`) REFERENCES `pedido` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `detallepedido_ibfk_2` FOREIGN KEY (`id_estatus`) REFERENCES `estatus` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `detallepedido_ibfk_3` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `entrada`
--
ALTER TABLE `entrada`
  ADD CONSTRAINT `entrada_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `producto_departamento`
--
ALTER TABLE `producto_departamento`
  ADD CONSTRAINT `producto_departamento_ibfk_1` FOREIGN KEY (`id_departamento`) REFERENCES `departamento` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `producto_departamento_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `stock`
--
ALTER TABLE `stock`
  ADD CONSTRAINT `stock_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`id_departamento`) REFERENCES `departamento` (`id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
