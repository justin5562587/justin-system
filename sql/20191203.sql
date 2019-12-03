create table product_order_related_table
(
    id          bigint auto_increment
        primary key,
    order_id bigint       null,
    product_id     bigint       null
)
    engine = innodb;

drop table article_table, blog_table;

alter table user_table engine=InnoDB;
alter table product_order_table engine=InnoDB;
alter table product_table engine=InnoDB;
alter table product_order_related_table engine=InnoDB;
