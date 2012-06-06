program randomgenerator;
    
    var 
        randomSeed:integer;

    function random():integer;
        {function abs(a:integer):integer;
        begin if a<0 then abs := -a else abs := a end;}
        function power(a:integer;b:integer):integer;
            var i : integer; p  : integer;
        begin p := 1; for i:=1 to b do p := p*a; power := p end;
        function modulo(i:integer;m:integer):integer;
        begin modulo := i-(i div m)*m end;
    begin
        randomSeed := modulo(615949*randomSeed + 797807, power(2,20));
        {random := modulo(abs(randomSeed - power(2,19)),31);}
        random := randomSeed - power(2,19)
    end;

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
                a^.b[i] := i;{random();}
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

