#include <iostream>

using namespace std;

class polinom
{
    int grad ;
    int *va;
    public:
        polinom(int g=0);
    void creare();
    void afisare();
    friend polinom suma (polinom a , polinom b);
    friend polinom diferenta (polinom a , polinom b);
    friend polinom produs(polinom a , polinom b);

};
polinom::polinom (int g)
{
    grad=g;
    va=new int [grad+1];
    for(int i=0;i<=grad;i++)
        va[i]=0;
}
void polinom :: creare ()
{
    cout<<"Introduceti gradul: ";
    cin>>grad;
    va=new int [grad+1];
for(int i=0;i<=grad;i++)
    va[i]=0;
    cout<<"Introduceti coeficientii polinomului  :"<<endl;
    for(int  i=grad; i>=0; i--)
    {
        cout<<"X^"<<i<<" : ";
        cin>>va[i];
    }
}
 void polinom:: afisare()
 {
 for (int i=grad;i>=0;i--)
    cout<<va[i]<<"X^"<<i<<" ";
    cout<<endl;
 }

 polinom suma (polinom a , polinom b)
 {polinom rez;
 if(a.grad>b.grad)
 {
     rez.grad=a.grad;
     rez.va=new int [rez.grad+1];
     for(int i=0;i<=b.grad;i++)
        rez.va[i]=a.va[i]+b.va[i];
        for(int i=b.grad+1;i<=a.grad;i++)
    rez.va[i]=a.va[i];
 }
 else{
    rez.grad=b.grad;
         rez.va=new int [rez.grad+1];
     for(int i=0;i<=a.grad;i++)
        rez.va[i]=a.va[i]+b.va[i];
        for(int i=a.grad+1;i<=b.grad;i++)
    rez.va[i]=b.va[i];
 }

        return rez;
 }

polinom produs(polinom a , polinom b)
{
    polinom rez;
    rez.grad=a.grad+b.grad;
    rez.va=new int [rez.grad];
    for(int i=0;i<=rez.grad;i++)
        rez.va[i]=0;
    for(int i=0;i<=a.grad;i++)
        for(int j=0;j<=b.grad;j++)
        rez.va[i+j]+=a.va[i]*b.va[j];
return rez;
}

  polinom diferenta (polinom a , polinom b)
 {polinom rez;
 if(a.grad>b.grad)
 {
     rez.grad=a.grad;
     rez.va=new int [rez.grad+1];
     for(int i=0;i<=b.grad;i++)
        rez.va[i]=a.va[i]-b.va[i];
        for(int i=b.grad+1;i<=a.grad;i++)
    rez.va[i]=a.va[i];
 }
 else{
    rez.grad=b.grad;
         rez.va=new int [rez.grad+1];
     for(int i=0;i<=a.grad;i++)
        rez.va[i]=a.va[i]-b.va[i];
        for(int i=a.grad+1;i<=b.grad;i++)
    rez.va[i]=-b.va[i];
 }

        return rez;
 }

int main()
{
    polinom p1,p2;
    cout<<"Introduceti primul polinom: \n";
p1.creare();
cout<<"Introduceti al doilea polinom: \n";

    p2.creare();
    p1.afisare();
    p2.afisare();
    polinom s ;
    s=suma(p1,p2);
    s.afisare();
    polinom d;
    d=diferenta(p1,p2);
    d.afisare();
    polinom p;
    p=produs(p1,p2);
    p.afisare();


}
