; LLVM IR for Extended Pascal/Delphi
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"
declare noalias i8* @malloc(i64)
declare void @free(i8*)
declare void @writeln_i32(i32)
declare void @writeln_str(i8*)


; Program: Test
@x = global i32 0
%1 = load i32, i32* @x
call void @writeln_i32(i32 %1)
%2 = load i1, i1* @x
br i1 %3, label %label1, label %label2

label1:
br label %label3

label2:
br label %label3

label3:

define i32 @main() {
	entry:
	%x = alloca i32
	store i32 0, i32* %x
	%3 = icmp eq i1 %2, 3
	ret i32 0
}