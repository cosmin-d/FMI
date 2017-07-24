function [ A ] = genereazaMatrice( n )
A=zeros(n,n+1);
for linii=1:n
    for coloane=1:n+1
        if linii==coloane
            A(linii,coloane)=2;
        else
            if abs(linii-coloane)==1
                A(linii,coloane)=-1;
            end
        end
    end
end


end

