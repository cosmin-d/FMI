x=[-1 -1 -1 -1 -1 -0.5 -0.5 -0.5 -0.5 -0.5 0 0 0 0 0 0.5 0.5 0.5 0.5 0.5 1 1 1 1 1;...
   -1 -0.5 0 0.5 1 -1 -0.5 0 0.5 1 -1 -0.5 0 0.5 1 -1 -0.5 0 0.5 1 -1 -0.5 0 0.5 1];
y=[0 0 0 0 0 1 0 0 0 0 1 1 0 0 0 1 1 1 0 0 1 1 1 1 0];
net=newp([-1 1;-1 1],1);
%net.iw{1,1}=[1 1];
%net.b{1}=0.9999999999;


net=train(net,x,y);
weight=net.iw{1,1};
bias=net.b{1};
net.b{1}
figure;
plotpv(x,y);
%keyboard;

plotpc(weight,bias);
%keyboard;
m=rand(2,10000)*2-1;
n=sim(net,m);

figure;
plotpv(m,n) 
%keyboard;
axis([-1.1 1.1 -1.1 1.1])  
line=plotpc(weight,net.b{1})
set(line, 'Color', 'r');
