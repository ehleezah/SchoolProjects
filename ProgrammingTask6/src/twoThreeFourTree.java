//This class implements the 2-3-4 tree which stores the int values. 
//It comprises of one instance datum(root),
//a constructor which initializes the root to null,
//an insert method inserts a new value,
//methods for traversal:
//	inorder: visits the left child, root, and right child
//	preorder: visits the root, left child and right child
//	postorder: visits the left child, right child and root
//getHeight: computes and returns the height of the tree 
//This class also implements a tree node class as a separate TreeNode class
//It has helper functions to split nodes



 
public class twoThreeFourTree {

	private TreeNode root;
	
	public twoThreeFourTree() {
		root = null;
	}
	
	//insert in 2-3-4 tree:

	//if we see a 3-key node, the middle key becomes the parent node,
	//if we want to insert in that node
	//
	//->this means splitting the 3-node
	//
	//side key becomes their own node
	//ensure parent's key are not full

	public void insert(int value) {
		
	}
}
