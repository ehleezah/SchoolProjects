package example;
//Eliza Karki CSC 501
/*This is our Queue class that uses circular array to store the number of students in a line.
 * It has two main methods of enqueue, for adding the students and dequeue for removing the students. 
*/
public class Queue {
	private int front, rear, count; //these are our variable for the index at front, rear 
									//and count is for counting number of students entered 
	private int[] list; 			//this is our circular array for storing the number of students 
	private int maxSize; 			//this is the maximum size of the array 
	
	public Queue() { // this is our default constructor for the queue which stores atleast 1000 students in the list
		this(1000);
	}
	public Queue(int size) { //this is our 1-arg constructor where size is the size of the list  
		maxSize = size; //maximum size that our list could be is the size of list
		count = 0;      // initially count is 0
		list = new int[size];	//the array is now of size, size	 	  
		front = 0; rear = size-1;  //front is at index 0 and rear is at our index, size - 1, which is the last index
	}
	public boolean isFull() { //checks to see if the array is full or not
		 //if count is equal to the size of the maximum size of the array it is full
		return (count == maxSize);
	}
	
	public boolean isEmpty() { //checks to see if the array is empty or not
		return (count == 0); //if the count is equal to 0, then the array is empty
	}

	public void enqueue(int item)  //this method is for inserting the element, item into the array
	{
		if (!isFull()) //if the array still has space available
		{
			rear = (rear + 1) % maxSize; //update rear as we have our formula for it
			list[rear] = item; //add element to the array
			count++; //increment count as an element is added now
		}
		else 			//if its full, print the message
			System.out.println("Queue full, cannot enqueue");
	}
	
	public int dequeue() { //this method is for removing the element from the array
		if (!isEmpty()) { //if the array is not empty
			int temp = list[front]; // store the element to be deleted in temp variable
			front = (front+1) % maxSize; //front is updated now as element has been deleted
			count--; //decrement count as the element has been removed now
			return temp; //returns the deleted element
		}
		else { //if the array is empty nothing to delete so prints the message
			System.out.println("Error, queue empty");
			return -9999;
		}
	}
	
}
