import java.io.*;
import java.util.*;
public class Planact
{
	static public int CB(Activitate activitati[], int index)
	{
		int st=0;
		int dr=index-1;
		while(st<=dr)
		{
			int mij=(st+dr)/2;
			if(activitati[mij].end<activitati[index].start)
			{
				if(activitati[mij+1].end<activitati[index].start)
					st=mij+1;
				else
					return mij;
			}
			else
				dr=mij-1;
		
		}
			return -1;
	}
	static public ArrayList<Activitate> Planif(Activitate activitati[],int n)
	{
		ArrayList<ArrayList<Activitate>> selAct =new ArrayList<ArrayList<Activitate>>();
		selAct.add(new ArrayList<Activitate>());
		selAct.get(0).add(activitati[0]);
		int actProfit[]=new int[n];
		actProfit[0]=activitati[0].profit;
       for(int i=1;i<n;i++)
	   {
		   ArrayList<ArrayList<Activitate>>activGas=new ArrayList<ArrayList<Activitate>>();
		   int profitGas=activitati[i].profit;
		   int a =CB(activitati,i);
		   if(a!=-1)
		   {
			   activGas.add(selAct.get(a));
			   activGas.get(0).add(activitati[i]);
			   profitGas+=actProfit[a];
		   }
		   else
		   {
			   activGas.add(new ArrayList<Activitate>());
			   activGas.get(0).add(activitati[i]);
		   }
		   actProfit[i]=Math.max(profitGas,actProfit[i-1]);
		   if(actProfit[i]==profitGas&&activGas.size()>0)
			   selAct.add(activGas.get(0));
		   else
			   selAct.add(selAct.get(i-1));
		   
	   }		
return selAct.get(n-1);	   
	}
	 public static void main(String[] args)
	 {
		File file = new File("activitati.in");
        Activitate[] activitati;
        ArrayList<Activitate> selAct;
        int n;
		try
		{
			Scanner sc = new Scanner(file);
			n=sc.nextInt();
			activitati=new Activitate[n];
			for(int i=0;i<n;i++)
			{
				activitati[i]=new Activitate(sc.nextInt(),sc.nextInt(),sc.nextInt());
			}
			Arrays.sort(activitati);
			selAct=Planif(activitati,n);
			int profit=0;
			for(int i=0;i<selAct.size();i++)
			{
				profit+=selAct.get(i).profit;
			}
			System.out.println(profit);
            System.out.println(selAct);
		}
		catch (IOException e) 
		{
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
	 }
}
