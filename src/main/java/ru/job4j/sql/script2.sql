create table buyer (
telephone text primary key,
name varchar(255)
);

create table adress (
id serial primary key,
city text,
street text,
postal code text,
country text,
telephone text references buyer(telephone)
);

create table orders (
id serial primary key,
name varchar(255),
telephone text references buyer(telepone)
);

create table goods (
id serial ptimay key,
name varchar(255),
price decimal(10,2)
);

create table order_list (
order_id int references orders (id) not null,
goods_id int references goods (id) not null,
count int,
primary key (order_id, goods_id)
);


create table drivers (
telephone text primary key,
name varchar(255),
car_id int references company_car (car_id) unique
);

create table company_car (
car_id primary key,
model text
);
