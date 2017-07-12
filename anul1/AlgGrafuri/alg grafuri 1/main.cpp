#include <iostream>
#include <fstream>

using namespace std;

ifstream f1("intrare2.in");
ofstream g1("iesire2.out");

int n,s;

struct graf{int grad,nod;};
graf v[100];

struct muchie{int a,b;};
muchie m[100];

void nr_muchii_noduri()
{
    int i=1,x,s=0;
    while(f1>>x)
       {g1<<x<<" ";
         v[i].grad=x;
         v[i].nod=i;
         i++;
         s+=x;}

    n=--i;
    g1<<endl<<"Numarul de noduri este egal cu "<<n<<endl;
    g1<<"Numarul de muchii este egal cu "<<s/2<<endl;
}

int graf_neorientat()
{
    if(s%2==0)return 1;
    else return 0;
}

int graf_simplu()
{
    int i,maxim,minim;
    maxim=v[1].grad;
    minim=v[1].grad;
    for(i=2;i<=n;i++)
    {
        if(maxim<v[i].grad)maxim=v[i].grad;
        if(minim>v[i].grad)minim=v[i].grad;
    }
    if(s%2==0 && minim>=0 && maxim<=n-1)return 1;
    else return 0;


}

void sortare()
{
    int ok,i,aux;
    do
    {
        ok=0;
        for(i=1;i<n;i++)
            if(v[i].grad<v[i+1].grad)
        {
            aux=v[i].grad;
            v[i].grad=v[i+1].grad;
            v[i+1].grad=aux;
            aux=v[i].nod;
            v[i].nod=v[i+1].nod;
            v[i+1].nod=aux;
            ok=1;
        }
        else if(v[i].grad==v[i+1].grad && v[i].nod>v[i+1].nod)
        {
            aux=v[i].grad;
            v[i].grad=v[i+1].grad;
            v[i+1].grad=aux;
            aux=v[i].nod;
            v[i].nod=v[i+1].nod;
            v[i+1].nod=aux;
            ok=1;
        }
    }
    while(ok==1);
    for(i=1;i<=n;i++)
        g1<<v[i].grad<<"/"<<v[i].nod<<",";
}

void adauga_muchie()
{
    int i,g=v[1].grad,k=1;
    for(i=2;i<=g+1;i++)
        {m[k].a=v[1].nod;
        m[k].b=v[i].nod;
        k++;
        v[1].grad--;
        v[i].grad--;}
    for(i=1;i<k;i++)
        g1<<"("<<m[i].a<<","<<m[i].b<<")";
    g1<<endl;
    for(i=1;i<=n;i++)
        g1<<v[i].grad<<"/"<<v[i].nod<<",";


}

void arbore()
{
    int i,maxim,minim;
    maxim=v[1].grad;
    minim=v[1].grad;
    s=v[1].grad;
    for(i=2;i<=n;i++)
    {
        s=v[i].grad;
        if(maxim<v[i].grad)maxim=v[i].grad;
        if(minim>v[i].grad)minim=v[i].grad;
    }
    if(s%2==0 && minim>0 && maxim<=n-1 && s==2*(n-1))g1<<"Poate fi arbore";
    else g1<<"Nu poate fi arbore";
}

void graf_conex()
{
    int i,maxim,minim;
    maxim=v[1].grad;
    minim=v[1].grad;
    s=v[1].grad;
    for(i=2;i<=n;i++)
    {
        s=v[i].grad;
        if(maxim<v[i].grad)maxim=v[i].grad;
        if(minim>v[i].grad)minim=v[i].grad;
    }
    if(s%2==0 && minim>0 && maxim<=n-1 && s>=2*(n-1))g1<<"Graf simplu conex";
    else g1<<"Graf simplu conex";
}

int main()
{
     nr_muchii_noduri();

     //Graf neorientat
     if(graf_neorientat()==1)g1<<"Graf neorientat";
     else g1<<"Graful nu este neorientat";
     g1<<endl;

     //Graf simplu
     if(graf_simplu()==1)g1<<"Graf simplu";
     else g1<<"Graful nu este simplu";
     g1<<endl;

     //Arbore
     arbore();
     g1<<endl;

     //Graf conex
     graf_conex();
     g1<<endl;

     //Graf simplu = 1
     g1<<endl;
     sortare();
     g1<<endl;
     adauga_muchie();
     if(graf_simplu()==1)
     {
         int ok=0;
         while(ok==0)
        {int y=v[1].grad;
            for(int i=2;i<=n;i++)
              if(y<v[i].grad)y=v[i].grad;
         if(y!=0){ g1<<endl<<endl;
                  sortare();
                   g1<<endl;
                   adauga_muchie();}
         else ok=1;}


     }
     g1<<endl;

     return 0;
}


