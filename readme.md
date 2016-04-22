Spring Phonebook
================

# SQL Scripts

    DROP DATABASE IF EXISTS `spring_phonebook`;

    CREATE DATABASE `spring_phonebook` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

    CREATE TABLE `users` (
        `id` bigint(20) NOT NULL,
        `username` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
        `password` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
        `full_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
        PRIMARY KEY (`id`),
        UNIQUE KEY `username_UNIQUE` (`username`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

    CREATE TABLE `phonebook_entry` (
       `id` bigint(20) NOT NULL,
       `user_id` bigint(20) NOT NULL,
       `first_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
       `middle_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
       `last_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
       `mobile_phone` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
       `home_phone` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
       `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
       `email` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
       PRIMARY KEY (`id`),
       KEY `fk_phonebook_entry_1_idx` (`user_id`),
       CONSTRAINT `fk_phonebook_entry_1` FOREIGN KEY (`user_id`) 
       REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;