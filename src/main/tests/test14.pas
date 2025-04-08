program Test;

class Human:
    public var name: string;
    public var age: integer;

    public constructor Human(var name: string);
        begin
            name := name;
        end;

    function doStuff: integer;
        begin
            age := 18
        end;
end;

var John: Human;
var height: integer;
var x: integer;
begin
    John := Human.Human('John');
    John.age := 18;

    for x:=1 to 10 do
        BEGIN
            IF x = 3 THEN
                BREAK;
            writeln(age);
        END;

    writeln(John.age);

end.