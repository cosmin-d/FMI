
package levenshtein;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

class Solution {
        ArrayList<String> operations;
        int cost;         
    }

public class Levenshtein {

  public static int minim (int a, int b, int c) {
    int min;
    
    min = a;
    if (b < min)
      min = b;
    if (c < min)
      min = c;
    
    return min;
  }
  
  public static Solution LevenshteinDistance (String s, String t, int c1, int c2, int c3) {

      Solution sol = new Solution();
    int n = s.length ();
    int m = t.length ();
    int substCosts[][] = new int[n+1][m+1];
    
    if (n == 0){ 
        sol.cost = m * c1;
      return sol;
    }
    
    if (m == 0){
        sol.cost = n * c2;
      return sol;
    }
    
    int matrix[][] = new int[n+1][m+1];

    for (int i = 0; i <= n; i++)
        matrix[i][0] = i;

    for (int j = 0; j <= m; j++)
        matrix[0][j] = j;
    
    int substitutionCost = 0;
    
    for (int i = 1; i <= n; i++) {
        
      for (int j = 1; j <= m; j++) {

        if (s.charAt (i - 1) == t.charAt (j - 1)) {
          substitutionCost = 0;
        }
        else {
          substitutionCost = c3;
        }

        matrix[i][j] = minim(matrix[i-1][j]+c2, matrix[i][j-1]+c1, matrix[i-1][j-1]+substitutionCost);
        substCosts[i][j] = substitutionCost;
        
      }

    }
    
        int i = n;
        int j = m;
        sol.operations = new ArrayList<String>();
        while(i>0 && j>0){
            if(matrix[i][j-1] == matrix[i][j]-c1){
                sol.operations.add(new String("inseram " + t.charAt (j - 1)));
                j = j-1;
            }
            else if(matrix[i-1][j] == matrix[i][j]-c2){
                sol.operations.add(new String("stergem " + s.charAt (i - 1)));
                i = i-1;
            }
            else if(matrix[i-1][j-1] == matrix[i][j]-substCosts[i][j] && substCosts[i][j] == 0){
                sol.operations.add(new String("pastram " + s.charAt (i - 1)));
                i = i-1;
                j= j-1;
            }
            else if(matrix[i-1][j-1] == matrix[i][j]-substCosts[i][j] && substCosts[i][j] == c3){
                sol.operations.add(new String("inlocuim " + s.charAt (i - 1) + " cu " + t.charAt (j - 1)));
                i = i-1;
                j= j-1;
            }
        }
        while(i>0 && j==0){
            if(matrix[i-1][j] == matrix[i][j]-c2){
                sol.operations.add(new String("stergem " + s.charAt (i - 1)));
                i = i-1;
            }
        }
        while(i == 0 && j>0){
            if(matrix[i][j-1] == matrix[i][j]-c1){
                sol.operations.add(new String("inseram " + t.charAt (j - 1)));
                j = j-1;
            }
        }

    sol.cost = matrix[n][m];
    
    return sol;

  }

    public static void main(String[] args) {
        
        File file = new File("levenshtein.txt");
        String from, to;
        int c1, c2, c3;
        Solution sol;
        
        try{
            
        Scanner stringScanner = new Scanner(file);
        
        from = stringScanner.next();
        to = stringScanner.next();
        
        c1 = stringScanner.nextInt();
        c2 = stringScanner.nextInt();
        c3 = stringScanner.nextInt();
        
        sol = LevenshteinDistance(from,to,c1,c2,c3);
        
        System.out.println(sol.cost);
        
        for(int i=sol.operations.size()-1; i>=0; i--)
            System.out.println(sol.operations.get(i));
        
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
    }
    
}
