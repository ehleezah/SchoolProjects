/*  The Radix Sort.  This sort is a non-comparison sort.  Instead, it iterates column times where column
 *  is the number of columns of digits (or chars if Strings) and for each column, peels off that digit/char
 *  of each value in the array and enqueues the item into the array of that position, and then recreates the
 *  array by dequeuing each item from each queue in order.  For example, if dealing with 3-digit numbers, we
 *  would need 10 queues, one for each digit 0-9.  Let's assume we have these numbers:
 *      251   984   974   981   41   616   216   351   201   202   959  904   921
 *  First, we peel off the rightmost digit, so 251 has a 1 and is enqueued into the 1s queue, 984 has a 4 
 *  so is enqueued in the 4's queue, 974 has a 4 so is enqueued in the 4's queue, etc
 *
 *  Now we reform the array which would take all the items from the 1's queue (251, 981, 41, 351, 201, 921)
 *  and then the 2's queue (202) and then the 3's queue (empty) and then the 4's queue (984, 974, 904), etc
 *  After 1 iteration, our list is 251 981 41 351 201 921 202 984 974 904 616 216 959.  Notice how these
 *  are now sorted with respect to the rightmost digit.  Now we do the same with the second digit and we will 
 *  then have 201 202 904 616 216 921 41 251 351 959 974 981 984 (the array is now sorted with respect to
 *  the rightmost two digits) and then 41 201 202 216 251 351 616 904 921 959 974 981 984 */

import java.util.*;									// for Random

public class RadixSort
{
	public static void main(String[] args)
	{
		Random g = new Random();
		Queue[] queues=new Queue[10];				// we need 10 queues since we are using int values (10 digits) (Strings would need at least 128 queues for the 128 ASCII characters)
		int size = 2500;							// the number of elements we are sorting, change this to see how the number of operations changes
		int max=6;									// max number of digits any number will have, here we are generating random numbers from 0-999999
		for(int i=0;i<10;i++) queues[i] = new Queue();	// create the queues
		int[] array = new int[size];				// create the array
		int digit;									// stores the digit we are peeling off a number
		int count=0;								// stores number of operations required for this sort
		int num;									// used as a counter to refill the array when done inserting all numbers into the queues
		for(int i=0;i<size;i++) array[i]=g.nextInt(1000000);	// fill the array
		for(int col=0;col<max;col++)				// do for each digit (6 columns worth)
		{
			for(int i=0;i<size;i++)					// for each number in the array
			{
				digit=peelOff(array[i], col);		// obtain its col' digit
				queues[digit].enqueue(array[i]);	// and enqueue it into that queue
				count++;							// enqueuing is O(1)
			}
			num=0;
			for(int i=0;i<10;i++)					// rebuild the array by taking every number from every queue, in order, and placing it back into the array
				while(!queues[i].isEmpty()) 		// for the ith queue, remove all elementsi n order
				{
					array[num++]=queues[i].dequeue();
					count++;						// dequeue is O(1)
				}
		}
		//for(int i=0;i<size;i++) System.out.println(array[i]);	// output the final array (commented out to save space)
		System.out.println("Number of operations:  " + count);	// output the number of operations, should be size * col (2500 * 6 in this case), O(n) sort but a pretty high value for k, would be higher for Strings
	}
	
	public static int peelOff(int n, int c)			// return the digit found in number n column c
	{
		int div=(int)Math.pow(10,c);				// if we want the 3rd digit, we divide by 100 and then mod by 10
		return (n/div)%10;
	}
}