program queens;
    const
        chessboard_size = 8;
    type
        chessboard = record
                num_queens : integer;
                used_cols : array [1..chessboard_size] of integer;
                free_lins : array [1..chessboard_size] of boolean
            end;
    procedure solver();
        var
            num_solutions : integer;
            empty_chessboard : chessboard;
            i : integer;
        procedure solve(current_chessboard : ^chessboard);
            var
                i : integer;
                j : integer;
                ok : boolean;       
        begin
            if current_chessboard^.num_queens = chessboard_size then
                begin
                    for j := 1 to chessboard_size do
                        begin
                            for i := 1 to chessboard_size do
                                if current_chessboard^.used_cols[i] = j
                                    then putch('*')
                                    else putch('.');
                            putch(chr(10))
                        end;
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
                empty_chessboard.used_cols[i] := 0;
                empty_chessboard.free_lins[i] := true
            end;
        empty_chessboard.num_queens := 0;
        solve(^empty_chessboard);
        putint(num_solutions)
    end;
begin
    solver()
end.