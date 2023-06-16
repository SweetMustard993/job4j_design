create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('x677', 10000);
insert into devices(name, price) values ('x855', 760);
insert into devices(name, price) values ('x647', 970);
insert into devices(name, price) values ('x937', 15000);
insert into devices(name, price) values ('x617', 690);
insert into devices(name, price) values ('x667', 1100);

insert into people(name) values ('bf');
insert into people(name) values ('vg');
insert into people(name) values ('zt');
insert into people(name) values ('lm');
insert into people(name) values ('bb');
insert into people(name) values ('aq');

insert into devices_people(device_id, people_id) values (1,6);
insert into devices_people(device_id, people_id) values (2,5);
insert into devices_people(device_id, people_id) values (3,4);
insert into devices_people(device_id, people_id) values (4,3);
insert into devices_people(device_id, people_id) values (5,2);
insert into devices_people(device_id, people_id) values (6,1);
insert into devices_people(device_id, people_id) values (2,1);
insert into devices_people(device_id, people_id) values (6,4);
insert into devices_people(device_id, people_id) values (5,1);
insert into devices_people(device_id, people_id) values (1,3);
insert into devices_people(device_id, people_id) values (2,3);
insert into devices_people(device_id, people_id) values (4,1);

select avg(price) from devices;

select p.name, avg(d.price)
from devices_people dp join people p on p.id=dp.people_id
join devices d on d.id=dp.device_id
group by p.name;

select p.name, avg(d.price)
from devices_people dp join people p on p.id=dp.people_id
join devices d on d.id=dp.device_id
group by p.name
having avg(d.price)>5000;
