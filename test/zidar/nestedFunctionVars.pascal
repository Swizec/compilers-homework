program a;
    var i:integer;
    function d(i:integer):integer;
        procedure f();
            var i:integer;
            procedure ff();
                procedure f();
                begin
                    putch('s');
                    putint(i)
                end;
            begin
                f()
            end;

        begin
            i:=6;
            ff()
        end;
    begin
        putint(i);
        f();
        putint(i);
        d:=8
    end;

    procedure b();
    begin
        putint(i);
        putint(d(3))
    end;
begin
    i:=5;
    b()
end.
{
This is Pascal compiler:
53s638
:-) Done.
}
