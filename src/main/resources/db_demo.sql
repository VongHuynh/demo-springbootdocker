create database demo_cloud;

use demo_cloud;

create table roles
(
	id				bigint			primary key auto_increment,
	`description`	varchar(255)	not null unique
);

create table users
(
	id				bigint			primary key auto_increment,
	username		varchar(255)	not null unique,
	fullname		varchar(255)	null,
	hashPassword	varchar(255)	not null,
	email			varchar(255)	not null unique,
	createdDate		datetime		not null default now(),
	imgUrl			varchar(255)	null,
	roleId			bigint			references roles(id),
	isDeleted		bit				not null default 0
);

create table product_types
(
	id				bigint			primary key auto_increment,
	`description`	varchar(255)	null,
	slug			varchar(255)	not null,
	isDeleted		bit				not null default 0
);

create table unit_types
(
	id				bigint			primary key auto_increment,
	`description`	varchar(255)	null,
	isDeleted		bit				not null default 0
);

create table products
(
	id				bigint			primary key auto_increment,
	`name`			varchar(255)	not null,
	typeId			bigint			references product_types(id),
	quantity		int				not null,
	price			decimal(12,3)	not null,
	unitId			bigint			references unit_types(id),
	imgUrl			varchar(255)	null,
	`description`	varchar(255)	null,
	slug			varchar(255)	not null unique,
	isDeleted		bit				not null default 0
);

create table orders
(
	id				bigint			primary key auto_increment,
	userId			bigint			references users(id),
	`address`		varchar(255)	not null,
	phone			varchar(255)	not null,
	createdDate		datetime		not null default now()
);

create table order_details
(
	id				bigint			primary key auto_increment,
	orderId			bigint			references orders(id),
	productId		bigint			references products(id),
	price			decimal(12,3)	not null,
	quantity		int				not null
);

insert into roles(`description`) values
('admin'),
('user');

-- duynt: pass 111
insert into users(username, fullname, hashPassword, email, imgUrl, roleId) values
('duynt', 'Nguyễn Trần Duy', '$2a$10$bC3fIu4WyB/FGwlbOPlZt.3IRzkM34vZNt1Kbe5ZDcq7r/XZFWNrO', 'duynt@abc.com', 'default.png', 1);

insert into product_types(`description`, slug) values
('Điện thoại', 'dien-thoai'),
('Laptop',		'laptop');

insert into unit_types(`description`) values
('Chiếc'),
('Bộ');

insert into products(`name`,  typeId, quantity, price, unitId, imgUrl, `description`, slug) values
('Iphone 11 64GB',					1, 5,  10000000, 1, 'iphone-11.jpg', 'Điện thoại Iphone 11 bản 64GB', 'iphone-11-64gb'),
('Samsung A95',					1, 12, 7500000,  1, 'samsung-a95.jpg', '<b>Điện thoại samsung A95</b> là mẫu điện thoại mới nhất của Samsung với nhiều tính năng vượt trội', 'samsung-a95'),
('Laptop HP Pavilion i7 10th',		2, 1,  19250000, 1, 'laptop-hp-pavilion.jpg', 'Laptop HP Pavilion core i7 10th', 'laptop-hp-pavilion-i7-10th'),
('Laptop DELL Inspirion i5 gen 8', 2, 3,  13450000, 1, 'laptop-dell-inspirion.jpg', 'Laptop DELL Inspirion', 'laptop-dell-inspirion-i5-gen-8'),
('Iphone 12 Pro max 256GB',		1, 2,  37000000, 1, 'iphone-12-pro-max.jpg', 'Iphone xịn', 'iphone-12-pro-max-256gb'),
('Oppo Reno 4',					1, 15, 13270000, 1, 'oppo-reno-4.png', 'Điện thoại Oppo', 'oppo-reno-4'),
('San pham demo 1',				1, 15, 13270000, 1, 'oppo-reno-4.png', 'Điện thoại Oppo', 'san-pham-demo-1'),
('San pham demo 2',				1, 15, 13270000, 1, 'oppo-reno-4.png', 'Điện thoại Oppo', 'san-pham-demo-2'),
('San pham demo 3',				1, 15, 13270000, 1, 'oppo-reno-4.png', 'Điện thoại Oppo', 'san-pham-demo-3');