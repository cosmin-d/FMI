import java.util.*;
import java.io.*;
import java.lang.*;
class XORDcipher{
public static void main(String arg[]){
	Scanner scn=new Scanner (System.in);
	String key=scn.next();
File file =new File("cipher.in");
Scanner sc=null;
try{sc=new Scanner (file);}
catch (FileNotFoundException e)
{e.printStackTrace();}
int z=0;
while(sc.hasNext())
{
	if(z==key.length()) z=0;
	 System.out.printf("%c",sc.nextInt()^key.charAt(z));
z++;}

}

}