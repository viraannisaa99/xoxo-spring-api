# Spring Boot REST API

JWT & CRUD REST API & Multiple File Upload using Spring Boot and MySQL. 

# Postman Test

Auth:

Signup: http://localhost:8080/api/auth/signup (POST)

Login: http://localhost:8080/api/auth/signin (POST)

(Fill the 'Body' with username & password, then fill the Authorization form with the token.)
 
CRUD (only admin & mod roles):

To do CRUD, pelase signin fill the Authorization form in 'Headers' with the token.

GET: http://localhost:8080/api/products/

POST: http://localhost:8080/api/products/

PUT: http://localhost:8080/api/products/{id}

UPLOAD: http://localhost:8080/api/upload/

DOWNLOAD FILE: http://localhost:8080/api/files/filename


        

