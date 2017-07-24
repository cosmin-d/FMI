function [ ] = ploteazaGraficPolinom(exempleTest, p )
figure
plot(exempleTest(:,1),exempleTest(:,2),'o');
hold on
x=linspace(0,1,10);
y=polyval(p,x);
plot(x,y,'color','r');
hold off
    


end

