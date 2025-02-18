program Test;

class Human:
    public var name: string;
    public var age: integer;

    public constructor Human(var name: string);
        begin
            name := name;
        end;

end;

var John: Human;
var age: integer;
begin
    John := Human.Human('John');
    age := 18;
    writeln(age);

end.