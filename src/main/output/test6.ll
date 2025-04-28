; LLVM IR for Extended Pascal/Delphi
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"
declare noalias i8* @malloc(i64)
declare void @free(i8*)
declare void @writeln_i32(i32)
declare void @writeln_str(i8*)


; Program: Test
@y = global i32 0
@x = global i32 0
%1 = load i32, i32* @y
call void @writeln_i32(i32 %1)
%2 = load i32, i32* @y
%4 = load i32, i32* @x
call void @writeln_i32(i32 %4)

define i32 @main() {
	entry:
	%x = alloca i32
	store i32 0, i32* %x
	%y = alloca i32
	store i32 0, i32* %y
	%3 = add i32 10, %2
	store i32 %3, i32* @x
	ret i32 0
}