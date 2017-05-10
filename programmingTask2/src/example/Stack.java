package example;

//Eliza Karki CSC 501

/*This is our Stack class that uses linked list to store the number of students in a line.
 * It has two main methods of push, for adding the students and pop, for removing the students.
 * It also has an inner Node class. There is one boolean method isEmpty that checks if the stack is empty or not. 
*/
public class Stack {
	private Node top; //our node for the top element in the stack
	
	public class Node {
		private int data; //data represents the value in the current node 
		private Node next; //next points to the next node
		
		public Node(int d, Node n) {// 2-arg constructor for storing the data d and next n 
			data = d; //assign data to the value d,
			next = n; //assign next to the value n
		}
		
		public Node(int d) { //1-arg constructor when there is single node and next is null
			data = d; //assign the data to the value d
			next = null; //assign the next to null since there is just a single node 
		}
		public int getData() { return data; } //getter method for getting the data value
		public Node getNext() { return next; } //getter method for getting the next value
		public void setData(int d) {data = d;} //setter method that sets the data to its value as d
		public void setNext(Node n) {next = n;} //setter method that sets the next to point towards n
	}
	public Stack() { //default constructor where top is empty
		top = null;
	}
	public boolean isEmpty() { //method that checks to see if the stack is empty
		return top == null; //if the top is empty or points to null, it means the stack is empty
	}
	public void push(int x) { //method that inserts the element, x into the stack
		Node temp = new Node(x); //create a new node, temp that has the value, x
		if (isEmpty()) //if the stack is empty, we set our top to temp
			top = temp;
		else { //if the stack is not empty and we already have an element in top
			temp.setNext(top); //we set our top to point towards the temp variable
			top = temp; //now our top becomes temp as it is added in the stack
		}
	}
	public int pop() { //method for removing the element from the stack
		if(isEmpty()) { //if the stack is empty, we have nothing to remove from the stack so prints the error message
			System.out.println("Error, stack empty");
			return -9999;
		}
		else { //if our stack is not empty,
			int temp = top.getData(); //create a new node, temp which stores the value at top
			top = top.getNext(); //now our top is the next element in the stack 
			return temp; //we return the temp which means that the initial top element has been removed
		}
	}
	

}
