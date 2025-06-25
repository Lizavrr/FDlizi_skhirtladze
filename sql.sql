create database fd;

use fd;

create table users (
	id int auto_increment primary key,
    username varchar(30),
    password varchar(50),
    email varchar(100) unique,
    Gender varchar(30)
    );

create table couriers (
	id int auto_increment primary key,
    username varchar(30),
    email varchar(100) unique,
    password varchar(50)
);

create table restaurants (
	 id int auto_increment primary key,
     username varchar(30),
     email varchar(100) unique,
     password varchar(50),
     location varchar(50)
);

create table menu_items (
	id int auto_increment primary key,
    restaurant_id int,
    name varchar(50),
    price decimal(4,2),
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id)
);

create table orders (
    id int auto_increment primary key,
    restaurant_id int,
    total_price decimal(4,2),
    user_id int,
    foreign key  (user_id) references users(id),
    foreign key (restaurant_id) references restaurants(id)
);

create table order_items (
     quantity int,
     order_id int,
     menu_item_id int,
     price decimal(4,2),
     primary key(order_id, menu_item_id),
     foreign key (order_id) references orders(id),
    foreign key (menu_item_id) references menu_items(id)
);

create table supports(
	 id int auto_increment primary key,
     username varchar(20),
     password varchar(20)
);
