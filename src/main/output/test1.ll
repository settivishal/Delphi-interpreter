; Program: Test
@x = global i32 0
@y = global i32 0
%1 = add i32 1, 5
store i32 %1, i32* @x
%2 = load i32, i32* @x
%3 = add i32 %2, 10
store i32 %3, i32* @y
%4 = load i32, i32* @y
%5 = mul i32 20, %4
store i32 %5, i32* @y
%6 = sdiv i32 200, 15
store i32 %6, i32* @x
%7 = load i8*, i8** @y
call void @writeln(i8* %7)

define i32 @main() {
  ret i32 0
}
; LLVM IR for Extended Pascal/Delphi
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"
declare noalias i8* @malloc(i64)
declare void @free(i8*)
