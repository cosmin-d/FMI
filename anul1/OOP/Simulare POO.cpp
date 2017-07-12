#include <iostream>
#include <string>


using namespace std;
class data
{
    int zi;
    int luna;
    int an;
public:
    data()
    {
    zi=1;
    luna=1;
    an=1990;
    }
    friend class articole;
};
class autor
{
    char nume[20];
    char prenume [20];
    friend class articole;
};
 class articole
{ int nr_autori;
          autor* aut;
          char titlu[100];
          int an_pub;
          int numar;
          char pagini;
      public:
  void   citire()
        {  cout<<"Introduceti numarul autorilor:";
            cin>>nr_autori;
            for(int i=0;i<nr_autori;i++)
                aut=new autor [nr_autori];
            for(int i=0;i<nr_autori;i++)
            {cout<<"Nume autor:";
                cin>>aut[i].nume;
                cout<<"Prenume autor:";
                cin>>aut[i].prenume;
            }
            cout<<"Titlul articolului:";
            getline(cin,titlu);
            cout<<"Anul publicarii:"<<endl;
           cin>>an_pub;
           cout<<"Numarul:";
           cin>>numar;
           cout<<"Pagini:";
           getline(pagini);
        }
void afisare ()
{ cout<<"Autori:"<<endl;
    for(int i=0;i<nr_autori;i++)
        cout<<aut[i].nume<<" "<<aut[i].prenume<<", ";
        cout<<":"<<titlu
}

/*~articole()
{
    for(int i=0;i<nr_autori;i++)
        delete aut[i];}*/
};
        class carti
         {
            int nr_autori;

        };
    class web
    {

    };
class referinte
{

    int nr_carti;
    int nr_articole;
    int nr_web;
    carti c[20];
    articole a[20];
    web w[20];

friend class carti;
friend class articole;
friend class web;

};

int main()
{
    articole a;
    a.citire();
    a.afisare();
}


