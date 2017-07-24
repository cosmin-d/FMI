import java.io.*;
import java.util.*;
import java.lang.*;
class Puncte{
	
	static double dist(Punct A, Punct B)
	{
        return Math.sqrt((A.x - B.x) * (A.x - B.x) + (A.y - B.y) * (A.y - B.y));
    }
	
	
	static MinDist BF(Punct P[], int n)
	{
        MinDist min = new MinDist();
        min.dist = Double.MAX_VALUE;
        for(int i=0; i<n; i++)
         for(int j=i+1; j<n; j++)
           if(dist(P[i],P[j]) < min.dist)
		   {
                min.dist = dist(P[i],P[j]);
                min.A = P[i];
                min.B = P[j];
            }
        return min;
    }
	
	static MinDist closest(Punct P[], int n, double d)
	{
        MinDist min = new MinDist();
        min.dist = d;
        int j;
        for(int i=0; i<n; i++)
         for( j=i+1; j<n && (P[j].y - P[i].y) < min.dist; j++)
            if(dist(P[i],P[j]) < min.dist)
			{
              min.dist = dist(P[i],P[j]);
              min.A = P[i];
              min.B = P[j];
            }
        return min;
    }
	
	 static MinDist closestPts(Punct Px[], Punct Py[], int n)
	 {
        if(n <= 3) return BF(Px, n);
        Punct mid = Px[n/2];
        Punct PyL[] = new Punct[n/2 + 1];
        Punct PyR[] = new Punct[n - n/2 - 1];
        int st = 0;
        int dr = 0;
        for(int i=0; i<n; i++)
            if(Py[i].x <= mid.x)
			{
              PyL[st] = Py[i];
             st++;
            }
            else
			{
              PyR[dr] = Py[i];
              dr++;
            }
        Punct PxL[] = new Punct[n/2 + 1];
        Punct PxR[] = new Punct[n - n/2 - 1];
        int w = 0;
        for(int i=0; i<n/2 + 1; i++)
            PxL[i] = Px[i];
        for(int i=n/2 + 1; i<n; i++)
		{
          PxR[w] = Px[i];
          w++;
        }
        
		MinDist min1 = new MinDist();
        MinDist min2 = new MinDist();
        min1 = closestPts(PxL, PyL, n/2 + 1);
        min2 = closestPts(PxR, PyR, n - n/2 - 1);
        MinDist minDist = new MinDist();
		
        if(min1.dist < min2.dist)
            minDist = min1;
        else
            minDist = min2;
        
        Punct part[] = new Punct[n];
        int k = 0; 
        for(int i=0; i<n; i++)
            if(Math.abs(Py[i].x - mid.x) < minDist.dist)
			{
              part[k] = Py[i];
              k++;
            }
        MinDist minDist2 = new MinDist();
        minDist2 = closest(part, k, minDist.dist);
        if(minDist.dist <= minDist2.dist)
          return minDist;
        else 
		  return minDist2;
       }
	
public static void main(String args[])
{
	File file =new File("puncte.in");
		Scanner sc=null;
		int n,a,b;
		Punct byX[];
		Punct byY[];
		MinDist minDist = new MinDist();
		try 
		{   
		sc = new Scanner(file);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File Not Found");
		}
			n=sc.nextInt();
			byX=new Punct[n];
			byY=new Punct[n];
			for(int i=0; i<n; i++)
			  {
                   a = sc.nextInt();
                   b = sc.nextInt();
                   byX[i] = new Punct(a,b);
                   byY[i] = new Punct(a,b);
              }
	    Arrays.sort(byX,new Punct_ComparatorX());
		Arrays.sort(byY,new Punct_ComparatorY());
		minDist = closestPts(byX, byY, n);
		System.out.println(minDist);
}

}