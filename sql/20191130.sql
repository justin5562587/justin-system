ALTER TABLE product_table ADD COLUMN `deleted` int DEFAULT 1 COMMENT '是否删除';

ALTER TABLE product_table CHANGE id id int AUTO_INCREMENT;

ALTER TABLE product_order_table CHANGE product_ids product_ids VARCHAR(255);

ALTER TABLE product_order_table DROP COLUMN order_price;

-- 删除商品订单表的商品id列表字段，并且新创建关联表
alter table product_order_table drop column product_ids;

create table product_order_related_table
(
    id          bigint auto_increment
        primary key,
    order_id bigint       null,
    product_id     bigint       null
)
    engine = innodb;
