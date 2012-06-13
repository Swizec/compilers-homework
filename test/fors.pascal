
program fors;

var
   a : array[1..5] of integer;
   i : integer;

begin
   for i:=1 to 5 do
   begin
      a[i] := i;
      {putint(i);}
      putch(',')
   end;

   putch(chr(10));

   for i:=1 to 5 do
   begin
      putint(a[i]);
      putch(',')
   end;

   putch(chr(10))
end.
