# Lucky Draw Game Service
Lucky draw game service is a Spring Boot backend Application which allows user to participate in the game based on raffle ticket system,
among all registered user in a particular event, among all participants one will be the winner that is totally random.

# Database
PostgreSql database (A relational powerful db)

# Requirement Setup
IDE : IntelliJ IDEA | Eclipse (Anyone would work) \
Java 8

# Algorithm
- main task is to finding winner among all participants for that I have used randomisation technique among all, have used Math.random() function.

# Project Structure

>- proper patterns has been followed to build the application with controller, service layer and repository
- Controllers
   > - EventController - Handle frontend request to perform an event related task (add an event, see next event, etc)
   > - UserController  - Handle frontend request to perform user related task (register user, get user, etc)
   > - GameController -  Handle frontend request to perform Game related task (participation, getWinner, etc)

- Service    
   > - EventService - implemented logic for eventController
   > - GameService - implemented logic for gameController
   > - UserService - implemented logic for userController

- Repository
   > - eventRepository
   > - gameRepository
   > - userRepository

- Entity
   > - Users
   > - Event
   > - Participation

# Steps to run application
- Install postgres database in system and create Role and database
- commands: 
  - sudo apt install postgresql postgresql-contrib
  - sudo -u postgres psql (to access postgres prompt)
- clone the repo and run command : mvn clean install -DskipTests  
- add database configuration, and you are good to go

# Some cases handled
- User can only participate if he has enough raffle ticket
- decr raffle count after every participation

# ER Diagram
> pic link : https://ibb.co/HXq4kVS

    


