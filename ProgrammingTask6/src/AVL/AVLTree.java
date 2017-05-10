package AVL;

/*
 *  This is the class to implement an AVL Tree.

 *  An AVL Tree is a self-balancing binary search tree.
 *  In an AVL tree, the difference between the heights of every node's 
 *  two subtree is 0 or 1. It can be shown that the maximum height of an AVL
 *  tree is O(log n). The balance factor of a node is the height of its left
 *  subtree minus the height of its right subtree.
 *  A node is said to be balanced if its balance factor is -1, 0 or 1.
 *  A node is considered left-heavy if its balance factor is -1, and 
 *  right-heavy if its balance factor is +1.
 *  
 *  As we perform insertion and deletion in the tree, there will be imbalance in the tree.
 *  The process of re-balancing the node in the tree is called rotation. 
 *  The imbalance can be fixed with the various rotations which are:
 *  LeftLeft (LL) rotation, - left left imbalance occurs which can be fixed by single right rotation,
 *  RightRight (RR) rotation - right right imbalance occurs which can be fixed by single left rotation,
 *  LeftRight (LR) rotation - left right imbalance occurs which can be fixed by double rotation- first a single left rotation and then a single right rotation and
 *  RightLeft (RL) rotation - right left imbalance occurs which can be fixed by double rotation - first a single right rotation and then a single left rotation.
 *  
 *  This class implements a nested inner tree node class.
 *	The tree stores int values.
 */
 
class  TreeNode {
 int key, height; // key is the int datum in the avl-tree, height is the height of the tree
 TreeNode left, right; //left is the left child of the tree and right is the right child of the tree

 public TreeNode(int d) { //1-arg constructor where d is the int value to be stored in the tree
     key = d; //key is assigned the int value to be stored in the tree
     height = 0; //initially the height is 0
 }
}

/* AVl Tree class
 * This class comprises one instance datum(root), 
 *  a constructor - to initialize root to null,
 *  Various methods like : insert(): to insert a new value in the tree,
 *  Traversal methods: 
 *  inorder() : visits the left subtree, root and right subtree.
 *  preorder() : visits the root, left subtree and right subtree.
 *  postorder(): visits the left subtree, right subtree and root.
 *  height(): computes and returns the height of the tree.
 *   
 */
 
class AVLTree { //the AVL tree class starts 
 
 TreeNode root; //one instance datum (root)

 // A helper function to get height of the tree
 public int height(TreeNode N) { 
     if (N == null) //if the tree is empty
         return -1; //we return -1
     return N.height; //else we return the height of the tree
 }
 // A helper function to get maximum of two integers
 public int max(int a, int b) {
     return (a > b) ? a : b; //if a is greater than b, return a else return b
 }

 // A helper function to right rotate subtree rooted with y
 //This operation results in a rotation of the tree in the clockwise direction.
 public TreeNode rightRotate(TreeNode y) { //y is the root here 
     TreeNode x = y.left; //initially, the node, x is y's left child
     TreeNode T2 = x.right; //T2 is x's right child

     // Performing right rotation - so we are rotating the root y in the right clockwise direction, to make x as the new root
     x.right = y; //now we want to rotate x in the right clockwise direction, so the new root, x's right child becomes the previous root, y
     y.left = T2; //previous x's right child is now the y's left child after the right rotation

     // Updating heights
     y.height = max(height(y.left), height(y.right)) + 1;
     x.height = max(height(x.left), height(x.right)) + 1;

     // Return new root
     return x;
 }

 // A helper function to left rotate subtree rooted with x
 //This operation results in a rotation of the tree in the counter-clockwise direction.
public  TreeNode leftRotate(TreeNode x) { //x is the root here 
     TreeNode y = x.right;   //we are setting y to be new root so, the previous root, x's right child is y which is now the root,
     TreeNode T2 = y.left; //we are setting y's left child as the tree node, T2

     // Performing left rotation - so we are rotating the root x in the left counter-clockwise direction, to make y as the new root
     y.left = x; // now the new root, y's left child is the previous root, x 
     x.right = T2; //previous y's left child is now x's right child after the left rotation   

     //  Updating heights
     x.height = max(height(x.left), height(x.right)) + 1;
     y.height = max(height(y.left), height(y.right)) + 1;

     // Return new root
     return y;
 }

