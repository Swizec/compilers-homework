
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

procedure dump(rec : ^r);
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

   printAr(rec.ar);
end;

begin
   a := [r];

   a^.a := 5;
   a^.b := false;
   a^.c := 'b';
   for i := 1 to 5 do
      a^.ar[i] := i;

   dump(a);
end.
