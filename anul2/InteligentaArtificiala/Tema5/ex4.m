x=[0 0 1 1;0 1 0 1];
y=[0 1 1 1];
%plotpv(x,y);
net=newp([0 1;0 1],1);
net.iw{1,1}=[1,1];
net.b{1}=0.5;
weight=net.iw{1,1};
bias=net.b{1};
net=train(net,x,y);
weight=net.iw{1,1};
bias=net.b{1};
figure;
plotpv(x,y);
plotpc(weight,bias);