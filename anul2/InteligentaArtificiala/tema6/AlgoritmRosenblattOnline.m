function [x y err] = AlgoritmRosenblattOnline(S,nrEpoci )
figure(1), hold on;
eticheta1 = find(S(3,:)==1);
etichetaMinus1 = find(S(3,:)==-1);
plot(S(1,eticheta1),S(2,eticheta1),'or');
plot(S(1,etichetaMinus1),S(2,etichetaMinus1),'o');
axis([-2 2 -2 2]);
d=size(S,1)-1;
w=rand(1,d);
b=rand();
net = perceptron;
net.layers{1}.transferFcn = 'hardlims';
net = configure(net,S(1:end-1,:),S(end,:));
net.iw{1,1} = w;
net.b{1} = b;
%sim (net,[0;0])
e=0;
it=1;
[m n]=size(S);
x=w;y=b; f=0;
err=[];
while((it/n)<=nrEpoci)
   
    if(sim(net,[S(1,mod(it-1,n)+1);S(2,mod(it-1,n)+1)])~=S(3,mod(it-1,n)+1))
        x=[x;w];y=[y,b];
       w=w+[S(3,mod(it-1,n)+1)*S(1,mod(it-1,n)+1),S(3,mod(it-1,n)+1)*S(2,mod(it-1,n)+1)];
       b=b+S(3,mod(it-1,n)+1);
       net.iw{1,1} = w;
        net.b{1} = b;
        f=f+1;
    end
    if(mod(it,n)==0)
        e=e+1;
        f=f*100/n;
        err=[err,f];
        if(f==0)
       break
        end
        f=0;
    end
    it=it+1;
end;
z=['Nr.epoci: ',num2str(e)];
disp(z);
plotpc(net.iw{1,1},net.b{1})
end

