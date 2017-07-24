import java.util.*;
import java.io.*;
import java.lang.*;
class Sum{
	
public static void main(String arg[])
{int n=0,i=0,ok=0;
int v[];
int k=0;
File file =new File("dateSum.in");
Scanner sc=null;
try{sc=new Scanner (file);}
catch (FileNotFoundException e)
{e.printStackTrace();}
n=sc.nextInt();
v=new int[n];
for(i=0;i<n;i++) v[i]=sc.nextInt();
	sc.close();
	Arrays.sort(v);
for(int j=0;j<n;j++)
	System.out.println(v[j]);
int a ,b,c,start,end;
for(int x=0;x<=n-3;x++)
{a=v[x];start=x+1;end=n-1;
while(start<end)
{b=v[start];
c=v[end];
if(a+b+c==0)
{System.out.printf("(%d,%d,%d)\n",a,b,c);
int d=v[end];
while(d==v[end])
end--;}
else if(a+b+c > 0)end--;
else
	start++;
}
}
}
}