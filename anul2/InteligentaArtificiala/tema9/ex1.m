X = [0 1 0 1; 0 0 1 1]; %input-urile
t = [1 0 0 1]; % target-urile
net=newff(minmax(X),[2 1],{'hardlim','hardlim'});%definim reteaua
net.IW{1,1} = [-1 -1; 1 1]; %matricea de ponderi de pe primul strat
net.LW{2,1}=[1 1]; % matricea de ponderi de pe al doilea strat
net.b{1} = [0.5;-1.5];
net.b{2} = -0.1; %bias-urile
a=sim(net,X)
isequal(a,t)