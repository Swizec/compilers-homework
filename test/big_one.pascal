program mojProgram;
var
        i,j,k : integer;
		a : boolean;
		b : char;
		f: array[0..8] of integer;
		p : ^integer;

function fact(a:integer): integer;
begin
	if a = 1 then
		fact := 1
	else
		fact := a*fact(a-1);
end;

begin
    i := 12;
    {ble{ ble} bleeee
	writeln('a');
	}
	writeln('b');
    j := -4;
    k := i+j;
    repeat f[k] := fact(k);
		writeln(fact(k));
		k := k-1;

	until k <= 0;

	b:='a';
	a:=false;
	for i := 0 to 10 do
		if i < 6 then
			begin
				writeln(a);
				a := not a;
				if a then begin k:=95; j:=-3; end else k:=-2;
				while (j > -9) and (j < -1) do
					begin
						writeln(j);
						case k of
							95: j := j-1;
							-2: j:=j+1
						end;
					end;
			end
		else if i > 9 then
			writeln(b)
		else
			writeln(i);
	writeln('k');
	for i:=0to 9do
	writeln(f[i]*1000*1000+i);
end.
