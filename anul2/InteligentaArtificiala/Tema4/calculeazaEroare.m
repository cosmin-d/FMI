function [  ] = calculeazaEroare( exempleTest,pol )

y=polyval(pol,exempleTest(:,1));
%table(exempleTest(:,1),exempleTest(:,2),y,exempleTest(:,2)-y,'VariableNames',{'X','Y','Fit','FitError'})
z=exempleTest(:,2)-.y;
exempleTest(:,2)
y
z

end

