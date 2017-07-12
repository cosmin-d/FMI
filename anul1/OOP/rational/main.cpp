#include <iostream>
#include <cmath>

using namespace std;

class rational
{
private:
    int numarator;
    int numitor;
    void simplificare()
    {int a ,b;
    if(numarator<0 && numitor<0)
    {set_numitor(-numitor);
    set_numarator(-numarator);}
    if(numarator<0)
        a=-numarator;
    else
        a=numarator;
    if(numitor<0)
        b=-numitor;
    else
        b=numitor;
        if(a<b)
        {
            for(int i=a; i>1; i--)
                if(a%i==0 && b%i==0)
                {
                    numarator=numarator/i;
                    numitor=numitor/i;
                }

        }
        else if(a>b)
        {
            for(int i=b; i>1; i--)
                if(a%i==0 && b%i==0)
                {
                    numarator=numarator/i;
                    numitor=numitor/i;
                }
        }
        else
           {
             set_numarator(1);
        set_numitor(1);
           }
    }
public:
    rational();
    rational (const rational &w);
     void citire();
    void afisare();
    int get_numarator()
    {
        return numarator;
    }
    int get_numitor()
    {
        return numitor;
    }
    void set_numarator(int x)
    {
        numarator=x;
    }
    void set_numitor(int y)
    {
        numitor=y;

    }
 friend rational operator+(const rational& a, const rational& b);
friend rational operator+(int& a ,const rational& b);
friend rational operator+(const rational& a , int &b);
friend rational operator*(const rational &a,const rational &b);
friend rational operator*(const rational &a,int &b);
friend rational operator*(int &a,const rational &b);
friend rational operator+=(rational &a,const rational &b);


};

rational:: rational()
{
    numarator=1;
    numitor=1;
}
rational::rational (const rational &w)
{
       numarator=w.numarator;
       numitor=w.numitor;
   }
void  rational::citire()
   {   int i;
       cout<<"Introduceti numaratorul"<<endl;
       cin>>i;
       set_numarator(i);
       cout<<"Introduceti numitorul"<<endl;
       cin>>i;
       if(i!=0)
       set_numitor(i);
       else
       {while(i==0)
          {
           cout<<"Introduceti un numitor diferit de 0"<<endl;
           cin>>i;
          }
           set_numitor(i);

       }
       simplificare();
   }
   void rational::afisare()
   {int a,b;
   a=get_numarator();
   b=get_numitor();
   if((a>=0 && b<0) || (a<0 && b>0))
    cout<<"-"<<abs(a)<<"/"<<abs(b)<<" ";
    else
        cout<<a<<"/"<<b<<" ";

   }
   rational  operator+(const rational& a,const rational& b)
   {
      rational c;
      c.numitor=b.numitor *a.numitor;
      c.numarator=(b.numarator * a.numitor) + (a.numarator * b.numitor);
      c.simplificare();
      return c;
   }
   rational operator+(int& a ,const rational& b)
   {
       rational c,d;
       d.set_numitor(1);
       d.set_numarator(a);
       c.numitor=b.numitor *d.numitor;
      c.numarator=(b.numarator * d.numitor) + (d.numarator * b.numitor);
      c.simplificare();
      return c;

   }
   rational operator+(const rational& a , int &b)
   {
     rational c,d;
     d.set_numitor(1);
       d.set_numarator(b);
        c.numitor=a.numitor *d.numitor;
      c.numarator=(a.numarator * d.numitor) + (d.numarator * a.numitor);
      c.simplificare();
      return c;


   }
   rational operator*(const rational &a,const rational &b)
   {
       rational c;
       c.numarator=a.numarator * b.numarator;
       c.numitor=a.numitor * b.numitor;
       c.simplificare();
       return c;
   }

   rational operator+=(rational &a,const rational &b)
   {
       rational c;
      c.numitor=b.numitor *a.numitor;
      c.numarator=(b.numarator * a.numitor) + (a.numarator * b.numitor);
      c.simplificare();
      a.set_numarator(c.numarator);
      a.set_numitor(c.numitor);
       return a ;
   }
int main()
{
rational a,b,c,d;
a.citire();
b.citire();
a.afisare();
b.afisare();
a+=b;
a.afisare();
int x;
cin>>x;
cout<<endl;
d=a+x;
d.afisare();
}
