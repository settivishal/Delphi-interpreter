Program Tes;

class Car:
    public constructor Cons(var y: integer);
        begin
            y := 1;
        end;

    public destructor Desc;
        begin
            writeln('destructor');
        end;
    private var size: integer;
    public function myFunction(var x: integer): integer ;
        begin
            x := 1;
        end;

    public procedure myProcedure(var x: integer);
        begin
            x := 1
        end;
end;

var myCar: Car;
var z: integer;
begin
    myCar := Car.Cons(1);
    z := myCar.myFunction(5);
    myCar.size := 5;
    myCar.myProcedure(1);
    myCar.Desc;
end.