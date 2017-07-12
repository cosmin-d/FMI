#include <iostream>

using namespace std;

struct polinom
{
    int grad ;
    int *va;
};
int suma(int v1[], int v2[], int rez[],int grad)
{
    for(int i=0;i<=grad;i++)
        rez[i]=v1[i]+v2[i];
        return 0;

}
 int diferenta (int v1[], int v2[], int rez[],int grad)
{
    for(int i=0;i<=grad;i++)
        rez[i]=v1[i]-v2[i];
        return 0;

}
int produs (int v1[], int v2[], int rez[],int gradv1, int gradv2)
{
  for(int i=0;i<=gradv1;i++)
        for(int j=0;j<=gradv2;j++)
        rez[i+j]+=v1[i]*v2[j];
  return 0;
}


int main()
{
    polinom p1;
    polinom p2;
    polinom rezultat;

    int i;

    cout<<"Introduceti gradul primului polinom: ";
    cin>>p1.grad;
    p1.va=new int [20];
for(i=0;i<20;i++)
    p1.va[i]=0;
    cout<<"Introduceti coeficientii polinomului  1 :"<<endl;
    for( i=p1.grad; i>=0; i--)
    {
        cout<<"X^"<<i<<" : ";
        cin>>p1.va[i];
    }

    cout<<"Introduceti gradul celui de-al doilea polinom: ";
    cin>>p2.grad;
    p2.va=new int [20];
for(i=0;i<20;i++)
    p2.va[i]=0;
    cout<<"Introduceti coeficientii polinomului  2 :"<<endl;
    for( i=p2.grad; i>=0; i--)
    {
        cout<<"X^"<<i<<" : ";
        cin>>p2.va[i];
    }
rezultat.va= new int [40];
rezultat.grad=max(p1.grad,p2.grad);
/*cout<<p1.grad<<endl;

    for(i=p1.grad; i>=0; i--)
        cout<<p1.va[i]<<" ";
    cout<<endl;
    for( i=p2.grad; i>=0; i--)
        cout<<p2.va[i]<<" ";
*/
cout<<"Suma :";
suma (p1.va,p2.va,rezultat.va,rezultat.grad);
for (i=rezultat.grad;i>=0;i--)
    cout<<rezultat.va[i]<<"X^"<<i<<" ";
cout<<endl;
cout<<"Diferenta :";
diferenta(p1.va,p2.va,rezultat.va,rezultat.grad);
for (i=rezultat.grad;i>=0;i--)
    cout<<rezultat.va[i]<<"X^"<<i<<" ";
    for(i=0;i<40;i++)
        rezultat.va[i]=0;
        cout<<endl;
        cout<<"Produsul :";
    produs(p1.va,p2.va,rezultat.va,p1.grad,p2.grad);
    for(i=p1.grad+p2.grad;i>=0;i--)
         cout<<rezultat.va[i]<<"X^"<<i<<" ";
}
