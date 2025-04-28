; LLVM IR for Extended Pascal/Delphi
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"
declare noalias i8* @malloc(i64)
declare void @free(i8*)
declare void @writeln_i32(i32)
declare void @writeln_str(i8*)


; Program: Test
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
%1 = load i32, i32* @name
call void @writeln_i32(i32 %1)

define i32 @main() {
	entry:
	%name = alloca i8*
	store i8* null, i8** %name
	%myBook = alloca %struct.Book*
	store %struct.Book* null, %struct.Book** %myBook
	%struct.Book = type {
	store i8* null, i8** @name
	ret i32 0
}