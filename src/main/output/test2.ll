; LLVM IR for Extended Pascal/Delphi
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "wasm32-unknown-unknown"
@.str.1 = private unnamed_addr constant [11 x i8] c"destructor\00"

declare void @writeln_i32(i32) #1
declare void @writeln_str(i8*) #1

attributes #0 = { "wasm-export-name"="memory" }
attributes #1 = { "wasm-import-module"="env" }

; Program: Test
i32 ; size
}

define %struct.Car* @Car_create() {
entry:
}

@myCar = global %struct.Car* null
@z = global i32 0
@a = global i32 0
@b = global i32 0
define i32 @main() {
entry:
	%struct.Car = type {
	%1 = getelementptr inbounds [11 x i8], [11 x i8]* @.str.1, i64 0, i64 0
	call void @writeln_str(i8* %1)
	%2 = call noalias i8* @malloc(i64 4)
	%3 = bitcast i8* %2 to %struct.Car*
	%4 = getelementptr %struct.Car, %struct.Car* %3, i32 0, i32 0
	store i32 0, i32* %4
	ret %struct.Car* %3
	store %struct.Car* null, %struct.Car** %myCar
	store i32 0, i32* %z
	store i32 0, i32* %a
	store i32 0, i32* %b
	store %struct.Car* null, %struct.Car** @myCar
	store i32 10, i32* @a
	%5 = load i32, i32* %a
	store i32 %5, i32* @b
	%6 = load i32, i32* %b
	call void @writeln_i32(i32 %6)
	%myCar = alloca %struct.Car*
	store %struct.Car* null, %struct.Car** %myCar
	%a = alloca i32
	store i32 0, i32* %a
	%b = alloca i32
	store i32 0, i32* %b
	%z = alloca i32
	store i32 0, i32* %z
	ret i32 0
}