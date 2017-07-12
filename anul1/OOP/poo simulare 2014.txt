#include <iostream>
#include <cstdio>
#include<string.h>


using namespace std;
class autori
{
    char nume[100] ;
    char prenume[100] ;
public:
    friend class referinta;
    const char *get_nume()
    {
        return nume;
    };
};
class referinta
{
protected:
    char nume_proprietar [100];
    int nr_autori;
    autori* aut;
    char titlu [100] ;
    int an_publicare;
public:
    friend void afisare_nume_dat(int x, referinta **&v,char *a);
    friend void afisare_electronice(int x, referinta **&v,char *a);
    virtual bool is_carte()
    {
        return false;
    };
    virtual bool is_articol()
    {
        return false;
    };
    virtual bool is_web()
    {
        return false;
    };
    int get_an()
    {
        return an_publicare;
    };
    referinta()
    {
        cout<<"Introduceti numarul de autori: ";
        cin>>nr_autori;
        while(nr_autori==0)
        {
            cout<<"Introduceti numarul de autori: ";
            cin>>nr_autori;
        }

        aut=new autori[nr_autori];
        cout<<"Creat"<<endl;
    }
    virtual void   citire()
    {
        cout<<"Introduceti titlul: ";
        cin.get();
        cin.get(titlu,100);
        for(int i=0; i<nr_autori; i++)
        {
            cout<<"Introduceti numele autorului: ";
            cin>>aut[i].nume;
            cout<<"Introduceti prenumele autorului: ";
            cin>>aut[i].prenume;

        }
        cout<<"Introduceti anul publicarii: ";
        cin>>an_publicare;


    }
    virtual void afisare()
    {
        for(int i=0; i<nr_autori; i++)
        {
            cout<<aut[i].nume<<", "<<aut[i].prenume[0]<<".";
            if(i<nr_autori-1)
                cout<<",";
        }
    }
};
class articol:public referinta
{
    char nume_articol [100] ;
    int nr_articol;
    char numere_pagini[100] ;
public:
    void citire()
    {
        referinta::citire();
        cout<<"Introduceti numele articolului: ";
        cin.get();
        cin.get(nume_articol,100);

        cout<<"Introduceti numarul articolului: ";
        cin>>nr_articol;
        cout<<"Introduceti numerele paginilor revistei: ";
        cin.get();
        cin.get(numere_pagini,100);
    }
    void afisare()
    {
        referinta::afisare();
        cout<<":"<<nume_articol<<" "<<titlu<<","<<an_publicare<<","<<nr_articol<<","<<numere_pagini<<endl;
    }
    bool is_articol()
    {
        return true;
    }
};
class carte:public referinta
{
    char nume_editura [100] ;
    char orasul_editurii [100] ;
public:
    void citire()
    {
        referinta::citire();
        cout<<"Introduceti numele editurii: ";
        cin.get();
        cin.get(nume_editura,100);
        cout<<"Intoduceti orasul editurii: ";
        cin.get();
        cin.get(orasul_editurii,100);
    }
    void afisare()
    {

        referinta::afisare();
        cout<<":"<<titlu<<","<<nume_editura<<","<<orasul_editurii<<","<<an_publicare<<endl;

    }
    bool is_carte()
    {
        return true ;
    }
};
class web:public referinta
{
    char URL [100] ;
    int zi;
    int luna;
    int an;
public:
    web(){};
    void citire()
    {
        cout<<"Introduceti numele proprietarului: ";
        cin.get();
        cin.get(nume_proprietar,100);
        cout<<"Introduceti titlul: ";
        cin.get();
        cin.get(titlu,100);
        cout<<"Introduceti URL: ";
        cin>>URL;
        cout<<"Introduceti data accesarii"<<endl;
        cout<<"Zi: ";
        cin>>zi;
        cout<<"Luna: ";
        cin>>luna;
        cout<<"An: ";
        cin>>an;
    }
    void afisare()
    {
        cout<<nume_proprietar<<":"<<titlu<<" "<<URL<<"(accesat "<<zi<<"."<<luna<<"."<<an<<")"<<endl;
    }
    bool is_web()
    {
        return true;
    }
};
void afisare_totala(int x,referinta** &v)
{
    cout<<"Bibliografie"<<endl;
    cout<<"Articole";
    for(int i=0; i<x; i++)
        if(v[i]->is_articol())
            v[i]->afisare();
    cout<<"Carti"<<endl;
    for(int i=0; i<x; i++)
        if(v[i]->is_carte())
            v[i]->afisare();
    cout<<"Webografie"<<endl;
    for(int i=0; i<x; i++)
        if(v[i]->is_web())
            v[i]->afisare();
}
void afisare_an_dat(int x,referinta** &v, int an)
{
    cout<<"Bibliografie"<<endl;
    cout<<"Articole";
    for(int i=0; i<x; i++)
        if(v[i]->is_articol())
            if(v[i]->get_an()==an)
                v[i]->afisare();
    cout<<"Carti"<<endl;
    for(int i=0; i<x; i++)
        if(v[i]->is_carte())
            if(v[i]->get_an()==an)
                v[i]->afisare();
}
void afisare_nume_dat(int x, referinta ** &v,char *a)
{
    cout<<"Bibliografie"<<endl;
    cout<<"Articole";
    for(int i=0; i<x; i++)
        if(v[i]->is_articol())
            for(int y=0; y<v[i]->nr_autori; y++)
                if(strcmp(v[i]->aut[y].get_nume(),a)==0)
                    v[i]->afisare();
    cout<<"Carti"<<endl;
    for(int i=0; i<x; i++)
        if(v[i]->is_carte())
            for(int y=0; y<v[i]->nr_autori; y++)
                if(strcmp(v[i]->aut[y].get_nume(),a)==0)
                    v[i]->afisare();
}
void afisare_electronice(int x, referinta **&v,char *a)
{
    cout<<"Webografie"<<endl;
    for(int i=0; i<x; i++)
        if(v[i]->is_web())
                if(strcmp(v[i]->nume_proprietar,a)==0)
                    v[i]->afisare();

}
int main(void)
{
    char a ;
    int contor=0;
    //articol a;
    //a.citire();
    //a.afisare();
    referinta **vptr;
    vptr=new referinta*[10];
    //vptr[0]=new web;
   // vptr[0]->citire();
   // vptr[0]->afisare();
   while(contor<10)
    {
        cout<<"Doriti sa introduceti o referinta ?(y/n)";
        cin>>a;
        if(a=='y')
        {
            cout<<"Introdu 0 pentru articol"<<endl;
            cout<<"Introdu 1 pentru carte"<<endl;
            cout<<"Introdu 2 pentru web"<<endl;
            int x;
            cin>>x;
            switch(x)
            {
            case 0:
                vptr[contor]=new articol;
                vptr[contor]->citire();
                contor++;
                break;
            case 1:
                vptr[contor]=new carte;
                vptr[contor]->citire();
                contor++;
                break;
            case 2:
                vptr[contor]=new web;
                vptr[contor]->citire();
                contor++;
                break;
            }
        }
        else break;

    }
    while (1)
    {
        cout<<"Doriti sa faceti afisari ?(y/n)";
        char b;
        cin>>b;
        if(b=='y')
        {
            cout<<"0 : Afisate toate referintele"<<endl;
            cout<<"1 : Afisare referinte tiparite publicate intr-un an dat"<<endl;
            cout<<"2 : Afisare toate referintele in format tiparit care au numele unuia dintre autori egal cu un nume dat"<<endl;
            cout<<"3 : Afisare toate referintele in format electronic care au numele proprietarului egal cu un nume dat"<<endl;
            int x;
            cin>>x;
            switch(x)
            {
            case 0:
                afisare_totala(contor,vptr);
                break;
            case 1:
                int x;
                cin>>x;
                afisare_an_dat(contor,vptr,x);
                break;
            case 2:
                char a[100];
                cin>>a;
                afisare_nume_dat(contor,vptr,a);
                break;
            case 3:
                char c[100];
                cin>>c;
                afisare_electronice(contor,vptr,c);
                break;
            }
        }
        else break;
    }
    return 0;
}
