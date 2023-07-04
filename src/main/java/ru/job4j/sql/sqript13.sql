create table students (
id serial primary key,
name varchar(255),
level integer,
average_score float
);

insert into students (name, level, average_score) values ('vv', 4, 4.0);
insert into students (name, level, average_score) values ('ff', 3, 3.9);
insert into students (name, level, average_score) values ('zz', 4, 2.9);



begin transaction isolation level repeatable read;
select * from students;
insert into students (name, level, average_score) values ('gg', 3, 3.7);
savepoint point_first;
update students set level = 4 where name = 'ff';
select * from students;
savepoint point_second;
delete from students;
select * from students;
rollback to point_second;
select * from students;
rollback to point_first;
commit;

