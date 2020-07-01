---conectarse con admin postgresq
CREATE USER ecommerce;
ALTER USER ecommerce WITH PASSWORD 'ecommerce';
CREATE DATABASE ecommercebd WITH OWNER = ecommerce ;


CREATE SCHEMA ecommerce AUTHORIZATION ecommerce;




CREATE TABLE ecommerce.client
(
    client_id integer NOT NULL,
    name character varying(100) not null,
    last_name character varying(100) not null,
    phone_number character varying(30)not null,
	CONSTRAINT client_id_pkey PRIMARY KEY (client_id),
	CONSTRAINT client_phone_number_uk UNIQUE (phone_number)
);

insert into ecommerce.client values(1,'Mario Esteban','Ortega Garces','3103096818');
insert into ecommerce.client values(2,'Pedro Andres','Quintero Vargas','3203086781');

