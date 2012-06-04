
program lincode;

const
   chessboard_size = 8;
type
   chessboard = record
                   num_queens : integer;
                   used_cols  : array [1..chessboard_size] of integer;
                   free_lins  : array [1..chessboard_size] of boolean
                end;
var
   i                  : integer;
   j                  : integer;
   ok                 : boolean;
   current_chessboard : ^chessboard;

begin
   for i:=1 to 10 do
   begin
      putint(i)
   end
end.
