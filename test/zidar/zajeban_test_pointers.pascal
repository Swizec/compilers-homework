program quicksort;

	const tabSize = 20;

	var randomSeed:integer;


	procedure test();
		type
			arrstuff = array[1..tabSize] of integer;
			rec = record
				zamenjave: integer;
				primerjave: integer;
				tabela: arrstuff
			end;
		var arr:^rec;

		procedure fillArr();
			var i:integer;
		begin
			i:=1;
			while i<= tabSize do
				begin
					arr^.tabela[i] := i;
					putint(arr^.tabela[i]);
					putch(' ');
					i := i+1
				end
		end;
		procedure printRec(a:^rec);
			var i:integer;
			procedure print(a:arrstuff; index:integer);
			begin
				putint(a[index]);
				putch(' ')
			end;
		begin
			putch(chr(10));
			putch(chr(10));

			for i:=1 to tabSize do
                           print(a^.tabela, i);
                      	putch(chr(10))
		end;
	begin
		fillArr();
		printRec(arr)
	end;

begin
	test()
end.
