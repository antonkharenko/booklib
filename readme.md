# Booklib #

Booklib is a reference web application backend. It provides REST API to the clients and it is built using following technology stack:

* Java
* Dropwizard
* Spring
* MongoDB

## How to run? ##

1. Clean and build your application (app_root_directory> mvn clean install)
2. java -jar target\booklib-1.0-SNAPSHOT.jar server booklib.yml

## API Reference ##

### Account Service ###

Sign up:

    curl http://localhost:8080/api/account/signup -X POST -H "Content-Type: application/json" -d '{"username":"johndoe","password":"qwerty","email":"johndoe@example.com", "first_name":"John", "last_name":"Doe"}'

Log in:

    curl http://localhost:8080/api/account/login -X POST -H "Content-Type: application/json" -d '{"login":"johndoe","password":"qwerty"}'

Update account:

    curl http://localhost:8080/api/account -X POST -u d9fafb49-990e-4c4d-b512-6c42fe24a269: -H "Content-Type: application/json" -d '{"username":"updated_johndoe","first_name":"updated John", "last_name":"updated Doe"}' 

### Book Service ###

Search books:

    curl "http://localhost:8080/api/books/search?q=Effective+Java" -u d9fafb49-990e-4c4d-b512-6c42fe24a269:
    
Save book:

    TBD
    
Get book:

    TBD

Get book list:

    TBD

Delete book:

    TBD

