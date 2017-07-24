import java.util.*;
class Activitate implements Comparable<Activitate>
{
	int start,end;
	int profit;
Activitate(){}
Activitate(int a,int b, int c)
{
	start=a;
	end=b;
	profit=c;
}
public int compareTo(Activitate a)
{
	return end-a.end;
}
public String toString()
{
	return "(" + start + "," + end + ")";
}
}