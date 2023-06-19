create table departments(
id serial primary key,
name varchar(255)
);


create table employees(
id serial primary key,
name varchar(255),
id_department int references departments(id)
);

insert into departments(name) values ('sales');
insert into departments(name) values ('development');
insert into departments(name) values ('quality');
insert into departments(name) values ('finance');


insert into employees(name, id_department) values ('dg',1);
insert into employees(name, id_department) values ('bv',2);
insert into employees(name, id_department) values ('vv',3);
insert into employees(name, id_department) values ('bb',3);
insert into employees(name, id_department) values ('rr',1);
insert into employees(name, id_department) values ('pp',2);


select *
from departments d left join employees e on d.id = e.id_department;

select *
from departments d right join employees e on d.id = e.id_department;

select *
from departments d full join employees e on d.id = e.id_department;

select *
from departments d cross join employees;

select *
from departments d left join employees e on d.id = e.id_department
where e.name is null;


select *
from departments d left join employees e on d.id = e.id_department
where e.name is not null;

select *
from departments d right join employees e on d.id = e.id_department;


create table teens(
id serial primary key,
name varchar(255),
gender varchar(255)
check (gender='male' or gender='female')
);


insert into teens(name, gender) values ('vv','female');
insert into teens(name, gender) values ('kk','male');
insert into teens(name, gender) values ('zv','female');
insert into teens(name, gender) values ('rk','male');
insert into teens(name, gender) values ('ss','female');
insert into teens(name, gender) values ('ln','male');
insert into teens(name, gender) values ('wq','female');
insert into teens(name, gender) values ('nm','male');
insert into teens(name, gender) values ('gl','female');
insert into teens(name, gender) values ('as','male');
insert into teens(name, gender) values ('mr','female');


select t1.name, t1.gender, t2.name, t2.gender, (t1.gender, t2.gender)
from teens t1 cross join teens t2
where (t1.gender, t2.gender)=('male','female');
