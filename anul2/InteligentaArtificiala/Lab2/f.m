function [ y ] = f( x )

y = zeros(size(x));
%for i=1:length(x)
%    if x(i) <= 2
 %  else
  %      y(i) = 3 * x(i) * x(i);
   % end
%end
%index1=x<=2;
y(x<=2)=2*x(x<=2)+8;
%index2=x>2;
y(x>2)=3*x(x>2).^2;
end

