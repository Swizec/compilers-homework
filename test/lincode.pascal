
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
   if (current_chessboard^.num_queens + 1) - i = (current_chessboard^.used_cols[i] - j) or
      (current_chessboard^.num_queens + 1) - i = (j - current_chessboard^.used_cols[i]) then
      ok := false;
end.
