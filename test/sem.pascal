
program semanal;

const
   lol  = 5;
   bla  = 5;
   hah  = 7;
   beh  = +20;

type
   a    = integer;
   b    = boolean;
   c    = a;
   d    = array[1..7] of integer;
   e    = array[1..bla] of d;
   f    = record
             f      : integer;
             g      : d;
             h      : array[3+5..8] of array[5*3..hah+5] of boolean
          end;
   sdf  = ^integer;

var
   aa : boolean;
   bb : d;
   cc : f;
   i  : integer;

procedure mew(a :integer );
const
   m = 5;

   procedure bla();
   var
      m : integer;
   begin
      mew(m);
   end;
begin
end;

function ohai3(a : boolean; b : array[1..7] of integer): boolean;
   function o():char;
      function ohai3():boolean;
      begin
      end;
   begin
   end;
   procedure e(a : boolean);
   begin
   end;
begin
end;

function ohai4(a : boolean): integer;
begin
end;



begin
   ohai3(true, bb);

   aa := (6+4 = 5) and false or true;
   i := bb[2];

   aa := ohai3(false, bb);

   if ohai3(false, bb) then
      a := a;

   if ohai3(false, bb) then
      b := aa
   else
      mew(5);
   if 5+3*(3+5) = 3 then
      mew(5)
   else
      6 := 7 div ohai4(true) * 15 div 1*5;

   while false do
      a := ohai4(false);

   for i := 1 to 100 do
      while ohai3(true, bb) do
         i := i+5;

   a := [integer];
end.
