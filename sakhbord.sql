-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Хост: localhost
-- Время создания: Фев 20 2023 г., 10:50
-- Версия сервера: 8.0.29
-- Версия PHP: 8.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `sakhbord`
--

-- --------------------------------------------------------

--
-- Структура таблицы `join_admin_and_role_admin`
--

CREATE TABLE `join_admin_and_role_admin` (
  `admin_id` bigint NOT NULL,
  `role_admin_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_ru_0900_ai_ci;

--
-- Дамп данных таблицы `join_admin_and_role_admin`
--

INSERT INTO `join_admin_and_role_admin` (`admin_id`, `role_admin_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `join_user_and_role_user`
--

CREATE TABLE `join_user_and_role_user` (
  `user_id` bigint NOT NULL,
  `role_user_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_ru_0900_ai_ci;

--
-- Дамп данных таблицы `join_user_and_role_user`
--

INSERT INTO `join_user_and_role_user` (`user_id`, `role_user_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `model_admin`
--

CREATE TABLE `model_admin` (
  `id` bigint NOT NULL,
  `account_non_locked` bit(1) NOT NULL,
  `email` varchar(58) COLLATE utf8mb4_ru_0900_ai_ci NOT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(65) COLLATE utf8mb4_ru_0900_ai_ci NOT NULL,
  `token` varchar(45) COLLATE utf8mb4_ru_0900_ai_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_ru_0900_ai_ci;

--
-- Дамп данных таблицы `model_admin`
--

INSERT INTO `model_admin` (`id`, `account_non_locked`, `email`, `enabled`, `password`, `token`) VALUES
(1, b'1', 'www@www.www', b'1', '$2a$12$oUcljGf89hDLSJhfhHl3PemCH3bTnpM6jBZXewv83i9F3gZkMB6EK', NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `model_announcement`
--

CREATE TABLE `model_announcement` (
  `id` bigint NOT NULL,
  `date_created_announcement` datetime(6) NOT NULL,
  `email` varchar(58) COLLATE utf8mb4_ru_0900_ai_ci DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `ip_address_registration` varchar(39) COLLATE utf8mb4_ru_0900_ai_ci NOT NULL,
  `message` varchar(335) COLLATE utf8mb4_ru_0900_ai_ci NOT NULL,
  `phone` bigint DEFAULT NULL,
  `telegram` varchar(58) COLLATE utf8mb4_ru_0900_ai_ci DEFAULT NULL,
  `category_id` bigint NOT NULL,
  `city_id` bigint NOT NULL,
  `type_category_id` bigint DEFAULT NULL,
  `user_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_ru_0900_ai_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `model_category`
--

CREATE TABLE `model_category` (
  `id` bigint NOT NULL,
  `name` varchar(30) COLLATE utf8mb4_ru_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_ru_0900_ai_ci;

--
-- Дамп данных таблицы `model_category`
--

INSERT INTO `model_category` (`id`, `name`) VALUES
(11, 'Авто-Мото'),
(4, 'Животные'),
(9, 'Знакомства'),
(2, 'Компьютеры'),
(3, 'Мобильная связь'),
(1, 'Недвижимость'),
(5, 'Прочее'),
(7, 'Работа'),
(10, 'Разное'),
(8, 'Розыск'),
(6, 'Услуги');

-- --------------------------------------------------------

--
-- Структура таблицы `model_cities`
--

CREATE TABLE `model_cities` (
  `id` bigint NOT NULL,
  `name` varchar(30) COLLATE utf8mb4_ru_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_ru_0900_ai_ci;

--
-- Дамп данных таблицы `model_cities`
--

INSERT INTO `model_cities` (`id`, `name`) VALUES
(2, 'Александровск-Сах.'),
(3, 'Анива'),
(4, 'Быков'),
(5, 'Вахрушев'),
(6, 'Горнозаводск'),
(7, 'Долинск'),
(8, 'Ильинский'),
(9, 'Корсаков'),
(10, 'Красногорск'),
(11, 'Курильск'),
(12, 'Макаров'),
(13, 'Невельск'),
(14, 'Ноглики'),
(15, 'Онор'),
(16, 'Оха'),
(17, 'Поронайск'),
(18, 'Северо-Курильск'),
(19, 'Смирных'),
(20, 'Томари'),
(21, 'Тымовское'),
(22, 'Углегорск'),
(23, 'Холмск'),
(24, 'Чехов'),
(25, 'Шахтерск'),
(26, 'Южно-Курильск'),
(27, 'Южно-Сахалинск');

-- --------------------------------------------------------

--
-- Структура таблицы `model_setting`
--

CREATE TABLE `model_setting` (
  `id` bigint NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_ru_0900_ai_ci DEFAULT NULL,
  `value` varchar(50) COLLATE utf8mb4_ru_0900_ai_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_ru_0900_ai_ci;

--
-- Дамп данных таблицы `model_setting`
--

INSERT INTO `model_setting` (`id`, `name`, `value`) VALUES
(1, 'Три объявления в день', '3');

-- --------------------------------------------------------

--
-- Структура таблицы `model_type_category`
--

CREATE TABLE `model_type_category` (
  `id` bigint NOT NULL,
  `name` varchar(30) COLLATE utf8mb4_ru_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_ru_0900_ai_ci;

--
-- Дамп данных таблицы `model_type_category`
--

INSERT INTO `model_type_category` (`id`, `name`) VALUES
(11, 'Женщины'),
(7, 'Ищу'),
(2, 'Куплю'),
(6, 'Межгород'),
(5, 'Меняю'),
(12, 'Мужчины'),
(10, 'Найдено'),
(8, 'Предлагаю'),
(1, 'Продам'),
(3, 'Сдам'),
(4, 'Сниму'),
(9, 'Утеряно');

-- --------------------------------------------------------

--
-- Структура таблицы `model_user`
--

CREATE TABLE `model_user` (
  `id` bigint NOT NULL,
  `account_non_locked` bit(1) NOT NULL,
  `confirmation_email` bit(1) NOT NULL,
  `date_created_user` datetime(6) NOT NULL,
  `email` varchar(58) COLLATE utf8mb4_ru_0900_ai_ci NOT NULL,
  `enabled` bit(1) NOT NULL,
  `ip_address_registration` varchar(39) COLLATE utf8mb4_ru_0900_ai_ci NOT NULL,
  `password` varchar(65) COLLATE utf8mb4_ru_0900_ai_ci NOT NULL,
  `token` varchar(45) COLLATE utf8mb4_ru_0900_ai_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_ru_0900_ai_ci;

--
-- Дамп данных таблицы `model_user`
--

INSERT INTO `model_user` (`id`, `account_non_locked`, `confirmation_email`, `date_created_user`, `email`, `enabled`, `ip_address_registration`, `password`, `token`) VALUES
(1, b'1', b'1', '2023-02-12 21:35:31.000000', 'www@www.www', b'1', '0:0:0:0:0:0:0:1', '$2a$10$yHbcglTrnhUNRBV34.1zlesMmauQs08eiiNh8VWnvur3Lxomj8Doy', NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `not_activated_user`
--

CREATE TABLE `not_activated_user` (
  `id` bigint NOT NULL,
  `active` bit(1) NOT NULL,
  `date_deletion_user` datetime(6) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_ru_0900_ai_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `role_admin`
--

CREATE TABLE `role_admin` (
  `id` bigint NOT NULL,
  `name` varchar(45) COLLATE utf8mb4_ru_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_ru_0900_ai_ci;

--
-- Дамп данных таблицы `role_admin`
--

INSERT INTO `role_admin` (`id`, `name`) VALUES
(1, 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Структура таблицы `role_user`
--

CREATE TABLE `role_user` (
  `id` bigint NOT NULL,
  `name` varchar(45) COLLATE utf8mb4_ru_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_ru_0900_ai_ci;

--
-- Дамп данных таблицы `role_user`
--

INSERT INTO `role_user` (`id`, `name`) VALUES
(1, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Структура таблицы `seq_a_admin`
--

CREATE TABLE `seq_a_admin` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_ru_0900_ai_ci;

--
-- Дамп данных таблицы `seq_a_admin`
--

INSERT INTO `seq_a_admin` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Структура таблицы `seq_a_announcement`
--

CREATE TABLE `seq_a_announcement` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_ru_0900_ai_ci;

--
-- Дамп данных таблицы `seq_a_announcement`
--

INSERT INTO `seq_a_announcement` (`next_val`) VALUES
(263);

-- --------------------------------------------------------

--
-- Структура таблицы `seq_a_category`
--

CREATE TABLE `seq_a_category` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_ru_0900_ai_ci;

--
-- Дамп данных таблицы `seq_a_category`
--

INSERT INTO `seq_a_category` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Структура таблицы `seq_a_citys`
--

CREATE TABLE `seq_a_citys` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_ru_0900_ai_ci;

--
-- Дамп данных таблицы `seq_a_citys`
--

INSERT INTO `seq_a_citys` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Структура таблицы `seq_a_type_category`
--

CREATE TABLE `seq_a_type_category` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_ru_0900_ai_ci;

--
-- Дамп данных таблицы `seq_a_type_category`
--

INSERT INTO `seq_a_type_category` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Структура таблицы `seq_a_user`
--

CREATE TABLE `seq_a_user` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_ru_0900_ai_ci;

--
-- Дамп данных таблицы `seq_a_user`
--

INSERT INTO `seq_a_user` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Структура таблицы `seq_not_activated_user`
--

CREATE TABLE `seq_not_activated_user` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_ru_0900_ai_ci;

--
-- Дамп данных таблицы `seq_not_activated_user`
--

INSERT INTO `seq_not_activated_user` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Структура таблицы `seq_role_admin`
--

CREATE TABLE `seq_role_admin` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_ru_0900_ai_ci;

--
-- Дамп данных таблицы `seq_role_admin`
--

INSERT INTO `seq_role_admin` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Структура таблицы `seq_role_user`
--

CREATE TABLE `seq_role_user` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_ru_0900_ai_ci;

--
-- Дамп данных таблицы `seq_role_user`
--

INSERT INTO `seq_role_user` (`next_val`) VALUES
(1);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `join_admin_and_role_admin`
--
ALTER TABLE `join_admin_and_role_admin`
  ADD PRIMARY KEY (`admin_id`,`role_admin_id`),
  ADD KEY `FK1bxnnulbr3krs12dx2c1xb30h` (`role_admin_id`);

--
-- Индексы таблицы `join_user_and_role_user`
--
ALTER TABLE `join_user_and_role_user`
  ADD PRIMARY KEY (`user_id`,`role_user_id`),
  ADD KEY `FKpvgpsvpvdxx77n1pkscy7pqdt` (`role_user_id`);

--
-- Индексы таблицы `model_admin`
--
ALTER TABLE `model_admin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_9hn5ivkklwgix4bh00pcdgk4b` (`email`),
  ADD UNIQUE KEY `UK_6w9vauy50gqirr4pvee69iw29` (`email`),
  ADD UNIQUE KEY `UK_9kvi70khfdn1bw4hqa0eot90i` (`token`);

--
-- Индексы таблицы `model_announcement`
--
ALTER TABLE `model_announcement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKi7gt5qrx9txx59qt8qo6guj67` (`category_id`),
  ADD KEY `FKd3po1tieb4tni050jwyw61b77` (`city_id`),
  ADD KEY `FKa6inut3atlwj554sc02sd4kar` (`type_category_id`),
  ADD KEY `FK1qnex4ojvewmhkr2giwwfd8px` (`user_id`);

--
-- Индексы таблицы `model_category`
--
ALTER TABLE `model_category`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_x2fxv5hyenrr2x1t6e3gjh6` (`name`),
  ADD UNIQUE KEY `UK_7c57horn8akkloh3av475om0d` (`name`);

--
-- Индексы таблицы `model_cities`
--
ALTER TABLE `model_cities`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_6x6ljk8e6plxs96g6det5x7eb` (`name`),
  ADD UNIQUE KEY `UK_ju30i82664d71y960ck39cpqv` (`name`);

--
-- Индексы таблицы `model_setting`
--
ALTER TABLE `model_setting`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `model_type_category`
--
ALTER TABLE `model_type_category`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ebv3khnr02xtnejoeh0lw0is7` (`name`);

--
-- Индексы таблицы `model_user`
--
ALTER TABLE `model_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_e7k8c1c255sv3aw6f2xwyx9cn` (`email`),
  ADD UNIQUE KEY `UK_qo5q6vxtex45exjr43ntelelo` (`email`),
  ADD UNIQUE KEY `UK_sxl58pnjo1h40j000o9xjr4o8` (`token`);

--
-- Индексы таблицы `not_activated_user`
--
ALTER TABLE `not_activated_user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKiyrygnkt0t6wdhu307805jwtc` (`user_id`);

--
-- Индексы таблицы `role_admin`
--
ALTER TABLE `role_admin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ly8tds7h2m1bfstn7yb5592gs` (`name`);

--
-- Индексы таблицы `role_user`
--
ALTER TABLE `role_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_jiltx188xdo3iehcnqj8fxfgn` (`name`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `model_setting`
--
ALTER TABLE `model_setting`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `join_admin_and_role_admin`
--
ALTER TABLE `join_admin_and_role_admin`
  ADD CONSTRAINT `FK1bxnnulbr3krs12dx2c1xb30h` FOREIGN KEY (`role_admin_id`) REFERENCES `role_admin` (`id`),
  ADD CONSTRAINT `FKi28fu2eohdyxg81k6gqyw9ot7` FOREIGN KEY (`admin_id`) REFERENCES `model_admin` (`id`);

--
-- Ограничения внешнего ключа таблицы `join_user_and_role_user`
--
ALTER TABLE `join_user_and_role_user`
  ADD CONSTRAINT `FKg13reuml4sbe7in7gs2btaha8` FOREIGN KEY (`user_id`) REFERENCES `model_user` (`id`),
  ADD CONSTRAINT `FKpvgpsvpvdxx77n1pkscy7pqdt` FOREIGN KEY (`role_user_id`) REFERENCES `role_user` (`id`);

--
-- Ограничения внешнего ключа таблицы `model_announcement`
--
ALTER TABLE `model_announcement`
  ADD CONSTRAINT `FK1qnex4ojvewmhkr2giwwfd8px` FOREIGN KEY (`user_id`) REFERENCES `model_user` (`id`),
  ADD CONSTRAINT `FKa6inut3atlwj554sc02sd4kar` FOREIGN KEY (`type_category_id`) REFERENCES `model_type_category` (`id`),
  ADD CONSTRAINT `FKd3po1tieb4tni050jwyw61b77` FOREIGN KEY (`city_id`) REFERENCES `model_cities` (`id`),
  ADD CONSTRAINT `FKi7gt5qrx9txx59qt8qo6guj67` FOREIGN KEY (`category_id`) REFERENCES `model_category` (`id`);

--
-- Ограничения внешнего ключа таблицы `not_activated_user`
--
ALTER TABLE `not_activated_user`
  ADD CONSTRAINT `FKiyrygnkt0t6wdhu307805jwtc` FOREIGN KEY (`user_id`) REFERENCES `model_user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
