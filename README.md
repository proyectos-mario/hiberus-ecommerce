# Ecommerce application for Hiberus

Welcome to e-commerce simulate for hiberus Test for Java Microservices.

## Technologies and tools:

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


 ## This project has six Microservices:

- **CheckOutService:**  (./checkoutecommerce folder): This service begin the process and call Bill service and Logistic service
- **CheckOutService Database:** This services is a postgres database that include Clients info
- **BillService:** (./billecommerce folder): This service receive Date, ClientId, Products info and get sum of products values
- **BillService Database:** : This services is another postgres database that include products info. (This database is diferent that Checkout Database)
- **Logisticservice:** (./logisticecommerce): This services is responsible of generation of sent order. It create a number Id and return this value in an OutLogisticVO object, also create a register in Order table with the Sent order info.
- **LogisticService Database:**: This services is another postgres database that include order info. (This database is diferent that Checkout Database) and generate an Order Id with ecommercelogistic.order_id_seq database secuence



## Installation

The package can be installed via docker-compose functionality, first donwload the project via github and later execute docker-compose:

```
git clone git@github.com:proyectos-mario/hiberus-ecommerce.git

```
Execute docker-compose

```
docker-compose up --build -d
```

If you need to stop the process execute:

```
docker-compose rm -f
```

## Swagger Documentation

Once the project had been installed,  This project generate swagger documentation in this links:

- [http://localhost:7002/swagger-ui.html](http://localhost:7002/swagger-ui.html). CheckOut documentation
- [http://localhost:7000/swagger-ui.html](http://localhost:7000/swagger-ui.html). Billing documentation
- [http://localhost:7001/swagger-ui.html](http://localhost:7001/swagger-ui.html). Logistic documentation

# How this work

## localhost urls (Why?)

That you can see, I use localhost for all services. That is because willfully, I expose APIs in localmachine for testing purposes for API services and database services, but internally microservices use its own network for connect its containers  thanks to Docker and Docker-compose technology. A easy test to see it,  is delete expose port in docker-compose file. It can see in docker-compose file like this

```
...
ports: 
      - "7000:7000" 
...
```
With this you can get rest services or connect local to databases

## Design

![alt text](https://github.com/proyectos-mario/hiberus-ecommerce/blob/master/images/design.png?raw=true)

In this design , I create a principal service called **checkoutservice**, this servive bengin the process and later call **billservice** that compute the sum of product values per quantity. Later Checkput process call **logisticservice** that save in order database table the sum of billservice, address info, order date, client id, generation date (Date of execution of service) and a number or sent order, this sent order number is given to database sequence called order_id_seq. This service return a OutLogisticVO object to **checkoutservice** and later this service return a OutCheckOutVO object and finish the process.

## Arquitecture

In this project is used 6 containers isolated and connected to docker network provided and configured for docker-compose technology. I exposed ports to localmachine for testing purposes.

# Start to work!!!

## Before to start

This project connect with database elements like products or clientes and get elemenets like orders, so I build 3 services to help you to get data to test this service. This get rest services are:

+ [http://localhost:7002/api/getClients](http://localhost:7002/api/getClients). Client List
+ [http://localhost:7000/api/getProducts](http://localhost:7000/api/getProducts). Product List
+ [http://localhost:7001/api/getOrders](http://localhost:7001/api/getOrders). Order List (This elemens show the result of executions of entire process)


## CheckOut Service


First, you should run post service: http://localhost:7002/api/checkout and put in the body something like this:

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

