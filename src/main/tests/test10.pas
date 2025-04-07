PROGRAM Test;
VAR x: INTEGER;
VAR i: INTEGER;
BEGIN
    x := 1;

    WHILE x < 8 DO
        BEGIN
            IF x = 3 THEN
                BREAK;
            writeln(x);
            x := x + 1;
        END;
END.