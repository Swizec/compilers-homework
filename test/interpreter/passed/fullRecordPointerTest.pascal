program randomgenerator;

    type
        r= record
            a:integer;
            d:integer;
            dd:integer;
            b:array[1..5] of integer;
            c:integer
        end;
    var
       a : ^r;
       b : r;
       i : integer;

    procedure printptrR(x:^r);
    begin
        putch(chr(10));
        putch(chr(10));
        putint(x^.a);
        putch(chr(10));
        putint(x^.c);
        putch(chr(10));
        putint(x^.d);
        putch(chr(10));
        putint(x^.dd);
        putch(chr(10));
        for i:= 1 to 5 do
        begin
            putint(x^.b[i]);
            putch(' ')
        end;
        putch(chr(10));
        putch(chr(10))
    end;

    procedure printR(x:r);
    begin
        putch(chr(10));
        putch(chr(10));
        putint(x.a);
        putch(chr(10));
        putint(x.c);
        putch(chr(10));
        putint(x.d);
        putch(chr(10));
        putint(x.dd);
        putch(chr(10));
        for i:= 1 to 5 do
        begin
            putint(x.b[i]);
            putch(' ')
        end;
        putch(chr(10));
        putch(chr(10))
    end;

    procedure printA(x:array[1..5] of integer);
    begin
        putch(chr(10));
        putch(chr(10));
        for i:= 1 to 5 do
        begin
            putint(x[i]);
            putch(' ')
        end;
        putch(chr(10));
        putch(chr(10))
    end;


begin
   a := ^b;

   a^.a:= 65;
   a^.dd:= 68;
   a^.c:= 67;
   a^.b[1] := 1;
   a^.b[2] := 2;
   a^.b[3] := 3;
   a^.b[4] := 4;
   a^.b[5] := 5;

   putint(65);
   putch(',');
   putint(a^.a);
   putch(chr(10));

   putint(67);
   putch(',');
   putint(a^.c);
   putch(chr(10));

   putint(0);
   putch(',');
   putint(a^.d);
   putch(chr(10));

   putint(68);
   putch(',');
   putint(a^.dd);
   putch(chr(10));

   for i:= 1 to 5 do
   begin
      putint(a^.b[i]);
      putch(' ')
   end;

    printptrR(a);
    printR(a^);
    printA(a^.b)
end.















