# Cinema booking service

Project Cinema is simply online booking service template with basic operations 
for movies, tickets, users and orders.   

## Summary

  - [Project purpose](#project-purpose)
  - [Project structure](#project-structure)
  - [Implementation details](#implementation-details)
  - [Run and deployment](#run-and-deployment)
  - [Authors](#authors)

## Project purpose

Project Cinema allows you to create and modify e-shop with basic operations such as:

  - User registration, login and logout
  - User password encryption
  - User authentication and RBAC authorization
  - Ticket management: CRUD operations
  - Adding tickets to shopping cart and order completion
 
Users have two roles: ADMIN and USER. 

Users with ADMIN role are authorized to:

    - create new movies, cinamehalls, movie sessions, users
    - get all Movies and CinemaHalls
    - get all available MovieSessions
    - get a user by login (email)
    
Users with USER role are authorized to:

    - get all Movies and CinemaHalls
    - get all available MovieSessions
    - add tickets to their shopping cart
    - view shopping cart and complete order
    - view all their orders

### Project structure

The project uses MVC architectural pattern. Project structure is the following:

  - Models (entity classes), DTO and DTOMappers
  - DAO layer, containing basic CRUD-operations for communication with the persistence layer
  - Service layer, containing business-logic of the application
  - Controllers, implementing client-server communication logic

### Implementation details

  - User authentication and RBAC authorization implements by Spring Security
  - JPA Criteria API - for DB queries
  - BCryptPasswordEncoder - for user password encryption
  - Jackson API - for working with JSON
  - Maven Checkstyle Plugin, Travis CI and SonarCloud Continuous Code Quality Tool are configured

## Run and deployment

To run this project you will need to install:

  - JDK 11 or higher
  - Apache Maven
  - Apache Tomcat
  - MySQL RDBMS

Here are the steps for you to follow:

  - Add this project to your IDE as Maven project.
  - If necessary, configure Java SDK 11 in Project Structure settings.
  - Add new Tomcat Server configuration and select war-exploded artifact to deploy. Set application context parameter to "/".
  - Run the project via Tomcat configuration.

After application have started you can see login page. The demo data is installed to database. You can login as admin with:

    - login: admin@pigs.com
    - password: admin

By default, the USER role is assigned to all registered users.

## Authors

  - [Andrii Borozdykh](https://github.com/aborozdykh/)
