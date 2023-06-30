create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price * 1.2
        where id in (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax();


create or replace function tax2()
    returns trigger as
$$
    BEGIN
        new.price = new.price * 1.2;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger2
    before insert on products
    for each row
    execute procedure tax2();


create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);


create or replace function save()
	returns trigger as
$$
	begin
		insert into history_of_price(name,price,date) values (new.name, new.price, current_date);
		return new;
	end;
$$
language 'plpgsql';

create trigger history_save
	after insert on products
	for each row
	execute procedure save();

