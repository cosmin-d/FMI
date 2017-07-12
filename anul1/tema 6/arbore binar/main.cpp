#include <iostream>

using namespace std;

struct nod{
    int info;
    nod *left,*right;
    };

nod *adaugare(){
    char x;
    nod *p;
    cout<<"dati un element: ";
        p=new nod;
        cin>>p->info;
        p->left=p->right=NULL;
        cout<<"fiu stang pentru "<<p->info<<" ? (d/n)"<<endl;
        cin>>x;
        if(x!='n') p->left=adaugare();
        cout<<"fiu drept pentru "<<p->info<<" ? (d/n)"<<endl;
        cin>>x;
        if(x!='n') p->right=adaugare();

    return p;

    }

void RSD(nod *r){
    if(r!=NULL){
        cout<<r->info<<' ';
        RSD(r->left);
        RSD(r->right);
        }
    }

void SRD(nod *r){
    if(r!=NULL){
        SRD(r->left);
        cout<<r->info<<' ';
        SRD(r->right);
        }
    }

void SDR(nod *r){
    if(r!=NULL){
        SDR(r->left);
        SDR(r->right);
        cout<<r->info<<' ';
        }
    }

int main()
{
    nod *rad=adaugare();

    cout<<"parcurgerea in preeordine"<<endl;
    RSD(rad);
    cout<<endl;

    cout<<"parcurgerea in inordine"<<endl;
    SRD(rad);
    cout<<endl;

    cout<<"parcurgerea in postordine"<<endl;
    SDR(rad);

    return 0;
}
