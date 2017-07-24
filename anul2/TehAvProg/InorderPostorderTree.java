import java.io.*;
import java.util.*;

class Node {
	int info;
	Node left;
	Node right;

	public Node(int data) {
		info = data;
		left = null;
		right = null;
	}
}

public class InorderPostorderTree {
    
    public static boolean ok = true;
    
    public static int getInorderIndex(int[] inOrder, int start, int end, int data){
	for (int i = start; i <= end; i++)
            if (inOrder[i] == data)
		return i;
	return -1;
    }
    
    
    public static Node makeTree(int[] inOrder, int[] postOrder, int inStart, int inEnd, int postStart, int postEnd) throws Exception {
        
        if(ok == false)
            throw new Exception();
        
	if (inStart > inEnd || postStart > postEnd){
            return null;
	}
        
	Node root = new Node(postOrder[postEnd]);

	if (inStart == inEnd) 
            return root;
        
	int index = getInorderIndex(inOrder, inStart, inEnd, root.info);
        if(index == -1){
            ok = false;
            return null;
        }
        
	root.left = makeTree(inOrder, postOrder, inStart, index - 1, postStart, postStart + index - (inStart + 1));
	root.right = makeTree(inOrder, postOrder, index + 1, inEnd, postStart + index - inStart, postEnd - 1);
	
        return root;
    }

    public static void printInorder(Node root) {
	if (root != null) {
            printInorder(root.left);
            System.out.print("  " + root.info);
            printInorder(root.right);
	}
    }
    
    public static void printPreorder(Node root) {
	if (root != null) {
            System.out.print("  " + root.info);
            printPreorder(root.left);
            printPreorder(root.right);
	}
    }
        
    public static void printPostorder(Node root) {
	if (root != null) {
            printPostorder(root.left);
            printPostorder(root.right);
            System.out.print("  " + root.info);
	}
    }
    public static void main(String[] args) {
        
        File file = new File("tree.txt");
        int[] inord;
        int[] postord;
        int n;
        Node root = null;
        
        try{
        
            Scanner treeScanner = new Scanner(file);
            
            n = treeScanner.nextInt();
            inord =  new int[n];
            postord = new int[n];
            
            for(int i=0; i<n; i++)
                postord[i] = treeScanner.nextInt();
            
            for(int i=0; i<n; i++)
                inord[i] = treeScanner.nextInt();
            
            try{
                root = makeTree(inord, postord, 0, n - 1, 0, n - 1);
            }catch (Exception e){
                System.out.println("nu");
            }
            
            
            System.out.print("Preordine: ");
            printPreorder(root);
            System.out.println();
            
            System.out.print("Inordine: ");
            printInorder(root);
            System.out.println();
            
            System.out.print("Postordine: ");
            printPostorder(root);
            System.out.println();
            
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
    }  
}
