CREATE TABLE users(
    user_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    password VARCHAR(45) NOT NULL
);

CREATE TABLE contacts(
  contact_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  mobile_number VARCHAR(45) NOT NULL,
  user_id INT NOT NULL,
  CONSTRAINT contact_user FOREIGN KEY (user_id) REFERENCES users (user_id)
);
CREATE INDEX contact_user_idx ON contacts (user_id);


#  TODO change

ALTER TABLE `phonebook`.`contacts`
  ADD COLUMN `surname` VARCHAR(45) NOT NULL AFTER `contact_id`,
  ADD COLUMN `patronymic` VARCHAR(45) NOT NULL AFTER `name`,
  ADD COLUMN `home_phone` VARCHAR(45) NULL AFTER `user_id`,
  ADD COLUMN `address` VARCHAR(45) NULL AFTER `home_phone`,
  ADD COLUMN `email` VARCHAR(45) NULL AFTER `address`;




CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1


CREATE TABLE `contacts` (
  `contact_id` int(11) NOT NULL AUTO_INCREMENT,
  `surname` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `patronymic` varchar(45) NOT NULL,
  `mobile_number` varchar(45) NOT NULL,
  `user_id` int(11) NOT NULL,
  `home_phone` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`contact_id`),
  KEY `contact_user_idx` (`user_id`),
  CONSTRAINT `contact_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=latin1