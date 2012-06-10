program p2;
  function fac (n : integer) : integer;
  begin
    if (n = 1) then fac := 1 else
    if (n = 2) then fac := 1 else
    fac := fac (n - 1) + fac (n - 2)
  end;
begin
  putint(fac (10))
end.
