# SakhBord

**Это Сахалинская доска объявлений.**

1. Установить базу данных **sakhbord.sql**


2. Установить если их нет **роли**:


INSERT INTO `model_role_user` (`id`, `name`) VALUES ('1', 'ROLE_USER'), ('2', 'ROLE_ADMIN');


INSERT INTO `model_user` (`id`, `account_non_locked`, `confirmation_email`, `date_created_user`, `email`, `enabled`, `ip_address_registration`, `password`, `token`) VALUES ('1', b'1', b'1', '2023-02-23 17:00:17.000000', 'www@www.www', b'1', '127.0.0.1', '1111', NULL);


INSERT INTO `join_user_and_role_users` (`user_id`, `role_users_id`) VALUES ('1', '1');


INSERT INTO `model_category` (`id`, `name`) VALUES ('1', 'Недвижимость'), ('2', 'Компьютеры'), ('3', 'Мобильная связь'), ('4', 'Животные'), ('5', 'Прочее'), ('6', 'Услуги'), ('7', 'Работа'), ('8', 'Розыск'), ('9', 'Знакомства'), ('10', 'Разное'), ('11','Авто-Мото');


INSERT INTO `model_city` (`id`, `name`) VALUES ('2', 'Александровск-Сах.'), ('3', 'Анива'), ('4', 'Быков'), ('5', 'Вахрушев'), ('6', 'Горнозаводск'), ('7', 'Долинск'), ('8', 'Ильинский'), ('9', 'Корсаков'), ('10', 'Красногорск'), ('11', 'Курильск'), ('12', 'Макаров'), ('13', 'Невельск'), ('14', 'Ноглики'), ('15', 'Онор'), ('16', 'Оха'), ('17', 'Поронайск'), ('18', 'Северо-Курильск'), ('19', 'Смирных'), ('20', 'Томари'), ('21', 'Тымовское'), ('22', 'Углегорск'), ('23', 'Холмск'), ('24', 'Чехов'), ('25', 'Шахтерск'), ('26', 'Южно-Курильск'), ('27', 'Южно-Сахалинск');


INSERT INTO `model_type_category` (`id`, `name`) VALUES ('1', 'Продам'), ('2', 'Куплю'), ('3', 'Сдам'), ('4', 'Сниму'), ('5', 'Меняю'), ('6', 'Межгород'), ('7', 'Ищу'), ('8', 'Предлагаю'), ('9', 'Утеряно'), ('10', 'Найдено'), ('11', 'Женщины'), ('12', 'Мужчины');


INSERT INTO `model_announcement` (`id`, `date_created_announcement`, `email`, `enabled`, `ip_address_registration`, `message`, `phone`, `telegram`, `category_id`, `city_id`, `type_category_id`, `user_id`) VALUES ('1', '2023-02-23 17:50:08.000000', 'www@www.www', b'1', '127.0.0.1', '1 - моё объявление - 1', '9242852535', 'telega', '5', '8', '6', '1');


INSERT INTO `model_announcement` (`id`, `date_created_announcement`, `email`, `enabled`, `ip_address_registration`, `message`, `phone`, `telegram`, `category_id`, `city_id`, `type_category_id`, `user_id`) VALUES ('2', '2023-02-23 17:51:22.000000', 'www@www.www', b'1', '127.0.0.1', '2 - моё объявление - 2', '9242852535', 'telega', '6', '12', NULL, '1');

INSERT INTO `model_rules` (`id`, `header`, `regulations`) VALUES (NULL, 'Правила пользования Интернет ресурсом', 'Вы можете быть заблокированы просто так');

ALTER TABLE `join_user_and_role_users` DROP FOREIGN KEY `FKbg6rcfxrq4r6teiht0kalpgi7`; ALTER TABLE `join_user_and_role_users` ADD CONSTRAINT `FKbg6rcfxrq4r6teiht0kalpgi7` FOREIGN KEY (`user_id`) REFERENCES `model_user`(`id`) ON DELETE CASCADE ON UPDATE RESTRICT;


