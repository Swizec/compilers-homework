program singletest;

var
   i : single integer;

procedure A();
var
   b : single char;
   procedure B();
   begin
      b := 'd';
   end;
begin
   {b := 'a';}
   B();
   b := 'z';
   putch(b);
end;

begin
   A();
   i := 20;
   putint(i);
end.
