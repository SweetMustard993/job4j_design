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

create table order (
id serial primary key,
name varchar(255),
telephone text references buyer(telepone)
);


create table order_list (
order_id int references order (id) not null,
goods_id int references goods (id) not null,
count int,
primary key (order_id, goods_id)
);

create table goods (
id serial ptimay key,
name varchar(255),
price decimal(10,2)
);
