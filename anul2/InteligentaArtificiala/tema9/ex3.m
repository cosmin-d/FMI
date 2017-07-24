X = [-1 0 1 -1 1 0;0 1 0 1 1 -1]; %input-urile
t = [1 1 1 0 0 0]; % target-urile
net=newff(minmax(X),[3 1],{'logsig','logsig'});%creare retea
view(net);%vizualizare retea
[net, info] = train(net,X,t);
a=sim(net,X)