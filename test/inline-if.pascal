
program inline;

var
   a : integer;
   b : boolean;

function i():integer;
begin
   i := 5;
end;

begin
   b := true;
   a := b ? 1 : i();

   putint(a);
   putch(chr(10));
end.
