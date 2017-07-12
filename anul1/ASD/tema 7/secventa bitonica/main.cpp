#include <iostream>

using namespace std;

void cresc_descresc(int v[],int n){
    int i,j,k;
    int L[n],R[n];

    i=1;
    k=n;
    j=1;
    while(v[i]<v[i+1] && i<n){
        L[j]=v[i];
        j++;
        i++;
        }
    while(v[i]>v[i+1] && i<=n){
        R[k]=v[i];
        i++;
        k--;
        }

    i=1;
    j=1;

    while(j<=k && k<=n){
        if(L[j]<R[k]){
            v[i]=L[j];
            i++;
            j++;}
        else{
            v[i]=R[k];
            i++;
            k++;}
        }

    while(j<=k){
        v[i]=L[j];
        i++;
        j++;}

    while(k<=n){
        v[i]=R[k];
        i++;
        k++;}

    }

void descresc_cresc(int v[],int n){
    int i,j,k,h,g;
    int L[n],R[n];

    i=1;
    j=n;
    while(v[i]>v[i+1] && i<n){
        L[j]=v[i];
        j--;
        i++;
        }
    k=j;
    while(v[i]<v[i+1] && i<=n){
        R[k]=v[i];
        i++;
        k--;
        }

    i=1;
    k=j;
    j=j+1;

    while(k>=1 && j<=n){
       if(L[j]<R[k]){
            v[i]=L[j];
            i++;
            j++;}
        else{
            v[i]=R[k];
            i++;
            k--;}
            }

    while(k>=1){
        v[i]=R[k];
        i++;
        k--;
        }

    while(j<=n){
        v[i]=L[j];
        i++;
        j++;
        }
    }

int main()
{
    int n,i,sem;
    cout<<"citeste numarul de elemente "; cin>>n;

    int v[n];
    cout<<"citeste elementele vectorului "<<endl;
    for(i=1;i<=n;i++)
        cin>>v[i];

    if(v[1]<v[2])
        sem=0;
    else
        sem=1;

    if(sem==0)
        cresc_descresc(v,n);
    else
        descresc_cresc(v,n);

    cout<<"vectorul sortat este "<<endl;
    for(i=1;i<=n;i++)
        cout<<v[i]<<' ';
}
