#include <iostream>
#include<fstream>

using namespace std;

class graf
{
protected:
    int n,m,**a,nr,viz[100],c[100];
public:
    graf(int x , int y, int z);
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
    virtual void nr_muchii()
    {
        int nr=0;
        for(int i=1; i<=n; i++)
            for(int j=1; j<=n; j++)
                if(a[i][j]==1)
                    nr++;
        cout<<"Numarul muchiilor este "<<nr<<endl;
    }
    friend istream &operator>>(istream &input,graf &b)
    {
        b.citire();
        return input;
    }

    friend ostream &operator<<(ostream &output,  graf &b)
    {
        cout<<"Numarul de noduri "<<b.n<<endl;
        cout<<"Numarul de muchii "<<b.m<<endl;
        b.afisare();
        b.nr_muchii(); // virtuala
        b.bf(1);
        b.golire();
        cout<<"Parcurgere in adancime "<<endl;
        b.df(1);
        b.RW();
        b.componente_tari_conexe();
        return output;
    }
    graf operator+(const graf&y);
    graf operator=(const graf&y);


};
class graf_complet:virtual public graf
{
public:
    int get_m()
    {
        return m;
    }
    ~graf_complet() //destructor
    {
        for(int i=1; i<=n; i++)
            delete []a[i];
        delete []a;
    }
    graf_complet(int x, int y,int z)  :graf(x,y,z) {}  //constructor cu parametri care va apela constructorul clasei de baza

   bool is_graf_complet()   //verificare graf complet
    {
        bool complet=true;
        for(int i=1; i<=n; i++)
            for(int j=1; j<=n; j++)
            {

                if(a[i][j]==0 && a[j][i]==0 && i!=j)
                    complet=false;

            }

            return complet;
    }
    friend ostream &operator<<(ostream &output,  graf_complet &b) //functia de afisare rescrisa
    {
        cout<<"Numarul de noduri "<<b.n<<endl;
        cout<<"Numarul de muchii "<<b.m<<endl;
        b.afisare();
        b.nr_muchii(); //metoda virtuala
        if(b.is_graf_complet())
            cout<<"Graful e complet"<<endl;
        else
            cout<<"Graful nu e complet"<<endl;

        b.bf(1);
        b.golire();
        cout<<"Parcurgere in adancime "<<endl;
        b.df(1);
        b.RW();
        b.componente_tari_conexe();

        return output;
    }
};
class graf_antisimetric:virtual public graf
{
public:
    ~graf_antisimetric() //destructor
    {
        for(int i=1; i<=n; i++)
            delete []a[i];
        delete []a;
    }
    graf_antisimetric(int x, int y,int z)   //constructor cu parametri care va apela constructorul clasei de baza
        :graf(x,y,z){ };
   bool is_graf_antisimetric()
    {
        bool antisimetric=true;
        for(int i=1; i<=n; i++)
            for(int j=1; j<=n; j++)
                if(a[i][j]==1 && a[j][i]==1)
                    antisimetric=false;
            return antisimetric;
    }
    friend ostream &operator<<(ostream &output,  graf_antisimetric &b) //functia de afisare rescrisa
    {
        cout<<"Numarul de noduri "<<b.n<<endl;
        cout<<"Numarul de muchii "<<b.m<<endl;
        b.afisare();
        b.nr_muchii();   //metoda virtuala
        if(b.is_graf_antisimetric())
            cout<<"Graful este antisimetric"<<endl;
        else
            cout<<"Graful nu este antisimetric"<<endl;

        b.bf(1);
        b.golire();
        cout<<"Parcurgere in adancime "<<endl;
        b.df(1);
        b.RW();
        b.componente_tari_conexe();

        return output;
    }

};
class graf_turneu:public graf_complet, public graf_antisimetric
{public:
    graf_turneu(int x,int y,int z) :graf_complet(x,y,z),graf_antisimetric(x,y,z),graf(x,y,z){ }  ; //constructor cu parametri care va apela constructorul clasei de baza


~graf_turneu() //destructor
    {
        for(int i=1; i<=n; i++)
            delete []a[i];
        delete []a;
    }
    bool is_graf_turneu()
    {
        if(is_graf_antisimetric() && is_graf_complet())
            cout<<"Graful este turneu"<<endl;
        else
            cout<<"Graful nu este turneu"<<endl;
        return (is_graf_antisimetric() && is_graf_complet());
    }
    friend ostream &operator<<(ostream &output,  graf_turneu &b) //functia de afisare rescrisa
    {
        cout<<"Numarul de noduri "<<b.n<<endl;
        cout<<"Numarul de muchii "<<b.m<<endl;
        b.afisare();
        b.nr_muchii();
        b.is_graf_turneu();
        b.bf(1);
        b.golire();
        cout<<"Parcurgere in adancime "<<endl;
        b.df(1);
        b.RW();
        b.componente_tari_conexe();
         return output;
    }
};


