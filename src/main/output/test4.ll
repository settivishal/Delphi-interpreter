; Program: Test
%struct.Book = type {
i8* ; name,
i8* ; author,
i32 ; price
define integer @Book_getPrice(%struct.Book* %this) {
entry:
ret integer null
}

}

@myBook = global %struct.Book* null
@name = global i8* null
store i8* null, i8** @name
%1 = load i8*, i8** @name
call void @writeln(i8* %1)

define i32 @main() {
  ret i32 0
}
; LLVM IR for Extended Pascal/Delphi
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"
declare noalias i8* @malloc(i64)
declare void @free(i8*)
