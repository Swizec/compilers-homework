program randomgenerator;
    
    const arrSize = 20;

    var 
        i:integer;
        a:array[1..arrSize] of integer;

    procedure printArr(b:integer;a:array[1..arrSize] of integer);
        var i:integer;
    begin
        putint(a[3]);
        putint(b)
    end;
begin
    a[3] := 4;
    
    putint(a[3]);
    printArr(5,a)

end.

{
This is Pascal compiler:
44
:-) Done.
}
