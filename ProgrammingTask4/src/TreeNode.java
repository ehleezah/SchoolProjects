//Eliza Karki CSC 501
//This is binary node class for the implementation of Binary tree. It has three instance data: 
//left, right and data. It has one default constructor which takes one argument and assigns it to null.
//It also has another constructor that takes three arguments for assigning the data, and the nodes:
//left and right. It has three accessors: getData() to return the value of data, 
//getLeft() to return the left node and getRight() to return the right node
//and three mutators: setData() for changing the data,
//setLeft() for changing the left node, and setRight() for changing the right node.
public class TreeNode {
	private int data;  //three instances: data field
	private TreeNode left; //pointer to the left node
	private TreeNode right; //pointer to the right node
	
	public TreeNode(int newData) { //1-arg default constructor , creates a new tree node with no children
		data = newData; //sets data to the newData 
		left = null;
		right = null;
	}
 
	//3-args constructor which takes new data value, new left pointer and the new right as arguments  
	public TreeNode(int newData, TreeNode newLeft, TreeNode newRight) {
		data = newData; //sets data to the new data
		left = newLeft; //sets pointer to the new left node
		right = newRight; //sets pointer to the new right node
	}
	//accessors
	//getter method that returns the value of the data
	public int getData() {
		return data; 
	}
	//getter method that returns the value of the left node
	public TreeNode getLeft() {
		return left;
	}
	//getter method that returns the value of the right node
	public TreeNode getRight() {
		return right;
	}
	//mutators
	//setter method that sets the value of data to the new data value
	public void setData(int newData) {
		data = newData;
	}
	//setter method that sets the value of the left node to the new left node
	public void setLeft(TreeNode newLeft) {
		left = newLeft;		
	}
	//setter method that sets the value of the right node to the new right node
	public void setRight(TreeNode newRight) {
		right = newRight;
	}
	
}
