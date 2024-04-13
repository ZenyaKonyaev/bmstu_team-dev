create table if not exists  table_user
(
    user_id serial primary key,
    user_login      varchar(255) not null,
    user_password   varchar(255) not null,
    user_name       varchar(128) not null,
    user_surname    varchar(128) not null,
    user_lastname   varchar(128) not null,
    user_address    varchar      not null,
    user_regdate    date         not null,
    user_email      varchar,
    user_tariff_plan integer,
    user_role varchar(128) not null
);


create table if not exists  table_add_descr
(
    add_descr_id serial primary key,
    add_descr_title varchar not null,
    add_descr_descr varchar not null,
    add_descr_img_name varchar not null,
    add_descr_amount_carb float not null,
    add_descr_amount_prot float not null,
    add_descr_amount_fat float not null,
    add_descr_expiry_time_days int not null
);

create table if not exists table_product
(
    product_id serial primary key,
    product_add_descr_id bigint not null,
    product_name varchar(100) not null,
    product_cost double precision,
    product_img_name varchar
);


create table if not exists table_cake_part
(
    cake_part_id serial primary key,
    cake_part_add_descr_id bigint,
    cake_part_name varchar(100) not null,
    cake_part_cost double precision,
    cake_part_type int,
    foreign key (cake_part_add_descr_id) references table_add_descr (add_descr_id)
);

create table if not exists table_custom_cake
(
    custom_cake_id serial primary key,
    custom_cake_base_part_id bigint not null,
    custom_cake_filling_part_id bigint not null,
    custom_cake_cream_part_id bigint not null,
    foreign key (custom_cake_base_part_id) references table_cake_part (cake_part_id),
    foreign key (custom_cake_filling_part_id) references table_cake_part (cake_part_id),
    foreign key (custom_cake_cream_part_id) references table_cake_part (cake_part_id)
);



create table if not exists  table_order
(
    order_id varchar primary key,
    order_user_id bigint not null,
    order_date_create timestamp not null,
    order_date_expiry date,
    order_address_to_send varchar not null,
    order_description varchar(300),
    order_status_code int,
    foreign key (order_user_id) references table_user (user_id)
);

create table if not exists table_order_to_product
(
    order_id varchar not null,
    product_id bigint not null,
    foreign key (order_id) references table_order (order_id),
    foreign key (product_id) references table_product (product_id)
);

create table if not exists table_order_to_custom_cake
(
    order_id varchar not null,
    custom_cake_id bigint not null,
    foreign key (order_id) references table_order (order_id),
    foreign key (custom_cake_id) references table_custom_cake (custom_cake_id)
);







------------------------------------------------------------------------------------------------





-- REASSIGN OWNED BY guest TO postgres;  -- or some other trusted role
-- DROP OWNED BY guest;
-- drop role guest;
-- REASSIGN OWNED BY authorized TO postgres;  -- or some other trusted role
-- DROP OWNED BY authorized;
-- drop role authorized;
-- REASSIGN OWNED BY "admin" TO postgres;  -- or some other trusted role
-- DROP OWNED BY "admin";
-- drop role "admin";

create role guest with
    login
    nosuperuser
    nocreatedb
    nocreaterole
    noreplication
    password 'guest'
    connection limit -1;

grant select on
    public.table_user,
    public.table_product,
    public.table_custom_cake,
    public.table_cake_part,
    public.table_add_descr
    to guest;

grant insert on
    public.table_user
    to guest;

grant usage, select on sequence table_user_user_id_seq to guest;

create role authorized with
    login
    nosuperuser
    nocreatedb
    nocreaterole
    noreplication
    password 'authorized'
    connection limit -1;

grant select on
    public.table_user,
    public.table_product,
    public.table_custom_cake,
    public.table_cake_part,
    public.table_add_descr
    to authorized;

grant select on
    public.table_user,
    public.table_product,
    public.table_custom_cake,
    public.table_cake_part,
    public.table_add_descr,
    public.table_order,
    public.table_order_to_product,
    public.table_order_to_custom_cake
    to authorized;

grant update on
    public.table_user
    to authorized;
grant insert on public.table_order, public.table_order_to_custom_cake, public.table_order_to_product to authorized;

create role admin with
    login
    nosuperuser
    nocreatedb
    nocreaterole
    noreplication
    password 'admin'
    connection limit -1;

