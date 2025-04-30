; LLVM IR for Extended Pascal/Delphi
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
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
  store i32 0, i32* @x
  store i32 0, i32* @i
  store i32 1, i32* @x
  br label %while.cond.0
while.cond.0:
  %2 = load i32, i32* @x
  %3 = icmp slt i32 %2, 8
  br i1 %3, label %while.body.1, label %while.end.2
while.body.1:
  %4 = load i32, i32* @x
  %5 = icmp eq i32 %4, 3
  br i1 %5, label %label4, label %label6
label4:
  br label %label6
label6:
  %6 = load i32, i32* @x
  call void @writeln_i32(i32 %6)
  %7 = load i32, i32* @x
  %8 = add i32 %7, 1
  store i32 %8, i32* @x
  br label %while.cond.0
while.end.2:
  ret i32 0
}