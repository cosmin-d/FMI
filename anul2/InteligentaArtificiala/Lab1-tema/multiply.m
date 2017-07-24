function [ C ] = multiply(A,B)
if(size(A,2)==size(B,1))
    C=zeros(size(A,1),size(B,2));
    for i=1:size(A,1)
        for j=1:size(A,2)
            for k=1:size(B,1)
            
              C(i,k)=C(i,k)+A(i,j)*B(j,k);  
            end
        end
    end
else errordlg('Imposibil de calculat');


end

