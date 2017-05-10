//Eliza Karki CSC 501

//This is the binary tree class which has two instance data, the root pointer and the current size of the tree(initialized to 0).
//This class implements the following methods:  
//Constructor BinaryTree(): creates an empty tree
//GetHeight() : computes and returns the height of the tree, 
//GetSize(): returns the size of the tree (number of elements, not height)
//Insert(): inserts a new value into the tree, 
//Delete(): locates and deletes a value from the tree, 
//Search(): given a value, locates it in the tree and returns the tree node( or null if the value is not found), 
//The three traversal methods:
//Inorder(): visits the left subtree, visits the root and visits the right subtree, 
//Preorder(): visits the root, visits the left subtree and visits the right subtree,
//Postorder(): visits the left subtree, visits the right subtree and visits the root, 
//Apart from the constructor and the getSize method, all of the above mentioned methods are implemented recursively.

public class BinaryTree {
	//two instance data:
	private TreeNode root; //the root pointer
	private int size = 0; //current size of the tree, initially there are no elements
	
	public BinaryTree() { //default constructor
		root = null; //creating an empty tree by initializing root to null
	}
	
	/* This method computes the height of a tree - the number of nodes along the longest path from the 
	root node down the farthest leaf node and returns the height of the tree */
	public int getHeight (TreeNode t) {
		if (t == null) return 0; //if its an empty tree, height of the tree is 0
		else //if the tree is not empty
		{
			/*Compute the height of each subtree */
			int lHeight = getHeight(t.getLeft()); //variable that stores the height of the left subtree
			int rHeight = getHeight(t.getRight()); //variable that stores the height of the right subtree
			return Math.max(lHeight, rHeight) + 1; //returns the larger one, plus the root node
		}
	}
	//This is the starter method for calling the above method of height of the tree because our user program does not have access to the root node  
	public int startGetHeight() {
		return getHeight(root); //returns the height of the tree
	}
	//This method returns the size of the tree, number of elements inside the tree
	public int getSize() {
		return size; //returning the instance datum, size
	}
	
	//This method recursively inserts a new value into the tree 
	public TreeNode insert(int x, TreeNode t) { //if we have reached a node, t, we insert x
		if (t == null) return new TreeNode(x, null, null);  //if t is equal to null, we have reached the null location in the tree, we return tree node with the value x and the left and right pointers as null
		else if (x < t.getData()) { //if x is less than the value of the current tree node, t, 
			t.setLeft(insert(x, t.getLeft())); // we recursively call insert, passing x in t's left child
			return t; //and we return t
		}
		else { //otherwise if x is greater than the value of the current tree node, t,
			t.setRight(insert(x, t.getRight())); //we recursively call insert, passing x in t's right child
			return t; //and we return t
		}
	}
	//The above insert method must be called recursively from another method because our user program will not have access to the root node
	public void startInsert(int x) { //It has one arg-the value to be inserted, x
		root = insert(x, root); //root node is assigned the insert method recursively passing x and root
		size++;
	}
	
	//The delete method locates and deletes a value recursively from the tree
	//It takes two arguments: the value to be deleted, x and the tree node t in which the value is being deleted
	//There are three cases handled by the delete method: 
	//Case 1: When the tree is empty, with no children
	//Case 2: When the tree has one child only, either the left child or the right child and
	//Case 3: When the tree has two children, the left child and the right child
	public TreeNode delete(int x, TreeNode t) { 
		//Casee 1: When the tree is empty, with no children
		if (t == null) { //if the tree is empty, nothing to delete
			System.out.println("Error " + x + " not found"); //prints error message
			return null; //we return null
		}
		else if (t.getData() == x) { //otherwise if the datum of the t is equal to x,
			//Case 2: When the tree has one child only, either the left child or the right child
			if (t.getLeft() == null) { //if we have only one right child
				return t.getRight(); //  if the t's left subtree is null, we attach t's right child
			}
			else if (t.getRight() == null) { //if we have only one left child
				return t.getLeft(); // however, if it's right subtree is null, we return t's left child
			}
			//Case 3: When the tree has two children, the left child and the right child
			else { // otherwise if both, left and right are not null, node has two children
				TreeNode temp = t.getLeft(); //we set a temporary variable, temp to be t's left child, since it initially holds minimum value
				while (temp.getRight() != null) temp = temp.getRight();  //and move as far right as possible to get the minimum element in the left subtree
				t.setData(temp.getData()); //copy the datum from temp into t
				t.setLeft(delete(t.getData(), t.getLeft())); //and we set the t's right subtree by recursively calling delete passing the datum copied from above and the tree's right subtree
				return t; //we return the t to re-attach it
			}
		}
		else { //if we have yet to find the datum, 
			if (x < t.getData()) //and if x is less than the datum
				t.setLeft(delete(x, t.getLeft())); //we set the t's left child by recursively calling the delete on x and the t's left child
			else  //if x is greater than the datum
				t.setRight(delete(x, t.getRight())); //we set the t's right child by recursively calling the delete on x and the t's right child
			return t; //return t to re-attach it 
		}
	}
	
