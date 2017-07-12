.data 
string1: .asciiz "Dati numarul n pentru matricea de dimensiune n x n : \n"
string2: .asciiz "Dati elementele matricei: \n"
string3: .asciiz "Dati numarul liniei 1 : \n"
string4: .asciiz "Dati numarul liniei 2: \n"
string5: .asciiz "Matricea este : \n"
string6: .asciiz "\n"
n:.word 0
l1:.word 0
l2:.word 0
elem:.space 64
.text
subrutina:
move $t4,$a0
move $t5,$a1
move $t6,$a2 #adresa primului element din vector
addi $t4,-1
addi $t5,-1
li $s0,4
mulo $t4,$t4,$s0
mulo $t5,$t5,$s0
mulo $t4,$t4,$t0
mulo $t5,$t5,$t0
add $t4,$t4,$t6
add $t5,$t5,$t6
li $t3,0
loop1:
beq $t0,$t3,end_loop1
mulo $s0,$s0,$t3
addi $t3,1
add $s1,$t4,$s0
add $s2,$t5,$s0
lw $s3,($s1)
lw $s4,($s2)
sw $s4,($s1)
sw $s3,($s2)
b loop1
end_loop1:
jr $ra

main:
li $v0,4
la $a0,string1
syscall
li $v0,5
syscall
sw $v0,n       #citire n
lw $t0,n
move $t1,$t0
mulo $t1,$t1,$t1  # nxn
la $t2,elem  #adresa inceput vector
li $v0,4
la $a0,string2
syscall
li $t3,0
loop:
beq $t1,$t3,end_loop
addi $t3,1
li $v0,5
syscall
sw $v0,($t2)
addi $t2,4 
b loop
end_loop:
li $v0,4
la $a0,string3
syscall
li $v0,5
syscall
sw $v0,l1
li $v0,4
la $a0,string4
syscall
li $v0, 5 
syscall
sw $v0 ,l2
lw $a0,l1
lw $a1,l2
la $a2,elem
jal subrutina
li $t3,0
li $t0,0
li $v0,4
la $a0, string5
syscall
loop_afisare:
beq $t1,$t0,end_loop_afisare
addi $t0,1
loop_2:
beq $t1,$t3,end_loop_2
addi $t3,1
lw $a0,($t2)
li $v0,1
syscall
addi $t2,4
b loop_2
end_loop_2:
li $v0,4
la $a0,string6
syscall
b loop_afisare
end_loop_afisare:
li $v0,10
syscall


