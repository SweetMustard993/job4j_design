create table roles (
id serial primary key,
name varchar(255) unique
);

create table users (
id serial primary key,
name varchar(255),
role_id int references roles(id)
);


create table rules (
id serial primary key,
rule text unique
);

create table roles_rules (
id serial primary key,
role_id int references role (id),
rule_id int references rule (id)
);

create table categories (
id serial primary key,
name varchar(255) unique
);

create table states (
id serial primary key,
name varchar(255) unique
);

create table items (
id serial primary key,
header text,
users_id int references users(id),
category_id int references categories(id),
state_id int references states(id)
);

create table comments (
id serial primary key,
description text,
item_id int references items(id)
);

create table attachs (
id serial primary key,
attach text,
item_id int references items(id)
);


