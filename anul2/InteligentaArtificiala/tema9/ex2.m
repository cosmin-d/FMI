net=newff([-2 2;-2 2],[3 1],{'hardlim','hardlim'});%definim reteaua
net.IW{1,1} = [1 -1;-1 -1;0 1]; %matricea de ponderi de pe primul strat
net.LW{2,1}=[1 1 1]; % matricea de ponderi de pe al doilea strat
net.b{1} = [1;1;0];
net.b{2} = -2.5; %bias-urile
X = rand(2,20000)*4 - 2;
t=sim(net,X);
 figure, hold on;
 eticheta1 = find(t==1);
 eticheta0 = find(t==0);
 plot(X(1,eticheta1),X(2,eticheta1),'xr');
 plot(X(1,eticheta0),X(2,eticheta0),'xg');
 axis([-2 2 -2 2]);
 hold off;