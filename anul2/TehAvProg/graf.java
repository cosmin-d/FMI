import java.util.*;
import java.io.*;

public class graf{
public static void main(String args[])
{
Scanner scFisier=null;
PrintWriter outFisier=null;
try
{
scFisier=new Scanner(new File("date_graf.in"));
outFisier=new PrintWriter("date_graf.out");

int n=scFisier.nextInt();
int m=scFisier.nextInt();
ArrayDeque<Integer>[] l = new ArrayDeque[n+1];

for(int i=1;i<=m;i++)
{
int x=scFisier.nextInt();
int y=scFisier.nextInt();
if(l[x]!=null)l[x].add(y);
else {l[x]=new ArrayDeque<>();
l[x].add(y);}
if(l[y]!=null)l[y].add(x);
else {l[y]=new ArrayDeque<>();
l[y].add(x);
}
}

int first=scFisier.nextInt();

for(int i=1;i<=n;i++)
{
outFisier.print(i);
outFisier.println(l[i]);
}

int prim=1;
int ultim=1;
int[] viz=new int[n+1];
for(int i=0;i<n+1;i++)
viz[i]=0;
ArrayDeque<Integer> coada=new ArrayDeque<>();
viz[first]=1;
coada.add(first);

while(prim<=ultim)
{
for(int k=1;k<=n;k++)
if(l[coada.get(prim)].contains(k)==true && viz[k]==0)
{
ultim++;
coada.add(k);
viz[k]=1;
System.out.println(k);
}
prim++;
}
System.out.println(coada);

}
catch(FileNotFoundException fnf)
{
System.out.println("File not found");
}


scFisier.close();
outFisier.close();
}
}
