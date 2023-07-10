CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

insert into customers(first_name, last_name, age, country) values ('qq', 'qqq', 21, 'RF');
insert into customers(first_name, last_name, age, country) values ('zz', 'zzz', 31, 'KR');
insert into customers(first_name, last_name, age, country) values ('aa', 'aaa', 29, 'RB');
insert into customers(first_name, last_name, age, country) values ('ss', 'sss', 24, 'RF');
insert into customers(first_name, last_name, age, country) values ('vv', 'vvv', 41, 'KR');
insert into customers(first_name, last_name, age, country) values ('tt', 'ttt', 55, 'RB');


select * from customers
where customers.age = (select min(age) from customers);


CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);


insert into orders(amount, customer_id) values (1800, 1);
insert into orders(amount, customer_id) values (2800, 2);
insert into orders(amount, customer_id) values (3800, 1);


select * from customers
where customers.id not in (select orders.customer_id from orders);