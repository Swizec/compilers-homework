program queens;
    
    type
        rec = record
            a:integer;
            b:integer
        end;
    function a():^rec;
        var
            rr:rec;
            r:^rec;
    begin
        a := [rec];
        a^.b := 3
    end;
begin
    putint(a()^.b)
end.

