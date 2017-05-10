//This class implements the hash table using chained hashing.
//The chained hashing is another great way of handling collisions in hash tables. 
//In this case, a “collision” only arises from those values that map to the same index.  
// We use an array of linked lists so that each array location is a linked list where array[key%size] is a front pointer
//to a list of all of the keys that mapped to this particular index

/* The chained hashing will itself represent the hash table as an array of linked lists,
 * we are implementing the linked list mechanisms as part of the chained hashing class.
 * We are making the linked list an unordered list so that the insertion is easy.
 *   This class contains two instance data, the front Node pointer and
	 * numberOfCollisions to record the number of collisions occurred during inserting/searching items. The front Node pointer is  
	 * initialized to null and the numberOfCollisions to 0 in the constructor. The class has three methods aside from the constructor:
	 *		insert:  to do an unordered insert of a new value into the list by placing it at the beginning of the list
	 *		search:  sequential search to locate an item in the list
	 *		getNumberOfCollisions:  to return the number of collisions (numberOfCollisions) that arised so far to do inserts and searches
	 *
	 *As we will not be required to delete any values, we do are not implementing a delete method here.
	 **/

public class chainedHashing {
		private int size; //the size for the chained hashing
		private int numberOfCollisions; // counts the number of collisions that arise during insertion and searching
		private Node[] arr;					// arr to store the values and search the values in the chained hashing					
		
		public chainedHashing(int size)				// 1-arg constructor, where the parameter is the size of the array 
		{
			this.size = size;
			arr = new Node[size]; //we initialize the array with the size of the given linked list
			for (int i = 0; i < arr.length; i++) 
				arr[i] = new Node(-1); //setting the value to be -1 initially as there are no elements
			numberOfCollisions = 0; //setting number of collisions to 0 as initially there are no collisions occurred
		}
		
		// insert new value into the linked list and check the number of collisions occured during the insertion
		public void insert(int x)			
		{
			int chainedHash = x % size; //variable for the index in the array
			if (arr[chainedHash].getData() != -1) { //if there are values already in the array we do the insertion in the next available node in the list
				Node node = new Node(x, arr[chainedHash]); //we create a new node so that we can insert our item at the front position of the node while there are still the old values present in the linked list
				arr[chainedHash] = node; //we insert the item in the position where there is availability as the position changes from previous to the new one
			}
			else { //if there are no values yet
				arr[chainedHash] = new Node(x); //we insert the value in the normal way by creating a new node in the linked list
			}
			
		}
		
		// find x in the array and check the number of collisions occured
		public void search(int x)
		{
			int chainedHash = x % size; 
			if (arr[chainedHash].getData() != x) //if the value is not at where we expect it to be we search our linked list to find the value
			{
				Node temp = arr[chainedHash]; //create a temporary variable to hold the value in the array
				while((temp.getNext() != null) && (temp.getData() != x)) { // while we have still the place to be looked for and we haven't reached the end of the linked list
					//and the value is not what we expect it to be, we keep moving forward
					temp = temp.getNext(); //we set the temp to be the next node 
					numberOfCollisions++; //we increment the collisions as we search for the value in the linked list
				}
			}
		}
		
		// accessor for numberOfCollisions counter variable
		public int getNumberOfCollisions() 
		{ 
			return numberOfCollisions;
		}
	}

