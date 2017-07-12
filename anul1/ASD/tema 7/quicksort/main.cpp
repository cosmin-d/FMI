#include <iostream>
#include <stdlib.h>

using namespace std;


int poz(int v[],int l,int r){
    int piv,aux,k;
    k=rand()%(r-l+1)+l;
    piv=v[k];
    while(l<r){
    if(v[l]>v[r]){
        aux=v[l];
        v[l]=v[r];
        v[r]=aux;
        }
    if(v[r]>piv)
        r--;
    if(v[l]<piv)
        l++;
        }
    k=l;
    return k;
    }

void quicksort(int v[],int l,int r){
    int k;
    if(l<r){
        k=poz(v,l,r);
        quicksort(v,l,k-1);
        quicksort(v,k+1,r);
        }
    }

int main()
{
    int n,i;
    cout<<"citeste numarul de elemente "; cin>>n;

    int v[n];
    cout<<"citeste vectorul"<<endl;
    for(i=1;i<=n;i++)
        cin>>v[i];

    quicksort(v,1,n);

    cout<<"vectorul sortat este"<<endl;
    for(i=1;i<=n;i++)
        cout<<v[i]<<' ';

    return 0;
}
