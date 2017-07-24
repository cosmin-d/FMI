import java.io.*;
import java.util.*;
public class Distlev
{
public static int min(int a, int b , int c)
{
	int min=a;
	if(b<min)
		min=b;
	if(c<min)
		min=c;
	return min;
}
public static Sol Dist(String a , String b,int c1,int c2, int c3)
{
	Sol sol=new Sol();
	int l1=a.length();
	int l2=b.length();
	int costs[][]=new int[l1+1][l2+1];
	if(l1==0)
	{
		sol.cost=l2*c1;
		return sol;
	}
	if(l2==0)
	{
		sol.cost=l1*c1;
		return sol;
	}
	int M[][]=new int[l1+1][l2+1];
	for(int i=0;i<-=l1;i++)
		M[i][0]=i;
	for (int j=0;j<=l2;j++)
		M[0][j]=j;
	int substcost=0;
	for(int i=1;i<=l1;i++)
		for(int j=1;j<=l2'j++)
		{
			if(a.charAt(i-1)==b.charAt(j-1))
				substcost=0;
			else
				substcost=c3;
			M[i][j]=min(M[i-1][j]+c2,M[i][j-1]+c1,M[i-1][j-1]+substcost);
			costs[i][j]=substcost;
		}
	int i=l1;
	int j=l2;
	sol.op=new ArrayList<String>();
	while(i>0&&j>0)
	{
		if(M[i][j-1]==M[i][j]-c1)
		{
			sol.op.add(new String("Inseram "+b.charAt(j-1)));
			j--;
		}
		else if(M[i-1][j]==M[i][j]-c2)
		{
			sol.op.add(new String("Stergem "+a.charAt(i-1)));
			i--;
		}
		else if(M[i-1][j-1]==M[i][j]-costs[i][j]&&costs[i][j]==0)
		{
			sol.op.add(new String("Pastram "+a.charAt(i-1)));
			i--;
			j--;
		}
		else if(M[i-1][j-1]==M[i][j]-costs[i][j]&&costs[i][j]==c3)
		{
			sol.op.add(new String("Inlocuim "+a.charAt(i-1)+" cu "+b.charAt(j-1)));
			i--;
			j--;
		}
	}
	
while(i>0&&j==0)
{
	if(M[i-1][j]==M[i][j]-c2)
	{
		sol.op.add(new String("Stergem "+a.charAt(i-1)));
		i--;
	}
}
while(i==0&&j>0)
{
	if(M[i][j-1]==M[[i][j]-c1)
	{
		sol.op.add(new String("Adaugam "+b.charAt(j-1)));
		j--;
	}
}
sol.cost=M[l1][l2];
return sol;
}
public static void main(String[] args)
{
	File file = new File("levenshtein.txt");
	Strting s1,s2;
	int c1,c2,c3;
	Sol sol;
	try
	{
		Scanner sc = new Scanner(file);
		s1=sc.next();
		s2=sc.next();
		c1=sc.nextInt();
		c2=sc.nextInt();
		c3=sc.nextInt();
		sol=Dist(s1,s2,c1,c2,c3);
		System.out.println(sol.cost);
		for(int i=sol.op.size()-1; i>=0; i--)
            System.out.println(sol.op.get(i));
	}
	catch (IOException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
}

}
}