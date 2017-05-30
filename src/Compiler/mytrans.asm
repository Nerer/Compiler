default rel

extern printf, malloc, strcpy, scanf, strlen, sscanf, sprintf, memcpy, strcmp, puts
global GLOBAL_V_C
global GLOBAL_V_A
global GLOBAL_V_B
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

main:
	push rbp
	mov rbp, rsp
	sub rsp, 6344

____main_0____enter:

____main_1____entry:
	mov rsi, 0

____main_2____for_condition:
	mov r11, 1000000
	cmp rsi, r11
	jge ____main_14____for_merge

____main_3____for_body:
	mov r10, rsi
	add r10, 1
	mov rbx, r10
	mov qword[rel GLOBAL_V_A], rbx
	mov r10, rsi
	add r10, 1
	mov rbx, r10
	mov qword[rel GLOBAL_V_B], rbx
	mov r10, rsi
	add r10, 1
	mov rbx, r10
	mov qword[rel GLOBAL_V_C], rbx

____main_4____while_loop:
	mov r10, 1
	mov rcx, 29
	shl r10, cl
	mov rdi, r10
	mov r10, qword[rel GLOBAL_V_C]
	cmp r10, rdi
	jge ____main_6____logical_false

____main_5____logical_true:
	mov r10, 1
	mov rcx, 29
	shl r10, cl
	mov rbx, r10
	mov r10, rbx
	neg r10
	mov rdi, r10
	mov r10, qword[rel GLOBAL_V_C]
	cmp r10, rdi
	setg al
	movzx rbx, al

____main_7____logical_merge:
	cmp rbx, 0
	je ____main_9____while_merge

