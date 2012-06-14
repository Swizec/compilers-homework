
program inline;

var
   a : integer;
   b : boolean;

function i():integer;
begin
   i := 5;
end; { i }

procedure bla();
begin
end;

begin
   b := true;
   a := b ? 1 : i();

   b ? bla() : bla();

   putint(a);
   putch(chr(10));
end.
