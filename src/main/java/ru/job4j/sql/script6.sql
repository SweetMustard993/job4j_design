create table type(
id serial primary key,
name varchar(255));


create table product(
id serial primary key,
name varchar(255),
type_id int references type(id),
expired_date date,
price float);


insert into type (name) values ('сыр');
insert into type (name) values ('молоко');
insert into type (name) values ('колбаса');


insert into product (name, type_id, expired_date, price) values ('эменталь', 1, '2023-08-12', 1000);
insert into product (name, type_id, expired_date, price) values ('голандский', 1, '2023-07-12', 1200);
insert into product (name, type_id, expired_date, price) values ('швейцарский', 1, '2023-09-12', 3000);
insert into product (name, type_id, expired_date, price) values ('топленое', 2, '2023-06-25', 100);
insert into product (name, type_id, expired_date, price) values ('обезжиренное', 2, '2023-06-12', 70);
insert into product (name, type_id, expired_date, price) values ('отборное', 2, '2023-05-12', 85);
insert into product (name, type_id, expired_date, price) values ('сервелат', 3, '2023-08-12', 1000);
insert into product (name, type_id, expired_date, price) values ('салями', 3, '2023-09-12', 1100);
insert into product (name, type_id, expired_date, price) values ('миланская', 3, '2023-08-12', 1800);




select p.name as "название продукта"
from product p join type t on p.type_id=t.id
where t.name='сыр';


select p.name as "название продукта"
from product p
where p.name='%мороженное%';


select p.name as "название продукта"
from product p
where p.expired_date<current_date;


select p.name as "название продукта", max(price)
from product p
group by p.name;


select t.name, count(type_id) as "количество продуктов"
from type t join product p on t.id=p.type_id
group by t.name;


select p.name as "название продукта"
from product p join type t on p.type_id=t.id
where t.name='сыр' or t.name'молоко';

select t.name, count(type_id) as "количество продуктов"
from type t join product p on t.id=p.type_id
group by t.name
having count(type_id)<10;


select t.name as "тип продукта", p.name as "название продукта"
from type t join product p on t.id=p.type_id;