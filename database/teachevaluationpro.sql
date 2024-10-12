-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 12-10-2024 a las 10:35:35
-- Versión del servidor: 10.11.8-MariaDB-0ubuntu0.24.04.1
-- Versión de PHP: 8.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `teachevaluationpro`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `classroom`
--

CREATE TABLE `classroom` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `filial` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `classroom`
--

INSERT INTO `classroom` (`id`, `name`, `grade`, `level`, `filial`) VALUES
(6, '2-BS', 25, 4, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `complete_exam`
--

CREATE TABLE `complete_exam` (
  `id` int(11) NOT NULL,
  `exam` int(11) DEFAULT NULL,
  `user` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `evaluation`
--

CREATE TABLE `evaluation` (
  `id` int(11) NOT NULL,
  `score` double NOT NULL,
  `question` varchar(255) NOT NULL,
  `array_text_answers` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL CHECK (json_valid(`array_text_answers`)),
  `message_answer_correct` varchar(255) DEFAULT NULL,
  `correct_answer` varchar(255) DEFAULT NULL,
  `header_text` varchar(255) DEFAULT NULL,
  `exam` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `evaluation_user_result`
--

CREATE TABLE `evaluation_user_result` (
  `id` int(11) NOT NULL,
  `score` int(11) DEFAULT NULL,
  `question` varchar(255) DEFAULT NULL,
  `user_answer` varchar(255) DEFAULT NULL,
  `exam` int(11) DEFAULT NULL,
  `user` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `exam`
--

CREATE TABLE `exam` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `date_message` varchar(255) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `subject` int(11) NOT NULL,
  `teacher` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `status` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `examTemp`
--

CREATE TABLE `examTemp` (
  `id` int(1) NOT NULL,
  `exam` int(1) DEFAULT NULL,
  `user` int(1) DEFAULT NULL,
  `dateinit` int(1) DEFAULT NULL,
  `datelimit` int(1) DEFAULT NULL,
  `status` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `exam_date`
--

CREATE TABLE `exam_date` (
  `id` int(11) NOT NULL,
  `exam` int(11) DEFAULT NULL,
  `init_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `exam_student`
--

CREATE TABLE `exam_student` (
  `id` int(11) NOT NULL,
  `exam` int(11) DEFAULT NULL,
  `student` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `exam_type`
--

CREATE TABLE `exam_type` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `exam_type`
--

INSERT INTO `exam_type` (`id`, `name`, `type`) VALUES
(1, 'Opcion multiple', 1),
(2, 'Abierta', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `failed_exam`
--

CREATE TABLE `failed_exam` (
  `id` int(11) NOT NULL,
  `exam` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `filial`
--

CREATE TABLE `filial` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `school` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `filial`
--

INSERT INTO `filial` (`id`, `name`, `school`) VALUES
(1, 'Reforma', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grade`
--

CREATE TABLE `grade` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `level` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `grade`
--

INSERT INTO `grade` (`id`, `name`, `level`) VALUES
(1, '(Primaria) Primer grado', 1),
(2, '(Primaria) Segundo grado', 1),
(3, '(Primaria) Tercer grado', 1),
(4, '(Primaria) Cuarto grado', 1),
(5, '(Primaria) Quinto grado', 1),
(6, '(Primaria) Sexto grado', 1),
(7, '(Secundaria) Primer grado', 2),
(8, '(Secundaria) Segundo grado', 2),
(9, '(Secundaria) Tercer grado', 2),
(10, '(Preparatoria) Primer semestre', 3),
(11, '(Preparatoria) Segundo semestre', 3),
(12, '(Preparatoria) Tercer semestre', 3),
(13, '(Preparatoria) Cuarto semestre', 3),
(14, '(Preparatoria) Quinto semestre', 3),
(15, '(Preparatoria) Sexto semestre', 3),
(16, '(Universidad) Primer semestre', 4),
(17, '(Universidad) Segundo semestre', 4),
(18, '(Universidad) Tercer semestre', 4),
(19, '(Universidad) Cuarto semestre', 4),
(20, '(Universidad) Quinto semestre', 4),
(21, '(Universidad) Sexto semestre', 4),
(22, '(Universidad) Septimo semestre', 4),
(23, '(Universidad) Octavo semestre', 4),
(24, '(Universidad) Noveno semestre', 4),
(25, '(Universidad) Decimo semestre', 4),
(26, '(Universidad) Decimo primero semestre', 4),
(27, '(Universidad) Decimo segundo semestre', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `init`
--

CREATE TABLE `init` (
  `id` int(1) NOT NULL,
  `registered` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `init`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `level`
--

CREATE TABLE `level` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `level`
--

INSERT INTO `level` (`id`, `name`, `status`) VALUES
(1, 'Primaria', 1),
(2, 'Secundaria', 1),
(3, 'Preparatoria', 1),
(4, 'Universidad', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `school`
--

CREATE TABLE `school` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `school`
--

INSERT INTO `school` (`id`, `name`) VALUES
(1, 'Tecsharp High School');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `subject`
--

CREATE TABLE `subject` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `filial` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `subject`
--

INSERT INTO `subject` (`id`, `name`, `filial`) VALUES
(1, 'Historia', 1),
(2, 'Matemáticas', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `subject_grade`
--

CREATE TABLE `subject_grade` (
  `id` int(11) NOT NULL,
  `subject` int(11) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `lastnameone` varchar(255) DEFAULT NULL,
  `lastnametwo` varchar(255) DEFAULT NULL,
  `filial` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `points` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `level` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `user`
--
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_classroom`
--

CREATE TABLE `user_classroom` (
  `id` int(11) NOT NULL,
  `user` int(11) DEFAULT NULL,
  `classroom` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `user_classroom`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_grade`
--

CREATE TABLE `user_grade` (
  `id` int(11) NOT NULL,
  `student` int(11) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_subject`
--

CREATE TABLE `user_subject` (
  `id` int(11) NOT NULL,
  `user` int(11) DEFAULT NULL,
  `subject` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_type`
--

CREATE TABLE `user_type` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `user_type`
--

INSERT INTO `user_type` (`id`, `name`) VALUES
(1, 'admin'),
(2, 'teacher'),
(3, 'student');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `classroom`
--
ALTER TABLE `classroom`
  ADD PRIMARY KEY (`id`),
  ADD KEY `level` (`level`),
  ADD KEY `filial` (`filial`),
  ADD KEY `grade` (`grade`);

--
-- Indices de la tabla `complete_exam`
--
ALTER TABLE `complete_exam`
  ADD PRIMARY KEY (`id`),
  ADD KEY `exam` (`exam`),
  ADD KEY `user` (`user`);

--
-- Indices de la tabla `evaluation`
--
ALTER TABLE `evaluation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `exam` (`exam`);

--
-- Indices de la tabla `evaluation_user_result`
--
ALTER TABLE `evaluation_user_result`
  ADD PRIMARY KEY (`id`),
  ADD KEY `exam` (`exam`),
  ADD KEY `user` (`user`);

--
-- Indices de la tabla `exam`
--
ALTER TABLE `exam`
  ADD PRIMARY KEY (`id`),
  ADD KEY `teacher` (`teacher`),
  ADD KEY `subject` (`subject`),
  ADD KEY `type` (`type`);

--
-- Indices de la tabla `exam_date`
--
ALTER TABLE `exam_date`
  ADD PRIMARY KEY (`id`),
  ADD KEY `exam` (`exam`);

--
-- Indices de la tabla `exam_student`
--
ALTER TABLE `exam_student`
  ADD PRIMARY KEY (`id`),
  ADD KEY `exam` (`exam`),
  ADD KEY `student` (`student`);

--
-- Indices de la tabla `exam_type`
--
ALTER TABLE `exam_type`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `failed_exam`
--
ALTER TABLE `failed_exam`
  ADD PRIMARY KEY (`id`),
  ADD KEY `exam` (`exam`);

--
-- Indices de la tabla `filial`
--
ALTER TABLE `filial`
  ADD PRIMARY KEY (`id`),
  ADD KEY `school` (`school`);

--
-- Indices de la tabla `grade`
--
ALTER TABLE `grade`
  ADD PRIMARY KEY (`id`),
  ADD KEY `level` (`level`);

--
-- Indices de la tabla `level`
--
ALTER TABLE `level`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `school`
--
ALTER TABLE `school`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`id`),
  ADD KEY `filial` (`filial`);

--
-- Indices de la tabla `subject_grade`
--
ALTER TABLE `subject_grade`
  ADD PRIMARY KEY (`id`),
  ADD KEY `subject` (`subject`),
  ADD KEY `grade` (`grade`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `type` (`type`),
  ADD KEY `filial` (`filial`),
  ADD KEY `level` (`level`);

--
-- Indices de la tabla `user_classroom`
--
ALTER TABLE `user_classroom`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user` (`user`),
  ADD KEY `classroom` (`classroom`);

--
-- Indices de la tabla `user_grade`
--
ALTER TABLE `user_grade`
  ADD PRIMARY KEY (`id`),
  ADD KEY `student` (`student`),
  ADD KEY `grade` (`grade`);

--
-- Indices de la tabla `user_subject`
--
ALTER TABLE `user_subject`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user` (`user`),
  ADD KEY `subject` (`subject`);

--
-- Indices de la tabla `user_type`
--
ALTER TABLE `user_type`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `classroom`
--
ALTER TABLE `classroom`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `complete_exam`
--
ALTER TABLE `complete_exam`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `evaluation`
--
ALTER TABLE `evaluation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `evaluation_user_result`
--
ALTER TABLE `evaluation_user_result`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de la tabla `exam`
--
ALTER TABLE `exam`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `exam_date`
--
ALTER TABLE `exam_date`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `exam_student`
--
ALTER TABLE `exam_student`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `failed_exam`
--
ALTER TABLE `failed_exam`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `filial`
--
ALTER TABLE `filial`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `grade`
--
ALTER TABLE `grade`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT de la tabla `level`
--
ALTER TABLE `level`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `school`
--
ALTER TABLE `school`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `subject`
--
ALTER TABLE `subject`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `subject_grade`
--
ALTER TABLE `subject_grade`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT de la tabla `user_classroom`
--
ALTER TABLE `user_classroom`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=86;

--
-- AUTO_INCREMENT de la tabla `user_grade`
--
ALTER TABLE `user_grade`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `user_subject`
--
ALTER TABLE `user_subject`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `classroom`
--
ALTER TABLE `classroom`
  ADD CONSTRAINT `classroom_ibfk_1` FOREIGN KEY (`level`) REFERENCES `level` (`id`),
  ADD CONSTRAINT `classroom_ibfk_2` FOREIGN KEY (`filial`) REFERENCES `filial` (`id`),
  ADD CONSTRAINT `classroom_ibfk_3` FOREIGN KEY (`grade`) REFERENCES `grade` (`id`);

--
-- Filtros para la tabla `complete_exam`
--
ALTER TABLE `complete_exam`
  ADD CONSTRAINT `complete_exam_ibfk_1` FOREIGN KEY (`exam`) REFERENCES `exam` (`id`),
  ADD CONSTRAINT `complete_exam_ibfk_2` FOREIGN KEY (`user`) REFERENCES `user` (`id`);

--
-- Filtros para la tabla `evaluation`
--
ALTER TABLE `evaluation`
  ADD CONSTRAINT `evaluation_ibfk_1` FOREIGN KEY (`exam`) REFERENCES `exam` (`id`);

--
-- Filtros para la tabla `evaluation_user_result`
--
ALTER TABLE `evaluation_user_result`
  ADD CONSTRAINT `evaluation_user_result_ibfk_1` FOREIGN KEY (`exam`) REFERENCES `exam` (`id`),
  ADD CONSTRAINT `evaluation_user_result_ibfk_2` FOREIGN KEY (`user`) REFERENCES `user` (`id`);

--
-- Filtros para la tabla `exam`
--
ALTER TABLE `exam`
  ADD CONSTRAINT `exam_ibfk_1` FOREIGN KEY (`teacher`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `exam_ibfk_2` FOREIGN KEY (`subject`) REFERENCES `subject` (`id`),
  ADD CONSTRAINT `exam_ibfk_3` FOREIGN KEY (`type`) REFERENCES `exam_type` (`id`);

--
-- Filtros para la tabla `exam_date`
--
ALTER TABLE `exam_date`
  ADD CONSTRAINT `exam_date_ibfk_1` FOREIGN KEY (`exam`) REFERENCES `exam` (`id`);

--
-- Filtros para la tabla `exam_student`
--
ALTER TABLE `exam_student`
  ADD CONSTRAINT `exam_student_ibfk_1` FOREIGN KEY (`exam`) REFERENCES `exam` (`id`),
  ADD CONSTRAINT `exam_student_ibfk_2` FOREIGN KEY (`student`) REFERENCES `user` (`id`);

--
-- Filtros para la tabla `failed_exam`
--
ALTER TABLE `failed_exam`
  ADD CONSTRAINT `failed_exam_ibfk_1` FOREIGN KEY (`exam`) REFERENCES `exam` (`id`);

--
-- Filtros para la tabla `filial`
--
ALTER TABLE `filial`
  ADD CONSTRAINT `filial_ibfk_1` FOREIGN KEY (`school`) REFERENCES `school` (`id`);

--
-- Filtros para la tabla `grade`
--
ALTER TABLE `grade`
  ADD CONSTRAINT `grade_ibfk_1` FOREIGN KEY (`level`) REFERENCES `level` (`id`);

--
-- Filtros para la tabla `subject`
--
ALTER TABLE `subject`
  ADD CONSTRAINT `subject_ibfk_1` FOREIGN KEY (`filial`) REFERENCES `filial` (`id`);

--
-- Filtros para la tabla `subject_grade`
--
ALTER TABLE `subject_grade`
  ADD CONSTRAINT `subject_grade_ibfk_1` FOREIGN KEY (`subject`) REFERENCES `subject` (`id`),
  ADD CONSTRAINT `subject_grade_ibfk_2` FOREIGN KEY (`grade`) REFERENCES `grade` (`id`);

--
-- Filtros para la tabla `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`type`) REFERENCES `user_type` (`id`),
  ADD CONSTRAINT `user_ibfk_2` FOREIGN KEY (`filial`) REFERENCES `filial` (`id`),
  ADD CONSTRAINT `user_ibfk_3` FOREIGN KEY (`level`) REFERENCES `level` (`id`);

--
-- Filtros para la tabla `user_classroom`
--
ALTER TABLE `user_classroom`
  ADD CONSTRAINT `user_classroom_ibfk_1` FOREIGN KEY (`user`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `user_classroom_ibfk_2` FOREIGN KEY (`classroom`) REFERENCES `classroom` (`id`);

--
-- Filtros para la tabla `user_grade`
--
ALTER TABLE `user_grade`
  ADD CONSTRAINT `user_grade_ibfk_1` FOREIGN KEY (`student`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `user_grade_ibfk_2` FOREIGN KEY (`grade`) REFERENCES `grade` (`id`);

--
-- Filtros para la tabla `user_subject`
--
ALTER TABLE `user_subject`
  ADD CONSTRAINT `user_subject_ibfk_1` FOREIGN KEY (`user`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `user_subject_ibfk_2` FOREIGN KEY (`subject`) REFERENCES `subject` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
