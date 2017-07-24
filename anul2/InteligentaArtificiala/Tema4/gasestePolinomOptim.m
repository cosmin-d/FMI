function [ p ] = gasestePolinomOptim( exempleTest,n )
p=polyfit(exempleTest(:,1),exempleTest(:,2),n);


end

