program queens;
    
    type
        rec = record
            a:integer;
            b:integer
        end;


    function a():rec;
        var
            r:rec;
    begin
        r.b := 3
    end;
begin
    putint(a().b)
end.

