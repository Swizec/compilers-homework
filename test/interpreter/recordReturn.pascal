program queens;
    
    type
        rec = record
            a:integer;
            b:integer
        end;


    function a():rec;
    begin
        a.b := 3
    end;
begin
    putint(a().b)
end.

