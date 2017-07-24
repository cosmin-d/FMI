import java.io.*;
import java.util.*;
import java.lang.Math;
public class Domino
{
	public static MaxEl legaturi(Piesa p, Piesa Piese[],int n,int k, int lung[])
	{
		MaxEl el=new MaxEl();
		el.max=0;
		el.pred=-1;
		el.npred=0;
		el.pos=0;
		ArrayList<Integer>lungpos=new ArrayList<Integer>();
		for(int i=k;i<n;i++)
		{
			if(Piese[i].st == p.dr)
				lungpos.add(i);
		}
	    for(int i=0; i<lungpos.size(); i++)
	    {
		  if(lung[lungpos.get(i)]>el.max)
		  {
			  el.max=lung[lungpos.get(i)];
			  el.pred=lungpos.get(i);
		  }
	    }
		for(int i=0; i<lungpos.size(); i++)
		{
			if(lung[lungpos.get(i)]==el.max)
				el.npred++;
			if(lung[lungpos.get(i)]==el.max)
				el.pos+=Piese[lungpos.get(i)].npred;
		}
			
		return el;
		
	}
public static MaxSeq dominoStr (Piesa Piese[],int n)
{
	int lung[]=new int[n];
	MaxEl el=new MaxEl();
	MaxSeq ms=new MaxSeq();
	int max,max1;
	max=1;
	max1=0;
	ms.sequence=new ArrayList<Integer>();
	lung[n-1]=1;
	Piese[n-1].pred=-1;
	Piese[n-1].nsub=0;
	for(int i=n-2; i>=0; i--)
	{
		el=legaturi(Piese[i],Piese,n,i+1,lung);
		lung[i]=el.max+1;
		Piese[i].pred=el.pred;
		Piese[i].nsub=el.pos;
		Piese[i].npred=el.npred;
	}
	for(int i=0; i<n; i++)
	{
		if(lung[i]>max)
		{
			max=lung[i];
			max1=i;
		}
	}
	while (max1!=-1)
	{
		ms.sequence.add(max1);
		max1=Piese[max1].pred;
	}
	ms.repetari=0;
	for(int i=0; i<n; i++)
	{
		if(lung[i]==max)
		{
			ms.repetari+=Piese[i].nsub;
		}
	}
	return ms;
}	
	
	
public static void main(String args[])
{
	 File file = new File("domino.in");
	 Piesa Piese[];
	 int n;
	 MaxSeq maxseq=new MaxSeq();
	 try
	 {
		 Scanner sc = new Scanner(file);
         n = sc.nextInt();
		 Piese=new Piesa[n];
		 for(int i=0; i<n; i++)
            Piese[i] = new Piesa(sc.nextInt(),sc.nextInt());
		maxseq=dominoStr(Piese,n);
		for(int i=0; i<maxseq.sequence.size(); i++)
            System.out.println(Piese[maxseq.sequence.get(i)]);
		System.out.println(maxseq.repetari);
	 }
	 catch(IOException e)
	 {
		 e.printStackTrace();
            System.out.print(e.getMessage());
	 }
}	
	
}