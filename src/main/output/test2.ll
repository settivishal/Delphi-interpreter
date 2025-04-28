; LLVM IR for Extended Pascal/Delphi
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"
declare noalias i8* @malloc(i64)
declare void @free(i8*)
declare void @writeln_i32(i32)
declare void @writeln_str(i8*)

@.str.1 = private unnamed_addr constant [11 x i8] c"destructor\00"

; Program: Test
i32 ; size
%1 = getelementptr inbounds [11 x i8], [11 x i8]* @.str.1, i64 0, i64 0
call void @writeln_str(i8* %1)
}

define %struct.Car* @Car_create() {
entry:
%2 = call noalias i8* @malloc(i64 4)
ret %struct.Car* %3
}

@myCar = global %struct.Car* null
@z = global i32 0
@a = global i32 0
@b = global i32 0
%5 = load i32, i32* @a
%6 = load i32, i32* @b
call void @writeln_i32(i32 %6)

define i32 @main() {
	entry:
	%myCar = alloca %struct.Car*
	store %struct.Car* null, %struct.Car** %myCar
	%a = alloca i32
	store i32 0, i32* %a
	%b = alloca i32
	store i32 0, i32* %b
	%z = alloca i32
	store i32 0, i32* %z
	%struct.Car = type {
	%3 = bitcast i8* %2 to %struct.Car*
	%4 = getelementptr %struct.Car, %struct.Car* %3, i32 0, i32 0
	store i32 0, i32* %4
	store %struct.Car* null, %struct.Car** @myCar
	store i32 10, i32* @a
	store i32 %5, i32* @b
	ret i32 0
}