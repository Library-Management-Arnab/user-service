## User-Service
User Service REST API

## Author
Arnab Mondal

### This Rest API supports the following endpoints

|   Endpoint      | HTTP Method | Description                          |  Access |       Comments      |
|-----------------|-------------|--------------------------------------|---------|---------------------|
|/users           | GET         | List of all users                    | GENERAL |                     |
|/users/{user_id} | GET         | Get user by id                       | GENERAL | Login, view profile |
|/users/{user_id} | POST        | Add a user                           | GENERAL | User Registration   |
|/users/{user_id} | DELETE      | Delete a user                        | ADMIN   |                     |
|/users/{user_id} | PUT         | Update a user                        | GENERAL | Update user profile |



### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Building a Hypermedia-Driven RESTful Web Service](https://spring.io/guides/gs/rest-hateoas/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)