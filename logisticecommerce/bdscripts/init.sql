CREATE SCHEMA ecommercelogistic AUTHORIZATION ecommercelogistic;
CREATE TABLE ecommercelogistic.order
(
    order_id integer NOT NULL,
    address character varying(100) not null,
    date_order timestamp without time zone NOT NULL,
	date_generation timestamp without time zone NOT NULL,
    product_total DOUBLE PRECISION not null,
	client_id integer NOT NULL,
	CONSTRAINT order_id_pkey PRIMARY KEY (order_id)
);
ALTER TABLE ecommercelogistic.order
    OWNER TO ecommercelogistic;
	
CREATE SEQUENCE ecommercelogistic.order_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE ecommercelogistic.order_id_seq
    OWNER TO ecommercelogistic;

