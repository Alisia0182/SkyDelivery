# Takeout Delivery System

## Deployment

### macOS

Make sure you have Homebrew installed.

#### Dependencies

This project uses Maven to organize dependencies. To install Maven, simply run
```shell
brew install maven
```
After that, click 'Reload All Maven Projects' button in the Maven menu to load the dependencies, then
run the following commands to install and compile.
```shell
mvn clean install
mvn compile
```
Then the project can be started by Spring Boot.

#### Frontend

This project uses Nginx as the web server. To install Nginx, run

```shell
brew install nginx
```

To deploy the frontend, we need to modify nginx configuration.

Use the following command to locate the configuration file.
```shell
nginx -t
```
And then substitute it with the configuration file of our project.
Make sure that the http/server/location term is the path to your frontend resource.  
Then run

```shell
brew services start nginx
```

#### Connect to Database
This project uses MySQL as the database. To install, run
```shell
brew install mysql
# set password for mysql
mysql_secure_installation
brew services start mysql
```
Note that the password is needed for JDBC connection.
After successfully deploy the MySQL service, we need to load the `sky.sql` file into database.
Run
```shell
mysql -u root -p
# enter the password you set just now
```
In MySQL, run
```mysql
source ${your .sql file path}/sky.sql
```
to load the database schema and data.

Then our project can be successfully deployed.

#### Test with frontend and backend
Enter site `localhost:80` and there should be a login page.
The default username and password have been filled.
By clicking `login`, you should be able to log in.
If something unexpected happens, check the console to see what else is needed.

### Recommended Plugins (IDEA CE)
- Database Navigator
  - for MySQL connection and view
- Lombok
  - also need lombok >= 1.18.30
- MybatisX
  - mapping .xml to sql query