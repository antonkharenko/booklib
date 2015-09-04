# Booklib #

Booklib is a reference web application backend. It provides REST API to the clients and it is built using following technology stack:

* Java
* Dropwizard
* Spring
* MongoDB
 
See also [booklib-client](https://github.com/antonkharenko/booklib-client) repository for frontend application sources.

## How to run? ##

1. Make sure you have MongoDB running locally and available on the port 27017.
2. Clean and build your application (app_root_directory> mvn clean install).
3. Run command: java -jar target\booklib-1.0-SNAPSHOT.jar server booklib.yml
4. Open [http://localhost:8080/](http://localhost:8080/) for web client or [http://localhost:8081/](http://localhost:8081/) for admin menu

## API Reference ##

### User Service ###

Sign up:

    curl http://localhost:8080/api/user/signup -X POST -H "Content-Type: application/json" -d '{"username":"johndoe","password":"qwerty","email":"johndoe@example.com", "first_name":"John", "last_name":"Doe"}'

Log in:

    curl http://localhost:8080/api/user/login -X POST -H "Content-Type: application/json" -d '{"login":"johndoe","password":"qwerty"}'

Update account:

    curl http://localhost:8080/api/user -X POST -u e1168ab9-5b1b-45db-9e7a-41036c06824d: -H "Content-Type: application/json" -d '{"username":"updated_johndoe","first_name":"updated John", "last_name":"updated Doe"}' 

### Book Service ###

Search books:

    curl "http://localhost:8080/api/books/search?q=Effective+Java" -u e1168ab9-5b1b-45db-9e7a-41036c06824d: 
    
Save book:

    curl "http://localhost:8080/api/books" -X POST -H "Content-Type: application/json" -u e1168ab9-5b1b-45db-9e7a-41036c06824d: -d '{"search_book_id":"ka2VUBqHiWkC"}' 
    
Get book:

    curl "http://localhost:8080/api/books/55e9ed8fd4c63096b460dc13" -u e1168ab9-5b1b-45db-9e7a-41036c06824d:

Get book list:

    curl "http://localhost:8080/api/books" -u e1168ab9-5b1b-45db-9e7a-41036c06824d:

Delete book:

    curl "http://localhost:8080/api/books/55e9ed8fd4c63096b460dc13" -X DELETE -u e1168ab9-5b1b-45db-9e7a-41036c06824d:

