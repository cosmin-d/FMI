t1 = zeros(1,20);
t2 = zeros(1,20);
 
for i = 10:20
    a = rand(i,i);
    b = rand(i,i);
   
    tic;
    a*b;
    t1(i) = toc;
   
    tic;
    multiply(a, b);
    t2(i) = toc;
end
 
plot(t1, 'b')
hold on;
plot(t2, 'r')
hold off;
 
ylabel('Timp');
xlabel('Dim');
xlim([10 20]);