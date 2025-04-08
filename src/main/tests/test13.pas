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
begin
    John := Human.Human('John');
    writeln(age);
end.