program pointertest;
	var
		j:^integer;
	procedure neki(a:^integer);
	begin
		a^:=22
	end;
begin
	j^:=232;
	neki(j);
	putint(j^)
end.

