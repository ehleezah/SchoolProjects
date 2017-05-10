package programmingTask5b;
//Eliza Karki CSC 501
//A binary heap is a binary tree with the following properties:
//Shape property: A complete binary tree (which means if each of its levels is full, except that the last level may not be full and
//all the leaves on the last level are placed leftmost. 
//Heap property: Each node is greater than or equal to any of its children 
//This is the Heap class that implements the array and has the following methods:
//a constructor - to build a heap whose size is given as a parameter,
//an isEmpty method - to check whether the heap is empty or not,
//and insert method - to insert a new item into the heap and
//delete method - to delete the item from the heap that returns the largest item  in the heap

public class Heap {
	private int heapSize; //the instance datum for the heap size 
	private int[] heap; //the heap array for storing the elements
	
	//default constructor
	public Heap() { //create a default heap
	}
	//1-arg constructor 
	public Heap(int size) { //the size of the array passed as the parameter
		heapSize = 0; //initialize the size of the heap to be 0 initially
		heap = new int[size];	//initialize the heap array with the number of elements, i.e. its size 
	}
	//isEmpty method checks if a heap is empty or not
	public boolean isEmpty() {
		return (heapSize == 0); //returns true if the size of the heap is 0 and false if its not
	}
	
	//insert method - insert a new item into the heap
	//to add a new node to the heap, first add it to the end of the heap and then rebuild the tree
	//throws exception if the new item is duplicate 
	public void insert(int newItem) throws Exception { //throws exception to handle the duplicate elements
		if (heap[heapSize] != newItem) { //it sees if there are any duplicate elements 
		heap[heapSize] = newItem; //initially the heap is empty so the new item is placed in the first position in the array
		int place = heapSize; //let the last node be the current node, so the current position, place is initialized to the size of the array  
		int parent = (place)/2;  //the index for the parent node which is initialized as the one position higher than the two childs so we divide by 2
		while ((place >= 0) && (heap[place] > heap[parent])) { //while the current node we are looking at is not the root node and the element at current node is greater than the parent node
				//we swap the two items
				int temp = heap[place]; //store the item at the current position in a temp variable
				heap[place] = heap[parent]; //the item at the parent position is now the item at the current position   
				heap[parent] = temp; //the item at the current position is the item at parent position now
			place = parent; //replace the current position and the parent position
			parent = (place)/2; //and we repeat until the place is less than parent
			}
		}
		else {
			throw new Exception("No duplicate items!");
		}
		heapSize++; //we increment the size to indicate that we have one more item inserted in the heap
			//so now the next location we insert the new item would be increased by 1
	}
	
	//delete method - deletes (and return) the item in the Heap that has the largest value
	public int delete() { //since we are deleting the largest value which will always be at the root in a heap, we are not passing any argument to delete
		int temp = heap[0];  //the temp variable stores the largest item that is always at root position
		   heap[0] = heap[heapSize-1]; //replace the root by moving the last item in the array to the first position as we rebuild the heap  
		   int root = 0, left = 2 * root + 1, right = left + 1;   //initialize the root node to 0, left child to be twice the root node + 1, 
		   //right child to be one position farther the right child
			   
		   while (root < heapSize/2 && heap[root] < Math.max(heap[left], heap[right])) { //while the current node has children, and we haven't yet
		    	 //hit a leaf level and  the current node is smaller than one of its children
		   
		    	 if(heap[left] > heap[right])  { //if the left child is greater than the right child , we move down to the left 
		         //swap element at the root position in the heap & element at left position
		           	int temp1 = heap[root];
		            heap[root] = heap[left];
		            heap[left] = temp1;       	
		            root = 2 * root + 1; //reset the root position as it moves further down the left subtree and is now parent of that subtree
		            left = 2 * root + 1; //reset the left subtree by incrementing the root by 1 position
		            right = left + 1; //reset the right subtree by incrementing the left subtree by 1
		         }
		    	 else { //we move down to the right
		         //swap element at the root position in the heap & element at right position
		           int temp2 = heap[root];
		           heap[root] = heap[right];
		           heap[right] = temp2;
		           root = 2 * root + 2; //reset the root position as it moves down the right
		           left = 2 * root + 1; //left is now reset by incrementing 1 further from its root or parent
		           right = left + 1; //right now incremented by 1 from its left subtree
		        }
		    }
		    heapSize--;  //we decrement the size of the heap array as the item has been deleted
		    return temp; //we return the largest item in the heap which is stored at the root
		 }

}