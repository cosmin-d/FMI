#include <iostream>

using namespace std;

void asambleaza_max(int v[],int i, int n){
    int l,r,max,aux;

    l=2*i;
    r=2*i+1;

    if(l<=n && v[l]>v[i])
        max=l;
    else
        max=i;
    if(r<=n && v[r]>v[max])
        max=r;
    if(max!=i){
        aux=v[i];
        v[i]=v[max];
        v[max]=aux;
        asambleaza_max(v,max,n);
        }
    }

void construieste_max_heap(int v[],int n){
    int j;

    for(j=n/2;j>=1;j--)
        asambleaza_max(v,j,n);
    }

void heapsort(int v[],int n){

    construieste_max_heap(v,n);

    int i,aux;

    for(i=n;i>=2;i--){
        aux=v[i];
        v[i]=v[1];
        v[1]=aux;
        asambleaza_max(v,1,i-1);
        }
    }

int main()
{
    int n,i;

    cout<<"citeste numarul de elemente "; cin>>n;
    int v[n];

    cout<<"citeste elementele"<<endl;
    for(i=1;i<=n;i++)
        cin>>v[i];
    cout<<endl;

    heapsort(v,n);

    cout<<"elementele sortate sunt"<<endl;
    for(i=1;i<=n;i++)
        cout<<v[i]<<' ';

    return 0;
}
