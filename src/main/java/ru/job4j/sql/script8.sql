create table car_bodies (
id serial primary key,
name varchar(255)
);


create table car_engines (
id serial primary key,
name varchar(255)
);


create table car_transmissions (
id serial primary key,
name varchar(255)
);


create table cars (
id serial primary key,
name varchar(255),
body_id int references car_bodies(id),
engine_id int references car_engines(id),
transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values ('седан');
insert into car_bodies(name) values ('кроссовер');
insert into car_bodies(name) values ('универсал');
insert into car_bodies(name) values ('хэтчбэк');
insert into car_bodies(name) values ('родстер');
insert into car_bodies(name) values ('лимузин');

insert into car_engines(name) values ('v6');
insert into car_engines(name) values ('v8');
insert into car_engines(name) values ('v10');
insert into car_engines(name) values ('v12');

insert into car_transmissions(name) values ('механическая');
insert into car_transmissions(name) values ('вариатор');
insert into car_transmissions(name) values ('гидротрасформатор');
insert into car_transmissions(name) values ('робот');
insert into car_transmissions(name) values ('дввойной робот');

insert into cars(name, body_id, engine_id, transmission_id) values ('ww1',4,1,1);
insert into cars(name, body_id, engine_id, transmission_id) values ('ww2',3,2,2);
insert into cars(name, body_id, engine_id, transmission_id) values ('so1',1,2,3);
insert into cars(name, body_id, engine_id, transmission_id) values ('mr1',4,2,4);
insert into cars(name, body_id, engine_id, transmission_id) values ('vo1',2,1,1);
insert into cars(name, body_id, engine_id, transmission_id) values ('ww3',2,1,2);
insert into cars(name, body_id, engine_id, transmission_id) values ('mr2',3,2,3);
insert into cars(name, body_id, engine_id, transmission_id) values ('so2',1,1,4);
insert into cars(name, body_id, engine_id, transmission_id) values ('vo3',1,null,2);
insert into cars(name, body_id, engine_id, transmission_id) values ('mr5',4,1,3);
insert into cars(name, body_id, engine_id, transmission_id) values ('ww6',3,2,null);
insert into cars(name, body_id, engine_id, transmission_id) values ('ro7',2,2,1);
insert into cars(name, body_id, engine_id, transmission_id) values ('vo2',1,2,2);
insert into cars(name, body_id, engine_id, transmission_id) values ('mr3',4,1,3);
insert into cars(name, body_id, engine_id, transmission_id) values ('ww3',3,2,4);
insert into cars(name, body_id, engine_id, transmission_id) values ('ro1',2,2,1);


select cars.id id, cars.name as name, b.name body_name, e.name engine_name, t.name transmission_name
from cars left join car_bodies b  on cars.body_id=b.id
left join car_engines e on  cars.engine_id=e.id
left join car_transmissions t on cars.transmission_id=t.id;

select b.id id, b.name кузов
from car_bodies b left join cars on cars.body_id=b.id
where cars.name is null;

select e.id id, e.name двигатель
from car_engines e left join cars on cars.engine_id=e.id
where cars.name is null;

select t.id id, t.name трансмиссия
from car_transmissions t left join cars on cars.transmission_id=t.id
where cars.name is null;