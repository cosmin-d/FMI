import java.util.*;
import java.io.*;

public class Main_Coada {
public static void main(String[] args) {
Queue<Integer> myQueue = new Queue<>();
myQueue.add(2);
myQueue.add(3);
myQueue.add(-5);
myQueue.add(17);
myQueue.add(21);
myQueue.add(-2);
Iterator<Integer> it =myQueue.iterator();	
while(it.hasNext())
	System.out.print(it.next());
//System.out.println(myQueue);
//myQueue.remove();
//System.out.println(myQueue);
//myQueue.remove();
//System.out.println(myQueue);
//myQueue.remove();
//System.out.println(myQueue.get(2));
///System.out.println(myQueue.peek());

}
}
