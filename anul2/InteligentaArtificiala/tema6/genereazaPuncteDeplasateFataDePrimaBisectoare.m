function [ a ] = genereazaPuncteDeplasateFataDePrimaBisectoare( m , deplasare )
a=rand(3,m)*2-1
for i=1:m
if(a(1,i)<a(2,i))% et 1
a(2,i)=a(2,i)+deplasare;
a(3,i)=1;
else
    a(2,i)=a(2,i)-deplasare;  %et -1
 a(3,i)=-1;
end
end
end

