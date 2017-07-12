#include <iostream>

using namespace std;

struct nod{
    int info;
    nod *left,*right;
    };

void insert(nod *&r,int x){
    if(r==NULL){
        r=new nod;
        r->info=x;
        r->left=NULL;
        r->right=NULL;
        }
    else{
        if(x<r->info)
            insert(r->left,x);
        if(x>r->info)
            insert(r->right,x);
        }
    }

bool search(nod *r,int x){
    if(r!=NULL){
        if(r->info==x)
            return 1;
        else
            if(r->info<x)
                search(r->right,x);
            else
                search(r->left,x);
            }
    else
        return 0;
        }



int findmax(nod *r){
    while(r->right!=NULL)
        r=r->right;
    return r->info;
    }

void delete_subarbori(nod *&r,nod *&q){
    nod *t;

    if(q->right!=NULL)
        delete_subarbori(r,q->right);
    else{
        r->info=q->info;
        t=q;
        q=q->left;
        delete t;
        }
    }

void delete_(nod *&r,int x){
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
            else delete_subarbori(r,r->left);
            }
        else{
            if(r->info<x)
                delete_(r->right,x);
            else
                delete_(r->left,x);
            }
        }
    else cout<<"numarul nu a fost gasit";
    }


void SRD(nod *r){
    if(r!=NULL){
        SRD(r->left);
        cout<<r->info<<' ';
        SRD(r->right);
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
        insert(rad,x);
        cin>>x;
        }

    cout<<"afisare"<<endl;
    SRD(rad);
    cout<<endl;

    cout<<"elementul cautat: ";
    cin>>y;
    if(search(rad,y)==1)
        cout<<"elementul a fost gasit";
    else
        cout<<"elementul nu a fost gasit";
    cout<<endl;

    cout<<"cel mai mare element este "<<findmax(rad);
    cout<<endl;

    cout<<"elementul pe care doriti sa il stergeti ";
    cin>>k;
    delete_(rad,k);
    cout<<"dupa ce elementul a fost sters ";
    cout<<endl;
    SRD(rad);


    return 0;


}
