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
  %x.addr = alloca i32
  store i32 0, i32* %x.addr
  %1 = load i32, i32* @x
  call void @writeln_i32(i32 %1)
  %2 = load i32, i32* @x
  %3 = icmp eq i32 %2, 3
  br i1 %3, label %label1, label %label3
  label1:
  br label %label3
  label3:
  ret i32 0
}