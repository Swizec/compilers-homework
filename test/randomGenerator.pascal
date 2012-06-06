program quicksort;

	var randomSeed:integer;
	
	function random():integer;

		function power(a:integer;b:integer):integer;
			var i:integer;
		begin
			power := 1;
			for i:=1 to b do
				power := power*a		
		end;
		
		function modulo(i:integer;m:integer):integer;
		begin
			modulo := i-(i div m)*m
		end;

	begin
		randomSeed := modulo(615949*randomSeed + 797807, power(2,20));
		random := randomSeed - power(2,19)
	end;
begin
	randomSeed := 0;
	putint(random());
	putch(chr(10));
	putint(random());
	putch(chr(10));
	putint(random());
	putch(chr(10))
end.

{
This is Pascal compiler:
273519
-153582
450905
:-) Done.
}
