program randomgenerator;
    
    var 
        randomSeed:integer;

    procedure run();
        type 
            r= record
                a:integer;
                d:integer;
                dd:integer;
                b:array[1..5] of integer;
                c:integer
            end;
        var 
            a:^r;
            i:integer;
    begin
        for i:= 1 to 5 do
            begin
                a^.b[i] := i;
                putint(a^.b[i]);
                putch(chr(10))
            end;
        
        putch(chr(10));
        putch(chr(10));
        for i:= 1 to 5 do
        begin
            putint(a^.b[i]);
            putch(chr(10))
        end
    end;

begin
    run()
end.

