import java.util.*;
import java.io.*;
import java.util.Map.Entry;
import java.lang.*;
class WordCounter{
public static void main(String arg[]){
	File file1=new File("date1.in");
	File file2=new File("date2.in");
	ArrayList<Integer>al1=new ArrayList<>();
	ArrayList<Integer>al2=new ArrayList<>();
HashMap<String,Integer>hm1=new HashMap<>();
HashMap<String,Integer>hm2=new HashMap<>();
Scanner sc1=null;
Scanner sc2=null;
String cuv1,cuv2;
double a=0.0;
double b=0.0,c=0.0,d=0.0;
try
{
	sc1=new Scanner(file1);
	sc2=new Scanner(file2);
	sc1.useDelimiter("[^A-Za-z0-9]+");
	sc2.useDelimiter("[^A-Za-z0-9]+");
	while(sc1.hasNext())
	{
		cuv1=sc1.next().toLowerCase();
		if(hm1.get(cuv1)==null)
			hm1.put(cuv1,1);
		else
			hm1.put(cuv1,hm1.get(cuv1)+1);
	}
	while(sc2.hasNext())
	{
		cuv2=sc2.next().toLowerCase();
		if(hm2.get(cuv2)==null)
			hm2.put(cuv2,1);
		else
			hm2.put(cuv2,hm2.get(cuv2)+1);
	}
	Set<Entry<String,Integer>>s1=hm1.entrySet();
	
for(Entry<String,Integer>H:s1)
{
	al1.add(H.getValue());
	if(hm2.get(H.getKey())!=null)
	{
		al2.add(hm2.get(H.getKey()));
		hm2.remove(H.getKey());
		
	}
else
	al2.add(0);
}

Set<Entry<String,Integer>>s2=hm2.entrySet();
for(Entry<String,Integer>I:s2)
{
	al2.add(I.getValue());
	al1.add(0);
}	

for(int i=0;i<al1.size();i++)
{
	a+=al1.get(i)*al2.get(i);
	b+=al1.get(i)*al1.get(i);
	c+=al2.get(i)*al2.get(i);
	
	}
	System.out.println(a);
	System.out.println(b);
	System.out.println(c);
d=a/(Math.sqrt(b)*Math.sqrt(c));	
System.out.print(d);
}
	catch (Exception e){System.out.print("FileNotFound");}
	finally 
	{
		if(sc1!=null)sc1.close();
		if(sc2!=null)sc2.close();
	}
	
	

}


}