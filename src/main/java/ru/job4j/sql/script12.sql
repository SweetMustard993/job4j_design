create table students (
id serial primary key,
name varchar(255),
level integer,
average_score float
);

insert into students (name, level, average_score) values ('vv', 4, 4.0);
insert into students (name, level, average_score) values ('ff', 3, 3.9);
insert into students (name, level, average_score) values ('zz', 4, 2.9);

begin transaction;
select * from students;
insert into students(name, level, average_score) values ('rr', 2, 4.9);
delete from students where average_scrore<3.0;
update students set average_scrore = 4.1 where name='ff';
select * from students;
commit;

begin transaction;
select * from students;
select * from students;
select * from students;
rollback;

delete from students;
alter sequence students_id_seq restart with 1;

insert into students (name, level, average_score) values ('vv', 4, 4.0);
insert into students (name, level, average_srore) values ('ff', 3, 3.9);
insert into students (name, level, average_srore) values ('zz', 4, 2.9);


begin transaction isolation level repeatable read;
select * from students;
insert into students(name, level, average_score) values ('rr', 2, 4.9);
delete from students where average_scrore<3.0;
update students set average_scrore = 4.1 where name='ff';
select * from students;
commit;


begin transaction isolation level repeatable read;
select * from students;
update students set average_scrore = 4.1 where name='ff';
rollback;

delete from students;
alter sequence students_id_seq restart with 1;

insert into students (name, level, average_score) values ('vv', 4, 4.0);
insert into students (name, level, average_score) values ('ff', 3, 3.9);
insert into students (name, level, average_score) values ('zz', 4, 2.9);


begin transaction isolation level serializable;
select avg(average_score) from students;
update students set average_score = 4.1 where name='ff';
commit;


begin transaction isolation level serializable;
select avg(average_score) from students;
update students set average_score = 4.1 where name='ff';
rollback;

