#include <iostream>
#include <cstdlib>
#include <string.h>

using namespace std;
class data
{   int zi ;
    int luna ;
    int an;
   public:
       friend class copii;
       friend class analize;
       friend class pacient;
       void afisare()
       {
           cout<<"("<<zi<<"."<<luna<<"."<<an<<")";
       }
};
class analize
{protected:
    data data_colesterol;
    data data_tensiune;
    int colesterol;
    int tensiune;
public:
    friend class pacient;
    friend class peste_40;
    friend class copii;
};
class proteina_C
{
    float c_reactiva;
    data data_c_reactiva;
public:
    friend class copii;
};
class pacient
{ protected:
    char nume [100];
    char prenume [100];
    int varsta;
    char adresa[100];
    analize analiza;
public:
    friend void afisare_nume(int x,pacient **&v,char*a);
  virtual  bool adult_peste_40(){return false;};
  virtual  bool adult_sub_40(){return false;};
  virtual  bool copil(){return false;};
    virtual const char* risc()
    {  int counter=0;
        if(analiza.colesterol>=240)
            counter++;
        if(analiza.tensiune>=140)
            counter++;
        if(counter ==1)
            return "DA";
        else if(counter>1)
            return "RIDICAT";
        else
            return "NU";
    }
  virtual  void citire()
    {
        cout<<"Nume pacient:";
        cin>>nume;
        cout<<"Prenume pacient:";
        cin>>prenume;
        cout<<"Varsta:";
        cin>>varsta;
        cout<<"Adresa: ";
        cin.get();
        cin.get(adresa,100);
        cout<<"Colesterol(mg/dl):";
        cin>>analiza.colesterol;
        cout<<"Data analizei"<<endl;
        cout<<"Zi:";
        cin>>analiza.data_colesterol.zi;
        cout<<"Luna:";
        cin>>analiza.data_colesterol.luna;
        cout<<"An:";
        cin>>analiza.data_colesterol.an;
        cout<<"Tensiune Arteriala:";
        cin>>analiza.tensiune;
        cout<<"Data analizei"<<endl;
        cout<<"Zi:";
        cin>>analiza.data_tensiune.zi;
        cout<<"Luna:";
      cin>>analiza.data_tensiune.luna;
        cout<<"An:";
        cin>>analiza.data_tensiune.an;
    }
   virtual void afisare()
    { cout<<nume<<" "<<prenume<<": Risc cardiovascular - "<<risc()<<";"<<"Colesterol:";
    analiza.data_colesterol.afisare();cout<<analiza.colesterol<<"mg/dl; TA ";
    analiza.data_tensiune.afisare();
    cout<<": "<<analiza.tensiune<<";"<<endl;

    }
};
class sub_40:public pacient
{
public:
bool    adult_sub_40()
    {
        return true;
    }
};
class peste_40:public pacient
{
    bool fumator;
    int sedentarism ;
public:
    bool adult_peste_40()
    {
        return true;
    }
    void citire()
    {
        pacient::citire();
        cout<<"Fumator?:(da/nu)";
        char s[2];
        cin>>s;
        if(s[0]=='d' && s[1]=='a')
            fumator=true;
        else
            fumator=false;
        cout<<"Sedentarism:"<<endl<<"0:scazut"<<endl<<"1:mediu"<<endl<<"2:ridicat";
        cin>>sedentarism;
    }
    const char* risc()
    {
        int counter=0;
        if(analiza.colesterol>=240)
            counter++;
        if(analiza.tensiune>=140)
            counter++;
            if(fumator && sedentarism==2)
                counter++;
        if(counter ==1)
            return "DA";
        else if(counter>1)
            return "RIDICAT";
        else
            return "NU";
    }
void afisare()
    { pacient::afisare();
    cout<<"Fumator:";
    if(fumator)
        cout<<"da; ";
    else
        cout<<"nu; ";
    cout<<"Sedentarism: ";
    if(sedentarism==0)
        cout<<"scazut."<<endl;
    else if(sedentarism==1)
        cout<<"mediu."<<endl;
    else
        cout<<"ridicat."<<endl;

    }
};
class copii:public pacient
{
    bool antecedente_familie;
    proteina_C prot;
    char nume_mama [100];
    char prenume_mama [100];
    char nume_tata [100];
    char prenume_tata [100];
public:
    bool copil()
    {
        return true;
    }
void citire()
{
    pacient::citire();
    cout<<"Proteina C reactiva: ";
    cin>>prot.c_reactiva;
    cout<<"Data analizei"<<endl;
    cout<<"Zi:";
        cin>>prot.data_c_reactiva.zi;
        cout<<"Luna:";
      cin>>prot.data_c_reactiva.luna;
        cout<<"An:";
        cin>>prot.data_c_reactiva.an;
    cout<<"Antecedente familie?:(da/nu)";
        char s[2];
        cin>>s;
        if(s[0]=='d' && s[1]=='a')
            antecedente_familie=true;
        else
            antecedente_familie=false;
}
void afisare()
{
    pacient::afisare();
    cout<<"Proteina C reacticva ";
    prot.data_c_reactiva.afisare();
    cout<<":"<<prot.c_reactiva<<"mg/dl; "<<"Antecedente familie:";
    if(antecedente_familie)
        cout<<"da. "<<endl;
    else
        cout<<"nu. "<<endl;

}
const char* risc()
{
    int counter=0;
        if(analiza.colesterol>=240)
            counter++;
        if(analiza.tensiune>=140)
            counter++;
            if(antecedente_familie)
                counter++;
        if(counter ==1)
            return "DA";
        else if(counter>1)
            return "RIDICAT";
        else
            return "NU";
}

};
void afisare_totala(int x,pacient**&v)
{
    cout<<"Adulti"<<endl;
    cout<<"Adulti peste 40 de ani"<<endl;
    for(int i=0;i<x;i++)
        if(v[i]->adult_peste_40())
        v[i]->afisare();
    cout<<"Adulti sub 40 de ani"<<endl;
    for(int i=0;i<x;i++)
        if(v[i]->adult_sub_40())
        v[i]->afisare();
        cout<<"Copii"<<endl;
        for(int i=0;i<x;i++)
        if(v[i]->copil())
        v[i]->afisare();
}
void afisare_risc(int x,pacient**&v)
{
    cout<<"Adulti"<<endl;
    cout<<"Adulti peste 40 de ani"<<endl;
    char s[8]="RIDICAT";
    for(int i=0;i<x;i++)
        if(v[i]->adult_peste_40())
        if(strcmp(v[i]->risc(),s)==0)
        v[i]->afisare();
         cout<<"Adulti sub 40 de ani"<<endl;
         for(int i=0;i<x;i++)
         if(v[i]->adult_sub_40())
         if(v[i]->risc(),s)
            v[i]->afisare();

}
void afisare_risc_copii(int x,pacient**&v)
{ cout<<"Copii"<<endl;
char s[8]="RIDICAT";
        for(int i=0;i<x;i++)
        if(v[i]->copil())
            if(strcmp(v[i]->risc(),s))
        v[i]->afisare();

}
void afisare_nume(int x , pacient **&v,char* a)
{
  cout<<"Adulti"<<endl;
    cout<<"Adulti peste 40 de ani"<<endl;
    for(int i=0;i<x;i++)
        if(v[i]->adult_peste_40())
            if(strcmp(v[i]->nume,a)==0)
        v[i]->afisare();
    cout<<"Adulti sub 40 de ani"<<endl;
    for(int i=0;i<x;i++)
        if(v[i]->adult_sub_40())
            if(strcmp(v[i]->nume,a)==0)
        v[i]->afisare();
        cout<<"Copii"<<endl;
        for(int i=0;i<x;i++)
        if(strcmp(v[i]->nume,a)==0)
        if(v[i]->copil())
        v[i]->afisare();
}
int main()
{   int counter=0;
char a;
    pacient **vptr;
    vptr=new pacient *[10];
   while(1)
   {   cout<<"Doriti sa introduceti pacienti ?(y/n)";
        cin>>a;
        if(a=='y')
      {  cout<<"1: Adult peste 40 de ani"<<endl<<"2:Adult sub 40 de ani"<<endl<<"3:Copil"<<endl;
       int x;
       cin>>x;
       switch(x)
       {
           case 1:vptr[counter]=new peste_40;
           vptr[counter]->citire();
           counter++;
           break;
           case 2:vptr[counter]=new sub_40;
           vptr[counter]->citire();
           counter++;
           break;
           case 3:vptr[counter]=new copii;
           vptr[counter]->citire();
           counter++;
           break;
       }
   }
   else
    break;
}
while (1)
    {  cout<<"Doriti sa faceti afisari ?(y/n)";
        char b;
        cin>>b;
        if(b=='y')
        {int x;
        cout<<"1:Afisare totala"<<endl;
        cout<<"2:Afisare toti pacientii cu risc cardiovascular ridicat"<<endl;
        cout<<"3:Afisare toti pacientii copii cu risc cardiovascular ridicat"<<endl;
        cout<<"4:Afisare dupa numele de familie"<<endl;
       cin>>x;
       switch(x)
       {
           case 1:
           afisare_totala(counter,vptr);
           break;
           case 2:
           afisare_risc(counter,vptr);
           break;
           case 3:
           afisare_risc_copii(counter,vptr);
           break;
           case 4:
           char d[100];
           cin>>d;
           afisare_nume(counter,vptr,d);
           break;
       }

        }
        else break;
    }
}
