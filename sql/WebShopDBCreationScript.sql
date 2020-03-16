SET NAMES utf8;

drop database IF EXISTS webshopdb;
create DATABASE webshopdb CHARACTER SET utf8 COLLATE utf8_bin;

USE webshopdb;

create TABLE `user`
(
    `id`         INT          NOT NULL AUTO_INCREMENT,
    `first_name` varchar(255) NOT NULL,
    `last_name`  varchar(255) NOT NULL,
    `email`      varchar(255) NOT NULL UNIQUE,
    `password`   varchar(255) NOT NULL,
    `mailing`    BOOLEAN      DEFAULT 1,
    `icon`       varchar(255) NOT NULL,
     PRIMARY KEY (`id`)
);

insert into user(first_name, last_name, email, password, mailing, icon)
values ('Bogdan', 'Admin', 'admin@epam.com', 'admin', 1, 'admin');

insert into user(first_name, last_name, email, password, mailing, icon)
values ('Vlad', 'Kaliuha', 'kaliuha@epam.com', 'kaliuha', 0, 'kaliuha');

create TABLE `category`
(
    `id`      INT          NOT NULL AUTO_INCREMENT,
    `name`    varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);

insert into category(name)
values ('Electronic');

insert into category(name)
values ('Gasoline');

insert into category(name)
values ('Diesel');

create TABLE `fabricator`
(
    `id`      INT          NOT NULL AUTO_INCREMENT,
    `name`    varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);

insert into fabricator(name)
values ('Toyota');

insert into fabricator(name)
values ('Suzuki');

insert into fabricator(name)
values ('Ferrari');

create TABLE `product`
(
    `id`            INT          NOT NULL AUTO_INCREMENT,
    `name`          varchar(255) NOT NULL,
    `category_id`   INT          NOT NULL,
    `fabricator_id` INT          NOT NULL,
    `price`         decimal(10,2) NOT NULL,
    `info`          varchar(10000),
    `icon`          varchar(255) NOT NULL,
    CONSTRAINT category_id_fk
           FOREIGN KEY (category_id)
           REFERENCES `category` (`id`) ON delete CASCADE,
    CONSTRAINT fabricator_id_fk
           FOREIGN KEY (fabricator_id)
           REFERENCES `fabricator` (`id`) ON delete CASCADE,
    PRIMARY KEY (`id`)
);

insert into product(name, category_id, fabricator_id, price, info, icon)
values ('Toyota', 2, 1, 100000, 'some info', '/image/car2.jpg');
insert into product(name, category_id, fabricator_id, price, info, icon)
values ('Suzuki', 2, 2, 105000, 'some info', '/image/car3.jpg');
insert into product(name, category_id, fabricator_id, price, info, icon)
values ('Ferrari', 2, 3, 155000, 'some info', '/image/car1.jpg');
insert into product(name, category_id, fabricator_id, price, info, icon)
values ('Toyota', 2, 1, 100000, 'some info', '/image/car2.jpg');
insert into product(name, category_id, fabricator_id, price, info, icon)
values ('Suzuki', 2, 2, 105000, 'some info', '/image/car3.jpg');
insert into product(name, category_id, fabricator_id, price, info, icon)
values ('Ferrari', 2, 3, 155000, 'some info', '/image/car1.jpg');
insert into product(name, category_id, fabricator_id, price, info, icon)
values ('Toyota', 2, 1, 100000, 'some info', '/image/car2.jpg');
insert into product(name, category_id, fabricator_id, price, info, icon)
values ('Suzuki', 2, 2, 105000, 'some info', '/image/car3.jpg');
insert into product(name, category_id, fabricator_id, price, info, icon)
values ('Ferrari', 2, 3, 155000, 'some info', '/image/car1.jpg');
insert into product(name, category_id, fabricator_id, price, info, icon)
values ('Toyota', 2, 1, 100000, 'some info', '/image/car2.jpg');
insert into product(name, category_id, fabricator_id, price, info, icon)
values ('Suzuki', 2, 2, 105000, 'some info', '/image/car3.jpg');
insert into product(name, category_id, fabricator_id, price, info, icon)
values ('Ferrari', 2, 3, 155000, 'some info', '/image/car1.jpg');
insert into product(name, category_id, fabricator_id, price, info, icon)
values ('Toyota', 2, 1, 100000, 'some info', '/image/car2.jpg');
insert into product(name, category_id, fabricator_id, price, info, icon)
values ('Suzuki', 2, 2, 105000, 'some info', '/image/car3.jpg');
insert into product(name, category_id, fabricator_id, price, info, icon)
values ('Ferrari', 2, 3, 155000, 'some info', '/image/car1.jpg');
insert into product(name, category_id, fabricator_id, price, info, icon)
values ('Suzuki', 2, 2, 105000, 'some info', '/image/car3.jpg');
insert into product(name, category_id, fabricator_id, price, info, icon)
values ('Ferrari', 2, 3, 155000, 'some info', '/image/car1.jpg');
insert into product(name, category_id, fabricator_id, price, info, icon)
values ('Toyota', 2, 1, 100000, 'some info', '/image/car2.jpg');
insert into product(name, category_id, fabricator_id, price, info, icon)
values ('Suzuki', 2, 2, 105000, 'some info', '/image/car3.jpg');
insert into product(name, category_id, fabricator_id, price, info, icon)
values ('Ferrari', 2, 3, 155000, 'some info', '/image/car1.jpg');
insert into product(name, category_id, fabricator_id, price, info, icon)
values ('Toyota', 2, 1, 100000, 'some info', '/image/car2.jpg');
insert into product(name, category_id, fabricator_id, price, info, icon)
values ('Suzuki', 2, 2, 105000, 'some info', '/image/car3.jpg');
insert into product(name, category_id, fabricator_id, price, info, icon)
values ('Ferrari', 2, 3, 155000, 'some info', '/image/car1.jpg');
insert into product(name, category_id, fabricator_id, price, info, icon)
values ('Toyota', 2, 1, 100000, 'some info', '/image/car2.jpg');
insert into product(name, category_id, fabricator_id, price, info, icon)
values ('Suzuki', 2, 2, 105000, 'some info', '/image/car3.jpg');
insert into product(name, category_id, fabricator_id, price, info, icon)
values ('Ferrari', 2, 3, 155000, 'some info', '/image/car1.jpg');

create TABLE `order`
(
    `id`            INT          NOT NULL AUTO_INCREMENT,
    `status`          varchar(255) NOT NULL,
    `info`          varchar(255) NOT NULL,
    `date_time`          varchar(255) NOT NULL,
    `user_id`   INT          NOT NULL,
    CONSTRAINT user_id_fk
           FOREIGN KEY (user_id)
           REFERENCES `user` (`id`) ON delete CASCADE,
    PRIMARY KEY (`id`)
);

create TABLE `ordered_product`
(
    `id`            INT          NOT NULL AUTO_INCREMENT,
    `product_id`   INT          NOT NULL,
    `count`   INT          NOT NULL,
    `price`   INT          NOT NULL,
    `order_id`   INT          NOT NULL,
    CONSTRAINT order_id_fk
           FOREIGN KEY (order_id)
           REFERENCES `order` (`id`) ON delete CASCADE,
    PRIMARY KEY (`id`)
);

select
* from user;
select
* from product;