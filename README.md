# MinimalTwitter
A minimalistic twitter web application, made with Spring Boot and Spring Security and deployed on AWS Elastic Beanstalk.

## Deployment and User details

The web-app is deployed at the URL: http://minimaltwitter-env.eba-6qzb9c5e.us-east-1.elasticbeanstalk.com

The following users have been created with username with the password the same as the username

- user1
- user2
- user3
- user4
- user5

The tech stack used is:

- Java JDK 8
- Spring Boot 2.0.2RELEASE
- Spring Security
- Thymeleaf for temmplates and UI
- [Burma](https://bulma.io/) for CSS styling
- AWS Elastic Beanstalk (EBS) for deployment
- AWS RDS with MySQL for storage
- [TRIED, NOT INCLUDED] AWS Cognito for user-authentication 

### Features

The UI has the following features,

- Create Tweet
- View other's tweets
- View your own tweets
- Login and Logout

The backend REST API supports a few more,

- Delete tweet
- Add user
- Delete user


## Details of implementation and approaches considered

The database was chosen as MySQL so joins can be performed easily. Extendable to add follwowers, likes, comments and pictures. Using the JPA repository, queries were defined using the method naming convention in Spring JPA. There are 2 tables: User and Tweet, joined by userId.

The `TweetController` and `UserContoller` are REST controllers, for a purely REST API perspective and not for powering the UI. Ideally these should be wrapped into TweetService and UserService so the `UIController` queries only the service and not directly the JPA repository methods to loosen coupling, but due to time constraints this was not done. The `UIController` directly queries the JPA repository methods. The request mapping methods (GET,POST) have minimal handling - invalid user, user not in DB, etc.

The `UIController` controls all functionality of the UI. Thymeleaf is used for creating the UI and showing the authenticated user the experience customised to them. 

In terms of authentication, if you see in the `application-properties` file, you will see a lot of AWS Cognito properties commented out. This is because this approach was tried first instead of using In-Memory BCrypt-Encoder user authentication, and it worked end-to-end locally, but due to time constraints this was not successfully deployed on AWS Elastic Beanstalk. So instead, In-Memory BCrypt-Encoder user authentication was used which can be seen in `SecurityConfiguration.usersDetailsService()` method.

AWS Elastic Beanstalk was used to implement the PoC minimal twitter since no major fine-graining was required here.

## Future considerations

1. Replace In-Memory authentication with AWS Cognito identity provider for authentication and authorisation
2. Cache the responses using a memcached (like Redis) between the backend and the MySQL RDS to reduce latency and trade-off for evetual consistency of viewing tweets
3. Refactor code to wrap the REST controllers inside service classes
4. Increase security and exception handling for null, invalid cases. Add CSRF protection.
5. Add followers, likes and comments features
6. Remove credentials from application.properties and instead use IAM roles for storing env variables or use AWS Secrets Manager

## Repository overview

The `src` directory contains the core source code. In there, the following sections exist

### controller

Here the controllers are defined for controlling the REST APIs and the user interface.

### model

Defined the entity classes for entities, like Tweet, User, Follower as well as input classes for user inputs like TweetInput

### repository

Defined the JPA repository queries for querying the

### SecurityConfiguration

Defines authentication and the user service for the hard-coded list of users using Spring's In-memory authentication

### TwitterApplication

Main application entry point

### WebConfiguration

Views for the controllers

### resources

statcs resources for
- MySQL RDS database
- Thymeleaf templates for the UI
- application.properties file(s) for configuration 

