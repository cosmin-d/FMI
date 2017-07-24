import java.util.*;
import java.io.*;
public class Queue<E> implements Iterable{
private class Nod<E>{
E info;
Nod<E> next;
Nod(E info,Nod<E> next){
this.info=info;
this.next=next;  }
}
Nod<E> first;

public Iterator<E> iterator()
{
	return  new QueueIterator();
}
class QueueIterator implements Iterator<E>
{//private Queue<E> queue;
private Nod<E> current;
		public E next()
		{
			Nod<E> temp=current;
    current=current.next;
    return temp.info;
		}
		
public boolean hasNext()
{
	if(current!=null) return true;
	else 
		return false;
}
}
public boolean add(E info) {
if (first==null) {
first=new Nod<>(info,null);
} else {
Nod<E> last=first;
while(last.next!=null){
last=last.next; }
last.next=new Nod<>(info,null); }
return true; }


public String toString() {
String queue=":";
Nod<E> current=first;
while(current!=null) {
queue+= current.info + " ";
current=current.next; }
return queue;
}
public E remove() {
E element=null;
if (first!=null) {
element=first.info;
first=first.next;
}
return element;
}

public E get(int n){
E element=null;

Nod<E>p=first;
int k=1;
while(n!=k && p.next!=null)
{p=p.next;
k++;}
if(n==k)element = p.info;
return element;}

public E peek() {
E element=null;
if (first!=null) {
element=first.info;
}
return element;
}

public boolean exists(E el){
Nod<E> p=first;
while(p!=null)
{if(el==p.info)return true;
p=p.next;}
return false;
}

}

