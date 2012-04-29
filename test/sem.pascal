
program semanal;

const
   lol  = 5;
   bla  = 2;
   hah  = (-7+5)*6 div (3*bla);
   beh  = +'c';

type
   a   = integer;
   b   = boolean;
   c   = a;
   d   = array[2..5] of integer;
   e   = array[1..bla] of d;
   f   = record
            f      : integer;
            g      : d;
            h      : array[3+5..8] of array[5*3..hah+5] of boolean;
            a      : record
                        blabla : integer
                     end
         end;
   sdf = ^integer;

var
   aa : boolean;
   bb : char;
   cc : f;
   i  : integer;

procedure mew();
const
   m = 5;

   procedure bla();
   var
      m : integer;
   begin
      mew(m);
   end;
begin
end; { mew }

function ohai3(a : boolean; b : array[1..7] of integer): boolean;
   function o():char;
      function ohai3():boolean;
      begin
      end;
   begin
   end; { o }
   procedure e(a : boolean);
   begin
   end; { e }
begin
end; { ohai3 }

function ohai4(a : boolean): integer;
begin
end;



begin
   ohai3(true);
   6+4 := ohai3(false);

   if ohai3() then
      a := cc;

   if ohai3() then
      b := 'l'
   else
      if 5+3*(3+5) then
         mew()
      else
         6 := 7 div ohai4(true) * 15 div 1*5;

   while 5+23 or 5*10 do
      a := ohai4(false);

   for i := 1 to 100+false do
      while i div 6 < 6 do
         i := i+5;
end.
