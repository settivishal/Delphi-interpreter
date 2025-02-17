Program Test;

Class Book:
    public var name: string;
    public var author: string;

    private var price: integer;

    constructor Create(var name: string; var author: string);
        begin
            name := name;
            author := author;
        end;

    function getPrice: integer;
        begin
            writeln(price);
            getPrice := price;
        end;

end;

var myBook: Book;
var price: integer;

begin
    myBook := Book.Create('Harry Potter', 'JK Rowling');
    price := myBook.getPrice;
end.