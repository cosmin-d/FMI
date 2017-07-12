#include <iostream>

using namespace std;

void shellsort(int v[],int n){
    int i,j,k,a,b;
    int K[5]={7,4,3,2,1};

    for(k=0;k<5;k++)
        if(k<n){
            a=K[k];
            for(i=a;i<=n;i++){
                b=v[i];
                j=i;
                while(j>a && v[j-a]>b){
                    v[j]=v[j-a];
                    j=j-a;
                    }
                v[j]=b;
                }
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

    shellsort(v,n);

    cout<<"elementele sortate sunt"<<endl;
    for(i=1;i<=n;i++)
        cout<<v[i]<<' ';

    return 0;
}
