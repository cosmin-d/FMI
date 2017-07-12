#include <iostream>
#include <fstream>

using namespace std;

struct muchie{
    int x,y;
    muchie *next;
    };

void adaugare(muchie *&q,int a,int b){
    muchie *p,*ultim;
    if(q==NULL){
        q=new muchie;
        q->x=a;
        q->y=b;
        q->next=NULL;
        ultim=q;
        }
    else{
        p=new muchie;
        p->x=a;
        p->y=b;
        p->next=NULL;
        ultim->next=p;
        ultim=p;
        }
    }

void comp_conexe(muchie *q){
    muchie *p;
    int a;
    while(q!=NULL){
        if(q->y!=0)
            a=q->x;
        if(q->y==0){
            cout<<q->x<<endl;
            break;
            }
        else{
            p=q->next;
            while(p!=NULL && p->y!=0){
                if(p->x==a){
                    cout<<a<<' ';
                    a=p->x;
                    break;
                    }
                if(p->y==a){
                    cout<<a<<' ';
                    a=p->y;
                    break;
                    }
                if(p->x!=a && p->y!=a){
                    cout<<p->x<<' '<<p->y<<' ';
                    cout<<endl;
                    break;
                    }
                p=p->next;
                }
            }
        q=q->next;
        }
    }


int main()
{
    muchie *q;
    q=NULL;
    int m,i,x,y;

    fstream f;
    f.open("in.txt",ios::in);
    f>>m;

    for(i=1;i<=m;i++){
        f>>x>>y;
        adaugare(q,x,y);
        }

    comp_conexe(q);

    return 0;

}
