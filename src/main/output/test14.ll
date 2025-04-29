; LLVM IR for Extended Pascal/Delphi
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "wasm32-unknown-unknown"

declare void @writeln_i32(i32) #1
declare void @writeln_str(i8*) #1

attributes #0 = { "wasm-export-name"="memory" }
attributes #1 = { "wasm-import-module"="env" }

; Program: Test
i8* ; name,
i32 ; age
define integer @Human_doStuff(%struct.Human* %this) {
entry:
}

}

define %struct.Human* @Human_create() {
entry:
}

@John = global %struct.Human* null
@height = global i32 0
@x = global i32 0
label1:
label2:
label3:
define i32 @main() {
entry:
	%struct.Human = type {
	ret integer null
	%1 = call noalias i8* @malloc(i64 8)
	%2 = bitcast i8* %1 to %struct.Human*
	%3 = getelementptr %struct.Human, %struct.Human* %2, i32 0, i32 0
	store i8* null, i8** %3
	%4 = getelementptr %struct.Human, %struct.Human* %2, i32 0, i32 1
	store i32 0, i32* %4
	ret %struct.Human* %2
	store %struct.Human* null, %struct.Human** %John
	store i32 0, i32* %height
	store i32 0, i32* %x
	store %struct.Human* null, %struct.Human** @John
	%5 = load i1, i1* %x
	%6 = icmp eq i1 %5, 3
	br i1 %6, label %label1, label %label2
	br label %label3
	br label %label3
	call void @writeln_i32(i32 0)
	call void @writeln_i32(i32 0)
	%John = alloca %struct.Human*
	store %struct.Human* null, %struct.Human** %John
	%x = alloca i32
	store i32 0, i32* %x
	%height = alloca i32
	store i32 0, i32* %height
	ret i32 0
}