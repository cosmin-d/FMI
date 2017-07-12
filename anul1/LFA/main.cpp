#include<iostream>
#include<fstream>
#include<string.h>
using namespace std;

ifstream f("intrare.txt");
struct lfa
{
    char v[100];
    char nume;
} l[100];
char A[100][3],q0;
char *F=new char[100];
char *v=new char[100];

char sa[100],s[100];//

int x,y,z,n,nr,n_lfa,nr_sa;

void citire()
{
    int i;

    f>>x;    //numarul de stari
    cout<<"Numarul de stari: "<<x<<endl;
    f>>q0;   //stare initiala
    cout<<"Starea initiala: "<<q0<<endl;

    f>>y;   //nr stari finale
    cout<<"Numarul de stari finale: "<<y<<endl;

    for(i=1; i<=y; i++)
        f>>F[i];
    cout<<"Starile finale: ";

    for(i=1; i<=y; i++)
        cout<<F[i];

    f>>z;   //nr uvinte ale alfabetului
    cout<<endl<<"Numarul de cuvinte ale alfabetului: "<<z<<endl;

    for(i=1; i<=z; i++)
        f>>v[i];

    cout<<"Cuvintele alfabetului: ";

    for(i=1; i<=z; i++)
        cout<<v[i];

    f>>n;// numarul de tranzitii
    cout<<endl<<"Tranzitiile automatului"<<endl;
    for( i=1; i<=n; i++ )
    {

        f>>A[nr][1];
        f>>A[nr][2];
        f>>A[nr][3];
        nr++;

    }
    nr=nr-1;
    for(i=0; i<=nr; i++)
        cout<<A[i][1]<<A[i][2]<<A[i][3]<<endl;
}

int main()
{
    citire();

    int i=0;
    int ii=0;
    int j=0;
    int k,ok;
    int poz;
    int okc;

    s[0]='0';//stare inceput

    cout<<endl<<"Automatul transformat"<<endl;
    while(i<=j)
    {
        for(k=0; k<=nr; k++)
            if(A[k][1]==s[i] && A[k][2]=='L')
            {
                ok=1;
                for(int kk=0; kk<=j; kk++)
                    if(s[kk]==A[k][3])ok=0;
                if(ok==1)
                    s[++j]=A[k][3];
            }
        i++;
    }

    for(int k=0; k<j; k++)  //sortare
        for(int o=k+1; o<=j; o++)
            if(s[k]>s[o])
            {
                char temp;
                temp=s[k];
                s[k]=s[o];
                s[o]=temp;
            }

    for(k=0; k<=j; k++)   //adaugare stare  noua
        l[n_lfa].v[k]=s[k];
    l[n_lfa].nume='A';

    while(ii<=n_lfa)
    {
        //cautare caracter
        cout<<endl;
        for(int d=1; d<=z; d++)  //cauta stari in functie de litera   okc-se poate ajunge cu litera undeva   daca da fac lambda inchidere
        {
            nr_sa=-1;
            okc=0;
            for(i=0; i<=strlen(l[ii].v); i++)  //cauta tranzitii cu caractere din alfabet
                for(j=0; j<=nr; j++)
                    if(A[j][1]==l[ii].v[i] && A[j][2]==v[d])
                    {
                        sa[++nr_sa]=A[j][3];
                        okc=1;
                    }

            if(okc==1) //determinare lambda inchidere
            {
                i=0;
                j=nr_sa;

                while(i<=j)
                {
                    for(k=0; k<=nr; k++)
                        if(A[k][1]==sa[i] && A[k][2]=='L')
                        {
                            ok=1;
                            for(int kk=0; kk<=j; kk++)
                                if(sa[kk]==A[k][3])ok=0;
                            if(ok==1)sa[++j]=A[k][3];
                        }
                    i++;//urm stare din structura
                }

                for(k=0; k<j; k++)   //sortare
                    for(int o=k+1; o<=j; o++)
                        if(sa[k]>sa[o])
                        {
                            char temp;
                            temp=sa[k];
                            sa[k]=sa[o];
                            sa[o]=temp;
                        }

                ok=0;

                for(i=0; i<=n_lfa; i++)   //verificaare stare diferita
                {
                    int numara=0;
                    for(k=0; k<=j; k++)
                        if(sa[k]==l[i].v[k])numara++;
                    if(numara==j+1)
                    {
                        ok=1;
                        poz=i;
                    }
                }

                if(ok==0)  //stare diferita  //incrementare litera si n_lfa
                {
                    char cr;
                    cr=l[n_lfa].nume;
                    cr++;
                    n_lfa++;

                    for(k=0; k<=j; k++)
                        l[n_lfa].v[k]=sa[k];
                    l[n_lfa].nume=cr;
                    cout<<l[ii].nume<<v[d]<<l[n_lfa].nume<<endl;
                }
                else cout<<l[ii].nume<<v[d]<<l[poz].nume<<endl;
            }
        }

        ii++;
    }

    cout<<endl;

    for(i=0; i<=n_lfa; i++)
    {
        cout<<l[i].nume<<": ";
        for(j=0; j<=strlen(l[i].v); j++)
            cout<<l[i].v[j];
        cout<<endl;
    }

}
