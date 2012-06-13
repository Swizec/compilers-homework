
program records;

type
   r = record
          a  : integer;
          d  : integer;
          dd : integer;
          b  : array[1..5] of integer;
          c  : integer
       end;
var
   a : ^r;
   b : r;
   i : integer;

begin
   a^.a := 65;
   putint(a^.a);
   putch(chr(10));

   b.a := 65;
   putint(b.a);
   putch(chr(10));
end.
