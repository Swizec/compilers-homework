program a;
	var i:integer;
	function d(i:integer):integer;
		procedure f();
		begin
			putint(i)
		end;
	begin
		putint(i);
		f();
		d:=8
	end;

begin
	i:=5;
	putint(d(3))
end.
