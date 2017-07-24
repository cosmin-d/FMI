function [A] = gm( n )
A=zeros(n,n+1);
ind2=1:n+1:n*n;
A(ind2)=2;
indmin1=2:n+1:n*n;
indmin2=n+1:n+1:n*(n+1);
A([indmin1 indmin2])=-1;

end

