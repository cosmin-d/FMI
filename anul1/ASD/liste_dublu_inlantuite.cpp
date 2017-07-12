#include<iostream>
#include<conio.h>
using namespace std;

struct nod{
  int nr;
  nod* ls,*ld;
};

nod *s,*d,*c,*e;
int n,m,i,val;
void creare(nod*& s,nod*& d){
  cout<<"info=";cin>>n;
  s= new nod;
  s->nr=n;
  	s->ld=0;
  s->ls=0;
  d=s;
}

void adaug_s(nod*& s,nod* d){
  nod*c;
  cout<<"info:";cin>>n;
  c=new nod;
  c->nr=n;
  c->ls=0;
  c->ld=s;
  s->ls=c;
  s=c;
}

void adaug_d(nod* s,nod*& d){
  cout<<"info=";cin>>n;
  c=new nod;
  c->nr=n;
  c->ld=0;
  d->ld=c;
  c->ls=d;
  d=c;
}

void adaug_i(nod* s,nod*& d){
  cout<<"Dupa care inregistrare adaugam?";cin>>val;
  cout<<"info=";cin>>n;
  nod* c=s;
  while (c->nr!=val) c=c->ld;
  if (c==d){
    	e=new nod;
    	e->nr=n;
    	e->ld=0;
    	d->ld=e;
    	e->ls=d;
     d=e;
  }else{
     e=new nod;
    	e->nr=n;
    	e->ld=c->ld;
    	c->ld->ls=e;
    	e->ls=c;
    	c->ld=e;
  }
}

void sterg_s(nod*& s,nod*& d){
  c=s;
  s=s->ld;
  s->ls=0;
  delete c;
}

void sterg_d(nod*& s,nod*& d){
  c=d;
  d=d->ls;
  d->ld=0;
  delete c;
}

void sterg_i(nod*& s,nod*& d){
  nod* c=s,*f;
  int st;
  cout<<"Ce valoare din interiorul listei stergeti?";cin>>st;
  if (s->nr==st) sterg_s(s,d);
  else if (d->nr==st) sterg_d(s,d);
  else {
    	while(c->ld->nr!=st) c=c->ld;
    	f=c->ld;
    	f->ld->ls=c;
    	c->ld=f->ld;
    	delete f;
  }
}

void sterg(nod*& s,nod*& d,int n){
  if (s==d){
    	e=s;
    	s=d=0;
  }else{
    	if (s->nr==n){
      	e=s;
      	s=s->ld;
      	e->ld=0;
      	s->ls=0;
    	} else if (d->nr==n){
      	e=d;
      	d=d->ls;
      	e->ls=0;
      	d->ld=0;
    }else{
      	c=s;
      	while(c->ld->nr!=n)c=c->ld;
      	e=c->ld;
      	e->ld->ls=c;
      	c->ld=e->ld;
      	e->ld=0;e->ls=0;
      	if (e=d) d=c;
    	}
  }
  delete e;
}

void listare_s(nod* s,nod *d){
  if (!s){
    	cout<<"Lista este vida!";
  }else{
    	c=s;
    	cout<<"Listez de la stanga la dreapta: ";
    	while(c){
      	cout<<c->nr<<" ";
      	c=c->ld;
    	}
  }
  cout<<endl;
}

void listare_d(nod*s ,nod* d){
  if (!s){
    	cout<<"Lista este vida!";
  }else{
    	c=d;
    	cout<<"Listez de la dreapta la stanga: ";
    	while(c){
      	cout<<c->nr<<" ";
      	c=c->ls;
    	}
  }
  cout<<endl;
}

int main(int argc,char *argv[]){
  int val0,val1;
  cout<<"Creez primul nod al listei."<<endl;
  creare(s,d);
  do{
    	cout<<"Cate inregistari adaugam la dreapta?";
    	cin>>val0;
  }while (val0<1);
    	for (i=1;i<=val0;i++){adaug_d(s,d);}
  do{
    	cout<<"Cate inregistrari adaugam la stanga?";
    	cin>>val1;
  }while (val1<1);
  for (i=1;i<=val1;i++) {adaug_s(s,d);}
  listare_s(s,d);
  listare_d(s,d);
  cout<<endl;
  cout<<"Inseram un nod in interiorul listei:"<<endl;
  adaug_i(s,d);
  listare_s(s,d);
  listare_d(s,d);
  cout<<endl;
  getch();
  cout<<"Stergem un nod din stanga listei:"<<endl;
  	sterg_s(s,d);
  	listare_s(s,d);
  listare_d(s,d);
  cout<<endl;
  getch();
  cout<<"Stergem un nod din dreapta listei:"<<endl;
  sterg_d(s,d);
  listare_s(s,d);
  listare_d(s,d);
  cout<<endl;
  getch();
  cout<<"Stergem un nod din interiorul listei:"<<endl;
  sterg_i(s,d);
  listare_s(s,d);
  listare_d(s,d);
  cout<<endl;
  getch();
  cout<<"Stergem un nod oarecare al listei:"<<endl;
  cout<<"Ce nod stergem?";cin>>n;
  sterg(s,d,n);
  listare_s(s,d);
  listare_d(s,d);
  getch();
  return 0;
}
