program queens;
    
    type
        rec = record
            a:integer;
            b:integer
        end;


    function a():array[1..3] of integer;
        var
            i:integer;
            r:rec;
    begin
        i:= 33;
        a[3] := 3
    end;
begin
    putint(a()[2])
end.

