; LLVM IR for Extended Pascal/Delphi
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "wasm32-unknown-unknown"

declare void @writeln_i32(i32) #1
declare void @writeln_str(i8*) #1

attributes #0 = { "wasm-export-name"="memory" }
attributes #1 = { "wasm-import-module"="env" }

@.str.1 = private unnamed_addr constant [14 x i8] c"Hello, World!\00"

; Program: Hello
define i32 @main() {
  entry:
  %1 = getelementptr inbounds [14 x i8], [14 x i8]* @.str.1, i64 0, i64 0
  call void @writeln_str(i8* %1)
  ret i32 0
}