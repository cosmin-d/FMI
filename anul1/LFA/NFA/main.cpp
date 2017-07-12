#include <iostream>
#include <fstream>
#include <string>
#include <windows.h>
using namespace std;
struct tranzitii
{
    int sti,stf; //stari initiale/finale
    char litera;
};
int main()
{
    int nr_stari, alfabet, nr_stari_finale,nr_stari_tranzitii;
    ifstream f ("automat1.txt");
    f>>nr_stari;
    f>>alfabet;
    char *alf=new char [alfabet];
    for(int i=0; i<alfabet; i++)
        f>>alf[i];
    f>>nr_stari_finale;
    int *stf=new int [nr_stari_finale];
    for(int i=0; i<nr_stari_finale; i++)
        f>>stf[i];
    f>>nr_stari_tranzitii;
    tranzitii *v =new tranzitii[20];
    int cont=0;
    int s=0,k=0,x;
    for(int i=0; i<nr_stari_tranzitii; i++)
    {
        s=0;
        f>>x;
        f>>cont;
        while(s<cont)
        {
            v[k].sti=x;
            f>>v[k].litera;
            f>>v[k].stf;

            k++;
            s++;
        }

    }
 /*   for(int i=0; i<k; i++)
    {
        cout<<v[i].sti<<" ";
        cout<<v[i].litera<<" ";
        cout<<v[i].stf<<endl;
    }
    system("pause"); */
    char *cuv= new char [30];
    gets(cuv);

    int *stc=new int [10];
    int *stp=new int [10];//m e contor pentru starea posibila
    int j=0,l=0,m=0,n=0;
    stc[l]=0;// l e contor pentru stc
    while(cuv[n]!='\0')
    {
        if(cuv[n]!='#') //THIS IS LAMDA
        {

            for(int i=0; i<k; i++)
                if(cuv[n]==v[i].litera || v[i].litera=='#')
                    for(j=0; j<=l; j++)
                        if(stc[j]==v[i].sti)
                        {
                            stp[m]=v[i].stf;
                            m++;
                        }
            for(int i=0; i<max(l,m); i++)
            {
                stc[i]=stp[i];
                stp[i]=0;
            }
            l=max(l,m);
            m=0;

        }


        n++;
    }
    for(int i=0; i<l; i++)
        for(j=0; j<nr_stari_finale; j++)
            if(stc[i]==stf[j])
            {
                cout<<"Accepted";
                goto sfarsit;
            }
    cout<<"Rejected";
sfarsit:
    return 0;
}
