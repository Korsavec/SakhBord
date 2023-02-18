# SakhBord

**Это Сахалинская доска объявлений.**

1. Установить базу данных **sakhbord.sql**


2. Установить если их нет **роли**:


INSERT INTO `role_user` (`id`, `name`) VALUES ('1', 'ROLE_USER');

INSERT INTO `role_admin` (`id`, `name`) VALUES ('1', 'ROLE_ADMIN');

INSERT INTO `model_user` (`id`, `account_non_locked`, `confirmation_email`, `date_created_user`, `email`, `enabled`, `ip_address_registration`, `password`, `token`) VALUES ('1', b'1', b'1', '2023-02-12 21:35:31.000000', 'www@www.www', b'1', '0:0:0:0:0:0:0:1', '$2a$10$yHbcglTrnhUNRBV34.1zlesMmauQs08eiiNh8VWnvur3Lxomj8Doy', NULL);

INSERT INTO `model_admin` (`id`, `account_non_locked`, `email`, `enabled`, `password`, `token`) VALUES ('1', b'1', 'www@www.www', b'1', '$2a$12$oUcljGf89hDLSJhfhHl3PemCH3bTnpM6jBZXewv83i9F3gZkMB6EK', NULL);

INSERT INTO `join_user_and_role_user` (`user_id`, `role_user_id`) VALUES ('1', '1');

INSERT INTO `join_admin_and_role_admin` (`admin_id`, `role_admin_id`) VALUES ('1', '1');

INSERT INTO `model_cities` (`id`, `name`) VALUES ('2', 'Александровск-Сах.'), ('3', 'Анива'), ('4', 'Быков'), ('5', 'Вахрушев'), ('6', 'Горнозаводск'), ('7', 'Долинск'), ('8', 'Ильинский'), ('9', 'Корсаков'), ('10', 'Красногорск'), ('11', 'Курильск'), ('12', 'Макаров'), ('13', 'Невельск'), ('14', 'Ноглики'), ('15', 'Онор'), ('16', 'Оха'), ('17', 'Поронайск'), ('18', 'Северо-Курильск'), ('19', 'Смирных'), ('20', 'Томари'), ('21', 'Тымовское'), ('22', 'Углегорск'), ('23', 'Холмск'), ('24', 'Чехов'), ('25', 'Шахтерск'), ('26', 'Южно-Курильск'), ('27', 'Южно-Сахалинск');

INSERT INTO `model_category` (`id`, `name`) VALUES ('1', 'Недвижимость'), ('2', 'Компьютеры'), ('3', 'Мобильная связь'), ('4', 'Животные'), ('5', 'Прочее'), ('6', 'Услуги'), ('7', 'Работа'), ('8', 'Розыск'), ('9', 'Знакомства'), ('10', 'Разное'), ('11','Авто-Мото');

INSERT INTO `model_type_category` (`id`, `name`) VALUES ('1', 'Продам'), ('2', 'Куплю'), ('3', 'Сдам'), ('4', 'Сниму'), ('5', 'Меняю'), ('6', 'Межгород'), ('7', 'Ищу'), ('8', 'Предлагаю'), ('9', 'Утеряно'), ('10', 'Найдено'), ('11', 'Женщины'), ('12', 'Мужчины');

