default rel

extern printf, malloc, strcpy, scanf, strlen, sscanf, sprintf, memcpy, strcmp, puts
global main

SECTION .text


print_Int:
     mov                  rsi,                  rdi
     mov                  rdi,    __print_IntFormat
     sub                  rsp,                    8
    call               printf
     add                  rsp,                    8
     ret
println_Int:
     mov                  rsi,                  rdi
     mov                  rdi,  __println_IntFormat
     sub                  rsp,                    8
    call               printf
     add                  rsp,                    8
     ret
Mx_builtin_print:
     mov                  rsi,                  rdi
     mov                  rdi,        __printFormat
     sub                  rsp,                    8
    call               printf
     add                  rsp,                    8
     ret
Mx_builtin_println:
     sub                  rsp,                    8
    call                 puts
     add                  rsp,                    8
     ret
Mx_builtin_getInt:
     mov                  rdi,       __getIntFormat
     mov                  rsi,           @getIntBuf
     sub                  rsp,                    8
    call                scanf
     add                  rsp,                    8
     mov                  rax,   qword [@getIntBuf]
     ret
Mx_builtin_getString:
    push                  r15
     mov                  rdi,                  300
    call               malloc
     mov                  r15,                  rax
     add                  r15,                    8
     mov                  rdi,    __getStringFormat
     mov                  rsi,                  r15
    call                scanf
     mov                  rdi,                  r15
    call               strlen
     mov      qword [r15 - 8],                  rax
     mov                  rax,                  r15
     pop                  r15
     ret
Mx_builtin_toString:
    push                  r15
    push                  rdi
     mov                  rdi,                   20
     sub                  rsp,                    8
    call               malloc
     add                  rsp,                    8
     mov                  r15,                  rax
     add                  r15,                    8
     mov                  rdi,                  r15
     mov                  rsi,     __toStringFormat
     pop                  rdx
    call              sprintf
     mov                  rdi,                  r15
    call               strlen
     mov      qword [r15 - 8],                  rax
     mov                  rax,                  r15
     pop                  r15
     ret
Mx_builtin_arr_size:
     mov                  rax,      qword [rdi - 8]
     ret
Mx_builtin_str_length:
     mov                  rax,      qword [rdi - 8]
     ret
Mx_builtin_str_parseInt:
     mov                  rsi,       __getIntFormat
     mov                  rdx,         @parseIntBuf
     sub                  rsp,                    8
    call               sscanf
     add                  rsp,                    8
     mov                  rax, qword [@parseIntBuf]
     ret
Mx_builtin_str_substring:
    push                  r15
    push                  r14
     mov                  r15,                  rdi
     add                  r15,                  rsi
     mov                  r14,                  rdx
     sub                  r14,                  rsi
     add                  r14,                    1
     mov                  rdi,                  r14
     add                  rdi,                    9
     sub                  rsp,                    8
    call               malloc
     add                  rsp,                    8
     add                  rax,                    8
     mov                  rdi,                  rax
     mov                  rsi,                  r15
     mov                  rdx,                  r14
     sub                  rsp,                    8
    call               memcpy
     add                  rsp,                    8
     mov      qword [rax - 8],                  r14
     mov                  r15,                  rax
     add                  r15,                  r14
     mov                  r15,                    0
     pop                  r14
     pop                  r15
     ret
Mx_builtin_str_ord:
     add                  rdi,                  rsi
   movsx                  rax,           byte [rdi]
     ret
Mx_builtin_str_concatenate:
    push                  r15
    push                  r14
    push                  r13
     mov                  r15,      qword [rdi - 8]
     add                  r15,      qword [rsi - 8]
     add                  r15,                    9
     mov                  r14,                  rdi
     mov                  r13,                  rsi
     mov                  rdi,                  r15
    call               malloc
     sub                  r15,                    9
     mov          qword [rax],                  r15
     mov                  r15,                  rax
     add                  r15,                    8
     mov                  rdi,                  r15
     mov                  rsi,                  r14
    call               strcpy
     add                  r15,      qword [r14 - 8]
     mov                  r14,                  rax
     mov                  rdi,                  r15
     mov                  rsi,                  r13
    call               strcpy
     mov                  rax,                  r14
     pop                  r13
     pop                  r14
     pop                  r15
     ret
Mx_builtin_str_e:
     sub                  rsp,                    8
    call               strcmp
     add                  rsp,                    8
     cmp                  eax,                    0
     mov                  rax,                    0
    sete                   al
     ret
Mx_builtin_str_ne:
     sub                  rsp,                    8
    call               strcmp
     add                  rsp,                    8
     cmp                  eax,                    0
     mov                  rax,                    0
   setne                   al
     ret
