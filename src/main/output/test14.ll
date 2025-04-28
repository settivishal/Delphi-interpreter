; LLVM IR for Extended Pascal/Delphi
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"
declare noalias i8* @malloc(i64)
declare void @free(i8*)
declare void @writeln_i32(i32)
declare void @writeln_str(i8*)


; Program: Test
i8* ; name,
i32 ; age
define integer @Human_doStuff(%struct.Human* %this) {
entry:
ret integer null
}

}

define %struct.Human* @Human_create() {
entry:
%1 = call noalias i8* @malloc(i64 8)
ret %struct.Human* %2
}

@John = global %struct.Human* null
@height = global i32 0
@x = global i32 0
%5 = load i1, i1* @x
br i1 %6, label %label1, label %label2

label1:
br label %label3

label2:
br label %label3

label3:
call void @writeln_i32(i32 0)
call void @writeln_i32(i32 0)

define i32 @main() {
	entry:
	%John = alloca %struct.Human*
	store %struct.Human* null, %struct.Human** %John
	%x = alloca i32
	store i32 0, i32* %x
	%height = alloca i32
	store i32 0, i32* %height
	%struct.Human = type {
	%2 = bitcast i8* %1 to %struct.Human*
	%3 = getelementptr %struct.Human, %struct.Human* %2, i32 0, i32 0
	store i8* null, i8** %3
	%4 = getelementptr %struct.Human, %struct.Human* %2, i32 0, i32 1
	store i32 0, i32* %4
	store %struct.Human* null, %struct.Human** @John
	%6 = icmp eq i1 %5, 3
	ret i32 0
}