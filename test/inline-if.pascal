
program inline;

var
   a  : integer;
   b  : boolean;
   bb : ^boolean;

{function i():integer;
begin
   i := 5;
end; { i }

procedure bla();
begin
end;}

begin
   bb := [boolean];
   b := true;
   a := b ? 1 : 2;

{   b ? bla() : bla();}

   putint(a);
   putch(chr(10));
end.
