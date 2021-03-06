//This class implements the hash table using quadratic probing.
//The quadratic probing is another form of handling collisions in hash tables. 
//This kind of probing works in the following way: 
//if there is a collision at position key % size then probe forward i^2 + 1 location at a time until a free array location is found
//same as linear probing but we use key+i^2 instead of key+i
//the idea is that by increasing i in larger increments than 1, we can jump over clusters found in linear probing although secondary clusters might occur
/*
 *  *   This class contains three instance data, the size for storing the size for the quadratic hashing, array where the quadratic hashing will be implemented and
	 * numberOfCollisions to record the number of collisions occurred during inserting/searching items. The class has three methods aside from the constructor:
	 *		insert:  to do an unordered insert of a new value into the list by placing it at the beginning of the list
	 *		search:  sequential search to locate an item in the list
	 *		getNumberOfCollisions:  to return the number of collisions (numberOfCollisions) that arises so far to do inserts and searches
	 *
	 *As we will not be required to delete any values, we do are not implementing a delete method here.
	 **/

public class quadraticProbing {
	private int numberOfCollisions; //the instance datum for incrementing the number of collisions
	private int size;  //the size to be used for the quadratic hashing
	private int[] arr; // the new array which is implemented as the quadratic hash table
	
	public quadraticProbing(int size) {
		this.size = size;
		arr = new int[size];
		for (int i = 0; i < arr.length; i++)
			arr[i] = -1; //initially we do not have any elements in the array so we set it to -1 for all the element in the array
	}
	//if there is a collision at position key % size then probe forward i * i location at a time until a free array location is found
	//the method for inserting an item in the hash table 
	public void insert(int value) { //parameter is the value to be inserted
		int linear = value % size;
		int i = 1;
		while(arr[linear] != -1 && (linear < arr.length)) {
			linear = (linear + i*i) % size;
			numberOfCollisions++;
			i++; //increment the i for the quadratic probing i * i
		}
		arr[linear] = value; //finally insert the value at that location
	}
	//the search method for search the value in the hash table
	public void search(int value) {
		int linearSearch = value % size; //variable to store the index in the hash table given by value % size
		int i = 0;
		//while the item at the given index in the array is not equal to the value to be searched and there is no empty space and we hit the collision    
		while ((arr[linearSearch] != value) && (arr[linearSearch] != -1) && linearSearch < arr.length) { 
			linearSearch = (linearSearch + i * i) % size; //we probe forward i * i until free array location is found to search for the item
			numberOfCollisions++; //we hit the collision so we increment the counter variable
			i++; 
		}
	}
	//the accessor method to return the number of collisions occured during the insertion and the searching
	public int getNumberOfCollisions() {
		return numberOfCollisions; //return the number of collisions
	}
	

}
