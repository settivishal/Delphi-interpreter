program Test;

class Human:
    public var name: string;
    public var age: integer;
    public var sex: string;

    public constructor Create(var name: string; var age: integer; var sex: string);
        begin
            name := name;
            age := age;
            sex := sex;
        end;

end;

var John: Human;

begin
    John := Human.Create('John', 18, 'M');
end.