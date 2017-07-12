#include <iostream>
#include<fstream>

using namespace std;
int n,m,x,y,a[100][100],grad[100],c[100],viz[100],s[100],varf,ok,prim,ultim;
ifstream f("intrare.in");
ofstream g("iesire.out");

struct muchie{int a,b;};
muchie e[100];

struct nod{int info;
            nod *urm;};
nod *L[20],*p,*q;

void lista()
{

    f>>n>>m;
    int nr;
    for(int i=1;i<=n;i++)
    {
        L[i]=new nod;
        L[i]->urm=0;
    }
    while(f>>x>>y)
    {
        p=new nod;
        p->info=y;
        p->urm=L[x]->urm;
        L[x]->urm=p;
        p=new nod;
        p->info=x;
        p->urm=L[y]->urm;
        L[y]->urm=p;
    }
    for(int i=1;i<=n;i++)
    {nod *p=L[i];
    if(p!=0)
    {
        g<<i<<":";
        nod *c=p;
        c=c->urm;
        while(c)
        {
            g<<c->info<<" ";
            c=c->urm;
            nr++;

        }
        g<<endl;}

    }
    g<<endl;

}

void grad_lista()
{
    int nr;
    for(int i=1;i<=n;i++)
    {
        nod *p=L[i];
        if(p!=0)
        {
        nod *c=p;
        c=c->urm;
        nr=0;
        while(c)
        {
            c=c->urm;
            nr++;

        }
        g<<endl<<"Gradul varfului "<<i<<" este "<<nr;
        }

        }
    g<<endl;

}

void structura()
{

    f>>n>>m;
    g<<"{";
    int i=1,c;
    c=m;
    while(c!=0)
    {
        f>>x>>y;
        e[i].a=x;
        e[i].b=y;
        g<<"("<<e[i].a<<","<<e[i].b<<")";
        c--;
        if(c==0)g<<"}";
        else g<<",";
        i++;

    }
    g<<endl;
  for(i=1;i<=m;i++)
        {grad[e[i].a]++;
        grad[e[i].b]++;}
    for(i=1;i<=n;i++)
        g<<"Gradul varfului "<<i<<" este "<<grad[i]<<endl;
    g<<endl;


}

void grad_struct()
{
    int i;
    for(i=1;i<=m;i++)
        {grad[e[i].a]++;
        grad[e[i].b]++;}
    for(i=1;i<=m;i++)
        g<<"Gradul varfului "<<i<<" este"<<grad[i]<<endl;
    g<<endl;
}

void matrice()
{
    f>>n>>m;
    int c=m;
    while(c!=0)
    {
        f>>x>>y;
        a[x][y]=a[y][x]=1;
        c--;
    }
    for(int i=1;i<=n;i++)
        {for(int j=1;j<=n;j++)
          g<<a[i][j]<<" ";
          g<<endl;}
    g<<endl;

}

void grad_matrice()
{
    int nr,i,j;
    for(i=1;i<=n;i++)
    {
        nr=0;
        for(j=1;j<=n;j++)
            if(a[i][j]!=0)nr++;
        grad[i]=nr;
    }
    for(i=1;i<=n;i++)
        g<<"Gradul varfurlui "<<i<<" este "<<grad[i]<<endl;
    g<<endl;
}


void bf(int nod)
{
    int k;
    viz[nod]=1;
    prim=ultim=1;
    c[ultim]=nod;
    g<<nod<<" ";
    while(prim<=ultim)
    {
        varf=c[prim];
        for(k=1;k<=n;k++)
            if(a[varf][k]==1 && viz[k]==0)
        {
            ultim++;
            c[ultim]=k;
            if(viz[c[prim]]==1)viz[k]=2;
            else viz[k]=1;
            g<<k<<" ";
            //viz[k]=1;
        }
        prim++;
    }
    g<<endl;
}

void df(int nod)
{
    g<<nod<<" ";
    viz[nod]=1;
    for(int k=1;k<=n;k++)
        if(a[nod][k]==1 && viz[k]==0)df(k);
}

void golire()
{
    int i;
    for(i=1;i<=n;i++)
    {
        c[i]=0;
        viz[i]=0;
    }
}

void bi(int nod)
{
    int k;
    viz[nod]=1;
    prim=ultim=1;
    c[ultim]=nod;
    while(prim<=ultim)
    {
        varf=c[prim];
        for(k=1;k<=n;k++)
            if(a[varf][k]==1 && viz[k]==0)
        {
            ultim++;
            c[ultim]=k;
            if(viz[c[prim]]==1)viz[k]=2;
            else viz[k]=1;
        }
        prim++;
    }
    for(int i=1;i<=n;i++)
        g<<viz[i]<<" ";
    g<<endl;
}

int bipartit()
{
    for(int i=1;i<=n;i++)
        for(int j=1;j<=n;j++)
          if(viz[i]==viz[j])
             if(a[i][j]==1)return 0;
    return 1;
}

int main()
{

    structura();
    f.seekg(0,ios::beg);
    matrice();
    grad_matrice();
    f.seekg(0,ios::beg);
    lista();
    grad_lista();

    //Parcurgere bf
    cout<<"Nodul de inceput = ";
    cin>>x;
    g<<endl<<"Parcurgerea in latime "<<endl;
    bf(x);
    golire();

    //Parcurgere df
    cout<<"Nodul de inceput = ";
    cin>>x;
    g<<endl<<"Parcurgerea in adancime "<<endl;
    df(x);
    golire();
    g<<endl;

    //Componente conexe
     g<<endl<<"Componente conexe "<<endl;
     for(int i=1;i<=n;i++)
        if(viz[i]==0)bf(i);

    //Graf bipartit
    golire();
    bi(1);
    if(bipartit()==1)g<<"Graf bipartit";
    else g<<"Graf nebipartit";

    g.close();
    return 0;
}
