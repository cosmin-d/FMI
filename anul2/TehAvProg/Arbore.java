import java.util.*;
import java.io.*;

public class Arbore<E> {
    private class Node<E extends Comparable<E>> {
        E item;
        Node<E> left;
		Node<E> right;

        Node(E element, Node<E> left, Node <E> right) {
            this.item = element;
            this.left=left;
			this.right=right;
        }
    }

    Node<E> rad;
	
	/*public boolean add(Object element) {
        //Node<E> nou=new Node<>(element, null,null);
		
		if (rad == null) {
            rad = new Node<>(element, null,null);
        } 
		else {
              Node<E> n=rad;
			  Node<E> p=null;
			  while(n!=null)
			  {
				  p=n;
				  if(element.compareTo(n.item)<0)
					  n=n.left;
				  else n=n.right;
			  }
        
		if(element<p.item)
			p.left=new Node<>(element,null,null);
	else p.right=new Node<>(element,null,null);}
	return true;
}*/
}