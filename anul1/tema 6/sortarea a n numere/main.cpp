#include <iostream>

using namespace std;

struct nod{
    int info;
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
        if(x<r->info)
            adaugare(r->left,x);
        if(x>r->info)
            adaugare(r->right,x);
        }
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
    int n,i,x;
    nod *rad;
    rad=NULL;

    cout<<"cate numere vor fi sortate: "; cin>>n;
    cout<<"citeste numerele"<<endl;
    for(i=0;i<n;i++){
        cin>>x;
        adaugare(rad,x);
        }

    cout<<endl;
    cout<<"numerele sortate sunt "<<endl;
    SRD(rad);

    return 0;
}
