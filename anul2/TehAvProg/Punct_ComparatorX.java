import java.util.*;
class Punct_ComparatorX implements Comparator<Punct>
{
    public int compare(Punct A,Punct B)
	{
        if(A.x < B.x)
            return -1;
        else if(A.x > B.x)
            return 1;
        else 
            return 0;
    }
    
}