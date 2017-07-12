#include <iostream>
#include<fstream>

using namespace std;
ifstream f("intrare.in");
ofstream g("iesire.out");

class graf
{
private:
    int n,m,**a,nr,viz[100],c[100];
public:
    graf();
    graf(const graf &w);
    ~graf();
    void citire();
    void citire_fisier();
    void afisare();
    void golire();
    void bf(int nod);
    void df(int nod);
    void RW();
    void componente_tari_conexe();
    friend void graf_tare_conex(graf &a);
    friend istream &operator>>(istream &input,graf &b)
    {
        f>>b.n;
        f>>b.m;
       cout<<"Numarul de noduri este ";cout<<b.n<<endl;
        cout<<"Numarul de muchii este ";cout<<b.m<<endl;
        cout<<endl;
         b.citire();
        return input;
    }

    friend ostream &operator<<(ostream &output,  graf &b)
    {
        cout<<"Numarul de noduri "<<b.n<<endl;
        cout<<"Numarul de muchii "<<b.m<<endl;
        b.afisare();
         b.bf(1);
         b.golire();
         cout<<"PArcurgere in adancime "<<endl;
         b.df(1);
         b.RW();
         b.componente_tari_conexe();
       return output;
    }
    graf operator+(const graf&y);



};
graf::graf()
{
    nr=0;
     f>>n;
     f>>m;
     a=new int * [n+1];
    for(int i=1; i<=n; i++)
    {
        a[i]=new int[n+1];
        for(int j=0; j<=n; j++)
            a[i][j]=0;
    }
     cout<<"Obiectul a fost creat"<<endl;

}
graf::~graf()
{
    for(int i=1;i<=n;i++)
        delete []a[i];
    delete []a;
}

graf graf::operator+(const graf& y)
 {
        graf c;
        cout<<"Reuniunea grafurilor "<<endl;
        int i,j;
      //  c.afisare();
        for(i=1;i<=n;i++)
            for(j=1;j<=n;j++)
             if(a[i][j]==y.a[i][j])
             {
                 if(y.a[i][j]==0)
                c.a[i][j]=0;
               else c.a[i][j]=1;

             }
             else c.a[i][j]=1;

     //  c.afisare();
        return c;

    }
void graf::citire()
{
    //cout<<"Numarul de noduri este egal cu: ";
    //cin>>n;
    //cout<<"Numarul de muchii este egal cu: ";
    //cin>>m;

    int i,x,y;
    for(i=1;i<=m;i++)
    {
       cout<<"Exista muchie de la nodul "; cin>>x;
       cout<<" la nodul "; cin>>y;
        a[x][y]=1;

    }


}

graf::graf(const graf &w)
{
    for(int i=1;i<=w.n;i++)
        for(int j=1;j<=w.n;j++)
           a[i][j]=w.a[i][j];
}

void graf::afisare()
{
    cout<<n<<endl;
    for(int i=1;i<=n;i++)
    {for(int j=1;j<=n;j++)
    cout<<a[i][j];
    cout<<endl;}

}

void graf::bf(int nod)
{
    int k,prim,ultim,varf,i;
     cout<<"Parcurgere in latime "<<endl;
    viz[nod]=1;
    prim=ultim=1;
    c[ultim]=nod;
    while(prim<=ultim)
    {varf=c[prim];
        for(k=1;k<=n;k++)
            if(a[varf][k]==1 && viz[k]==0)
        {
            ultim++;
            c[ultim]=k;
            viz[k]=1;
        }
        prim++;
    }
    for(i=1;i<=ultim;i++)
        cout<<c[i]<<" ";

    cout<<endl;
}

void graf::golire()
{
    for(int i=1;i<=100;i++)
    {
        viz[i]=0;
        c[i]=0;
    }
}

void graf::df(int nod)
{
    int i;

    cout<<nod<<" ";
    viz[nod]=1;
    for(i=1;i<=n;i++)
        if(a[nod][i]==1 && viz[i]==0)df(i);
}

void graf::RW()
{
    int i,j,k;
    cout<<"Matrice drumurilor "<<endl;
    for(k=1;k<=n;k++)
        for(i=1;i<=n;i++)
          for(j=1;j<=n;j++)
            if(i!=j)if(a[i][j]==0)a[i][j]=a[i][k]*a[k][j];
    for(int i=1;i<=n;i++)
        {for(j=1;j<=n;j++)
          cout<<a[i][j];
       cout<<endl;}

}

void graf::componente_tari_conexe()
{
    int i,p[100];
    for(i=1;i<=100;i++)
        p[i]=0;
     cout<<"Componente tari conexe "<<endl;
    for(i=1;i<=n;i++)
        if(!p[i])
    { nr++;
        cout<<i<<" ";
        p[i]=1;
        for(int j=1;j<=n;j++)
            if(a[i][j]*a[j][i]==1)
        {
            cout<<j<<" ";
            p[j]=1;
        }

        cout<<endl;
    }

}

void graf_tare_conex(graf &a)
{

    if(a.nr!=1)cout<<"Graful nu este tare conex"<<endl;
    else cout<<"Graful este tare conex "<<endl;
}

int main()
{
    graf G,F;

    cin>>G;
    cout<<G;
    cin>>F;
    cout<<F;
     graf S(G+F);
     cout<<S;

    return 0;
}


