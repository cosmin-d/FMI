#include <iostream>

using namespace std;

int main()
{
    int grp1,grp2;
    cout<<"introdu gradul polinomului 1: ";
    cin>>grp1;
    int p1[grp1+1];
    for(int i=grp1;i>=0;i--)
    {
        cout<<"coeficientul lui X^"<<i<<": ";
        cin>>p1[i];
    }
    cout<<"introdu gradul polinomului 2: ";
    cin>>grp2;
    int p2[grp2+1];int k=grp1+grp2+2;
    int rez[k];
    for(int i=grp2;i>=0;i--)
    {
        cout<<"coeficientul lui X^"<<i<<": ";
        cin>>p2[i];
    }
    for(int i=0;i<k;i++)
        rez[i]=0;
    for(int i=grp1;i>=0;i--)
    {
        for(int j=grp2;j>=0;j--)
            rez[i+j]=rez[i+j]+p1[i]*p2[j];
    }

for(int i=k-2;i>=0;i--)
if(rez[i]>0)
{
 cout<<"+"<<rez[i]<<"X^"<<i<<" ";
}
else if(rez[i]<0)
    cout<<rez[i]<<"X^"<<i<<" ";
}
