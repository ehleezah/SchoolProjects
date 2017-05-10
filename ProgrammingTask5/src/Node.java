
//our node class for a linked list, we use this in chainedHashing class


public class Node
{
	private int data;				// each Node will store an int data and a next pointer to the next Node in the list, null if its the last item in the list
	private Node next;
	
	public Node(int x)				// 1-arg constructor, we know the datum, set next to null
	{
		data=x;
		next=null;
	}
	
	public Node(int x, Node n)		// 2-arg constructor, sets next to the node n 
	{
		data=x; next=n;
	}
	
	// accessors (getters) for the two instance data
	public Node getNext()
	{
		return next;
	}
	
	public int getData()
	{
		return data;
	}
	
	// mutators (setters) for the two instance data
	public void setNext(Node n)
	{
		next=n;
	}
	
	public void setData(int x)
	{
		data=x;
	}
}