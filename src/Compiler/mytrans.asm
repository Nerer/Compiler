default rel

extern printf, malloc, strcpy, scanf, strlen, sscanf, sprintf, memcpy, strcmp, puts
global GLOBAL_V_M
global GLOBAL_V_w
global GLOBAL_V_ch
global GLOBAL_V_r
global GLOBAL_V_N
global GLOBAL_V_l
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

merge:
	push rbp
	mov rbp, rsp
	sub rsp, 272

____merge_0____enter:

____merge_1____entry:
	mov r10, 0
	mov r11, qword [rsp+8]
	cmp r10, r11
	sete al
	movzx r10, al
	mov qword [rsp+24], r10
	mov r10, qword [rsp+24]
	cmp r10, 0
	jz ____merge_3____if_false

____merge_2____if_true:
	mov rax, qword [rsp+16]
	jmp ____merge_11____exit
	jmp ____merge_11____exit

____merge_3____if_false:

____merge_4____if_merge:
	mov r10, 0
	mov r11, qword [rsp+16]
	cmp r10, r11
	sete al
	movzx r10, al
	mov qword [rsp+32], r10
	mov r10, qword [rsp+32]
	cmp r10, 0
	jz ____merge_6____if_false

____merge_5____if_true:
	mov rax, qword [rsp+8]
	jmp ____merge_11____exit
	jmp ____merge_11____exit

____merge_6____if_false:

____merge_7____if_merge:
	mov r10, qword [rsp+8]
	mov r11, 8
	imul r10, r11
	mov qword [rsp+40], r10
	mov r10, [rel GLOBAL_V_w]
	mov r11, qword [rsp+40]
	add r10, r11
	mov qword [rsp+48], r10
	mov r10, qword [rsp+48]
	mov r10, qword[r10+0]
	mov qword [rsp+64], r10
	mov r10, qword [rsp+16]
	mov r11, 8
	imul r10, r11
	mov qword [rsp+72], r10
	mov r10, [rel GLOBAL_V_w]
	mov r11, qword [rsp+72]
	add r10, r11
	mov qword [rsp+80], r10
	mov r10, qword [rsp+80]
	mov r10, qword[r10+0]
	mov qword [rsp+88], r10
	mov r10, qword [rsp+64]
	mov r11, qword [rsp+88]
	cmp r10, r11
	setl al
	movzx r10, al
	mov qword [rsp+96], r10
	mov r10, qword [rsp+96]
	cmp r10, 0
	jz ____merge_9____if_false

____merge_8____if_true:
	mov r10, qword [rsp+8]
	mov qword [rsp+104], r10
	mov r10, qword [rsp+16]
	mov qword [rsp+8], r10
	mov r10, qword [rsp+104]
	mov qword [rsp+16], r10
	jmp ____merge_10____if_merge

____merge_9____if_false:

____merge_10____if_merge:
	mov r10, qword [rsp+8]
	mov r11, 8
	imul r10, r11
	mov qword [rsp+112], r10
	mov r10, [rel GLOBAL_V_r]
	mov r11, qword [rsp+112]
	add r10, r11
	mov qword [rsp+120], r10
	mov r10, qword [rsp+8]
	mov r11, 8
	imul r10, r11
	mov qword [rsp+136], r10
	mov r10, [rel GLOBAL_V_r]
	mov r11, qword [rsp+136]
	add r10, r11
	mov qword [rsp+144], r10
	mov r10, qword [rsp+144]
	mov r10, qword[r10+0]
	mov qword [rsp+152], r10
	mov r10, qword [rsp+152]
	mov [rsp-280], r10
	mov r10, qword [rsp+16]
	mov [rsp-272], r10
	call merge
	mov qword [rsp+160], rax
	xor rax, rax
	mov r10, qword [rsp+160]
	mov r11, qword [rsp+120]
	mov [r11+0], r10
	mov r10, qword [rsp+8]
	mov r11, 8
	imul r10, r11
	mov qword [rsp+168], r10
	mov r10, [rel GLOBAL_V_l]
	mov r11, qword [rsp+168]
	add r10, r11
	mov qword [rsp+176], r10
	mov r10, qword [rsp+176]
	mov r10, qword[r10+0]
	mov qword [rsp+192], r10
	mov r10, qword [rsp+192]
	mov qword [rsp+200], r10
	mov r10, qword [rsp+8]
	mov r11, 8
	imul r10, r11
	mov qword [rsp+208], r10
	mov r10, [rel GLOBAL_V_l]
	mov r11, qword [rsp+208]
	add r10, r11
	mov qword [rsp+216], r10
	mov r10, qword [rsp+8]
	mov r11, 8
	imul r10, r11
	mov qword [rsp+224], r10
	mov r10, [rel GLOBAL_V_r]
	mov r11, qword [rsp+224]
	add r10, r11
	mov qword [rsp+232], r10
	mov r10, qword [rsp+232]
	mov r10, qword[r10+0]
	mov qword [rsp+240], r10
	mov r10, qword [rsp+240]
	mov r11, qword [rsp+216]
	mov [r11+0], r10
	mov r10, qword [rsp+8]
	mov r11, 8
	imul r10, r11
	mov qword [rsp+248], r10
	mov r10, [rel GLOBAL_V_r]
	mov r11, qword [rsp+248]
	add r10, r11
	mov qword [rsp+256], r10
	mov r10, qword [rsp+200]
	mov r11, qword [rsp+256]
	mov [r11+0], r10
	mov rax, qword [rsp+8]
	jmp ____merge_11____exit

