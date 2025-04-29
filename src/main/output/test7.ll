; LLVM IR for Extended Pascal/Delphi
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "wasm32-unknown-unknown"

; LLVM IR for WebAssembly
target datalayout = "e-m:e-p:32:32-i64:64-n32:64-S128"
target triple = "wasm32-unknown-unknown"

declare void @writeln_i32(i32) #1
declare void @writeln_str(i8*) #1

attributes #0 = { "wasm-export-name"="memory" }
attributes #1 = { "wasm-import-module"="env" }

; Program: Test
@x = global i32 0
@i = global i32 0

define i32 @main() {
entry:
	store i32 0, i32* %x
	store i32 0, i32* %i
	store i32 1, i32* @x
	br label %label2
	br i1 false, label %label3, label %label4
	%1 = load i32, i32* %x
	%2 = add i32 %1, 2
	store i32 %2, i32* @x
	%3 = load i32, i32* %x
	call void @writeln_i32(i32 %3)
	br label %label2
	%x = alloca i32
	store i32 0, i32* %x
	%i = alloca i32
	store i32 0, i32* %i
	ret i32 0
}