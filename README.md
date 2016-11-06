# Phone book

##Application contains data

Information about the user in the system:

`Login (only English characters, not less than 3, no special characters)`

`Password (at least 5 characters)`

The stored information (one record):
```
Name (required, at least 4 characters)
Name (required, at least 4 characters)
Middle Name (required, at least 4 characters)
Mobile Phone (required)
Home phone number (not required)
Address (optional)
e-mail (not mandatory, common validation)
```

##The project consists of the page:

Check in

Authorization - login (username / password)

Home page:
```
Sign Out
Working with stored data:
View all data with the ability to filter by any field (not fully fit).
Add / Edit / Delete stored records
```

The system is available only to authorized users. If the user is not logged in, when you try to open any page it redirects to the login page.

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

resources/db.properties
1. Change `DB_USER_NAME`, `DB_USER_PASSWORD` and `DB_URL` in properties file(`resources/db.properties`).
If you changed `database name` during creating database also change `databaseName`.

2.In project implemented two options to choose the data warehouse: 
  
  Selection is made in the file:
  `WEB-INF/application-context.xml`
  
  Select beans `userDao` and `contactDao`:
  
  1. for database (MySQL):
  
  choose `Store in a local file(JSON)`
  and comment out `Store in a database (MySQL)`
  
  2. for file storage (JSON):
  
  choose `Store in a database (MySQL)`
  and comment out `Store in a local file(JSON)`
  


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

