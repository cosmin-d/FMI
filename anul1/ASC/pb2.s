.data 
string1: .asciiz "Dati numarul de elemente ale vectorului.\n" 
string3: .asciiz "Dati elementele vectorului.\n" 
string2: .asciiz "Vectorul este:\n" 
n:.word 0 
elem: .space 64  
.text 
main:  
li $v0, 4   
la $a0,string1 
syscall   
li $v0, 5  
syscall  
sw $v0, n  
lw $t0,n  #memorare nr de elem n in reg t0 
li $t1,0 
la $t2, elem  #initializare t2 cu adresa la care se memoreaza primul element  
li $v0, 4  #afisare mesaj de introdus elemente 
la $a0,string3 
syscall   
loop: 
beq $t0,$t1,end_loop 
addi $t1,1  #contorul: cand t1 ajunge la t0 = n atunci iese din bucla 
li $v0, 5  #in bucla se citeste fiecare element  
syscall  
sw $v0, ($t2) #se pune elementul la urmatoarea adresa in zona de date;  
addi$t2,4  # adresarea se face din 4 in 4 
b loop  
end_loop: 
li $v0, 4   
la $a0,string2 
syscall    
li $t1,0 
la $t2, elem  
loop_afisare: #afisarea valorilor 
beq $t0,$t1,end_loop_afisare 
addi $t1,1  #contorul: cand t1 ajunge la t0=n atunci iese din bucla  
lw $a0,($t2) #afisarea valorii de la adresa memorata in t2 
li $v0,1 
syscall 
addi $t2,4 
b loop_afisare 
end_loop_afisare:  
li $v0,10  #oprire executie program 
syscall  