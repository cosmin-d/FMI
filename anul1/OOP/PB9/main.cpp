#include <iostream>
#include <math.h>

using namespace std;

class polinom
{
    int grad ;
    int *va;
public:
    polinom(int g=0);//constructor
    void creare();
    void afisare();
    friend polinom suma (polinom a , polinom b);
    friend polinom diferenta (polinom a , polinom b);
    friend polinom produs(polinom a , polinom b);
    friend polinom valoare(polinom a, int val);

};


polinom::polinom (int g) //constructor
{
    grad=g;
    va=new int [grad+1];//alocare dinamica a vectorului de adiacenta
    for(int i=0; i<=grad; i++)
        va[i]=0; //initializare vector adiacenta cu 0
}

polinom valoare(polinom a, int val) //calcul valoare intr-un punct
{
    int s;
    s=a.va[0];  //initializare suma cu valoarea termenului liber
    for(int i=1; i<=a.grad; i++)
        s+=a.va[i]*(pow(val,i));  //incrementare suma cu valoarea calculata in functie de coeficientul lui X si gradul acestuia
    cout<<"Valoarea polinomului in punctul "<<val<<" este :"<<s<<endl;
    return 0;
}
void polinom :: creare ()
{
    cout<<"Introduceti gradul: ";
    cin>>grad;
    va=new int [grad+1]; //alocsre dinamica a vectorului de coeficienti
    for(int i=0; i<=grad; i++)
        va[i]=0; //initializare vector cu 0
    cout<<"Introduceti coeficientii polinomului  :"<<endl;
    for(int  i=grad; i>=0; i--)
    {
        cout<<"X^"<<i<<" : ";
        cin>>va[i]; //citire coeficienti
    }
}
void polinom:: afisare()
{
    int i;
    for ( i=grad; i>=0; i--)
    {
        if(i!=grad && va[i]>0)
            cout<<"+"<<va[i]<<"X^"<<i<<" ";//afisare polinom in functie de coeficienti
        else if(va[i]!=0) //nu vor fi afisati coeficientii 0 ai polinomului
            cout<<va[i]<<"X^"<<i<<" ";
    }
    cout<<endl;
}

polinom suma (polinom a , polinom b)
{
    polinom rez;
    if(a.grad>b.grad)
    {
        rez.grad=a.grad;
        rez.va=new int [rez.grad+1];//alocare dinamica
        for(int i=0; i<=b.grad; i++)
            rez.va[i]=a.va[i]+b.va[i];//calcul suma polinoamelor
        for(int i=b.grad+1; i<=a.grad; i++)
            rez.va[i]=a.va[i];//copierea valorilor coeficientilor de la gradele mai mari decat gradul celui de-al doilea polinom
    }
    else  //analog cu expresia de mai sus, consideranda gradul celui de-al doilea polinom mai mare sau egal decat gradul primului
    {
        rez.grad=b.grad;
        rez.va=new int [rez.grad+1];
        for(int i=0; i<=a.grad; i++)
            rez.va[i]=a.va[i]+b.va[i];
        for(int i=a.grad+1; i<=b.grad; i++)
            rez.va[i]=b.va[i];
    }

    return rez;//rez memoreaza vectorul de adiacenta al polinomului suma
}

polinom produs(polinom a , polinom b)
{
    polinom rez;
    rez.grad=a.grad+b.grad;//gradul produsului a doua polinoame este suma gradelor celor 2 polinoame
    rez.va=new int [rez.grad];//alocare dinamica
    for(int i=0; i<=rez.grad; i++)
        rez.va[i]=0; //initializare cu 0 a vectorului de adiacenta
    for(int i=0; i<=a.grad; i++)
        for(int j=0; j<=b.grad; j++)
            rez.va[i+j]+=a.va[i]*b.va[j];//calcul produs
    return rez; //rez memoreaza produsul celor 2 polinoame
}

polinom diferenta (polinom a , polinom b)
{
    polinom rez;
    if(a.grad>b.grad)  //comparare grade
    {
        rez.grad=a.grad;  //grad maxim
        rez.va=new int [rez.grad+1]; //alocare dinamica
        for(int i=0; i<=b.grad; i++)
            rez.va[i]=a.va[i]-b.va[i]; //calcul diferenta
        for(int i=b.grad+1; i<=a.grad; i++)
            rez.va[i]=a.va[i]; //copiere valori ramase
    }
    else
    {
        rez.grad=b.grad;//grad maxim
        rez.va=new int [rez.grad+1];//alocare dinamica
        for(int i=0; i<=a.grad; i++)
            rez.va[i]=a.va[i]-b.va[i]; //calcul diferenta
        for(int i=a.grad+1; i<=b.grad; i++)
            rez.va[i]=-b.va[i]; //copiere valori ramase cu schimbarea semnului
    }

    return rez; //returnare rezultat
}

int main()
{
    polinom p1,p2;  //declarare variabile tip "polinom"
    cout<<"Introduceti primul polinom: \n";
    p1.creare();
    cout<<"Introduceti al doilea polinom: \n";

    p2.creare();
    p1.afisare();
    p2.afisare();
    polinom s ;
    s=suma(p1,p2);
    cout<<"Suma este:";
    s.afisare();
    suma(p1,p2);
    polinom d;
    d=diferenta(p1,p2);
    cout<<"Diferenta este:";
    d.afisare();
    diferenta(p1,p2);
    diferenta (p2,p1);
    polinom p;
    p=produs(p1,p2);
    cout<<"Produsul este:";
    p.afisare();
    int v1,v2;
    cout<<"Introduceti valoarea lui X pentru primul polinom :";
    cin>>v1;
    cout<<"Introduceti valoarea lui X pentru al doilea polinom :";
    cin>>v2;
    valoare(p1,v1); //calcul valoare in punctul v1
    valoare(p2,v2); //calcul valoare in punctul v2


}
