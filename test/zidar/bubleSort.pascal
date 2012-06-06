program randomgenerator;
    
    const arrSize = 20;

    var 
        randomSeed:integer;
        i:integer;
        a:array[1..arrSize] of integer;

    function random():integer;
        
        function abs(a:integer):integer;
        begin
            if a<0 then
                abs := -a
            else
                abs := a
        end;
        function power(a:integer;b:integer):integer;
            var i : integer;
            p  : integer;
        begin
            p := 1;
            for i:=1 to b do
                p := p*a;
            power := p
        end;

        function modulo(i:integer;m:integer):integer;
        begin
            modulo := i-(i div m)*m
        end;

    begin
        randomSeed := modulo(615949*randomSeed + 797807, power(2,20));
        random := modulo(abs(randomSeed - power(2,19)),31)
    end;

    procedure printArr(a:array[1..arrSize] of integer);
        var i:integer;
    begin
        for i:=1 to arrSize do
        begin
            putint(a[i]);
            putch(' ')
        end
    end;
begin
    for i:=1 to arrSize do
        a[i] := random();
    
    printArr(a)

end.
{
This is Pascal compiler:
6
8
10
8
12
:-) Done.
}

