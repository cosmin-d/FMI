#include <iostream>
#include <fstream>

using namespace std;

struct nod{
    int info;
    nod *next;
    };
int n,a[50];
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

void BF(nod *&q,int k){
    int i;
    a[k]=1;
    while(v[k]!=NULL){
        adaugare(q,v[k]->info);
        a[v[k]->info]=1;
        v[k]=v[k]->next;
        }

        for(i=1;i<=n;i++){
        if(a[i]!=1)
            adaugare(q,i);
        }
    }



int main()
{
    int i,x,a;
    fstream f;
    f.open("in.txt",ios::in);
    f>>n;
    nod *bf;
    bf=NULL;

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

    cout<<"nodul de la care porneste parcurgerea BF "; cin>>a;
    BF(bf,a);
    cout<<"parcurgerea BF"<<endl<<a<<' ';
    afisare(bf);
    cout<<endl;


    return 0;
}
