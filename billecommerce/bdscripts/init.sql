CREATE SCHEMA ecommercebill AUTHORIZATION ecommercebill;
CREATE TABLE ecommercebill.product
(
    product_id integer NOT NULL,
    name character varying(100) not null,
	CONSTRAINT product_id_pkey PRIMARY KEY (product_id)
);

insert into ecommercebill.product values(1,'Alpina Milk');
insert into ecommercebill.product values(2,'Brisa Water');
insert into ecommercebill.product values(3,'Cocacola');
insert into ecommercebill.product values(4,'Pepsi');
insert into ecommercebill.product values(5,'Alpina Cheese');
insert into ecommercebill.product values(6,'Brothers Chicken');

