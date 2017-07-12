#include <iostream>

using namespace std;

void merge_(int v[],int l,int m,int r){
    int i,j,k;
    int n1,n2;

    n1=m-l+1;
    n2=r-m;

    int L[n1],R[n2];

    for(i=0;i<n1;i++)
        L[i]=v[l+i];
    for(j=0;j<n2;j++)
        R[j]=v[m+j+1];

    i=0;
    j=0;
    k=l;

    while(i<n1 && j<n2){
        if(L[i]<=R[j]){
            v[k]=L[i];
            i++;
            k++;}
        else{
            v[k]=R[j];
            j++;
            k++;}
        }

    while(i<n1){
        v[k]=L[i];
        i++;
        k++;}

    while(j<n2){
        v[k]=R[j];
        j++;
        k++;}

    }

void mergesort(int v[],int l,int r){
    int m;
    if(l<r){
        m=(l+r)/2;
        mergesort(v,l,m);
        mergesort(v,m+1,r);
        merge_(v,l,m,r);
        }
    }

int main()
{
    int n,i;
    cout<<"citeste numarul de elemente "; cin>>n;

    int v[n];
    cout<<"citeste elementele vectorului "<<endl;
    for(i=1;i<=n;i++)
        cin>>v[i];

    mergesort(v,1,n);

    cout<<"vectorul sortat este "<<endl;
    for(i=1;i<=n;i++)
        cout<<v[i]<<' ';

    return 0;
}
