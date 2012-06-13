
program pointers;

type
   r = record
          a  : integer;
          b  : boolean;
          ar : array[1..5] of integer;
          c  : char
       end;

var
   a : ^r;
   i : integer;


procedure dump(rec : r);
procedure printAr(ar : array[1..5] of integer);
   var i : integer;
   begin
      for i := 1 to 5 do
      begin
         putint(ar[i]);
         putch(' ')
      end;
      putch(chr(10))
   end; { printAr }

begin
   putint(rec.a);
   putch(chr(10));

   if rec.b then
      putch('y')
   else
      putch('n');
   putch(chr(10));

   putch(rec.c);
   putch(chr(10));

   for i := 1 to 5 do
   begin
      putint(rec.ar[i]);
      putch(' ')
   end;
   putch(chr(10));

   printAr(rec.ar)
end;

begin
   a := [r];

   a^.a := 5;
   a^.b := false;
   for i := 1 to 5 do
      a^.ar[i] := i;
   a^.c := 'b';

   putint(a^.a);
   putch(chr(10));

   if a^.b then
      putch('y')
   else
      putch('n');
   putch(chr(10));

   putch(a^.c);
   putch(chr(10));

   for i := 1 to 5 do
   begin
      putint(a^.ar[i]);
      putch(' ')
   end;
   putch(chr(10));

   {printAr(a^.ar);}

   dump(a^)
end.
