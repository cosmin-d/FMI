#include <iostream>
#include <fstream>

using namespace std;
int noduri,muchii;
struct muchie
{
    int a;
    int b;
    int cost;
};
muchie m[100];
ifstream f("graf_ponderat.in");
ofstream g("graf_ponderat.out");
void citire()
{
    f>>noduri;
    f>>muchii;
    for(int i=1; i<=muchii; i++)
    {
        f>>m[i].a;
        f>>m[i].b;
        f>>m[i].cost;
    }
}
void sortare()
{
    for(int i=1;i<=muchii;i++)
        for(int j=1;j<=muchii;j++)
        if(m[i].cost<m[j].cost)
    {
     muchie aux;
     aux.a=m[i].a;
     aux.b=m[i].b;
     aux.cost=m[i].cost;
     m[i].a=m[j].a;
     m[i].b=m[j].b;
     m[i].cost=m[j].cost;
     m[j].a=aux.a;
     m[j].b=aux.b;
     m[j].cost=aux.cost;
    }
}
void afisare()
{
    for(int i=1;i<=muchii;i++)
    {
        g<<m[i].a<<" "<<m[i].b<<" "<<m[i].cost<<endl;
    }
    g<<endl;
}
void kruskal()
{g<<"Kruskal:\t";
    int v[noduri+1];
for(int i=1;i<=noduri;i++)
    v[i]=i;
    int i=1;
    int k=1;
while(k<noduri)
{
if(v[m[i].a]!=v[m[i].b])
{
    v[m[i].b]=v[m[i].a];
    for(int l=1;l<=noduri;l++)
        if(m[i].b==v[l])
        v[l]=m[i].a;
    g<<"("<<m[i].a<<","<<m[i].b<<")"<<"\t";
    k++;
    i++;
}
else
i++;

}

}
int main()
{
    citire();
    sortare();
    afisare();
    kruskal();
    return 0;
}
