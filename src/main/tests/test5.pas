program test;

Class Mobile:
    public var name: string;
    public var os: string;

    private var price: integer;

    constructor Create(var name: name; var os: string);
        begin
            name := name;
            os := os;
        end;

    procedure setPrice(var price: integer);
        begin
            price := price;
        end;

    function getPrice: integer;
        begin
            writeln(price);
            getPrice := price;
        end;

end;


var MyMobile: Mobile;
var cost: integer;

begin
    MyMobile := Mobile.Create('Apple 15 Pro', 'Apple');
    MyMobile.setPrice(2000);

    cost := MyMobile.getPrice;

end.