 // Get Balance factor of node N
 public int getBalance(TreeNode N) {
     if (N == null) //if the tree is empty,
         return 0; //we return 0

     return height(N.left) - height(N.right); //balance factor is the difference between the tree's left child's height and right child's height
 }

 //the insertion method returns the tree itself after the insertion of a key element and the node to be inserted in, 
public TreeNode insert(TreeNode node, int key) { //the parameters here are the node that is to be inserted in and key is the int value to be inserted in that node

     /* 1.  Performing the normal Binary Search Tree insertion */
     if (node == null) //if the node is empty
         return (new TreeNode(key)); //we insert the element in that node

     if (key < node.key) // if the element to be inserted is less than the node
         node.left = insert(node.left, key); // we insert the element in the node's left subtree
     else if (key > node.key) // if the element to be inserted is greater than the node
         node.right = insert(node.right, key); // we insert the element in the node's right subtree
     else // Duplicate keys not allowed
         return node; //we return the node after the insertion 

     /* 2. Updating height of this ancestor node */
     node.height = 1 + max(height(node.left),
                           height(node.right));

     /* 3. Getting the balance factor of this ancestor
           node to check whether this node became
           unbalanced */
     int balance = getBalance(node);

     // If this node becomes unbalanced, then there
     // are 4 cases:
     //	Left Left Case - An LL imbalance occurs at a node, such that the node has a balance factor of -2
     //	and a left child of that node with a balance factor of -1 or 0
     if (balance > 1 && key < node.left.key)
         return rightRotate(node); //This type of imbalance can be fixed by performing a single right rotation at that particular node

     // Right Right Case - An RR imbalance occurs at a node, such that the node has a balance factor of +2 
     // and a right child of that node with a balance factor of +1 or 0
     if (balance < -1 && key > node.right.key)
         return leftRotate(node); //This type of imbalance can be fixed by performing a single left rotation at that particular node

     // Left Right Case - An LR imbalance occurs at a node, such that the node has a balance factor of -2
     // and a left child of that node with a balance factor of +1
     //This type of imbalance can be fixed by performing a double rotation 
     if (balance > 1 && key > node.left.key) {
         node.left = leftRotate(node.left); //first a single left rotation at the node's left child
         return rightRotate(node); //and then a single right rotation at the node itself
     }

     // Right Left Case - An RL imbalance occurs at a node such that the node has a balance factor of +2
     // and a right child of the node with a balance factor of -1 
     // This type of imbalance can be fixed by performing a double rotation 
     if (balance < -1 && key < node.right.key) {
         node.right = rightRotate(node.right); //first a single right at the node's right child
         return leftRotate(node); //and then a single left rotation at the node itself
     }

     /* return the (unchanged) node pointer */
     return node;
 }

 // A helper function to print pre-order traversal
 // of the tree.
public void preOrder(TreeNode node) {
     if (node != null) { //if the tree is not empty
         System.out.print(node.key + " "); //visit the node first
         preOrder(node.left); //visit the node's left child
         preOrder(node.right); //visit the node's right child
     }
 }
//A helper function to print in-order traversal
// of the tree.
 public void inOrder(TreeNode node) {
	 if (node != null) { //if the tree is not empty
		 inOrder(node.left); //visit the left child of the node first
		 System.out.print(node.key + " "); //visit the the node 
		 inOrder(node.right); //visit the right child of the node
	 }
 }
//A helper function to print post-order traversal
//of the tree.
 public void postOrder(TreeNode node) {
	 if (node != null) { //if the tree is not empty
		 postOrder(node.left); //visit the left child of the node first
		 postOrder(node.right); //visit the right child of the node
		 System.out.print(node.key + " "); //visit the node itself
	 }
 }
}
 