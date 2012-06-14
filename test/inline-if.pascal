
program inline;

var
   a : integer;
   b : boolean;

begin
   b := true;
   a := b ? 1 : 2;

   putint(a);
   putch(chr(10));
end.