____merge_11____exit:
	add rsp, 272
	pop rbp
	ret
main:
	push rbp
	mov rbp, rsp
	sub rsp, 560

____main_0____enter:

____main_1____entry:
	call Mx_builtin_getInt
	mov qword [rsp+8], rax
	xor rax, rax
	mov r10, qword [rsp+8]
	mov qword [rel GLOBAL_V_N], r10
	call Mx_builtin_getInt
	mov qword [rsp+24], rax
	xor rax, rax
	mov r10, qword [rsp+24]
	mov qword [rel GLOBAL_V_M], r10
	call Mx_builtin_getString
	mov qword [rsp+40], rax
	xor rax, rax
	mov r10, qword [rsp+40]
	mov qword [rel GLOBAL_V_ch], r10
	mov r10, [rel GLOBAL_V_N]
	mov r11, [rel GLOBAL_V_M]
	add r10, r11
	mov qword [rsp+56], r10
	mov r10, qword [rsp+56]
	mov r11, 5
	add r10, r11
	mov qword [rsp+64], r10
	mov r10, qword [rsp+64]
	mov qword [rsp+72], r10
	mov r10, qword [rsp+72]
	mov r11, 1
	add r10, r11
	mov qword [rsp+72], r10
	mov r10, qword [rsp+72]
	mov r11, 8
	imul r10, r11
	mov qword [rsp+72], r10
	mov rdi, qword [rsp+72]
	call malloc
	mov qword [rsp+80], rax
	mov r10, qword [rsp+64]
	mov r11, qword [rsp+80]
	mov [r11+0], r10
	mov r10, qword [rsp+80]
	mov r11, 8
	add r10, r11
	mov qword [rsp+80], r10
	mov r10, qword [rsp+80]
	mov qword [rel GLOBAL_V_l], r10
	mov r10, [rel GLOBAL_V_N]
	mov r11, [rel GLOBAL_V_M]
	add r10, r11
	mov qword [rsp+96], r10
	mov r10, qword [rsp+96]
	mov r11, 5
	add r10, r11
	mov qword [rsp+104], r10
	mov r10, qword [rsp+104]
	mov qword [rsp+112], r10
	mov r10, qword [rsp+112]
	mov r11, 1
	add r10, r11
	mov qword [rsp+112], r10
	mov r10, qword [rsp+112]
	mov r11, 8
	imul r10, r11
	mov qword [rsp+112], r10
	mov rdi, qword [rsp+112]
	call malloc
	mov qword [rsp+120], rax
	mov r10, qword [rsp+104]
	mov r11, qword [rsp+120]
	mov [r11+0], r10
	mov r10, qword [rsp+120]
	mov r11, 8
	add r10, r11
	mov qword [rsp+120], r10
	mov r10, qword [rsp+120]
	mov qword [rel GLOBAL_V_r], r10
	mov r10, [rel GLOBAL_V_N]
	mov r11, [rel GLOBAL_V_M]
	add r10, r11
	mov qword [rsp+136], r10
	mov r10, qword [rsp+136]
	mov r11, 5
	add r10, r11
	mov qword [rsp+144], r10
	mov r10, qword [rsp+144]
	mov qword [rsp+152], r10
	mov r10, qword [rsp+152]
	mov r11, 1
	add r10, r11
	mov qword [rsp+152], r10
	mov r10, qword [rsp+152]
	mov r11, 8
	imul r10, r11
	mov qword [rsp+152], r10
	mov rdi, qword [rsp+152]
	call malloc
	mov qword [rsp+160], rax
	mov r10, qword [rsp+144]
	mov r11, qword [rsp+160]
	mov [r11+0], r10
	mov r10, qword [rsp+160]
	mov r11, 8
	add r10, r11
	mov qword [rsp+160], r10
	mov r10, qword [rsp+160]
	mov qword [rel GLOBAL_V_w], r10
	mov r10, 1
	mov qword [rsp+176], r10

____main_2____for_condition:
	mov r10, qword [rsp+176]
	mov r11, [rel GLOBAL_V_N]
	cmp r10, r11
	setle al
	movzx r10, al
	mov qword [rsp+184], r10
	mov r10, qword [rsp+184]
	cmp r10, 0
	jz ____main_5____for_merge

