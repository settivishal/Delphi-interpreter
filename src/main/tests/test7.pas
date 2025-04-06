PROGRAM Test;
VAR x: INTEGER;
BEGIN
    for x:=1 to 10 do
        BEGIN
            IF x = 3 THEN
                  CONTINUE;  (* Skip printing when x = 3 *)

            IF x = 7 THEN
              BREAK;
        END;
END.