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
