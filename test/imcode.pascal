
program imcode;

var a  : integer;
   arr : array[1..5] of integer;

procedure bla(a : integer; d:array[1..5] of integer; c:boolean;);
const b = 5;
   function foo(e : integer; ):char;
   begin
      a := a+5;
      foo := 'b';
   end;
begin
end; { bla }

function foo(i : integer): boolean;
begin
   foo := true;
end;


begin
   for a := 5 to 100 do
   begin
      while foo(a) do
         bla(a, arr, true);
   end
end.