Mx_builtin_str_g:
     sub                  rsp,                    8
    call               strcmp
     add                  rsp,                    8
     cmp                  eax,                    0
     mov                  rax,                    0
    setg                   al
     ret
Mx_builtin_str_ge:
     sub                  rsp,                    8
    call               strcmp
     add                  rsp,                    8
     cmp                  eax,                    0
     mov                  rax,                    0
   setge                   al
     ret
Mx_builtin_str_l:
     sub                  rsp,                    8
    call               strcmp
     add                  rsp,                    8
     cmp                  eax,                    0
     mov                  rax,                    0
    setl                   al
     ret
Mx_builtin_str_le:
     sub                  rsp,                    8
    call               strcmp
     add                  rsp,                    8
     cmp                  eax,                    0
     mov                  rax,                    0
   setle                   al
     ret

tak:
	push rbp
	mov rbp, rsp
	sub rsp, 240
	mov qword[rsp + 8], rbx

____tak_0____enter:

____tak_1____entry:
	mov r10, qword[rsp + 224]
	mov r11, qword[rsp + 216]
	cmp r10, r11
	jge ____tak_3____if_false

____tak_2____if_true:
	mov r10, qword[rsp + 216]
	sub r10, 1
	mov rbx, r10
	mov qword[rsp-40], rbx
	mov r10, qword[rsp + 224]
	mov qword[rsp-32], r10
	mov r10, qword[rsp + 232]
	mov qword[rsp-24], r10
	mov qword[rsp + 40], rdi
	mov qword[rsp + 32], rsi
	call tak
	mov rdi, qword[rsp+40]
	mov rsi, qword[rsp+32]
	mov rsi, rax
	xor rax, rax
	mov r10, qword[rsp + 224]
	sub r10, 1
	mov rbx, r10
	mov qword[rsp-40], rbx
	mov r10, qword[rsp + 232]
	mov qword[rsp-32], r10
	mov r10, qword[rsp + 216]
	mov qword[rsp-24], r10
	mov qword[rsp + 40], rdi
	mov qword[rsp + 32], rsi
	call tak
	mov rdi, qword[rsp+40]
	mov rsi, qword[rsp+32]
	mov rdi, rax
	xor rax, rax
	mov r10, qword[rsp + 232]
	sub r10, 1
	mov rbx, r10
	mov qword[rsp-40], rbx
	mov r10, qword[rsp + 216]
	mov qword[rsp-32], r10
	mov r10, qword[rsp + 224]
	mov qword[rsp-24], r10
	mov qword[rsp + 40], rdi
	mov qword[rsp + 32], rsi
	call tak
	mov rdi, qword[rsp+40]
	mov rsi, qword[rsp+32]
	mov rbx, rax
	xor rax, rax
	mov qword[rsp-40], rsi
	mov qword[rsp-32], rdi
	mov qword[rsp-24], rbx
	mov qword[rsp + 40], rdi
	mov qword[rsp + 32], rsi
	call tak
	mov rdi, qword[rsp+40]
	mov rsi, qword[rsp+32]
	mov rbx, rax
	xor rax, rax
	mov r10, 1
	add r10, rbx
	mov rbx, r10
	mov rax, rbx
	jmp ____tak_5____exit
	jmp ____tak_5____exit

____tak_3____if_false:
	mov rax, qword[rsp + 232]
	jmp ____tak_5____exit

____tak_5____exit:
	mov rbx, qword[rsp + 8]
	add rsp, 240
	pop rbp
	ret
main:
	push rbp
	mov rbp, rsp
	sub rsp, 160

____main_0____enter:

____main_1____entry:
	mov r10, 18
	mov qword[rsp-40], r10
	mov r10, 12
	mov qword[rsp-32], r10
	mov r10, 6
	mov qword[rsp-24], r10
	call tak
	mov rbx, rax
	xor rax, rax
	mov r10, rbx
	mov rdi, r10
	call Mx_builtin_toString
	mov rbx, rax
	xor rax, rax
	mov r10, rbx
	mov rdi, r10
	call Mx_builtin_println
	xor rax, rax

____main_2____exit:
	add rsp, 160
	pop rbp
	ret
SECTION .data
__println_IntFormat:
      db         "%ld", 10, 0
__print_IntFormat:
      db             "%ld", 0
__printFormat:
      db              "%s", 0
__getIntFormat:
      db             "%ld", 0
__getStringFormat:
      db              "%s", 0
__toStringFormat:
      db             "%ld", 0
__parseIntFormat:
      db             "%ld", 0

SECTION .bss
@getIntBuf:
    resq                    1
@parseIntBuf:
    resq                    1
