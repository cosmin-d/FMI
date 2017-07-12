#include <iostream>

using namespace std;

struct nod{
    char info;
    nod *left,*right;
    };

void adaugare(nod *&r,char x){
    if(r==NULL){
        r=new nod;
        r->info=x;
        r->left=NULL;
        r->right=NULL;
        }
    else{
        if(r->info<x)
            adaugare(r->left,x);
        if(r->info>x)
            adaugare(r->right,x);
        }
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
    char x;
    nod *rad;
    rad=NULL;

    cout<<"cititi elementele arborelui "<<endl;
    cin>>x;
    while(x!='0'){
        adaugare(rad,x);
        cin>>x;
        }

    cout<<"literele aranjate descrescator sunt "<<endl;
    afisare(rad);

    return 0;
}
