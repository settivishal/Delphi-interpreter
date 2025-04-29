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
  %x.addr = alloca i32
  store i32 0, i32* %x.addr
  %i.addr = alloca i32
  store i32 0, i32* %i.addr
  store i32 1, i32* @x
  br label %label1
  label1:
  %2 = load i32, i32* @x
  %3 = icmp slt i32 %2, 8
  br i1 %3, label %label2, label %label3
  label2:
  %4 = load i32, i32* @x
  %5 = add i32 %4, 2
  store i32 %5, i32* @x
  %7 = load i32, i32* @x
  call void @writeln_i32(i32 %7)
  br label %label1
  label3:
  ret i32 0
}