grant all privileges on all tables in schema public to admin;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO admin;

select * from pg_roles;










-----------------------------------------------------------------------------------



drop trigger if exists product_operations_audit on table_product;
drop trigger if exists cake_part_operations_audit on table_cake_part;

drop table if exists operations_audit;
CREATE table operations_audit
(
    table_operated text NOT NULL,
    operation char(1) NOT NULL,
    time_operation timestamp NOT NULL,
    operation_row text NOT NULL
);

CREATE OR REPLACE FUNCTION auditOperationsTrigger()
    RETURNS TRIGGER
AS '
    BEGIN
        IF tg_op = ''DELETE'' THEN
            insert into operations_audit (table_operated, operation, time_operation, operation_row) values (TG_TABLE_NAME,''D'', now(), OLD);
            RETURN OLD;
        ELSIF tg_op = ''UPDATE'' THEN
            insert into operations_audit (table_operated, operation, time_operation, operation_row) values (TG_TABLE_NAME, ''U'', now(), NEW);
            RETURN NEW;
        ELSIF tg_op = ''INSERT'' THEN
            insert into operations_audit (table_operated, operation, time_operation, operation_row) values (TG_TABLE_NAME, ''I'', now(), NEW);
            RETURN NEW;
        END IF;
        RETURN NULL;
    END
'
    LANGUAGE plpgsql;

create trigger product_operations_audit after INSERT OR UPDATE OR DELETE ON table_product
    for each row execute procedure auditOperationsTrigger();
create trigger cake_part_operations_audit after INSERT OR UPDATE OR DELETE ON table_cake_part
    for each row execute procedure auditOperationsTrigger();

insert into table_product(product_add_descr_id, product_name, product_cost, product_img_name) VALUES
    (3, 'Bread3', 0, 'some_img');



---------------------------------------------------------------------------------------------

create or replace function countCakeCost(_cake_id int)
    returns float
as '
    select cost_base + cost_cream + cost_filling from (
                                                          select joined_1.custom_cake_id as id_cake,
                                                                 tcp_c.cake_part_cost as cost_cream,
                                                                 tcp_f.cake_part_cost as cost_filling,
                                                                 joined_1.cake_part_cost as cost_base from
                                                              (table_cake_part tcp_c join (
                                                                  table_cake_part tcp_f join  (
                                                                      (select * from table_custom_cake where _cake_id = custom_cake_id) as tcc
                                                                          join table_cake_part tcp_b on
                                                                              tcc.custom_cake_base_part_id = tcp_b.cake_part_id) as joined_1
                                                                  on joined_1.custom_cake_filling_part_id = tcp_f.cake_part_id
                                                                  ) on joined_1.custom_cake_cream_part_id = tcp_c.cake_part_id
                                                                  )
                                                      ) as t;
'
    language sql;

select countCakeCost(3);



























----------------------------------------------------------






insert into table_user(user_login, user_password, user_name, user_surname, user_lastname, user_address, user_regdate,
                       user_email, user_tariff_plan, user_role)
values ('zenya', '$2a$12$ShWWiQQvSWPOAnPudUVPSO7nZPv5yXmqa10T/Wz3Hd3guEWiBPBgy',
        'Евгений', 'Коняев', 'Андреевич', 'Москва', '2012-12-12', 'test_value', 1, 'ROLE_ADMIN');



insert into table_add_descr(add_descr_title, add_descr_descr, add_descr_img_name, add_descr_amount_carb,
                            add_descr_amount_prot, add_descr_amount_fat, add_descr_expiry_time_days)
values ('Awesome Bread', 'some_descr', 'some_url', 5, 10, 15, 10);
insert into table_add_descr(add_descr_title, add_descr_descr, add_descr_img_name, add_descr_amount_carb,
                            add_descr_amount_prot, add_descr_amount_fat, add_descr_expiry_time_days)
values ('Awesome Baget', 'some_descr', 'some_url', 5, 10, 15, 10);

insert into table_add_descr(add_descr_title, add_descr_descr, add_descr_img_name, add_descr_amount_carb,
                            add_descr_amount_prot, add_descr_amount_fat, add_descr_expiry_time_days)
values ('AwesomeBasePart1', 'some_descr1', 'some_url1', 1, 2, 3, 4);

