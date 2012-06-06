program randomgenerator;
    
    const arrSize = 20;

    var 
        i:integer;
        a:array[1..arrSize] of integer;

    procedure printArr(a:array[1..arrSize] of integer);
        var i:integer;
    begin
        putint(a[3])
    end;
begin
    a[3] := 4;
    
    putint(a[3]);
    printArr(a)

end.

{
This is Pascal compiler:
44
:-) Done.
}