graf::graf(int x=0, int y=0, int z=0)                                     //constructor
{
    nr=x;                                     //initializare numar componente conexe cu 0
    n=y;                                      //initializare numar de varfuri cu 0
    m=z;                                      //initializare numar de muchii cu 0
     a=new int * [n+1];                        //alocare dinamica pentru matricea de adiacenta
    for(int i=1; i<=n; i++)
    {
        a[i]=new int[n+1];
        for(int j=0; j<=n; j++)
            a[i][j]=0;
    }
}
graf::~graf()
{
    for(int i=1; i<=n; i++)
        delete []a[i];
    delete []a;
}
graf graf::operator=(const graf& y)
{
    nr=y.nr;
    m=y.m;
    n=y.n;
    for(int i=1; i<=y.n; i++)
        for(int j=1; j<=y.n; j++)
            a[i][j]=y.a[i][j];
    return *this;
}
graf graf::operator+(const graf& y)
{
    graf c;
    c.nr=0;

    c.n=n;

    c.a=new int * [c.n+1];
    for(int i=1; i<=c.n; i++)
    {
        c.a[i]=new int[c.n+1];
        for(int j=1; j<=c.n; j++)
            c.a[i][j]=0;
    }
    cout<<"Reuniunea grafurilor "<<endl;
    int i,j;

    for(i=1; i<=n; i++)
        for(j=1; j<=n; j++)
            if(a[i][j]==y.a[i][j])
            {
                if(y.a[i][j]==0)
                    c.a[i][j]=0;
                else c.a[i][j]=1;


            }
            else c.a[i][j]=1;
    //c.afisare();
    return c;

}
void graf::citire()            //functie de citire a grafului
{
    int i,x,y;

    cin>>n>>m;                     //se citesc n si m din fisier (n-numarul de varfuri; m-numarul de muchii)

    a=new int * [n+1];               //alocare dinamica pentru matricea de adiacenta
    for(int i=1; i<=n; i++)
    {
        a[i]=new int[n+1];
        for(int j=0; j<=n; j++)
            a[i][j]=0;
    }
     cout<<"Obiectul a fost creat"<<endl;

    cout<<"Numarul de noduri ";cout<<n<<endl;
    cout<<"Numarul de muchii ";cout<<m<<endl;
    for(i=1;i<=m;i++)                                   //citirea de la tastatura a muchiilor
    {
        cout<<"Exista muchie de la varful ";cin>>x;
        cout<<"la vaful ";cin>>y;
        a[x][y]=1;
    }
}


graf::graf(const graf &w)
{
    nr=w.nr;
    n=w.n;
    m=w.m;
    for(int i=1; i<=w.n; i++)
        for(int j=1; j<=w.n; j++)
            a[i][j]=w.a[i][j];
}

void graf::afisare()
{
    cout<<n<<endl;
    for(int i=1; i<=n; i++)
    {
        for(int j=1; j<=n; j++)
            cout<<a[i][j];
        cout<<endl;
    }

}

void graf::bf(int nod)
{
    int k,prim,ultim,varf,i;
    cout<<"Parcurgere in latime "<<endl;
    viz[nod]=1;
    prim=ultim=1;
    c[ultim]=nod;
    while(prim<=ultim)
    {
        varf=c[prim];
        for(k=1; k<=n; k++)
            if(a[varf][k]==1 && viz[k]==0)
            {
                ultim++;
                c[ultim]=k;
                viz[k]=1;
            }
        prim++;
    }
    for(i=1; i<=ultim; i++)
        cout<<c[i]<<" ";

    cout<<endl;
}

void graf::golire()
{
    for(int i=1; i<=100; i++)
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
    for(i=1; i<=n; i++)
        if(a[nod][i]==1 && viz[i]==0)df(i);
}

void graf::RW()
{
    int i,j,k;
    cout<<endl<<"Matricea drumurilor "<<endl;
    for(k=1; k<=n; k++)
        for(i=1; i<=n; i++)
            for(j=1; j<=n; j++)
                if(i!=j)if(a[i][j]==0)a[i][j]=a[i][k]*a[k][j];
    for(int i=1; i<=n; i++)
    {
        for(j=1; j<=n; j++)
            cout<<a[i][j];
        cout<<endl;
    }

}

void graf::componente_tari_conexe()
{
    int i,p[100];
    for(i=1; i<=100; i++)
        p[i]=0;
    cout<<"Componente tari conexe "<<endl;
    for(i=1; i<=n; i++)
        if(!p[i])
        {
            nr++;
            cout<<i<<" ";
            p[i]=1;
            for(int j=1; j<=n; j++)
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
    graf_turneu G(0,4,6);
    cin>>G;
    cout<<G;
    /* cin>>F;
     cout<<F;
     graf S(F+G);
     cout<<S;
    */
    return 0;
}


