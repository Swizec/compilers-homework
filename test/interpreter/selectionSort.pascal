program randomgenerator;
    
    var randomSeed:integer;

    procedure run();
        const arrSize = 20;

        var 
            i:integer;
            a:array[1..arrSize] of integer;

        function random():integer;
            function abs(a:integer):integer;
            begin if a<0 then abs := -a else abs := a end;
            function power(a:integer;b:integer):integer;
                var i : integer; p  : integer;
            begin p := 1; for i:=1 to b do p := p*a; power := p end;
            function modulo(i:integer;m:integer):integer;
            begin modulo := i-(i div m)*m end;
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
            end;
            putch(chr(10))
        end;
        procedure selectionSort(x:^array[1..arrSize] of integer);
            var
                i:integer;
                j:integer;
                t:integer;
                min:integer;
        begin
            for i:=1 to arrSize do
            begin
                min := i;
                for j:=i to arrSize do
                begin
                    if x^[min]>x^[j] then
                        min := j
                end;
                t := x^[i];
                x^[i] := x^[min];
                x^[min] := t
            end
        end;
    begin
        for i:=1 to arrSize do
            a[i] := random();
        printArr(a);
        selectionSort(^a);
        printArr(a)
    end;

begin
    run()
end.

{
This is Pascal compiler:
6 8 10 8 12 26 29 27 25 18 3 15 20 15 18 1 2 27 25 3 
1 2 3 3 6 8 8 10 12 15 15 18 18 20 25 25 26 27 27 29 
:-) Done.
}