insert into table_add_descr(add_descr_title, add_descr_descr, add_descr_img_name, add_descr_amount_carb,
                            add_descr_amount_prot, add_descr_amount_fat, add_descr_expiry_time_days)
values ('AwesomeBasePart2', 'some_descr2', 'some_url2', 3, 2, 1, 5);

insert into table_add_descr(add_descr_title, add_descr_descr, add_descr_img_name, add_descr_amount_carb,
                            add_descr_amount_prot, add_descr_amount_fat, add_descr_expiry_time_days)
values ('AwesomeFillingPart1', 'some_descr3', 'some_url2', 3, 3, 3, 5);

insert into table_add_descr(add_descr_title, add_descr_descr, add_descr_img_name, add_descr_amount_carb,
                            add_descr_amount_prot, add_descr_amount_fat, add_descr_expiry_time_days)
values ('AwesomeFillingPart2', 'some_descr4', 'some_url2', 1, 1, 1, 5);

insert into table_add_descr(add_descr_title, add_descr_descr, add_descr_img_name, add_descr_amount_carb,
                            add_descr_amount_prot, add_descr_amount_fat, add_descr_expiry_time_days)
values ('AwesomeCreamPart1', 'some_descr5', 'some_url3', 3, 3, 3, 5);

insert into table_add_descr(add_descr_title, add_descr_descr, add_descr_img_name, add_descr_amount_carb,
                            add_descr_amount_prot, add_descr_amount_fat, add_descr_expiry_time_days)
values ('AwesomeCreamPart2', 'some_descr5', 'some_url3', 3, 0, 0, 5);

insert into table_product(product_add_descr_id, product_name, product_cost, product_img_name)
values (1, 'Bread', 15.99, 'some_url');
insert into table_product(product_add_descr_id, product_name, product_cost, product_img_name)
values (2, 'Baget', 19.99, 'some_url_2');


insert into table_cake_part(cake_part_add_descr_id, cake_part_name, cake_part_cost, cake_part_type)
values (3, 'Base1', 50, 1);
insert into table_cake_part(cake_part_add_descr_id, cake_part_name, cake_part_cost, cake_part_type)
values (4, 'Base2', 51, 1);

insert into table_cake_part(cake_part_add_descr_id, cake_part_name, cake_part_cost, cake_part_type)
values (5, 'Filling1', 25, 2);
insert into table_cake_part(cake_part_add_descr_id, cake_part_name, cake_part_cost, cake_part_type)
values (6, 'Filling2', 26, 2);

insert into table_cake_part(cake_part_add_descr_id, cake_part_name, cake_part_cost, cake_part_type)
values (7, 'Cream1', 15, 3);
insert into table_cake_part(cake_part_add_descr_id, cake_part_name, cake_part_cost, cake_part_type)
values (8, 'Cream2', 16, 3);

insert into table_custom_cake(custom_cake_base_part_id, custom_cake_filling_part_id, custom_cake_cream_part_id)
values (1, 3, 5);
insert into table_custom_cake(custom_cake_base_part_id, custom_cake_filling_part_id, custom_cake_cream_part_id)
values (1, 4, 5);
insert into table_custom_cake(custom_cake_base_part_id, custom_cake_filling_part_id, custom_cake_cream_part_id)
values (2, 3, 6);



insert into table_order(order_id, order_user_id, order_date_create, order_date_expiry, order_address_to_send, order_description, order_status_code) VALUES ('abc1', 1, now(), now(), 'moscow', '', 1);
insert into table_order(order_id, order_user_id, order_date_create, order_date_expiry, order_address_to_send, order_description, order_status_code) VALUES ('abc2', 1, now(), now(), 'volgograd', '', 2);

insert into table_order_to_product(order_id, product_id) VALUES ('abc1', 1);
insert into table_order_to_product(order_id, product_id) VALUES ('abc1', 1);
insert into table_order_to_product(order_id, product_id) VALUES ('abc1', 2);

insert into table_order_to_product(order_id, product_id) VALUES ('abc2', 1);
insert into table_order_to_custom_cake(order_id, custom_cake_id) VALUES ('abc2', 2);
insert into table_order_to_custom_cake(order_id, custom_cake_id) VALUES ('abc2', 3);





