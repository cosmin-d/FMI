#include <iostream>

using namespace std;

struct nod{
    int info;
    int bal;
    nod *left,*right;
    };

void rot_dr(nod *&r){
    nod *t;
    t=r->left;
    r->left=r->right;
    t->right=r;
    r->bal=r->bal+(1-min(t->bal,0));
    t->bal=t->bal+(1+max(r->bal,0));
    r=t;
    }

void rot_st(nod *&r){
    nod *t;
    t=r->right;
    r->right=r->left;
    t->left=r;
    r->bal=r->bal-(1+max(t->bal,0));
    t->bal=t->bal-(1-min(r->bal,0));
    r=t;
    }

void rot_st_dr(nod *&r){
    rot_st(r->left);
    rot_dr(r);
    }

void rot_dr_st(nod *&r){
    rot_dr(r->right);
    rot_st(r);
    }

nod *creeaza_nod(int x){
    nod *p;
    p=new nod;
    p->info=x;
    p->left=NULL;
    p->right=NULL;
    p->bal=0;
    return p;
    }

void echilibreaza(nod *&r){
    if(r->bal==-2){
        if(r->left->bal==1)
            rot_st_dr(r);
        else
            rot_dr(r);
            }
    else if(r->bal==2){
        if(r->right->bal==-1)
            rot_dr_st(r);
        else
            rot_st(r);
            }
        }

bool adaugare(nod *&r,int x){
    if(r==NULL){
        r=creeaza_nod(x);
        return 1;
        }
    if(r->info==x)
        return 0;
    if(r->info>x){
        if(adaugare(r->left,x)==1)
            r->bal=r->bal-1;
        else
            return 0;
            }
    if(r->info<x){
        if(adaugare(r->right,x)==1)
            r->bal=r->bal+1;
        else
            return 0;
            }
    if(r->bal==0)
        return 0;
    else if(r->bal==1 || r->bal==-1)
            return 1;
    else{
        echilibreaza(r);
        return 0;
        }
    }

bool cauta(nod *r,int x){
    if(r!=NULL){
        if(r->info==x)
            return 1;
        else
            if(r->info<x)
                cauta(r->right,x);
            else
                cauta(r->left,x);
            }
    else
        return 0;
        }

int maxim(nod *r){
    while(r->right!=NULL)
        r=r->right;
    return r->info;
    }

void sterge_subarbori(nod *&r,nod *&q){
    nod *t;

    if(q->right!=NULL)
        sterge_subarbori(r,q->right);
    else{
        r->info=q->info;
        t=q;
        q=q->left;
        delete t;
        }
    }

void sterge(nod *&r,int x){
    nod *t;

    if(r!=NULL){
        if(r->info==x){
            if(r->left==NULL && r->right==NULL){
                delete r;
                r=NULL;}
            else if(r->left==NULL){
                t=r->right;
                delete r;
                r=t;}
            else if(r->right==NULL){
                t=r->left;
                delete t;
                r=t;}
            else sterge_subarbori(r,r->left);
            }
        else{
            if(r->info<x)
                sterge(r->right,x);
            else
                sterge(r->left,x);
            }
        }
    else cout<<"numarul nu a fost gasit";
    }


void afisare(nod *r){
    if(r!=NULL){
        afisare(r->left);
        cout<<r->info<<' ';
        afisare(r->right);
        }
    }

int main()
{
    nod *rad;
    rad=NULL;
    int x,y,k;

    cout<<"dati elementele arborelui "<<endl;
    cin>>x;
    while(x!=NULL){
        adaugare(rad,x);
        cin>>x;
        }

    cout<<"afisare"<<endl;
    afisare(rad);
    cout<<endl;

    cout<<"elementul cautat: ";
    cin>>y;
    if(cauta(rad,y)==1)
        cout<<"elementul a fost gasit";
    else
        cout<<"elementul nu a fost gasit";
    cout<<endl;

    cout<<"cel mai mare element este "<<maxim(rad);
    cout<<endl;

    cout<<"elementul pe care doriti sa il stergeti ";
    cin>>k;
    sterge(rad,k);
    cout<<"dupa ce elementul a fost sters ";
    cout<<endl;
    afisare(rad);

    return 0;
}
