program queens;
const
   chessboard_size = 8;
type
   chessboard = record
                   num_queens : integer;
                   used_cols  : array [1..chessboard_size] of integer;
                   free_lins  : array [1..chessboard_size] of boolean
                end;

procedure printA(arr : array[1..chessboard_size] of integer);
var i : integer;
begin
   putch('A');
   putch(':');
   putch(' ');
   for i := 1 to chessboard_size do
      putint(arr[i]);
   putch(chr(10))
end; { printA }

procedure printB(arr : array[1..chessboard_size] of boolean);
var i : integer;
begin
   putch('B');
   putch(':');
   putch(' ');
   for i := 1 to chessboard_size do
      if arr[i] then
         putint(1)
      else
         putint(0);
   putch(chr(10))
end; { printA }

procedure debug(c : char);
begin
   putch(c);
   putch(chr(10))
end; { debug }

procedure solver();
var
   num_solutions    : integer;
   empty_chessboard : chessboard;
   i                : integer;
   procedure solve(current_chessboard: ^chessboard);
   var
      i  : integer;
      j  : integer;
      ok : boolean;
   begin
      if current_chessboard^.num_queens = chessboard_size then
      begin
         printA(current_chessboard^.used_cols);
         printB(current_chessboard^.free_lins);
         for j := 1 to chessboard_size do
         begin
            for i := 1 to chessboard_size do
               if current_chessboard^.used_cols[i] = j
                  then putch('*')
               else putch('.');
            putch(chr(10))
         end;
         debug('S');
         num_solutions := num_solutions + 1
      end
      else
      begin
         for j := 1 to chessboard_size do
            if current_chessboard^.free_lins[j] then
            begin
               ok := true;
               for i := 1 to current_chessboard^.num_queens do
                  if (current_chessboard^.num_queens + 1) - i = (current_chessboard^.used_cols[i] - j) or
                     (current_chessboard^.num_queens + 1) - i = (j - current_chessboard^.used_cols[i]) then
                     ok := false;
               if ok then
               begin
                  current_chessboard^.num_queens := current_chessboard^.num_queens + 1;
                  current_chessboard^.used_cols[current_chessboard^.num_queens] := j;
                  current_chessboard^.free_lins[j] := false;
                  solve(current_chessboard);
                  current_chessboard^.free_lins[j] := true;
                  current_chessboard^.used_cols[current_chessboard^.num_queens] := 0;
                  current_chessboard^.num_queens := current_chessboard^.num_queens - 1
               end
            end
      end
   end;
begin
   num_solutions := 0;
   for i := 1 to chessboard_size do
   begin
      empty_chessboard.used_cols[i] := i;
      empty_chessboard.free_lins[i] := true
   end;
   printA(empty_chessboard.used_cols);
   printB(empty_chessboard.free_lins);
   debug('S');
   empty_chessboard.num_queens := 0;
   solve(^empty_chessboard);
   putint(num_solutions)
end; { solver }

begin
   solver()
end.
