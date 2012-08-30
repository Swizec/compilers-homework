program breakcontinue;

var
   i : integer;

begin
   i := 0;
   for i:= 0 to 10 do
      begin
         if i >= 5 then
            break;
         putint(i);
      end;
   putint(i);
   putch(chr(10));
end.
