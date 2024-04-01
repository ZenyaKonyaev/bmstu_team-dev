drop function countCakeCost(_cake_id int);

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