program pointertest;
	
	var 
		i:integer;
		j:integer;
		rand:integer;
	 	k:array[2..6] of integer;
		
	function random():integer;
		function modulo(a:integer;b:integer):integer;
		begin
			modulo := (a-a div b * b)
		end;
		function abs(a:integer):integer;
		begin
			if a<0 then
				abs:= -a
			else
				abs:= a
		end;
	begin
		rand := modulo((615949*rand + 797807),1024*1024);
		random:=abs(modulo(rand-524288,23))
	end;
begin	
	
	while j<20 do
		for i:= 0 to 5 do
		begin
			j:=j+1;
			putint(i);
			putch(':');
			putch(' ');
			putch(' ');
			putint(random());
			putch(chr(ord(chr(10))*10-90))
		end;
		
	putch(chr(10))
end.

{
This is Pascal compiler:
0:  3
1:  11
2:  13
3:  4
4:  2
5:  21
0:  4
1:  6
2:  10
3:  19
4:  17
5:  13
0:  10
1:  3
2:  19
3:  2
4:  1
5:  12
0:  6
1:  16
2:  11
3:  18
4:  20
5:  16

:-) Done.
}
