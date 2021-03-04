# vacancy diary
to get started:
1. clone the repository, load maven dependencies. You can use the settings, database and mail that are already indicated, then skip the next steps.
2. in the main/resources/application.yml change lines 21-23 - write down the settings for your database. And also write down the settings for sending mail in lines 7-10.
3. to automatically populate the database in the main/java/vacancy_diary/DBRunner uncomment 19 line.

or you can try it on heroku: https://vacancydiary.herokuapp.com/

endpoints:
Swagger on <server>/doc

CREATE USER:
POST
http://localhost:8080/api/v1/users/registration
  
  body: {
          "email": "maria@ukr.net",
          "password": "12345678"
        }

LOGIN:
POST
http://localhost:8080/api/v1/users/login
  
  body: {
          "email": "maria@ukr.net",
          "password": "12345678"
        }
        
UPDATE USER:
PUT
http://localhost:8080/api/v1/users

Headers: "Authorization" : "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYXJpYUB1a3IubmV0IiwiaWF0IjoxNjE0ODA0NDgyLCJleHAiOjE2MTQ4MTE2ODJ9.z0PiRCG8hVbnlcnVZzezf8qFhXb81veb2ut1q8JkFB8"
  
  body: {
          "id": 13,
          "email": "maria@ukr.net",
          "password": "12345678"
        }
 
GET USER BY ID:
GET
http://localhost:8080/api/v1/users/{id}

Headers: "Authorization" : "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYXJpYUB1a3IubmV0IiwiaWF0IjoxNjE0ODA0NDgyLCJleHAiOjE2MTQ4MTE2ODJ9.z0PiRCG8hVbnlcnVZzezf8qFhXb81veb2ut1q8JkFB8"
        
       
DELETE USER BY ID:
DELETE
http://localhost:8080/api/v1/users/{id}

Headers: "Authorization" : "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYXJpYUB1a3IubmV0IiwiaWF0IjoxNjE0ODA0NDgyLCJleHAiOjE2MTQ4MTE2ODJ9.z0PiRCG8hVbnlcnVZzezf8qFhXb81veb2ut1q8JkFB8"
 
CREATE VACANCY:
POST
http://localhost:8080/api/v1/vacancies

Headers: "Authorization" : "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYXJpYUB1a3IubmV0IiwiaWF0IjoxNjE0ODA0NDgyLCJleHAiOjE2MTQ4MTE2ODJ9.z0PiRCG8hVbnlcnVZzezf8qFhXb81veb2ut1q8JkFB8"
  
  body: {
          "name": "vacancy_name",
          "position": "vacancy_position",
          "expectedSalary": 100,
          "vacancyLink": "vacancy_link",
          "recruiterContacts": "recruiter@ukr.net",
          "status": "GIVE_IN"
        }

UPDATE VACANCY:
PUT
http://localhost:8080/api/v1/vacancies

Headers: "Authorization" : "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYXJpYUB1a3IubmV0IiwiaWF0IjoxNjE0ODA0NDgyLCJleHAiOjE2MTQ4MTE2ODJ9.z0PiRCG8hVbnlcnVZzezf8qFhXb81veb2ut1q8JkFB8"
  
  body: {
          "id": 78,
          "name": "vacancy_name",
          "position": "vacancy_position",
          "expectedSalary": 100,
          "vacancyLink": "vacancy_link",
          "recruiterContacts": "recruiter@ukr.net",
          "status": "GIVE_IN"
        }
        
SEND MESSAGES:
GET
http://localhost:8080/api/v1/vacancies/sendMessages

Headers: "Authorization" : "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYXJpYUB1a3IubmV0IiwiaWF0IjoxNjE0ODA0NDgyLCJleHAiOjE2MTQ4MTE2ODJ9.z0PiRCG8hVbnlcnVZzezf8qFhXb81veb2ut1q8JkFB8"
  
GET VACANCIES:
GET
http://localhost:8080/api/v1/vacancies

Headers: "Authorization" : "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYXJpYUB1a3IubmV0IiwiaWF0IjoxNjE0ODA0NDgyLCJleHAiOjE2MTQ4MTE2ODJ9.z0PiRCG8hVbnlcnVZzezf8qFhXb81veb2ut1q8JkFB8"

Optional query params:  int size(items in page),
                        int page(page number),
                        String status,
                        String name