____main_8____while_body:
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub rdi, rbx
	mov r8, rdi
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub rdi, rbx
	mov rdi, rdi
	lea r8, [r8 + rdi]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub rdi, rbx
	mov rdi, rdi
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [rbx + r11]
	lea rbx, [rdi + r9]
	lea r8, [r8 + rbx]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rdi, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [rdi + r11]
	lea rbx, [rbx + rdi]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [r10 + r11]
	sub rbx, rdi
	mov r9, rbx
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [r10 + r11]
	sub rbx, rdi
	mov rbx, rbx
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rdi, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [rdi + r11]
	lea rbx, [rbx + rdi]
	lea rdi, [r9 + rbx]
	sub r8, rdi
	mov r9, r8
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea r12, [rdi + rbx]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea r8, [rdi + rbx]
	sub r12, r8
	mov rdi, r12
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov r8, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r8, [r8 + r11]
	lea rbx, [rbx + r8]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r8, [r10 + r11]
	sub rbx, r8
	mov rbx, rbx
	sub rdi, rbx
	mov r8, rdi
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r12, [r10 + r11]
	sub rbx, r12
	mov rdi, rbx
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea rdi, [rdi + rbx]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r12, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea r12, [r12 + rbx]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub r12, rbx
	mov r12, r12
	sub rdi, r12
	mov rbx, rdi
	lea rdi, [r8 + rbx]
	sub r9, rdi
	mov r8, r9
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r10 + r11]
	sub rbx, r9
	mov rdi, rbx
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r10 + r11]
	sub rbx, r9
	mov rbx, rbx
	lea rdi, [rdi + rbx]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub r9, rbx
	mov r9, r9
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r12, [rbx + r11]
	lea rbx, [r9 + r12]
	lea r12, [rdi + rbx]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [rbx + r11]
	lea rbx, [rdi + r9]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [r10 + r11]
	sub rbx, rdi
	mov r9, rbx
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub rdi, rbx
	mov rdi, rdi
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea rdi, [rdi + rbx]
	lea rbx, [r9 + rdi]
	sub r12, rbx
	mov r9, r12
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea rdi, [rdi + rbx]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub rdi, rbx
	mov r12, rdi
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [r10 + r11]
	sub rbx, rdi
	mov rbx, rbx
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rdi, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [rdi + r11]
	lea rbx, [rbx + rdi]
	lea r12, [r12 + rbx]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea rdi, [rdi + rbx]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub rdi, rbx
	mov rdi, rdi
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r13, [r10 + r11]
	sub rbx, r13
	mov r14, rbx
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea r13, [r14 + rbx]
	lea rbx, [rdi + r13]
	sub r12, rbx
	mov rdi, r12
	sub r9, rdi
	mov rbx, r9
	lea r8, [r8 + rbx]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea r9, [rdi + rbx]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea rdi, [rdi + rbx]
	sub r9, rdi
	mov r9, r9
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r12, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [rbx + r11]
	lea rbx, [r12 + rdi]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [r10 + r11]
	sub rbx, rdi
	mov rbx, rbx
	sub r9, rbx
	mov r9, r9
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub rdi, rbx
	mov rdi, rdi
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea r12, [rdi + rbx]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r13, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [rbx + r11]
	lea rbx, [r13 + rdi]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [r10 + r11]
	sub rbx, rdi
	mov rbx, rbx
	sub r12, rbx
	mov rdi, r12
	lea r9, [r9 + rdi]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [r10 + r11]
	sub rbx, rdi
	mov rbx, rbx
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rdi, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [rdi + r11]
	lea r12, [rbx + rdi]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea r13, [rdi + rbx]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [r10 + r11]
	sub r13, rdi
	mov rbx, r13
	sub r12, rbx
	mov rdi, r12
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r12, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub r12, rbx
	mov r12, r12
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea r13, [r12 + rbx]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov r12, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r12, [r12 + r11]
	lea rbx, [rbx + r12]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r12, [r10 + r11]
	sub rbx, r12
	mov rbx, rbx
	sub r13, rbx
	mov r12, r13
	lea rbx, [rdi + r12]
	lea r12, [r9 + rbx]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r10 + r11]
	sub rbx, r9
	mov rdi, rbx
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r13, [r10 + r11]
	sub rbx, r13
	mov r9, rbx
	lea rbx, [rdi + r9]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rdi, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [rdi + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r13, [r10 + r11]
	sub rdi, r13
	mov r9, rdi
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rdi, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [rdi + r11]
	lea r9, [r9 + rdi]
	lea r13, [rbx + r9]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rdi, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [rdi + r11]
	lea rbx, [rbx + rdi]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r10 + r11]
	sub rbx, r9
	mov rdi, rbx
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub r9, rbx
	mov r9, r9
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r14, [rbx + r11]
	lea rbx, [r9 + r14]
	lea r9, [rdi + rbx]
	sub r13, r9
	mov rdi, r13
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea r9, [r9 + rbx]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub r9, rbx
	mov r9, r9
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r13, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub r13, rbx
	mov r13, r13
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea r14, [r13 + rbx]
	lea r13, [r9 + r14]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov r9, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r9 + r11]
	lea r14, [rbx + r9]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub r14, rbx
	mov r14, r14
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r10 + r11]
	sub rbx, r9
	mov r15, rbx
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea r15, [r15 + rbx]
	lea r9, [r14 + r15]
	sub r13, r9
	mov rbx, r13
	sub rdi, rbx
	mov rdi, rdi
	lea rbx, [r12 + rdi]
	sub r8, rbx
	mov rdi, r8
	mov qword[rel GLOBAL_V_A], rdi
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [r10 + r11]
	sub rbx, rdi
	mov r8, rbx
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub rdi, rbx
	mov rdi, rdi
	lea r8, [r8 + rdi]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub rdi, rbx
	mov rdi, rdi
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea rdi, [rdi + rbx]
	lea r8, [r8 + rdi]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rdi, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [rdi + r11]
	lea r9, [rbx + rdi]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub r9, rbx
	mov rdi, r9
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r12, [r10 + r11]
	sub rbx, r12
	mov r9, rbx
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea r9, [r9 + rbx]
	lea rbx, [rdi + r9]
	sub r8, rbx
	mov rdi, r8
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r8, [rbx + r11]
	lea rbx, [r9 + r8]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov r8, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r8, [r8 + r11]
	lea r9, [r9 + r8]
	sub rbx, r9
	mov r8, rbx
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea r9, [r9 + rbx]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub r9, rbx
	mov r12, r9
	sub r8, r12
	mov r9, r8
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r8, [r10 + r11]
	sub rbx, r8
	mov r12, rbx
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea r12, [r12 + rbx]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov r8, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r8, [r8 + r11]
	lea rbx, [rbx + r8]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r8, [r10 + r11]
	sub rbx, r8
	mov rbx, rbx
	sub r12, rbx
	mov r8, r12
	lea rbx, [r9 + r8]
	sub rdi, rbx
	mov r8, rdi
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub rdi, rbx
	mov r9, rdi
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub rdi, rbx
	mov rdi, rdi
	lea rbx, [r9 + rdi]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rdi, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [rdi + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r12, [r10 + r11]
	sub rdi, r12
	mov r9, rdi
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rdi, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r12, [rdi + r11]
	lea rdi, [r9 + r12]
	lea rbx, [rbx + rdi]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov r9, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r9 + r11]
	lea rdi, [rdi + r9]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r10 + r11]
	sub rdi, r9
	mov r12, rdi
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rdi, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [rdi + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r10 + r11]
	sub rdi, r9
	mov rdi, rdi
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov r9, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r9 + r11]
	lea r13, [rdi + r9]
	lea rdi, [r12 + r13]
	sub rbx, rdi
	mov r9, rbx
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea rdi, [rdi + rbx]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub rdi, rbx
	mov rdi, rdi
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r12, [r10 + r11]
	sub rbx, r12
	mov rbx, rbx
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov r12, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r12, [r12 + r11]
	lea rbx, [rbx + r12]
	lea r12, [rdi + rbx]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea rdi, [rdi + rbx]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub rdi, rbx
	mov r13, rdi
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub rdi, rbx
	mov r14, rdi
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [rbx + r11]
	lea rbx, [r14 + rdi]
	lea rdi, [r13 + rbx]
	sub r12, rdi
	mov r12, r12
	sub r9, r12
	mov rbx, r9
	lea rdi, [r8 + rbx]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r8, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea r9, [r8 + rbx]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r8, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea r8, [r8 + rbx]
	sub r9, r8
	mov r9, r9
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov r8, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r8, [r8 + r11]
	lea rbx, [rbx + r8]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r8, [r10 + r11]
	sub rbx, r8
	mov rbx, rbx
	sub r9, rbx
	mov r9, r9
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r8, [r10 + r11]
	sub rbx, r8
	mov rbx, rbx
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov r8, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r8, [r8 + r11]
	lea r12, [rbx + r8]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov r8, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r8, [r8 + r11]
	lea rbx, [rbx + r8]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r13, [r10 + r11]
	sub rbx, r13
	mov r8, rbx
	sub r12, r8
	mov rbx, r12
	lea r9, [r9 + rbx]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r8, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub r8, rbx
	mov r8, r8
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea r8, [r8 + rbx]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r12, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea r12, [r12 + rbx]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub r12, rbx
	mov r13, r12
	sub r8, r13
	mov r12, r8
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r8, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub r8, rbx
	mov r8, r8
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea r13, [r8 + rbx]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r8, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r14, [rbx + r11]
	lea rbx, [r8 + r14]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r8, [r10 + r11]
	sub rbx, r8
	mov r14, rbx
	sub r13, r14
	mov rbx, r13
	lea r12, [r12 + rbx]
	lea r8, [r9 + r12]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r10 + r11]
	sub rbx, r9
	mov r12, rbx
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub r9, rbx
	mov r9, r9
	lea r12, [r12 + r9]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r13, [r10 + r11]
	sub rbx, r13
	mov r9, rbx
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r13, [rbx + r11]
	lea rbx, [r9 + r13]
	lea r12, [r12 + rbx]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r13, [rbx + r11]
	lea rbx, [r9 + r13]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r10 + r11]
	sub rbx, r9
	mov r13, rbx
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub r9, rbx
	mov r9, r9
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea r9, [r9 + rbx]
	lea rbx, [r13 + r9]
	sub r12, rbx
	mov r12, r12
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea r9, [r9 + rbx]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub r9, rbx
	mov r9, r9
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r13, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub r13, rbx
	mov r13, r13
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea r13, [r13 + rbx]
	lea r9, [r9 + r13]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r13, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea r13, [r13 + rbx]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r14, [r10 + r11]
	sub r13, r14
	mov rbx, r13
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov r13, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r13, [r13 + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r14, [r10 + r11]
	sub r13, r14
	mov r13, r13
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov r14, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r14, [r14 + r11]
	lea r13, [r13 + r14]
	lea rbx, [rbx + r13]
	sub r9, rbx
	mov r9, r9
	sub r12, r9
	mov rbx, r12
	lea r8, [r8 + rbx]
	sub rdi, r8
	mov rbx, rdi
	mov qword[rel GLOBAL_V_B], rbx
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub rdi, rbx
	mov rdi, rdi
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r8, [r10 + r11]
	sub rbx, r8
	mov rbx, rbx
	lea r8, [rdi + rbx]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [r10 + r11]
	sub rbx, rdi
	mov rbx, rbx
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rdi, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [rdi + r11]
	lea rbx, [rbx + rdi]
	lea rdi, [r8 + rbx]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov r8, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r8, [r8 + r11]
	lea rbx, [rbx + r8]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r8, [r10 + r11]
	sub rbx, r8
	mov r12, rbx
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r8, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub r8, rbx
	mov r8, r8
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [rbx + r11]
	lea rbx, [r8 + r9]
	lea r8, [r12 + rbx]
	sub rdi, r8
	mov rdi, rdi
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov r8, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r8, [r8 + r11]
	lea r9, [rbx + r8]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov r8, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r8, [r8 + r11]
	lea rbx, [rbx + r8]
	sub r9, rbx
	mov r9, r9
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r8, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r12, [rbx + r11]
	lea rbx, [r8 + r12]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r8, [r10 + r11]
	sub rbx, r8
	mov rbx, rbx
	sub r9, rbx
	mov r8, r9
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r10 + r11]
	sub rbx, r9
	mov rbx, rbx
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov r9, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r9 + r11]
	lea r12, [rbx + r9]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea r9, [r9 + rbx]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub r9, rbx
	mov r9, r9
	sub r12, r9
	mov r12, r12
	lea rbx, [r8 + r12]
	sub rdi, rbx
	mov r8, rdi
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub rdi, rbx
	mov rdi, rdi
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r10 + r11]
	sub rbx, r9
	mov rbx, rbx
	lea r9, [rdi + rbx]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [r10 + r11]
	sub rbx, rdi
	mov rbx, rbx
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rdi, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [rdi + r11]
	lea rbx, [rbx + rdi]
	lea rdi, [r9 + rbx]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov r9, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r9 + r11]
	lea rbx, [rbx + r9]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r10 + r11]
	sub rbx, r9
	mov r12, rbx
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r10 + r11]
	sub rbx, r9
	mov r13, rbx
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea r9, [r13 + rbx]
	lea rbx, [r12 + r9]
	sub rdi, rbx
	mov rdi, rdi
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r12, [rbx + r11]
	lea rbx, [r9 + r12]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r10 + r11]
	sub rbx, r9
	mov r13, rbx
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r10 + r11]
	sub rbx, r9
	mov r12, rbx
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea r12, [r12 + rbx]
	lea r9, [r13 + r12]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov r12, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r12, [r12 + r11]
	lea r13, [rbx + r12]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub r13, rbx
	mov r12, r13
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r13, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub r13, rbx
	mov r13, r13
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea r13, [r13 + rbx]
	lea rbx, [r12 + r13]
	sub r9, rbx
	mov r9, r9
	sub rdi, r9
	mov rbx, rdi
	lea r8, [r8 + rbx]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [rbx + r11]
	lea rbx, [r9 + rdi]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov r9, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r9 + r11]
	lea rdi, [rdi + r9]
	sub rbx, rdi
	mov r9, rbx
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rdi, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [rdi + r11]
	lea rbx, [rbx + rdi]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [r10 + r11]
	sub rbx, rdi
	mov rbx, rbx
	sub r9, rbx
	mov r9, r9
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [r10 + r11]
	sub rbx, rdi
	mov rbx, rbx
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rdi, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [rdi + r11]
	lea r12, [rbx + rdi]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rdi, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [rdi + r11]
	lea rbx, [rbx + rdi]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r13, [r10 + r11]
	sub rbx, r13
	mov rdi, rbx
	sub r12, rdi
	mov rbx, r12
	lea r12, [r9 + rbx]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rdi, [r10 + r11]
	sub rbx, rdi
	mov rbx, rbx
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rdi, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [rdi + r11]
	lea rdi, [rbx + r9]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov r9, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r9 + r11]
	lea r13, [rbx + r9]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub r13, rbx
	mov r9, r13
	sub rdi, r9
	mov rdi, rdi
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub r9, rbx
	mov r9, r9
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea r9, [r9 + rbx]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r13, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea r13, [r13 + rbx]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub r13, rbx
	mov r13, r13
	sub r9, r13
	mov r9, r9
	lea rbx, [rdi + r9]
	lea rdi, [r12 + rbx]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r10 + r11]
	sub rbx, r9
	mov r12, rbx
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub r9, rbx
	mov r9, r9
	lea r12, [r12 + r9]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub r9, rbx
	mov r9, r9
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea r9, [r9 + rbx]
	lea r12, [r12 + r9]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea r9, [r9 + rbx]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub r9, rbx
	mov r13, r9
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub r9, rbx
	mov r9, r9
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea r9, [r9 + rbx]
	lea rbx, [r13 + r9]
	sub r12, rbx
	mov r12, r12
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea r9, [r9 + rbx]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub r9, rbx
	mov r9, r9
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r13, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub r13, rbx
	mov r13, r13
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	lea r14, [r13 + rbx]
	lea r13, [r9 + r14]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov r9, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r9 + r11]
	lea r14, [rbx + r9]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [r10 + r11]
	sub r14, rbx
	mov r14, r14
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov rbx, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea rbx, [rbx + r11]
	mov r10, qword[rel GLOBAL_V_A]
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r10 + r11]
	sub rbx, r9
	mov rbx, rbx
	mov r10, qword[rel GLOBAL_V_C]
	mov r11, qword[rel GLOBAL_V_A]
	sub r10, r11
	mov r9, r10
	mov r11, qword[rel GLOBAL_V_B]
	lea r9, [r9 + r11]
	lea rbx, [rbx + r9]
	lea r14, [r14 + rbx]
	sub r13, r14
	mov r9, r13
	sub r12, r9
	mov rbx, r12
	lea rdi, [rdi + rbx]
	sub r8, rdi
	mov rbx, r8
	mov qword[rel GLOBAL_V_C], rbx
	jmp ____main_4____while_loop

