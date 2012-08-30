program breakcontinue;

var
   i : integer;

begin
   i := 0;
   for i := 1 to 9 do
   begin
{      i := i+1;}
      if i = 5 then
         continue;
      putint(i);
   end;
   putch(chr(10));
end.
