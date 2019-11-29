ALTER TABLE product_table ADD COLUMN `deleted` int DEFAULT 1 COMMENT '是否删除';

ALTER TABLE product_table CHANGE id id int AUTO_INCREMENT;
