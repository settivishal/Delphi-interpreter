PROGRAM Test;
VAR x: INTEGER;
VAR y: INTEGER;
BEGIN
    x := 1 + 5;
    y := x + 10;
    y := 20 * y;
    x := 200 / 15;
    writeln(y);
END.