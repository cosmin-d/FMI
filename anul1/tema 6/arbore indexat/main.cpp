#include <iostream>
#include <cmath>

using namespace std;

struct nod{
    int info;
    int index;
    nod *left,*right;
    };

void adaugare(nod *&r,int x){
    if(r==NULL){
        r=new nod;
        r->info=x;
        r->left=NULL;
        r->right=NULL;
        }
    else{
        if(r->info<x)
            adaugare(r->right,x);
        if(r->info>x)
            adaugare(r->left,x);
        }
    }


void noduri_stanga(nod *r,int &k)
{
if (r!=NULL){ k++;
if(r->left!=NULL)noduri_stanga(r->left,k);
if(r->right!=NULL)noduri_stanga(r->right,k);
}
}

void indexare(nod *&r){
    int k=0;
    if(r!=NULL){
        noduri_stanga(r->left,k);
        r->index=k;
        indexare(r->left);
        indexare(r->right);
        }
    }

void afisare_pozitie(nod *r, int k,int x){
    if(r!=NULL){
     k=abs(k-x-1);
    if(x==k)
        cout<<r->info;
    else{
        if(r->index>k){
            afisare_pozitie(r->left,k,r->index);
            }
        if(r->index<k){
            afisare_pozitie(r->right,k,r->index);
            }
        }
    }
    }

int main()
{
    nod *rad;
    rad=NULL;
    int x,t;

    cout<<"citeste elementele arborelui"<<endl;
    cin>>x;
    while(x!=0){
        adaugare(rad,x);
        cin>>x;
        }
    indexare(rad);
    cout<<endl;

    cout<<"citeste pozitia elementului cautat ";
    cin>>t;
    cout<<endl;
    cout<<"elementul de pe pozitia "<<t<<" este ";
    if (rad->index==t)
    cout<<rad->info;
    else{
    if (t>rad->index)
    afisare_pozitie(rad->right,t,rad->index);
    if (t<rad->index)
    afisare_pozitie(rad->left,t,rad->index);
    }

    return 0;
}