____main_3____for_body:
	mov r10, qword [rsp+176]
	mov r11, 8
	imul r10, r11
	mov qword [rsp+192], r10
	mov r10, [rel GLOBAL_V_w]
	mov r11, qword [rsp+192]
	add r10, r11
	mov qword [rsp+200], r10
	call Mx_builtin_getInt
	mov qword [rsp+208], rax
	xor rax, rax
	mov r10, qword [rsp+208]
	mov r11, qword [rsp+200]
	mov [r11+0], r10
	mov r10, qword [rsp+176]
	mov r11, 8
	imul r10, r11
	mov qword [rsp+216], r10
	mov r10, [rel GLOBAL_V_l]
	mov r11, qword [rsp+216]
	add r10, r11
	mov qword [rsp+224], r10
	mov r10, 0
	mov r11, qword [rsp+224]
	mov [r11+0], r10
	mov r10, qword [rsp+176]
	mov r11, 8
	imul r10, r11
	mov qword [rsp+232], r10
	mov r10, [rel GLOBAL_V_r]
	mov r11, qword [rsp+232]
	add r10, r11
	mov qword [rsp+240], r10
	mov r10, 0
	mov r11, qword [rsp+240]
	mov [r11+0], r10

____main_4____for_loop:
	mov r10, qword [rsp+176]
	mov qword [rsp+248], r10
	mov r10, qword [rsp+176]
	mov r11, 1
	add r10, r11
	mov qword [rsp+176], r10
	jmp ____main_2____for_condition

____main_5____for_merge:
	mov r10, 1
	mov qword [rsp+176], r10

____main_6____for_condition:
	mov r10, qword [rsp+176]
	mov r11, [rel GLOBAL_V_M]
	cmp r10, r11
	setle al
	movzx r10, al
	mov qword [rsp+256], r10
	mov r10, qword [rsp+256]
	cmp r10, 0
	jz ____main_9____for_merge

____main_7____for_body:
	mov r10, qword [rsp+176]
	mov r11, [rel GLOBAL_V_N]
	add r10, r11
	mov qword [rsp+264], r10
	mov r10, qword [rsp+264]
	mov r11, 8
	imul r10, r11
	mov qword [rsp+272], r10
	mov r10, [rel GLOBAL_V_w]
	mov r11, qword [rsp+272]
	add r10, r11
	mov qword [rsp+280], r10
	mov r10, qword [rsp+176]
	mov r11, 1
	sub r10, r11
	mov qword [rsp+288], r10
	mov rdi, [rel GLOBAL_V_ch]
	mov rsi, qword [rsp+288]
	call Mx_builtin_str_ord
	mov qword [rsp+296], rax
	xor rax, rax
	mov r10, qword [rsp+296]
	mov r11, qword [rsp+280]
	mov [r11+0], r10
	mov r10, qword [rsp+176]
	mov r11, [rel GLOBAL_V_N]
	add r10, r11
	mov qword [rsp+304], r10
	mov r10, qword [rsp+304]
	mov r11, 8
	imul r10, r11
	mov qword [rsp+312], r10
	mov r10, [rel GLOBAL_V_l]
	mov r11, qword [rsp+312]
	add r10, r11
	mov qword [rsp+320], r10
	mov r10, 0
	mov r11, qword [rsp+320]
	mov [r11+0], r10
	mov r10, qword [rsp+176]
	mov r11, [rel GLOBAL_V_N]
	add r10, r11
	mov qword [rsp+328], r10
	mov r10, qword [rsp+328]
	mov r11, 8
	imul r10, r11
	mov qword [rsp+336], r10
	mov r10, [rel GLOBAL_V_r]
	mov r11, qword [rsp+336]
	add r10, r11
	mov qword [rsp+344], r10
	mov r10, 0
	mov r11, qword [rsp+344]
	mov [r11+0], r10

____main_8____for_loop:
	mov r10, qword [rsp+176]
	mov qword [rsp+352], r10
	mov r10, qword [rsp+176]
	mov r11, 1
	add r10, r11
	mov qword [rsp+176], r10
	jmp ____main_6____for_condition

____main_9____for_merge:
	mov r10, 1
	mov qword [rsp+360], r10
	mov r10, [rel GLOBAL_V_N]
	mov r11, 1
	add r10, r11
	mov qword [rsp+368], r10
	mov r10, qword [rsp+368]
	mov qword [rsp+376], r10
	mov r10, 2
	mov qword [rsp+176], r10

____main_10____for_condition:
	mov r10, qword [rsp+176]
	mov r11, [rel GLOBAL_V_N]
	cmp r10, r11
	setle al
	movzx r10, al
	mov qword [rsp+384], r10
	mov r10, qword [rsp+384]
	cmp r10, 0
	jz ____main_13____for_merge

