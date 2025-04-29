; LLVM IR for Extended Pascal/Delphi
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "wasm32-unknown-unknown"

declare void @writeln_i32(i32) #1
declare void @writeln_str(i8*) #1

attributes #0 = { "wasm-export-name"="memory" }
attributes #1 = { "wasm-import-module"="env" }


; Program: Test
@x = global i32 0
@y = global i32 0
define i32 @main() {
  entry:
  %x.addr = alloca i32
  store i32 0, i32* %x.addr
  %y.addr = alloca i32
  store i32 0, i32* %y.addr
  %1 = add i32 1, 5
  store i32 %1, i32* @x
  %3 = load i32, i32* @x
  %4 = add i32 %3, 10
  store i32 %4, i32* @y
  %6 = load i32, i32* @y
  %7 = mul i32 20, %6
  store i32 %7, i32* @y
  %9 = sdiv i32 200, 15
  store i32 %9, i32* @x
  %11 = load i32, i32* @y
  call void @writeln_i32(i32 %11)
  ret i32 0
}