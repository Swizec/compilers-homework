
program abstree;

type
   a = integer;
   b = boolean;
   c = a;
   d = array[2..5] of integer;
   e = array[1..3] of d;
   f = record
          f : integer;
          g : e;
          h : array[3+5..8] of array[5*3..a+5] of boolean
       end;
   h = ^f;
   i = (integer);

var
   a : boolean;
   b : char;
   c : integer;

begin
end.
