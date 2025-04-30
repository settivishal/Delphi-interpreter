; LLVM IR for Extended Pascal/Delphi
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "wasm32-unknown-unknown"

declare void @writeln_i32(i32) #1
declare void @writeln_str(i8*) #1

attributes #0 = { "wasm-export-name"="memory" }
attributes #1 = { "wasm-import-module"="env" }

@y = global i32 0
@x = global i32 0

define i32 @main() {
entry:
  store i32 1, i32* @y          ; Initialize y = 1
  br label %for.cond

for.cond:
  %y.val = load i32, i32* @y    ; Load current y value
  %cmp = icmp sle i32 %y.val, 10 ; Compare y <= 10
  br i1 %cmp, label %for.body, label %for.end

for.body:
  %y.print = load i32, i32* @y  ; Load y for printing
  call void @writeln_i32(i32 %y.print)

  ; Increment y
  %y.inc = add i32 %y.val, 1
  store i32 %y.inc, i32* @y
  br label %for.cond

for.end:
  ; After loop, y will be 11
  %y.final = load i32, i32* @y
  %x.val = add i32 10, %y.final ; x = 10 + y
  store i32 %x.val, i32* @x

  ; Print x
  %x.print = load i32, i32* @x
  call void @writeln_i32(i32 %x.print)

  ret i32 0
}