____main_9____while_merge:
	mov r10, rsi
	mov rax, r10
	xor rdx, rdx
	cqo
	mov r10, 1000
	idiv r10
	mov rbx, rdx
	mov r11, 0
	cmp rbx, r11
	jne ____main_11____if_false

____main_10____if_true:
	mov r10, qword[rel GLOBAL_V_A]
	mov qword[rsp+32], rsi
	mov qword[rsp+40], rdi
	mov qword[rsp+72], r9
	mov qword[rsp+64], r8
	mov rdi, r10
	sub rsp, 8
	call Mx_builtin_toString
	add rsp, 8
	mov rsi, qword[rsp+32]
	mov rdi, qword[rsp+40]
	mov r9, qword[rsp+72]
	mov r8, qword[rsp+64]
	mov rbx, rax
	xor rax, rax
	mov r10, rbx
	mov r11, Mx__mxg#0
	mov qword[rsp+32], rsi
	mov qword[rsp+40], rdi
	mov qword[rsp+72], r9
	mov qword[rsp+64], r8
	mov rdi, r10
	mov rsi, r11
	sub rsp, 8
	call Mx_builtin_str_concatenate
	add rsp, 8
	mov rsi, qword[rsp+32]
	mov rdi, qword[rsp+40]
	mov r9, qword[rsp+72]
	mov r8, qword[rsp+64]
	mov rbx, rax
	xor rax, rax
	mov r10, qword[rel GLOBAL_V_B]
	mov qword[rsp+32], rsi
	mov qword[rsp+40], rdi
	mov qword[rsp+72], r9
	mov qword[rsp+64], r8
	mov rdi, r10
	sub rsp, 8
	call Mx_builtin_toString
	add rsp, 8
	mov rsi, qword[rsp+32]
	mov rdi, qword[rsp+40]
	mov r9, qword[rsp+72]
	mov r8, qword[rsp+64]
	mov rdi, rax
	xor rax, rax
	mov r10, rbx
	mov r11, rdi
	mov qword[rsp+32], rsi
	mov qword[rsp+40], rdi
	mov qword[rsp+72], r9
	mov qword[rsp+64], r8
	mov rdi, r10
	mov rsi, r11
	sub rsp, 8
	call Mx_builtin_str_concatenate
	add rsp, 8
	mov rsi, qword[rsp+32]
	mov rdi, qword[rsp+40]
	mov r9, qword[rsp+72]
	mov r8, qword[rsp+64]
	mov rbx, rax
	xor rax, rax
	mov r10, rbx
	mov r11, Mx__mxg#1
	mov qword[rsp+32], rsi
	mov qword[rsp+40], rdi
	mov qword[rsp+72], r9
	mov qword[rsp+64], r8
	mov rdi, r10
	mov rsi, r11
	sub rsp, 8
	call Mx_builtin_str_concatenate
	add rsp, 8
	mov rsi, qword[rsp+32]
	mov rdi, qword[rsp+40]
	mov r9, qword[rsp+72]
	mov r8, qword[rsp+64]
	mov rbx, rax
	xor rax, rax
	mov r10, qword[rel GLOBAL_V_C]
	mov qword[rsp+32], rsi
	mov qword[rsp+40], rdi
	mov qword[rsp+72], r9
	mov qword[rsp+64], r8
	mov rdi, r10
	sub rsp, 8
	call Mx_builtin_toString
	add rsp, 8
	mov rsi, qword[rsp+32]
	mov rdi, qword[rsp+40]
	mov r9, qword[rsp+72]
	mov r8, qword[rsp+64]
	mov rdi, rax
	xor rax, rax
	mov r10, rbx
	mov r11, rdi
	mov qword[rsp+32], rsi
	mov qword[rsp+40], rdi
	mov qword[rsp+72], r9
	mov qword[rsp+64], r8
	mov rdi, r10
	mov rsi, r11
	sub rsp, 8
	call Mx_builtin_str_concatenate
	add rsp, 8
	mov rsi, qword[rsp+32]
	mov rdi, qword[rsp+40]
	mov r9, qword[rsp+72]
	mov r8, qword[rsp+64]
	mov rbx, rax
	xor rax, rax
	mov r10, rbx
	mov qword[rsp+32], rsi
	mov qword[rsp+40], rdi
	mov qword[rsp+72], r9
	mov qword[rsp+64], r8
	mov rdi, r10
	sub rsp, 8
	call Mx_builtin_println
	add rsp, 8
	mov rsi, qword[rsp+32]
	mov rdi, qword[rsp+40]
	mov r9, qword[rsp+72]
	mov r8, qword[rsp+64]
	xor rax, rax

____main_12____if_merge:

____main_13____for_loop:
	mov r10, rsi
	add r10, 1
	mov rsi, r10
	jmp ____main_2____for_condition

____main_11____if_false:
	jmp ____main_12____if_merge

____main_6____logical_false:
	mov rbx, 0
	jmp ____main_7____logical_merge

____main_14____for_merge:
	mov rax, 0
	jmp ____main_15____exit

____main_15____exit:
	add rsp, 6344
	pop rbp
	ret
SECTION .data
      dq                    1
Mx__mxg#0:
      db               " ", 0
      dq                    1
Mx__mxg#1:
      db               " ", 0
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
GLOBAL_V_A:
    resq                    1
GLOBAL_V_B:
    resq                    1
GLOBAL_V_C:
    resq                    1
@getIntBuf:
    resq                    1
@parseIntBuf:
    resq                    1

