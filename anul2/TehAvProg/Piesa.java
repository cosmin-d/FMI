import java.io.*;
import java.util.*;
class Piesa
{
int st,dr;
int nsub;
int npred;
int pred;
Piesa(int a , int b )
{
st=a;
dr=b;
}
Piesa(){}
public String toString()
{
  return "(" + st + "," + dr + ")"; 
}
}