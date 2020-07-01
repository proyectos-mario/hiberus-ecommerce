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
insert into ecommerce.client values(3,'Luis Alejandro','Ruiz Alvarez','3103096814');
insert into ecommerce.client values(4,'Ivan','Buitrago','3203086751');
insert into ecommerce.client values(5,'Dario','Gomez','3103096817');
insert into ecommerce.client values(6,'Alejandro','Muñoz','3203086741');
