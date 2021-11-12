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