	//The delete method must be called by a starter method. But unlike the starter method for insert, we have 2 special cases:
	//is the datum to be deleted in root which has no children, in which case we reset root to null, or is the datum to be deleted
	//in the root which has one child, in which case we reset root to that child
	public void startDelete(int x) {
		if (x == root.getData() && root.getLeft() == null) root = root.getRight(); //if the element to be deleted is in the root and the left node is empty we set the root to the right child of the root
		else root = delete(x, root); //root is set to call the delete method to delete the element x from the root
		size--; //decrement the size of the tree
	}
	
	//This is the search method that searches a value, x by locating it in the tree, t and returning the tree node(or null if the value is not found)
	// It takes two parameters as argument: the int value, x to be searched for and the tree node, t in which is the value is being searched for
	public TreeNode search(int x, TreeNode t)  { 
	       if (t == null) //if the root is empty, there is nothing to be searched for 
	       {
	    	   System.out.println(x  + " not found"); //prints the message for element not found
	    	   return t; //returns the null since the value is not found
	       }
	       if (t.getData() == x) { //if the element to be searched for is equal to the datum in the tree
	    	   System.out.println(x  + " found"); // prints the message for element to be found
	    	   return t; //returns the tree if the element is found
	       }
	       else if (t.getData() > x) //if the element to be searched for is less than the value in the tree
	    	   return search(x, t.getLeft()); //we recursively search in the left subtree of the tree
	       else //if the element to be searched is greater than the value in the tree
	    	   return search(x, t.getRight());  //we recursively search in the right subtree of the tree
	}
	
	//The above search method must be called by a starter method since our user program does not have access to the root node 
	public TreeNode startSearch(int x) { //It takes as an argument the int value, x to be searched in the tree
		return search(x, root); //invokes the search method which receives the int value, x and the root node
	}
	
	//The three methods for the traversal of the tree:
	//The first method is for the inorder traversal of the tree, based on: visit left child, visit root node, and visit the right child
	public void inorder(TreeNode r) { //Method with one arg, the root node, r
		if (r != null) { //if the binary tree is not empty
			inorder(r.getLeft()); //we visit the node's left child by recursively calling the inorder method 
			System.out.print(r.getData() + " "); //we visit the root node now since its inorder 
			inorder(r.getRight()); //we visit the node's right child by recursively calling the indorder method
		}
	}
	//The second method  is for the preorder traversal of the tree, based on: visit the root node, visit the left child, and visit the right child
	public void preorder(TreeNode r) { //Method with one arg, the root node, r
		if (r != null) { //if the binary tree is not empty
			System.out.print(r.getData() + " "); //we first visit the root node incase of the preorder
			preorder(r.getLeft()); //then we visit the left child by recursively calling the preorder method
			preorder(r.getRight()); // finally we visit the right child by recursively calling the preorder method
		}
	}
	//The third method is for the postorder traversal of the tree, based on: visit the left child, visit the right child and finally visit the root node
	public void postorder(TreeNode r) { //Method with one arg, the root node, r
		if (r != null) { //if the tree is not empty
			postorder(r.getLeft()); //visit the left child of the tree by recursively calling the postorder method
			postorder(r.getRight()); //visit the right child of the tree by recursively calling the post order method
			System.out.print(r.getData() + " "); //finally we visit the root node incase of the postorder
		}
	}
	
	//invoking the traversals non-recursively and passing them the root to be traversed as they need a method that can start the traversals
	public void startInorder() {
		inorder(root); //invokes the inorder method
	}
	public void startPreorder() {
		preorder(root); //invokes the preorder method
	}
	public void startPostorder() {
		postorder(root); //invokes the postorder method
	}
	//destroy method for the tree
	public void destroyTree() {
		root = null; //setting the root node to null
	}
}
