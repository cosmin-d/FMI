clear all
clc
load('trainData.mat')
load('testData.mat')
trainVectors=trainVectors';
trainLabels=trainLabels';
trainVectors1=trainVectors1';
trainLabels1=trainLabels1';
total = size(trainVectors, 2);
antrenare = floor(total * 0.90); %rotunjeste rezultatul 
verificare = total - antrenare;
ind = randperm(total);%amsteca datele de intrare
ind1 = ind(1:antrenare);%genereaza indicii pentru antrenare
ind2 = ind(antrenare + (1:verificare)); %genereaza indicii pentru testare
ind1=ind1';
x1 = trainVectors(:, ind1);%genereaza date pentru antrenare
t1 = trainLabels(:, ind1);% genereaza etichete pentru antrenare
x2 = trainVectors(:, ind2);%genereaza date pentru testare 
t2 = trainLabels(:, ind2);% genereaza etichete pentru testare
t = 0.2*randn(size(x1));%genereaza zgomot random in [0,0.2]
xgen=x1+t;%genereaza date 'alterate'
xgen2=x1-t;%genereaza date 'alterate'
t = 0.2*randn(size(x1));%genereaza zgomot random in [0,0.2]
xgen3=x1+t;%genereaza date 'alterate'
xgen4=x1-t;%genereaza date 'alterate'
t = 0.2*randn(size(x1));%genereaza zgomot random in [0,0.2]
xgen5=x1+t;%genereaza date 'alterate'
xgen6=x1-t;%genereaza date 'alterate'
t = 0.2*randn(size(x1));%genereaza zgomot random in [0,0.2]
xgen7=x1+t;%genereaza date 'alterate'
xgen8=x1-t;%genereaza date 'alterate'
xtrain=[xgen xgen2 xgen3 xgen4 xgen5 xgen6 xgen7 xgen8];%concateneaza datele de antrenare alterate 
ttrain=[t1 t1 t1 t1 t1 t1 t1 t1];%concateneaza etichetele 
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
classes=zeros(5,size(xtrain,2));
for i=1:size(xtrain,2)
if(ttrain(1,i)==1)
classes(1,i)=1;
end
end
for i=1:size(xtrain,2)
if(ttrain(1,i)==2)
classes(2,i)=1;
end
end
for i=1:size(xtrain,2)
if(ttrain(1,i)==3)                     % am incercat sa folosesc ind2vec,
classes(3,i)=1;                  %dar parea ceva in neregula cu datele , asa ca am facut asta manual
end
end
for i=1:size(xtrain,2)
if(ttrain(1,i)==4)
classes(4,i)=1;
end
end
for i=1:size(xtrain,2)
if(ttrain(1,i)==5)
classes(5,i)=1;
end
end
%%%%%%%%%%%%%%%%%%%%  net initialization
net = patternnet(100);
net.trainFcn='traincgp';
net.performFcn = 'crossentropy';
net.performParam.regularization =0.25 ;
[net,tr] = train(net,xtrain,classes);
%%%%%%%%%%%%%%%%%%%%%%%%%%
inputs=x2;
outputs=net(inputs);
t2=ind2vec(t2);
figure, plotconfusion(outputs,t2) %ploteaza matricea de confuzie pentru acel set de 10% pe care nu l-a vazut niciodata 
%%%%%%%%%%%%%%%%%%%%%%%%%%%
testVectors=testVectors';
rez=sim(net,testVectors);
rez=vec2ind(rez);              %prezice etichete pentru datele de test
rez=rez';
%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%10-fold cross validation%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%
labels=zeros(5,size(trainVectors,2));
for i=1:size(trainVectors,2)
if(trainLabels(1,i)==1)
labels(1,i)=1;
end
end
for i=1:size(trainVectors,2)
if(trainLabels(1,i)==2)
labels(2,i)=1;
end
end
for i=1:size(trainVectors,2)    %generez vector de etichete pentru datele de intrare
if(trainLabels(1,i)==3)
labels(3,i)=1;
end
end
for i=1:size(trainVectors,2)
if(trainLabels(1,i)==4)
labels(4,i)=1;
end
end
for i=1:size(trainVectors,2)
if(trainLabels(1,i)==5)
labels(5,i)=1;
end
end
%%%%%%%%%%%%%%%%%%%%%%%%%%
partitions = cvpartition(length(trainVectors),'kfold',10);
totalAccuracy = 0;
for i=1:10
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
net = patternnet(100);
net.trainFcn='traincgp';            %initializare retea 
net.performFcn = 'crossentropy';
net.performParam.regularization =0.25 ;
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
trainIndex{i} = find(partitions.training(i));%gasesc partitia de antrenare cu indicele i
testIndex{i}  = find(partitions.test(i));%gasesc partitia de test cu indicele i 
testV{i} = trainVectors(:, testIndex{i});%generez partitie pentru testare 
testL{i} = labels(:, testIndex{i});%generez etichete pentru testare 
trainV{i} = trainVectors(:, trainIndex{i});%generez partitie pentru antrenare 
trainL{i} = labels(:, trainIndex{i});%generez etichete pentru antrenare
i
[net,tr] = train(net,trainV{i},trainL{i});%antrenez
crossValLabel=sim(net,testV{i});%prezic etichete
figure, plotconfusion(testL{i},crossValLabel);%plotez matricea de confuzie 
crossValLabel=vec2ind(crossValLabel);
checkLabel=vec2ind(testL{i});
matchedLabels = sum(crossValLabel == checkLabel);%verific cate etichete sunt prezise corect 
accuracy = matchedLabels / length(testIndex{i});%calculez   performanta
totalAccuracy = totalAccuracy + accuracy%retin performantele
end
crossValidationAccuracy = totalAccuracy / 10 %calculez performanta medie 