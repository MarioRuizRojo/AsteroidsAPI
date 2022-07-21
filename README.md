# Asteroids API
This java reactive(WEBFLUX) backend application is a REST API with 2 endpoints:  
GET /api/largestbyyear
GET /api/tenclosest
The first endpoint has 2 parameters: start_date and end_date, and the second endpoint has 1 parameter: year  
So the first endpoint returns all data of the largest asteroid that have passed closest to the earth that year.  
And the second endpoint returns a list of the 10 asteroids that have passed closest to the earth inside a period of time.  
This application is a client of Neo Feed API from NASA.

## Cache
This application uses a mongoDB as cache for saving all the already requested Neo Data storing it by date.  
Neo Feed API has a limit of request per hour (1000) and also has a limit of request per min.  
Neo Feed API has a limit of dates inside the date's range in a request (7).  
So using the mongoDB this app decrease the number of requests per hour.  
This app only requests ranges of one day to avoid the range date parameter limitation.  
It also uses reactive delays in the streams to avoid the limitation of requests per min.  

## Code Modules
### com.nasa.client.app
#### AppConfig
Configuraion file to setup the Neo Feed API reactive web client
#### RouterFunctionConfig
Configuraion file to setup the 2 endpoints of Asteroids API in the reactive controller
#### SpringBootWebfluxClientApplication
Configuraion file to setup the spring boot app
### com.nasa.client.app.handler
#### AsteroidHandler
This handles all the requests to the 2 endpoints. It calls to AsteroidService to get the asteroid's data and then send it to the user.
### com.nasa.client.app.business
#### AsteroidService
This service checks if the requested data is already in mongodb so fetch it from the DB or else it fetch it from NasaService.
This service implements the business logic.
### com.nasa.client.app.models
All asteroid classes are the final format to deliver the data to the user.
AsteroidLarge uses diameter comparison.
AsteroidClose uses close approach data to make comparisons (shorted as astronomical).  
#### Dao and Documents
Configuration files to define MongoDb collections and reactive connection
### com.nasa.client.app.models.json
All classes inside this package are made from JSON response structure of Neo Feed API
### com.nasa.client.app.models.services
#### NasaService
Neo Feed API web client that makes reactive requests  
## Get It Running
Open an account on NASA website.
Set up your api_key as environment variable:
```Shell
API_KEY=<yourApiKeyValue>
```  
Install mongodb and jdk11.
In windows run mongo:
```Shell
mongod --port 27017 --dbpath C:\MongoDB\data\db
```  
In windows run the app:
```Shell
java -jar /target/nasa-client-0.0.1-SNAPSHOT.jar
```  

## TODOs  
Fix UnitTests and add some failure cases
Fix the Dockerfile  