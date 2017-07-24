import java.util.Scanner;
class MyClass{
public static void main(String args[])
{Complex a,b;
a=new Complex(2,3);
b=new Complex(3,4);
System.out.println(a.Modul());
a.Conjugatul().Afisare();
a.Suma(b);
a.Afisare();
Scanner IntScanner=new Scanner(System.in);
int x=IntScanner.nextInt();
System.out.println(x);
}
}
class Complex{
private int real ;
private int imaginar;
Complex(){real=0;imaginar=0;}
Complex(int x){real=x;imaginar=0;}
Complex(int x, int y ){real=x;imaginar=y;}
double Modul(){return Math.sqrt(real+imaginar);}
Complex Conjugatul(){Complex a ;a=new Complex(real,0-imaginar);return a;}
Complex Suma(Complex a){real=real+a.real;imaginar+=a.imaginar;return this;}
void Afisare(){if(imaginar>=0)
System.out.println(real+" +"+imaginar+"i");
else
System.out.println(real+" "+imaginar+"i");
}
static Complex Plus (Complex a, Complex b){return new Complex(a.real+b.real,a.imaginar+b.imaginar); }


}

