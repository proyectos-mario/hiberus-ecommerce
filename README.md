# E-Commerce Project

Welcome to e-commerce simulate for hiberus Test for Java Microservices.

My personal info is:

* Name: **Mario Esteban Ortega Garcés**
* Mail: **maes0186@gmail.com**
* LinkedIn profile: **[https://www.linkedin.com/in/maes0186/](https://www.linkedin.com/in/maes0186/)** 


## Technologies and tools

To achieve the goals for Hiberus and give the best possible experience for the customers, I evaluated the available technologies of the market that and I Choose which I considered the best and proper for an e-commerce project.

Taking into account the wide experience of Spring boot and Docker technologies in the market, currently, they are the most recommended solutions for microservices systems, their main advantages are related to security infrastructure and support service.  Java is one of the most used technologies on applications development, on the other hand, Docker has presented a good performance with containers and it can be adapted in more complex architecture using tools like kuberentes for load balancing in services.

Below are related in detail the technologies that I selected for this project:

- Java version "1.8.0_241"
- Spring tools suite 4 (IDE)
- Spring boot 2.3.1
- Spring Data
- Maven
- Docker version 19.03.8
- Docker-compose version 1.25.5
- Swagger 2.7.0
- User SO: Windows 10 Pro
- Git version 2.26.0
- Postgres Database
- Postman
- React 16.13.1
- Node.js 12
- Nginx

## Design

![alt text](https://github.com/proyectos-mario/hiberus-ecommerce/blob/master/images/design.png?raw=true)

For this phase, I created a main service called “check out service”, this service starts the process, and then it calls the “bill service” which multiply each quantity by the corresponding value of the product to obtain the total value through the sum of all the values.
After that, “checkout service” calls “logistic servic” to save the following information in “order database table”: 

* Sum of billservice.
* Address info.
* Order date.
* Client id
* Generation date (Date of execution of service) 
* Sent number.

The “sent number” is obtained through the sequence of the database called “order_id_seq”

Finally the “logistic service” returns an “OutLogisticVO” object to “checkout service” and then the “checkout service” returns an ¨OutCheckOutVO¨ object and the process finishes.
For the development of this Project, seven isolated containers were used which were connected through a network provided and configured by docker-compose technology. For test purposes, the services ports were exposed to local machine.


 ## Microservices:

This project has 7 Microservices:
* **CheckOutService:** (./checkoutecommerce folder): This service starts the process and calls “Bill service” and “Logistic service”
* **CheckOutService Database:** This service is a “postgres database” that includes Clients information.
* **BillService: (./billecommerce folder): This service receives Date, ClientId, Products information and get sum of products values.
* **BillService Database:** This service is another “postgres database” that includes products information. (This database is different that Checkout Database)
* **Logisticservice:** (./logisticecommerce): This service is responsible to generate the "sent order". It creates a number Id and returns this value in an OutLogisticVO object, finally it creates a register in the “Order table” with the ¨Sent order” information.
* **LogisticService Database:** This service is another “Postgres database” that includes order information. (This database is different that Checkout Database). It generates an Order Id with the following database sequence:
order_id_seq 
* **Frontend** Like a plus I added a page developed in React to test the process and show results.

# Java Projects

This backend solution was implemented with Java Spring boot, spring data, and maven.
You can open these maven projects with Spring tools suite 4 :
1.	Checkoutecommerce.
2.	logisticecommerce
3.	billecommerce
4.	voecommerce

![alt text](https://github.com/proyectos-mario/hiberus-ecommerce/blob/master/images/designJava.png?raw=true)

If you change something in the code and you need to compile and see this changes, you should execute the following steps:

```
docker-compose stop
docker-compose rm -f
docker-compose up --build -d
```
## Prerequisites

First you need to install :

1) Docker and docker-compose (Docker-compose is into docker installation)
2) Jdk 8
3) Git

## Installation

The package can be installed via docker-compose functionality, first download the project via github and later execute docker-compose:

