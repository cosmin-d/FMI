import java.util.*;
import java.io.*;
import java.lang.Math;
public class Mediana{
	
public static void main(String args[])
{
		
		File file =new File("mediana.in");
		Scanner sc=null;
			int m, n;
			int v1[];
			int v2[];
		try
		{
		   sc=new Scanner (file);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File Not Found");
		}
		
	 n=sc.nextInt();
	 v1=new int[n];
	 for(int i=0; i<n; i++)
     v1[i] = sc.nextInt();
     m=sc.nextInt();
	 v2=new int[m];
	 for(int i=0; i<m; i++)
     v2[i] = sc.nextInt();
	 System.out.println(Med(v1, v2, n, m));
}
private static float Med(int v1[], int v2[], int n, int m) 
	{ 
		int st = (n + m +1) / 2;
        int dr = (n + m + 2) / 2;
        return ((medEl(v1,v2,n,m,st)+medEl(v1,v2,n,m,dr))/2);
	}
private static float medEl(int v1[], int v2[], int n, int m, int k) 
		{
        if(n > m)
          return medEl(v2,v1,m,n,k);
        if(n == 0 && m > 0)
          return v2[k-1];
		if(k==1)
          return Math.min(v1[0], v2[0]);

        int i =  Math.min(n, k/2) ;
        int j =  Math.min(m, k/2) ;   
		if(v1[i-1] > v2[j-1])
            return medEl(v1, Arrays.copyOfRange(v2, j, m),n,m-j, k-j);
        else
            return medEl(Arrays.copyOfRange(v1, i, n), v2,n-i,m ,  k-i);
    }
}