package instructor;

/*  Implements a stack of int using a linked structure, with a private inner class for the linked list Nodes.  
 *  The class is minimal, it only implements push, pop, peek and isEmpty.  The constructor sets top to null. */

public class MyStack
{
	private Node top;					// pointer to the top of the stack
	
	// nested inner class defines a node for the linked list
	private class Node {
		private int data;				// Node stores int values only
		private Node next;				// next Node in the linked list
		
		public Node(int d, Node n) {	// constructor to add this node at the top while attaching the rest of the list
			data=d;
			next=n;
		}
		
		public Node(int d) {			// constructor to create a Node but not attach it to the list
			data=d;
			next=null;
		}
		
		// accessors
		public int getData() { return data;	}		
		public Node getNext() { return next;	}
		// mutators
		public void setData(int d) { data=d;	}
		public void setNext(Node n) { next=n;	}
	}
	
	public MyStack() {					// stack constructor creates an empty stack
		top=null;
	}
	
	public void push(int x) 			// push x onto the stack
	{
		Node temp=new Node(x);			// create a new node with the datum x
		if(isEmpty())					// if the stack is empty, then this new node becomes top
			top=temp;	
		else {							// otherwise this new node needs to point at top
			top.setNext(temp);			// and then top points at this new node
			top=temp;
		}
	}
	
	public int pop() {					// remove the top item off the stack and return it
		if(isEmpty()) {					// special case:  stack is empty, output error and return error code
			System.out.println("Error, stack empty");
			return -9999;
		}
		else {							// otherwise set temp to the datum at the top of the stack
			int temp=top.getData();
			top=top.getNext();			// reset top to point at the next node
			return temp;				// and return the top value
		}
	}

	public int peek() {					// return the value at top
		if(isEmpty()) {					// special case:  stack is empty, output error and return error code
			System.out.println("Error, stack empty");
			return -9999;
		}
		else 							// otherwise return the value at the top of the stack
			return top.getData();
		
	}	
	public boolean isEmpty() 			// return whether the stack is empty (top is null) or not
	{ 
		return top==null; 
	}
}