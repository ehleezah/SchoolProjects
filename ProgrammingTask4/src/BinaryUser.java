//Eliza Karki CSC 501

//This is the user program for the binary user class that implements the BinaryTree inputs the int values from the disk file into an array.
//It creates a new binary tree. Inserts all of the values from the array into the tree.
//After performing the insertions, the size and height of the tree are accessed and the preorder, inorder and postorder traversals are performed.
//It deletes every fourth element of the array from the tree. After each delete, it outputs the current size and height of the tree.
//Then, it searches for every fifth element of the array. The result of a search merely outputs if the value was found or not.
//Finally, it does the preorder, inorder and postorder traversals again.

import java.util.*;
import java.io.*;

public class BinaryUser {
	
	public static void main(String[] args) throws Exception { //using File so we need to handle the IOExceptions
		File f = new File("prog4in1.txt"); //a new file instance for reading the file
		Scanner inp = new Scanner(f); //the scanner for taking the input values from the file
		int ctr = 0; //counter variable for counting the number of int values in the file
		while (inp.hasNextInt()) { //while there are int values in the file, continue counting the number of int values 
			ctr++;  //increment the variable to 1 for each int values
			inp.nextInt(); //get the next int value in the file
		}
		inp.close(); //close the file once done
		
		int[] array = new int[ctr]; //a new array has been created here of the array size of the number of int values in the file
		Scanner inp1 = new Scanner(f); //we create another scanner since our previous scanner has already read through the file and we reached the end of the file,
		//we need a new one here to start inserting the int values from the  beginning
		int n = array.length; //stores the length of the array
		for (int i = 0; i < n; i++) { //the for loop for inserting the elements in the array from the file
			array[i] = inp1.nextInt(); //keep on inserting as there are int values 
		}
		inp1.close(); //close the file after its done
		
		BinaryTree b = new BinaryTree(); //create a new empty binary tree
		//inserting the int values from the array to the binary tree
		for (int j = 0; j < n; j++) { 
			b.startInsert(array[j]); //the values from the array are inserted into our binary tree, by calling the starter method for insert
		}
		//printing the value for the size and the height of the tree
		System.out.println("Size: " + b.getSize() + "\tHeight: " + b.startGetHeight());
		System.out.print("Preorder: "); b.startPreorder(); //printing the items for the preorder traversal
		System.out.print("\nInorder: "); b.startInorder(); //printing the items in the inorder traversal
		System.out.print("\nPostorder: "); b.startPostorder(); //printing the items in the postorder traversal
		
		//deleting every fourth element of the array from the tree
		for (int k = 0; k < n; k+=4) {
			System.out.print("\nDeleting " + array[k]); //prints the message for the element to be deleted
			b.startDelete(array[k]); //calls the starter method for deleting the element
			System.out.print("\tSize: " + b.getSize() + "\tHeight: " + b.startGetHeight()); //prints the value for size and height after each delete performed
		}
	
		//searching for every fifth element of the array
		for (int k = 0; k < n; k += 5) {
			b.startSearch(array[k]); //calling the starter method for search
		}
		
		//performing the preorder, inorder and postorder traversals again
		System.out.print("Preorder: "); b.startPreorder();
		System.out.print("\nInorder: "); b.startInorder();
		System.out.print("\nPostorder: "); b.startPostorder();
		System.out.println("\nSize: " + b.getSize() + "\tHeight: " + b.startGetHeight()); //finally printing the size and height of the tree in the end of the performance
	
	}
}
