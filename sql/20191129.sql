-- init tables
create table article_table
(
    id          bigint auto_increment
        primary key,
    content     varchar(255) null,
    create_time bigint       null,
    description varchar(255) null,
    label       varchar(255) null,
    read_num    int          null,
    star_num    int          null,
    title       varchar(255) null,
    update_time bigint       null
)
    engine = MyISAM;

create table blog_table
(
    id          bigint auto_increment
        primary key,
    content     varchar(255) null,
    create_time bigint       null,
    description varchar(255) null,
    img_url     varchar(255) null,
    label_name  varchar(255) null,
    title       varchar(255) null,
    update_time bigint       null,
    user_id     bigint       null
)
    engine = MyISAM;

create table product_order_table
(
    id          bigint       not null
        primary key,
    buyer_id    bigint       null,
    create_time bigint       null,
    handler_id  bigint       null,
    order_price int          null,
    product_ids tinyblob     null,
    status      varchar(255) null,
    update_time bigint       null
)
    engine = MyISAM;

create table product_table
(
    id          bigint       not null
        primary key,
    create_time bigint       null,
    description varchar(255) null,
    img_url     varchar(255) null,
    name        varchar(255) null,
    point_price bigint       null,
    price       bigint       null,
    update_time bigint       null
)
    engine = MyISAM;

create table user_table
(
    id          bigint auto_increment
        primary key,
    create_time bigint       null,
    email       varchar(255) null,
    password    varchar(255) null,
    points      bigint       null,
    update_time bigint       null,
    user_type   varchar(255) null,
    username    varchar(255) null
)
    engine = MyISAM;
