; LLVM IR for Extended Pascal/Delphi
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"
declare noalias i8* @malloc(i64)
declare void @free(i8*)
declare void @writeln_i32(i32)
declare void @writeln_str(i8*)


; Program: Test
@x = global i32 0
@i = global i32 0
br label %label2

label2:
br i1 false, label %label3, label %label4

label3:
%1 = load i32, i32* @x
%3 = load i32, i32* @x
call void @writeln_i32(i32 %3)
br label %label2

label4:

define i32 @main() {
	entry:
	%x = alloca i32
	store i32 0, i32* %x
	%i = alloca i32
	store i32 0, i32* %i
	store i32 1, i32* @x
	%2 = add i32 %1, 2
	store i32 %2, i32* @x
	ret i32 0
}