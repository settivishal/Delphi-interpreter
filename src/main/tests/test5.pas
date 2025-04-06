program test;

Class Mobile:
    public var name: string;
    public var os: string;
    private var price: integer;

    constructor Create(var name: name);
        begin
            name := name;
        end;

    function getPrice: integer;
        begin
            price := 100;
            price := price + 100;
            writeln(price);
        end;

end;


var MyMobile: Mobile;
var cost: integer;

begin
    MyMobile := Mobile.Create('Apple 15 Pro');
    cost := 2000;
    writeln(cost);
end.
