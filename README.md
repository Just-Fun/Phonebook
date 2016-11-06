# Phone book

##Application contains data

Information about the user in the system:

`Login (only English characters, not less than 3, no special characters)`

`Password (at least 5 characters)`

The stored information (one record):
```
Name (required, at least 4 characters)
Surname (required, at least 4 characters)
Middle name (required, at least 4 characters)
Mobile phone (required)
Home phone number (not required)
address (optional)
e-mail (not mandatory, common validation)
```

##The project consists of the page:

1. Check in

2. Authorization - login (username / password)

3. Home page:
```
Sign Out
Working with stored data:
View all data with the ability to filter by any field (not fully fit).
Add / Edit / Delete stored records
```

The system is available only to authorized users. If the user is not logged in, when they try to open any page it redirects to the login page.

## Getting Started


### Installing

Create DATABASE (you can change database name):

``` 
CREATE DATABASE phonebook;
```

Create a “users” table.

```
CREATE TABLE users(
    user_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    password VARCHAR(45) NOT NULL
);
```
  
Create a “contacts” table.

```
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
```

### Prerequisites


1. Change `DB_USER_NAME`, `DB_USER_PASSWORD` and `DB_URL` in properties file(`resources/db.properties`).

2. In project implemented two options to choose the data warehouse: 
  
  Selection is made in the file:
  `WEB-INF/application-context.xml`
  
  Select beans `userDao` and `contactDao`:
  
   for database (MySQL):
  
   choose `Store in a local file(JSON)`
   comment out `Store in a database (MySQL)`
  
   for file storage (JSON):
  
   choose `Store in a database (MySQL)`
   comment out `Store in a local file(JSON)`
  


## Running the tests

`src/test/java` 

Run -> Run 'All Tests'

## Running project on your local machine

Maven projects -> Plugins -> jetty -> jetty:run

`http://localhost:8080/phonebook`

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Jetty](http://www.eclipse.org/jetty/) - Web server and javax.servlet container


## Authors

* **Sergii Zagryvyi** - [Just-Fun](https://github.com/Just-Fun)