____main_11____for_body:
	mov r10, qword [rsp+360]
	mov [rsp-280], r10
	mov r10, qword [rsp+176]
	mov [rsp-272], r10
	call merge
	mov qword [rsp+392], rax
	xor rax, rax
	mov r10, qword [rsp+392]
	mov qword [rsp+360], r10

____main_12____for_loop:
	mov r10, qword [rsp+176]
	mov qword [rsp+400], r10
	mov r10, qword [rsp+176]
	mov r11, 1
	add r10, r11
	mov qword [rsp+176], r10
	jmp ____main_10____for_condition

____main_13____for_merge:
	mov r10, [rel GLOBAL_V_N]
	mov r11, 2
	add r10, r11
	mov qword [rsp+408], r10
	mov r10, qword [rsp+408]
	mov qword [rsp+176], r10

____main_14____for_condition:
	mov r10, [rel GLOBAL_V_N]
	mov r11, [rel GLOBAL_V_M]
	add r10, r11
	mov qword [rsp+416], r10
	mov r10, qword [rsp+176]
	mov r11, qword [rsp+416]
	cmp r10, r11
	setle al
	movzx r10, al
	mov qword [rsp+424], r10
	mov r10, qword [rsp+424]
	cmp r10, 0
	jz ____main_17____for_merge

____main_15____for_body:
	mov r10, qword [rsp+376]
	mov [rsp-280], r10
	mov r10, qword [rsp+176]
	mov [rsp-272], r10
	call merge
	mov qword [rsp+432], rax
	xor rax, rax
	mov r10, qword [rsp+432]
	mov qword [rsp+376], r10

____main_16____for_loop:
	mov r10, qword [rsp+176]
	mov qword [rsp+440], r10
	mov r10, qword [rsp+176]
	mov r11, 1
	add r10, r11
	mov qword [rsp+176], r10
	jmp ____main_14____for_condition

____main_17____for_merge:
	mov r10, qword [rsp+360]
	mov r11, 8
	imul r10, r11
	mov qword [rsp+448], r10
	mov r10, [rel GLOBAL_V_w]
	mov r11, qword [rsp+448]
	add r10, r11
	mov qword [rsp+456], r10
	mov r10, qword [rsp+456]
	mov r10, qword[r10+0]
	mov qword [rsp+464], r10
	mov rdi, qword [rsp+464]
	call Mx_builtin_toString
	mov qword [rsp+472], rax
	xor rax, rax
	mov rdi, qword [rsp+472]
	call Mx_builtin_print
	xor rax, rax
	mov rdi, Mx__mxg#0
	call Mx_builtin_print
	xor rax, rax
	mov r10, qword [rsp+376]
	mov r11, [rel GLOBAL_V_N]
	sub r10, r11
	mov qword [rsp+488], r10
	mov r10, qword [rsp+488]
	mov r11, 1
	sub r10, r11
	mov qword [rsp+496], r10
	mov r10, qword [rsp+376]
	mov r11, [rel GLOBAL_V_N]
	sub r10, r11
	mov qword [rsp+504], r10
	mov r10, qword [rsp+504]
	mov r11, 1
	sub r10, r11
	mov qword [rsp+512], r10
	mov rdi, [rel GLOBAL_V_ch]
	mov rsi, qword [rsp+496]
	mov rdx, qword [rsp+512]
	call Mx_builtin_str_substring
	mov qword [rsp+520], rax
	xor rax, rax
	mov rdi, qword [rsp+520]
	call Mx_builtin_print
	xor rax, rax
	mov rdi, Mx__mxg#1
	call Mx_builtin_print
	xor rax, rax
	mov r10, qword [rsp+360]
	mov [rsp-280], r10
	mov r10, qword [rsp+376]
	mov [rsp-272], r10
	call merge
	mov qword [rsp+536], rax
	xor rax, rax
	mov rdi, qword [rsp+536]
	call Mx_builtin_toString
	mov qword [rsp+544], rax
	xor rax, rax
	mov rdi, qword [rsp+544]
	call Mx_builtin_println
	xor rax, rax
	mov rax, 0
	jmp ____main_18____exit

____main_18____exit:
	add rsp, 560
	pop rbp
	ret
SECTION .data
      dq                    1
Mx__mxg#0:
      db               " ", 0
      dq                    2
Mx__mxg#1:
      db        "", 10, "", 0
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
GLOBAL_V_N:
    resq                    1
GLOBAL_V_M:
    resq                    1
GLOBAL_V_ch:
    resq                    1
GLOBAL_V_l:
    resq                    1
GLOBAL_V_r:
    resq                    1
GLOBAL_V_w:
    resq                    1
@getIntBuf:
    resq                    1
@parseIntBuf:
    resq                    1

