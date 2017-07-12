#include <iostream>
#include <fstream>

using namespace std;

struct nod{
    int info;
    nod *next;
    };

int n,b[50];
nod *v[50];

void adaugare(nod *&q,int x){
    nod *p,*ultim;
    if(q==NULL){
        q=new nod;
        q->info=x;
        q->next=NULL;
        ultim=q;
        }
    else{
        p=new nod;
        p->info=x;
        p->next=NULL;
        ultim->next=p;
        ultim=p;
        }
    }

void afisare(nod *q){
    while(q!=NULL){
        cout<<q->info<<' ';
        q=q->next;
        }
    }

void DF(int k){
    int x;
    b[k]=1;
    cout<<k<<' ';
    while(v[k]!=NULL){
        x=v[k]->info;
        if(b[x]==0)
            DF(x);
        v[k]=v[k]->next;
        }
    }

int main()
{
    int i,x,b;
    fstream f;
    f.open("in.txt",ios::in);
    f>>n;

    for(i=1;i<=n;i++){
        v[i]=NULL;
        f>>x;
        while(x!=NULL){
            adaugare(v[i],x);
            f>>x;
            }
        }
    f.close();

    cout<<"graful este "<<endl;
    for(i=1;i<=n;i++){
        cout<<i<<"->";
        afisare(v[i]);
        cout<<endl;
        }
    cout<<endl;

    cout<<"nodul de la care porneste parcurgerea DF "; cin>>b;
    cout<<"parcurgerea DF"<<endl;
    DF(b);


    return 0;
}

