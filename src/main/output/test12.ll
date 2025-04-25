; Program: Test
@x = global i32 0
store i32 1, i32* @x
%1 = load i1, i1* @x
%2 = icmp eq i1 %1, 1
br i1 %2, label %label1, label %label2

label1:
br label %label3

label2:
br label %label3

label3:
call void @writeln(i8* null)

define i32 @main() {
  ret i32 0
}
; LLVM IR for Extended Pascal/Delphi
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"
declare noalias i8* @malloc(i64)
declare void @free(i8*)
