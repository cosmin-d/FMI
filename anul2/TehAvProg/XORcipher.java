import java.util.*;
import java.io.*;
import java.lang.*;
class XORcipher{
public static void main(String arg[]){
	Scanner scn=new Scanner (System.in);
	String key=scn.next();
String content=null;
File file =new File("date_cipher.in");
Scanner sc=null;
try{sc=new Scanner (file);}
catch (FileNotFoundException e)
{e.printStackTrace();}
content=sc.useDelimiter("\\Z").next();
System.out.println(content);
int z=0;
for(int i=0;i<content.length();i++)
{
	
	if(z==key.length()) z=0;
	
	System.out.printf("%d ",content.charAt(i)^key.charAt(z));
z++;}
}

}
