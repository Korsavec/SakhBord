# SakhBord

**Это Сахалинская доска объявлений.**

1. Установить базу данных **sakhbord.sql**


2. Установить если их нет **роли**:


INSERT INTO `role_user` (`id`, `name`) VALUES ('1', 'ROLE_USER');


INSERT INTO `role_admin` (`id`, `name`) VALUES ('1', 'ROLE_SELLER');


INSERT INTO `model_admin` (`id`, `account_non_locked`, `email`, `enabled`, `password`, `token`) VALUES ('1', b'1', 'bbb@bb.bb', b'1', '$2a$12$oUcljGf89hDLSJhfhHl3PemCH3bTnpM6jBZXewv83i9F3gZkMB6EK', NULL);


INSERT INTO `join_admin_and_role_admin` (`admin_id`, `role_admin_id`) VALUES ('1', '1');
