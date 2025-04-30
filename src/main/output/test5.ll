; LLVM IR for Extended Pascal/Delphi
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"
declare noalias i8* @malloc(i64)
declare void @free(i8*)
declare void @writeln_i32(i32)
declare void @writeln_str(i8*)


; Program: test
i8* ; name,
i8* ; os,
i32 ; price
define integer @Mobile_getPrice(%struct.Mobile* %this) {
entry:
ret integer null
}

}

define %struct.Mobile* @Mobile_create() {
entry:
%1 = call noalias i8* @malloc(i64 12)
ret %struct.Mobile* %2
}

@MyMobile = global %struct.Mobile* null
@cost = global i32 0
%6 = load i32, i32* @cost
call void @writeln_i32(i32 %6)

define i32 @main() {
	entry:
	%cost = alloca i32
	store i32 0, i32* %cost
	%MyMobile = alloca %struct.Mobile*
	store %struct.Mobile* null, %struct.Mobile** %MyMobile
	%struct.Mobile = type {
	%2 = bitcast i8* %1 to %struct.Mobile*
	%3 = getelementptr %struct.Mobile, %struct.Mobile* %2, i32 0, i32 0
	store i8* null, i8** %3
	%4 = getelementptr %struct.Mobile, %struct.Mobile* %2, i32 0, i32 1
	store i32 0, i32* %4
	%5 = getelementptr %struct.Mobile, %struct.Mobile* %2, i32 0, i32 2
	store i8* null, i8** %5
	store %struct.Mobile* null, %struct.Mobile** @MyMobile
	store i32 2000, i32* @cost
	ret i32 0
}