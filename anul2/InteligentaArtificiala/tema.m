S = [0 0 0 0.5 0.5 0.5 1 1;0 0.5 1 0 0.5 1 0 0.5;1 1 1 1 -1 -1 -1 -1];

figure(1), hold on;
eticheta1 = find(S(3,:)==1);
etichetaMinus1 = find(S(3,:)==-1);
plot(S(1,eticheta1),S(2,eticheta1),'or');
plot(S(1,etichetaMinus1),S(2,etichetaMinus1),'o');
axis([-2 2 -2 2]);
d=size(S,1)-1;
w=rand(1,d);
b=rand();
b
w