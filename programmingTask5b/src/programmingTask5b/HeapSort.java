package programmingTask5b;
//Eliza Karki CSC 501
//This is the heap sort class that creates a Heap by sorting the elements in a descending order. 
//A heap sort uses a binary heap. It first adds all the elements to a heap and then removes the 
//largest elements successively to obtain a sorted list. A heap property states that each node
//is greater than or equal to any of its children.
//It inputs from disk file a list of int values and insert them into the Heap.
//Then, it deletes each element of the Heap, outputting the value, until
//the Heap is empty. The result should be the values from the disk file in descending order.

import java.io.File;
import java.util.Scanner;

public class HeapSort {
	public static void main (String[] args)  throws Exception { //using File so we need to handle the IOExceptions
		
		File f = new File("prog4in1.txt"); //a new file instance for reading the file
		Scanner inp = new Scanner(f); //the scanner for taking the input values from the file
		int ctr = 0; //counter variable for counting the number of int values in the file
		while (inp.hasNextInt()) { //while there are int values in the file, continue counting the number of int values 
			ctr++;  //increment the variable to 1 for each int values
			inp.nextInt(); //get the next int value in the file
		}
		inp.close(); //close the file once done
	
		int[] array = new int[ctr]; //a new array has been created here of the array size of the number of int values in the file to insert all the items from the file
		
		Scanner inp1 = new Scanner(f); //we create another scanner since our previous scanner has already read through the file and we reached the end of the file,
		//we need a new one here to start inserting the int values from the  beginning
		int n = array.length; //stores the length of the array
		
		for (int i = 0; i < n; i++) { //the for loop for inserting the elements in the array from the file
			array[i] = inp1.nextInt(); //keep on inserting as there are int values 
		}
		inp1.close(); //close the file after its done
		Heap h = new Heap(n); //creates a new heap of size in the disk file
		//inserting the integer values from the array to the heap		
		for (int j = 0; j < n; j++) { 
			h.insert(array[j]); //the values from the array are inserted into our heap by calling the method for insert
		}
		
		//continue deleting until the heap is empty
		while (!h.isEmpty())
			System.out.println(h.delete());	//prints the values in the descending order
	}
}
	 
//}
