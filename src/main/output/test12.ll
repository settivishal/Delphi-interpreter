; LLVM IR for Extended Pascal/Delphi
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "wasm32-unknown-unknown"

declare void @writeln_i32(i32) #1
declare void @writeln_str(i8*) #1

attributes #0 = { "wasm-export-name"="memory" }
attributes #1 = { "wasm-import-module"="env" }


; Program: Test
@x = global i32 0
define i32 @main() {
  entry:
  store i32 0, i32* @x
  store i32 1, i32* @x
  %2 = load i32, i32* @x
  %3 = icmp eq i32 %2, 1
  br i1 %3, label %if.then.0, label %if.end.2
if.then.0:
  br label %if.end.2
  br label %if.end.2
if.end.2:
  call void @writeln_i32(i32 0)
  ret i32 0
}