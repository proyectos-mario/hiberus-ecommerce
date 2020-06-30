---conectarse con admin postgresq
CREATE USER ecommerce;
ALTER USER ecommerce WITH PASSWORD 'ecommerce';
CREATE DATABASE ecommercebd WITH OWNER = ecommerce ;


CREATE SCHEMA ecommerce AUTHORIZATION ecommerce;
