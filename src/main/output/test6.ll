; LLVM IR for Extended Pascal/Delphi
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "wasm32-unknown-unknown"

declare void @writeln_i32(i32) #1

attributes #0 = { "wasm-export-name"="memory" }
attributes #1 = { "wasm-import-module"="env" }

@y = global i32 0
@x = global i32 0

define i32 @main() {
entry:
  ; Initialize loop counter (y := 1)
  store i32 1, i32* @y
  br label %loop_cond

loop_cond:
  ; Check if y <= 10
  %y.val = load i32, i32* @y
  %cmp = icmp sle i32 %y.val, 10
  br i1 %cmp, label %loop_body, label %loop_exit

loop_body:
  ; Print current y value
  call void @writeln_i32(i32 %y.val)

  ; Increment y (y := y + 1)
  %next_y = add i32 %y.val, 1
  store i32 %next_y, i32* @y
  br label %loop_cond

loop_exit:
  ; x := 10 + y (y will be 11 after loop)
  %final_y = load i32, i32* @y
  %x.val = add i32 10, %final_y
  store i32 %x.val, i32* @x

  ; Print x
  call void @writeln_i32(i32 %x.val)

  ret i32 0
}