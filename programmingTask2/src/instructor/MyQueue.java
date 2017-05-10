package instructor;
/*  Queue ADT implemented using a circular array.  As it is an array, we use two indices, front and rear, to indicate
 *  where to dequeue/peek and enqueue respectively.  In order to not run out of array space because of leftward drift,
 *  we circle around to reach the front of the array whenever we get passed the end.  This queue implements isFull, isEmpty,
 *  enqueue, dequeue and peek plus two constructors.  */

public class MyQueue
{
	private int[] queue;						// the queue itself, stores int data
	private int size, front, rear;				// size of queue used for isEmpty and isFull, front and rear indices used to dequeue/peek and enqueue
	
	public MyQueue(int x)						// use a queue whose size is x
	{
		queue=new int[x];
		size=0;front=0; rear=-1;
	}
	
	public MyQueue()							// default to queue of size 100
	{
		this(100);
	}
	
	public boolean isFull()						// queue is full if size is the number of array elements
	{
		return size==queue.length;
	}
	
	public boolean isEmpty()					// queue is empty if size is 0
	{
		return size==0;
	}
	
	public void enqueue(int x)					// if queue is not full, enqueue x at the rear
	{
		if(!isFull())
		{
			rear=(rear+1)%queue.length;			// wrap around as needed
			queue[rear]=x;						// enqueue x
			size++;								// number of elements in queue is now 1 greater
		}
		else System.out.println("Queue is full, cannot enqueue");	// error message if queus is full
	}
	
	public int dequeue()						// remove front element if not empty
	{
		if(!isEmpty())
		{
			int x=queue[front];					
			front=(front+1)%queue.length;		// move front on to next element, wrapping around if necessary
			size--;								// queue 1 item smaller now
			return x;							// return the dequeued item
		}
		else {									// if queue is empty, output error message and return error code
			System.out.println("Queue is empty, cannot dequeue");
			return -9999;
		}
	}
		
	public int peek()							// return front of queue if not empty
	{
		if(!isEmpty())
			return queue[front];
		else {									// if queue is empty, output error message and return error code
			System.out.println("Queue is empty, cannot peek");
			return -9999;
		}
	}
}