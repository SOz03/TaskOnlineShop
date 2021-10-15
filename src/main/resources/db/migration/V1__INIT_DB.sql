CREATE TABLE products
(
    id uuid NOT NULL,
    name text NOT NULL,
    price numeric NOT NULL,
    production_date date,
    description text NOT NULL,
    CONSTRAINT products_pkey PRIMARY KEY (id)
) TABLESPACE pg_default;

ALTER TABLE products
    OWNER to postgres;

CREATE TABLE customers
(
    id uuid NOT NULL,
    first_name text NOT NULL,
    phone_number text NOT NULL,
    date_birth date,
    address text NOT NULL,
    CONSTRAINT customers_pkey PRIMARY KEY (id)
) TABLESPACE pg_default;

ALTER TABLE customers
    OWNER to postgres;

CREATE TABLE delivery
(
    id uuid NOT NULL,
    name text NOT NULL,
    cost numeric,
    time_delivery time without time zone,
    CONSTRAINT delivery_pkey PRIMARY KEY (id)
) TABLESPACE pg_default;

ALTER TABLE delivery
    OWNER to postgres;

CREATE TABLE orders
(
    id uuid NOT NULL,
    delivery_id uuid NOT NULL,
    cost numeric,
    date timestamp without time zone,
    CONSTRAINT orders_pkey PRIMARY KEY (id),
    CONSTRAINT fk_orders_delivery FOREIGN KEY (delivery_id)
        REFERENCES delivery (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
) TABLESPACE pg_default;

ALTER TABLE orders
    OWNER to postgres;

CREATE TABLE customer_baskets
(
    id uuid NOT NULL,
    customers_id uuid,
    CONSTRAINT customer_baskets_pkey PRIMARY KEY (id),
    CONSTRAINT fk_customer_baskets_customer FOREIGN KEY (customers_id)
        REFERENCES customers (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
) TABLESPACE pg_default;

ALTER TABLE customer_baskets
    OWNER to postgres;

CREATE TABLE baskets_products
(
    id uuid NOT NULL,
    product_id uuid NOT NULL,
    customer_baskets_id uuid NOT NULL,
    count_product integer NOT NULL,
    orders_id uuid NOT NULL,
    CONSTRAINT baskets_products_pkey PRIMARY KEY (id),
    CONSTRAINT "FK_customer_baskets_id" FOREIGN KEY (customer_baskets_id)
        REFERENCES customer_baskets (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "FK_orders_id" FOREIGN KEY (orders_id)
        REFERENCES orders (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "FK_product_id" FOREIGN KEY (product_id)
        REFERENCES products (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
) TABLESPACE pg_default;

ALTER TABLE baskets_products
    OWNER to postgres;