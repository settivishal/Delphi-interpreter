PROGRAM Test;
VAR x: INTEGER;
VAR i: INTEGER;
BEGIN
    x := 1;

    WHILE x < 8 DO
        BEGIN
            writeln(x);
            IF x = 3 THEN
                x := x + 1;
                CONTINUE;
        END;
END.