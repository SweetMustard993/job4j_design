create table students(id serial primary key, name varchar (255), age bigint, course text);
insert into students (name, age, course) values ('Bogdan', 29, 'Computer sciense');
update students set age = 30;
delete from students;