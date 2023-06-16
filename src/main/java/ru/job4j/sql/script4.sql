create table client(
id serial primary key,
name varchar(255),
age int,
sex boolean
);

create table adress (
id serial primary key,
adress text,
client_id int references client(id)
);

insert into client (name, age, sex) values ('BB', 21, true);
insert into client (name, age, sex) values ('GG', 22, true);
insert into client (name, age, sex) values ('CC', 29, false);;
insert into client (name, age, sex) values ('EE', 59, true);
insert into client (name, age, sex) values ('RR', 34, null);

insert into adress (adress, client_id) values ('msk',1);
insert into adress (adress, client_id) values ('spb',2);
insert into adress (adress, client_id) values ('spb',3);
insert into adress (adress, client_id) values ('msk',4);
insert into adress (adress, client_id) values ('tul',5);
insert into adress (adress, client_id) values ('tul',1);
insert into adress (adress, client_id) values ('rzn',2);
insert into adress (adress, client_id) values ('rzn',3);
insert into adress (adress, client_id) values ('chl',4);
insert into adress (adress, client_id) values ('chl',5);

select cl.name, cl.sex, ad.adress
from client cl join adress ad on cl.id = ad.client_id;

select cl.name as "Имя покупателя", ad.adress адрес, cl.sex as "Пол покупателя"
from client cl join adress ad on cl.id = ad.client_id;

select cl.name as "Имя покупателя", ad.adress адрес, cl.sex as "Пол покупателя"
from client cl join adress ad on cl.id = ad.client_id;

select cl.name as Имя, ad.adress as адрес, cl.sex as Пол
from client cl join adress ad on cl.id = ad.client_id;