program pointertest;

	type rec = record
		a       : integer;
                      b : array[1..5] of integer
	end;

                   var r : rec;
                      i  : integer;

        begin
           for i:=1 to 5 do
           begin
              r.b[i] := i
           end;

           for i:=1 to 5 do
           begin
              putint(r.b[i]);
              putch(chr(10))
           end
end.


