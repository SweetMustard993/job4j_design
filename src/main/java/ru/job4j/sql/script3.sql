create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);
insert into fauna (name, avg_age, discovery_date) values ('cat', 5475, '1760-04-01');
insert into fauna (name, avg_age, discovery_date) values ('dog', 6205,  '1920-08-01');
insert into fauna (name, avg_age, discovery_date) values ('kichen', 365,  '18490-01-01');
insert into fauna (name, avg_age, discovery_date) values ('wolf', 6570,  '1948-09-01');
insert into fauna (name, avg_age, discovery_date) values ('bear', 10950,  '1270-04-01');
insert into fauna (name, avg_age, discovery_date) values ('goat', 6407,  '1720-02-01');
insert into fauna (name, avg_age, discovery_date) values ('cow', 8715,  '2008-09-01');
insert into fauna (name, avg_age, discovery_date) values ('duck', 365,  '2002-05-01');
insert into fauna (name, avg_age, discovery_date) values ('fish', 970, null);
insert into fauna (name, avg_age, discovery_date) values ('swordfish', 1097,  '1920-07-01');
insert into fauna (name, avg_age, discovery_date) values ('rainbowfish', 9870,  '1720-12-01');

select * from fauna where name like '%fish%';
select * from fauna where avg_age>10000 and avg_age<21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date<'1950-01-01';