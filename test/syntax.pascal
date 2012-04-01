
program hello;

const
   lol = 5;
   bla = 6;
   hah = 7;

type
   a = integer;
   b = boolean;
   c = a;
   d = array[2..5] of integer;
   e = array[1..3] of d;
   f = record
          f : integer;
          g : e;
       end;
   h = ^f;
   i = (integer);

var
   eh  : integer;
   meh : d;

procedure mew();
begin
end;

procedure hello2(a : integer);

   procedure a(b : boolean );
   begin
   end; { a }

begin
end; { hello2 }

procedure hello();
const
   bla = 6;
var
   a : f;
begin
end; { hello }

procedure hello3(a : integer; b : boolean);
type
   d = array[1..6] of boolean;
begin
end; { hello3 }


begin
end.
