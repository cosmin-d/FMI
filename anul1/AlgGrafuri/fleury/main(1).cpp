#include <iostream>
#include <fstream>

using namespace std;

struct nod{
    int x,y;
    };

nod *muchii;
int matrice[50][50],noduri[50],circuit[50];
int n,m,i_circ;

void construire_matrice(){
    int i,j;
    for(i=1;i<=n;i++){
        for(j=1;j<=n;j++)
            matrice[i][j]=0;
        }
    for(i=1;i<=m;i++)
        matrice[muchii[i].x][muchii[i].y]=matrice[muchii[i].y][muchii[i].x]=1;

        }

int grad_nod(int nod){
    int grad=0;

    for(int i=1;i<=n;i++){
        if(matrice[nod][i]==1)
            grad++;
        }

    return grad;

    }

int radacina(){
    int t=1;
    int k=0;

    for(int i=1;i<=n;i++)
        if(grad_nod(noduri[i])%2!=0){
            k++;
            t=i;
            }
    if(k!=0 && k!=2)
        return 0;

    else
        return noduri[t];

    }

int nod_final(int nod){
    int suma_grade=0;

    for(int i=1;i<=n;i++)
        suma_grade=suma_grade+grad_nod(noduri[i]);

    if(suma_grade==2)
        return 1;
    else
        return 0;

        }

int nodul_urmator(int nod){

    for(int i=1;i<=n;i++){
        if(matrice[nod][i]==1){
            if(grad_nod(noduri[i])!=1)
                return noduri[i];
            else if(nod_final(noduri[i]==1))
                return noduri[i];
                }
            }
        return -1;

    }

int completat(){

    for(int i=1;i<=n;i++){
        if(grad_nod(noduri[i]>0))
            return 0;
            }

    return 1;

    }

void eliminare_muchie(int rad,int nod){

    matrice[rad][nod]=matrice[nod][rad]=0;

    }

void gasire_ciclu(int rad){
    int nod;

    while(completat()==0){
        nod=nodul_urmator(rad);
        i_circ++;
        circuit[i_circ]=nod;
        eliminare_muchie(rad,nod);
        rad=nod;
        }
    }


int main()
{
   ifstream f("in.txt");
    int i,x,y,rad;

    f>>n>>m;

    muchii=new nod[m+1];

    for(i=1;i<=m;i++){
        f>>x>>y;
        muchii[i].x=x;
        muchii[i].y=y;
        }

   /* matrice=new int*[n];
    for(i=1;i<=n;i++)
        matrice[i]=new int[n+1];

    noduri=new int[n+1];*/

    for(i=1;i<=n;i++)
        noduri[i]=i;

    construire_matrice();

    rad=radacina();

    if(rad!=0){
        i_circ++;
        circuit[i_circ]=rad;
        gasire_ciclu(rad);
        cout<<"circuitul este: ";
        for(i=1;i<=i_circ;i++)
            cout<<circuit[i]<<" ";
        }
    else
        cout<<"nu exista circuit";

    return 0;

}
