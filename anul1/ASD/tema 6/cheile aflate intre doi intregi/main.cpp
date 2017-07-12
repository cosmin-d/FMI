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

void afisare(nod *r,int x,int y){
    if(r!=NULL){
        afisare(r->left,x,y);
        if(r->info>=x && r->info<=y)
            cout<<r->info<<' ';
        afisare(r->right,x,y);
        }
    }

int main()
{
    int k1,k2,x;
    nod *rad;
    rad=NULL;

    cout<<"citeste elementele"<<endl;
    cin>>x;
    while(x!=0){
        adaugare(rad,x);
        cin>>x;
        }

    cout<<endl;
    cout<<"citeste k1 "; cin>>k1;
    cout<<"citeste k2 "; cin>>k2;

    cout<<"elementele care se afla intre k1 si k2 sunt"<<endl;
    afisare(rad,k1,k2);

    return 0;
}
