; LLVM IR for Extended Pascal/Delphi
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "wasm32-unknown-unknown"

declare void @writeln_i32(i32) #1
declare void @writeln_str(i8*) #1

attributes #0 = { "wasm-export-name"="memory" }
attributes #1 = { "wasm-import-module"="env" }


; Program: Test
@y = global i32 0
@x = global i32 0
define i32 @main() {
  entry:
  store i32 0, i32* @x
  store i32 0, i32* @y
  store i32 1, i32* @y
  br label %for.cond.0
for.cond.0:
  %1 = load i32, i32* @y
  %2 = icmp sle i32 %1, 10
  br i1 %2, label %for.body.1, label %for.end.3
for.body.1:
  %3 = load i32, i32* @y
  %4 = add i32 10, %3
  store i32 %4, i32* @x
  br label %for.inc.2
for.inc.2:
  %6 = add i32 %1, 1
  store i32 %6, i32* @y
  br label %for.cond.0
for.end.3:
  %7 = load i32, i32* @x
  call void @writeln_i32(i32 %7)
  ret i32 0
}