package AVL;
//
//Eliza Karki CSC 501
//
////This implements a driver class which tests the AVL tree. 
//It generates random positive numbers that are  
////less than 10000 and inserts them into the AVL tree.
////After the insertions, it performs the preorder, inorder and postorder traversals.
////Finally it outputs the height of the tree.
////It does this for a tree of 10 random numbers,
////a tree of 50 random numbers and 
////a tree of 200 random numbers.
//
import java.util.Random; //for generating random numbers

public class DriverClass { 
	
     public static void main(String[] args)
    {            
        /* Creating object of AVLTree */
        AVLTree tree = new AVLTree();  	

        //For 10 random numbers
        System.out.println("A tree of 10 random numbers: ");
		for (int i = 0; i < 10; i++)  //for loop to insert the 10 random elements in the tree
			 //calls the random generator to input the elements in the array
			tree.root = tree.insert(tree.root, randomGenerator());  

		/*  Display tree traversal */ 
        System.out.print("\nPre order : ");
        tree.preOrder(tree.root);
        System.out.print("\nIn order : ");
        tree.inOrder(tree.root);
        System.out.print("\nPost order : ");
        tree.postOrder(tree.root);
        
        //Display the height of the tree
        System.out.println("\nThe height of the tree is : " + tree.height(tree.root));
        
        //For 50 random numbers
        System.out.println("\n\nA tree of 50 random numbers:");
	    for (int i = 0; i < 50; i++) //for loop to insert the 50 random elements in the tree
	    	//calls the random generator to input the elements in the array
	    	tree.root = tree.insert(tree.root, randomGenerator());
	    
        /*  Perform the tree traversal */ 
	    System.out.print("\nPre order : ");
        tree.preOrder(tree.root);
        System.out.print("\nIn order : ");
        tree.inOrder(tree.root);
        System.out.print("\nPost order : ");
        tree.postOrder(tree.root);
        
        //Display the height of the tree
        System.out.println("\nThe height of the tree is : " + tree.height(tree.root));
        
        //For 200 random numbers
	    System.out.println("\n\nA tree of 200 random numbers:");
	    for (int i = 0; i < 200; i++) //for loop to insert the 200 random elements in the tree
	    	//calls the random geenerator to input the elements in the array
	      	tree.root = tree.insert(tree.root, randomGenerator());
		  
        /*  Perform the tree traversal */ 
	    System.out.print("\nPre order : ");
        tree.preOrder(tree.root);        
        System.out.print("\nIn order : ");
        tree.inOrder(tree.root);
        System.out.print("\nPost order : ");
        tree.postOrder(tree.root);
        
        //Display the height of the tree
        System.out.println("\nThe height of the tree is : " + tree.height(tree.root));
          
	}
   /* This method is the random generator method which randomly generates positive numbers less than 10000 */
 	public static int randomGenerator(){
 		Random random = new Random(); //creates a new random object
 		int rand = 0; //initiate the rand variable to 0
 		rand = random.nextInt(10000); //generate the number from 0 to 9,999
 		return rand; //returns the number generated
 	}
}

