Program Test;

Class Book:
    public var name: string;
    public var author: string;
    private var price: integer;


    function getPrice: integer;
        begin
            writeln(price);
        end;

end;

var myBook: Book;
var name: string;

begin
    name := 'Harry Potter';
    author := 'JK Rowling';
    myBook.name := name;
    writeln(name);
end.