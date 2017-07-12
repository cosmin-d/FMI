#include <iostream>
#include <fstream>

using namespace std;

int a[100][100],n;

int grad(int x){
    int g=0,i,j;
    i=x;
    for(j=1;j<=n;j++)
        g=g+a[i][j];
    return g;
    }

int numar_muchii(){
    int s=0,i,j;
    for(i=1;i<=n;i++)
        for(j=1;j<=n;j++)
            s=s+a[i][j];
    return s/2;
    }

void gradmax(){
    int g,gmax=0,i,j;
    for(i=1;i<=n;i++){
        g=0;
        for(j=1;j<=n;j++)
            g=g+a[i][j];
        if(g>gmax)
            gmax=g;
        }
    for(i=1;i<=n;i++){
        g=0;
        for(j=1;j<=n;j++)
            g=g+a[i][j];
        if(g==gmax)
            cout<<i<<' ';
        }
    }

int main()
{
    int i,j,x;
    fstream f;
    f.open("in.txt",ios::in);
    f>>n;
    for(i=1;i<=n;i++)
        for(j=1;j<=n;j++)
         f>>a[i][j];
    f.close();

    cout<<"citeste nodul cautat "; cin>>x;
    cout<<"gradul nodului "<<x<<" este "<<grad(x);

    cout<<endl;
    cout<<"numarul muchiilor este "<<numar_muchii();

    cout<<endl;
    cout<<"nodurile de grad maxim sunt ";
    gradmax();

    return 0;


}
