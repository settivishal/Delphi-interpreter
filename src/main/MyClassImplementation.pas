program TestProgram;

uses
  System;

type
  TMyClass = class
  private
    FName: string;
  public
    constructor Create(AName: string);
    procedure Display;
  end;

var
  Obj: TMyClass;

{ Implementation Section }

constructor TMyClass.Create(AName: string);
begin
  FName := AName;
end;

procedure TMyClass.Display;
begin
  // Custom output method since writeln isn't in grammar
  { Output: Name: FName }
end;

begin
  Obj := TMyClass.Create('Delphi Object');
  Obj.Display;
  Obj.Free; // Properly free the object
end.