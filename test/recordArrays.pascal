
program recordArrays;
const
   N = 8;
type
   Rec = record
            a    : integer;
            arr1 : array[1..N] of integer;
            arr2 : array[1..N] of boolean
         end;
var
   rec : Rec;
   i   : integer;

procedure printA(arr : array[1..N] of integer);
var i : integer;
begin
   putch('A');
   putch(':');
   putch(' ');
   for i := 1 to N do
      putint(arr[i]);
   putch(chr(10))
end; { printA }

{procedure printB(arr : array[1..N] of boolean);
var i : integer;
begin
   putch('B');
   putch(':');
   putch(' ');
   for i := 1 to N do
      if arr[i] then
         putint(1)
      else
         putint(0);
   putch(chr(10));
end; { printA }}


begin

   for i := 1 to N do
      rec.arr1[i] := i;

   printA(rec.arr1);
{   printB(rec.arr2);

   for i := 1 to N do
   begin
      if i > 4 then
         rec.arr2[i] := true
      else
         rec.arr2[i] := false;
   end;

   putch(chr(10));

   printA(rec.arr1);
   printB(rec.arr2);}
   for i := 1 to N do
      putint(rec.arr1[i]);

   putch(chr(10))
end.
