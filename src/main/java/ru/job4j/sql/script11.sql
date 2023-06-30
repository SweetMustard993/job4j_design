create or replace procedure delete_data(u_id integer)
language 'plpgsql'
as $$
	begin
		delete from products where id=u_id and count=0;
	end;
$$;



create or replace function f_delete_data(u_id integer)
returns integer
language 'plpgsql'
as
$$
	declare
	result integer;
	begin
		delete from products where id=u_id and count=0;
		if count = 0 from products where id=u_id then
		select into result price from products where id = u_id;
		end if;
		if count > 0 from products where id=u_id then
		select into result count from products where id = u_id;
		end if;
		return result;
	end;
$$;