//Eliza Karki CSC 501
//Hash Table : This is the first part of the assignment where we experiment with hashing
//This is the user class that randomly generates 75 numbers from 0 to 999,999 to be hashed and sought which will be placed into an array.
//Then we use this array and insert each value into the array and then do all the insertions into three different hash tables and output 
//the number of collisions, and search for all of the values in the same three hash tables and obtain the number of collisions discovered during searching 
//and output the results to compare the three hash tables. 
//There is one method randomGenerator() for generating random values from 0 to 999,999 to insert into the array
//This class uses 4 other classes for the hashing:
//linearProbing,
//quadraticProbing,
//chainedHashing,
//Node class which is implemented in chainedHashing class

import java.util.*;
public class userClass {
	
	public static void main(String[] args) {	
		int[] arr = new int[75]; //create a new array with the size 75 to store the randomly generated values
		int n = arr.length; //length of the array
		for (int i = 0; i < n; i++)  //for loop to insert the elements in the array
			arr[i] = randomGenerator(); //calls the random generator to input the elements in the array
			
		//instantiating the three hashtables 
		linearProbing lp = new linearProbing(101); //hashtable for linear probing with 101
		quadraticProbing qp = new quadraticProbing(101); //hashtable for quadratic probing with 101
		chainedHashing ch = new chainedHashing(31); //hashtable for chained hashing with size 31
		
		//the for loop for inserting the randomly generated int values from the array to each hash tables calling the insert method
		for (int i = 0; i < n; i++) {
			lp.insert(arr[i]); 
			qp.insert(arr[i]);
			ch.insert(arr[i]);
		}
		//prints the number of collisions for each hash tables after the insertion
		System.out.println("Collisions: ");
		System.out.println("\tLinear:\t\t" + lp.getNumberOfCollisions());
		System.out.println("\tQuadratic:\t" + qp.getNumberOfCollisions());
		System.out.println("\tChained:\t" + ch.getNumberOfCollisions());
		
		//for loop for searching the int values in the three hash tables
		for (int i = 0; i < n; i++) {
			lp.search(arr[i]);
			qp.search(arr[i]);
			ch.search(arr[i]);
		}
		
		//prints the number of collisions for each hash tabels after searching
		System.out.println("Collisions: ");
		System.out.println("\tLinear:\t\t" + lp.getNumberOfCollisions());
		System.out.println("\tQuadratic:\t" + qp.getNumberOfCollisions());
		System.out.println("\tChained:\t" + ch.getNumberOfCollisions());
	}
	
	//this method is the random generator method which randomly generates 75 numbers from 0 to 999,999 to be hashed and sought
	public static int randomGenerator(){
		
		Random random = new Random(); //creates a new random object
		int rand = 0; //intiate the rand variable to 0
		rand = random.nextInt(1000000); //generate the number from 0 to 999,999
		return rand; //returns the number generated
	}
}