1) Clone repository from github
```
git clone git@github.com:proyectos-mario/hiberus-ecommerce.git

```
2) Execute docker-compose in the /hiberus-ecommerce folder

```
docker-compose up --build -d
```

3) You can check if the services are up with this docker command:

```
docker ps
```
And you should be see something like this:

![alt text](https://github.com/proyectos-mario/hiberus-ecommerce/blob/master/images/dockerps.png?raw=true)

4) If you need to stop the process execute:

```
docker-compose stop
docker-compose rm -f
```

## Swagger Documentation

Once the project has been installed,  This project generate swagger documentation in this links:

- [http://localhost:7002/swagger-ui.html](http://localhost:7002/swagger-ui.html). CheckOut documentation
- [http://localhost:7000/swagger-ui.html](http://localhost:7000/swagger-ui.html). Billing documentation
- [http://localhost:7001/swagger-ui.html](http://localhost:7001/swagger-ui.html). Logistic documentation

# How this work

## localhost public urls (Why?)

As you can see, I used localhost for all services, due to I exposed willfully the  APIs and database services in localmachine for test purposes, but internally the microservices use their own network to connect their containers thanks to Docker and Docker-compose technology. An easy way to test is to  delete the exposed ports in docker-compose file. It can be reviewed in docker-compose file like this

```
...
ports: 
      - "7000:7000" 
...
```
With this, you can get “rest services” or local databases conections.

# Start to work!!!

## FrontEnd to Test complete service

I develop a simple React.js interface to test rest sercives, and you can acces with this url in local machine:

[http://localhost:3000/](http://localhost:3000/)

You put data in the input elements and in the top of the page appears the history of orders that show the result of all checkout process.

## Database local acces

I tell you that I expose ports to be used in the local machine for local test , so for database you can acces locally.

Those are de credentials and acces with your Postgres client:

* CheckOut database (Client table in schema ecommerce)

```
server: localhost
port: 6002
user: ecommerce
password: ecommerce
database: ecommercebd
```

* Bill database (Product table in schema ecommercebill)

```
server: localhost
port: 6000
user: ecommercebill
password: ecommercebill
database: ecommercebillbd
```

* Logistic database (Orders table in schema ecommercebill and sequence called “order_id_seq”)

```
server: localhost
port: 6001
user: ecommercelogistic
password: ecommercelogistic
database: ecommercelogisticbd
```

## Before to start

This project connects with database elements like products or clients and gets elements like orders, for that reason, I built 3 services to test each one of them. 

These “rest services” are:


+ [http://localhost:7002/api/getClients](http://localhost:7002/api/getClients). Client List
+ [http://localhost:7000/api/getProducts](http://localhost:7000/api/getProducts). Product List
+ [http://localhost:7001/api/getOrders](http://localhost:7001/api/getOrders). Order List (This service show the result of executions of entire process)

## Checkout Service

You can test twith React.js interface: [http://localhost:3000/](http://localhost:3000/) or you can run this post service in Postman or another rest tool for http://localhost:7002/api/checkout and put in the body something like this:

```json
{
  "clientId": 1,
  "date": "2020-07-01T00:19:43.509Z",
  "direction": "Carrera 11 # 140 -52",
  "products": [
    {
      "cost": 100.5,
      "id": 1,
      "quantity": 2
    },
    {
      "cost": 200.3,
      "id": 2,
      "quantity": 3
    }
  ]
} 
```
When this process is executed you get a response like this:


```json
{
    "processOk": true,
    "message": "Correct CheckOut",
    "logisticOut": {
        "processOk": true,
        "message": "Logistic out ok",
        "numberOrder": 1
    },
    "billOut": {
        "processOk": true,
        "message": "Bill process pass ok",
        "sum": 801.9000000000001
    }
}
```
As you can see this json returns a process Ok=true if all procedures are ok.
Also it returns the bill response with billOut object and logistic response with logisticOut object. 
If the process fail, the processOk = false and show message error in "message"
