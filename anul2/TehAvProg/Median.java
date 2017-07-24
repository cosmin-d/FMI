import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.Math;

public class Median {
    
    private static float findMedian(int[] arr1, int[] arr2, int n, int m) { 

        int left = (n + m +1) / 2;
        int right = (n + m + 2) / 2;
        return ((findElement(arr1,arr2,n,m,left)+findElement(arr1,arr2,n,m,right))/2);
    
    }
    private static float findElement(int[] arr1, int[] arr2, int n, int m, int k) {

        if(n > m)
            return findElement(arr2,arr1,m,n,k);
        
        if(n == 0 && m > 0)
            return arr2[k-1];

        if(k==1)
            return Math.min(arr1[0], arr2[0]);

        int i =  Math.min(n, k/2) ;
        int j =  Math.min(m, k/2) ;   

        if(arr1[i-1] > arr2[j-1])
            return findElement(arr1, Arrays.copyOfRange(arr2, j, m), n, m-j, k-j);
        else
            return findElement(Arrays.copyOfRange(arr1, i, n), arr2, n-i, m,  k-i);
    }

    public static void main(String[] args) {

        File file = new File("median.txt");
        int n, m;
        int[] vect1;
        int[] vect2;
        
        try{
            
            Scanner medianScanner = new Scanner(file);
            
            n = medianScanner.nextInt();
            vect1 = new int[n];
            
            for(int i=0; i<n; i++)
                vect1[i] = medianScanner.nextInt();
            
            m = medianScanner.nextInt();
            vect2 = new int[m];
            
            for(int i=0; i<m; i++)
                vect2[i] = medianScanner.nextInt();
            
            System.out.println(findMedian(vect1, vect2, n, m));
            
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
    }
    
}
