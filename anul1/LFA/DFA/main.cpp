#include <iostream>
#include <fstream>
#include <string>
using namespace std;

int main()
{
    int nr_stari , alfabet , nr_stari_finale , nr_tranzitii;
    ifstream f ("automat.txt");
    f>>nr_stari;
    f>>alfabet;
    int *alf=new int [alfabet];
    for(int i=0; i<alfabet; i++)
        f>>alf[i];
    f>>nr_stari_finale;
    int *stf=new int [nr_stari_finale];
    for(int i=0; i<nr_stari_finale; i++)
        f>>stf[i];
    f>>nr_tranzitii;
    int **tranz = new int *[nr_tranzitii];
    for(int i =0;i<nr_tranzitii;i++)
    tranz[i]=new int[3];
    for(int i=0; i<nr_tranzitii; i++)
        for(int j=0; j<3; j++)
            f>>tranz[i][j];
    char cuv[20];
    gets(cuv);
    /* cout<<nr_stari<<endl;
     cout<<alfabet<<endl;

     for(int i=0; i<alfabet; i++)
     cout<<alf[i]<<" ";
     cout<<endl;
     cout<<nr_stari_finale<<endl;
      for(int i=0; i<nr_stari_finale; i++)
     cout<<stf[i]<<" ";
     cout<<endl;
     cout<<nr_tranzitii<<endl;
     for(int i=0; i<nr_tranzitii; i++)
        {
           for(int j=0; j<3; j++)
              cout<<tranz[i][j]<<" ";
              cout<<endl;
        }
    */
    int poz_crt=0,k=0,ok;
    int l=0;
    while(cuv[k]!='\0')
    {    ok=0;
        for(int i=0; i<nr_tranzitii; i++)
            if(tranz[i][0]==poz_crt && tranz[i][1]==(cuv[k]-48) && ok==0)
                  {
                     poz_crt=tranz[i][2];

                     ok=1;
                 }

                   if(ok==0)
                   {
                cout<<"cuvantul nu e valid";
                 goto sfarsit;
                   }
k++;
        }

 for(int i=0; i<nr_stari_finale; i++)
                    if(poz_crt==stf[i])
                    {cout<<"cuvantul e valid";
                    l=1;
                    }
if(l==0)
    cout<<"cuvantul nu e valid";

sfarsit:
    return 0;









}
