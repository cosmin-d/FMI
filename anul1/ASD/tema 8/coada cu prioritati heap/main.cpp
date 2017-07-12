#include <iostream>

using namespace std;

struct nod{
    int info;
    int p;
    nod *next;
    };

nod *q;
int n;

void asambleaza_max(int k,int x){
    int l,r,max;
    nod *maxx,*aux;

    l=2*k;
    r=2*k+1;

    while(q!=NULL){
    if(l<=n && q->p==l && q->info>x){
        max=l;
        maxx=q;
        }
    else{
        max=k;
        maxx=q;
        }
    q=q->next;
        }
    while(q!=NULL){
    if(r<=n && q->p==r && q->info>maxx->info){
        max=r;
        maxx=q;
        }
    q=q->next;
    }


    if(max!=k){
        aux=q;
        q=maxx;
        maxx=aux;
        }
    }

void heap(){
    while(q!=NULL){
        if(q->p>=1 && q->p<=n/2)
            maxim(q->p,q->info);
        q=q->next;
        }
    }


void inserare(int x,int i){
    nod *p,*ultim;

    if(q==NULL){
        q=new nod;
        q->info=x;
        q->p=i;
        q->next=NULL;
        ultim=q;
        }
    else{
        p=new nod;
        p->info=x;
        p->p=i;
        p->next=NULL;
        ultim->next=p;
        ultim=p;
        }
    heap();

    }

void afisare(int i){
    nod *t;
    while(t!=NULL){
        if(t->p==i){
            cout<<t->info<<' ';
            break;
            }
            t=t->next;
        }
    }

int main()
{
    int i,x;
    q=NULL;

    cout<<"dati numarul de elemente "; cin>>n;


    cout<<"cititi elementele"<<endl;
    for(i=1;i<=n;i++){
        cin>>x;
        inserare(x,i);
        }


    heap();

    cout<<"coada este"<<endl;
    for(i=1;i<=n;i++)
    afisare(i);

    return 0;
}
