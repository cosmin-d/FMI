import java.util.*;
class Punct_ComparatorY implements Comparator<Punct>
{
    public int compare(Punct A,Punct B)
	{
        if(A.y < B.y)
            return -1;
        else if(A.y > B.y)
            return 1;
        else 
            return 0;
    }
    
 }