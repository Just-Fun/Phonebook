CREATE TABLE users(
    user_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    password VARCHAR(45) NOT NULL
);


CREATE TABLE contacts(
  contact_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  surname varchar(45) NOT NULL,
  name VARCHAR(45) NOT NULL,
  patronymic varchar(45) NOT NULL,
  mobile_number VARCHAR(45) NOT NULL,
  home_phone varchar(45) DEFAULT NULL,
  address varchar(45) DEFAULT NULL,
  email varchar(45) DEFAULT NULL,
  user_id INT NOT NULL,

  CONSTRAINT contact_user FOREIGN KEY (user_id) REFERENCES users (user_id)
);
CREATE INDEX contact_user_idx ON contacts (user_id);