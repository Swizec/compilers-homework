program quicksort;
    
    var randomSeed:integer;

    procedure run();
        const arrSize = 20;

        type arr = array[1..arrSize] of integer;
        var 
            i:integer;
            a:arr;

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
            random := modulo(abs(randomSeed - power(2,19)),100)
        end;

        procedure printArr(a:arr);
            var i:integer;
        begin
            for i:=1 to arrSize do
            begin
                putint(a[i]);
                putch(' ')
            end;
            putch(chr(10))
        end;
    
        function testArray(x:arr):boolean;
            var i:integer;
                b:boolean;   
        begin
            b:=true;
            for i:=2 to arrSize do
                if x[i-1]>x[i] then
                    b:= false;
            testArray := b
        end;
        
        procedure quicksort(x:^arr ; f:integer ; t:integer);
            var
                part:integer;
            function partition(x:^arr ; f:integer ; t:integer):integer;
                var left:integer;
                    right:integer;
                    pivot:integer;
                    p:integer;
                procedure swap(x:^arr ; ii:integer ; jj:integer);
                    var t:integer;
                begin
                    t := x^[ii];
                    x^[ii] := x^[jj];
                    x^[jj] := t
                end;
            begin
                left:=f+1;
                right:=t;
                pivot:=x^[f];
                while left <= right do 
                begin
                    while x^[left] < pivot do left:=left+1;
                    while x^[right] > pivot do right:=right-1;
                    if left<right then 
                        swap(x,left,right);
                    left:=left+1;
                    right:=right-1
                end;
                p:= (right+left) div 2;
                swap(x,f,p);
                partition:=p
            end;
        begin
            if f<t then
            begin
                part:=partition(x,f,t);
                quicksort(x,f,part-1);
                quicksort(x,part+1,t)
            end
        end;
    begin
        for i:=1 to arrSize do
            a[i] := random();
        if arrSize < 50 then printArr(a);
        quicksort(^a,1,arrSize);
        if arrSize < 50 then printArr(a);

        putch(chr(10));
        if not testArray(a) then
        begin
            putch('n');
            putch('o');
            putch('t');
            putch(' ')
        end;
        putch('s');
        putch('o');
        putch('r');
        putch('t');
        putch('e');
        putch('d');
        putch(chr(10))
    end;

begin
    run()
end.

{
This is Pascal compiler:
19 82 5 68 53 42 45 28 45 94 3 44 69 94 63 28 81 66 9 96 77 30 11 36 65 78 35 72 57 98 
3 5 9 11 19 28 28 30 35 36 42 44 45 45 53 57 63 65 66 68 69 72 77 78 81 82 94 94 96 98 
:-) Done.
}
