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
                                                                                                  (3, 'Bread3', 0, 'some_img')