PROGRAM Test;
VAR x: INTEGER;
BEGIN
    for x:=1 to 10 do
        BEGIN
            writeln(x);
            IF x = 3 THEN
                BREAK;  (* Skip printing when x = 3 *)
        END;
END.