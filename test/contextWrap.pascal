program contextWrap;
var
   n : integer;

procedure A();
var
   N : integer;
   procedure B(i : integer);
   begin
      putint(N);
      putch(':');

      N := N+1;
      n := n+1;

      putint(N);
      putch(chr(10));

      if i > 0 then
         B(i-1)
   end;
begin
   N := 10;
   putint(N);
   putch(chr(10));
   B(3)
end;

begin
   n := 5;
   A();
   putint(n);
   putch(chr(10));
end.
