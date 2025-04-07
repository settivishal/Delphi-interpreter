PROGRAM Test;
VAR x: INTEGER;
BEGIN
    for x:=1 to 10 do
        BEGIN
            IF x = 3 THEN
                CONTINUE;
            writeln(x);
        END;
END.