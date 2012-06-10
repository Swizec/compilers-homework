program queens;
    
    var recursive:integer;

    procedure solver();
        var
            num_solutions : integer;

        procedure solve();
            var
                i : integer;
        begin
            i := 3 ;
            
            putint(num_solutions);

            if recursive < 1 then
                begin
                    recursive := recursive+1;
                    solve()
                end
        end;
    begin
        solve()
    end;
begin
    solver()
end.

