Program Test;

class Car:

    private var size: integer;

    public constructor Car(var y: integer);
        begin
            y := 1;
        end;

    public destructor Car;
        begin
            writeln('destructor');
        end;

end;

var myCar: Car;
var z: integer;
var a: integer;
var b: integer;

begin
    myCar := Car.Car(1);
    myCar.size := 5;
    myCar.Desc;

    a := 10;
    b := a;

    writeln(b);

end.