/*  A program to demonstrate the difference between a sequential search and a binary search.  
 *  We will fill an array with random numbers and sort it.  We will then random search for
 *  some values and determine the number of search steps.  From this, we will compute the
 *  average and worst case search times for both sequential and binary search.  */
 
 import java.util.*;
 
 public class searchComplexity
 {
 	public static void main(String[] args)
 	{
 		Random g = new Random();
 		int[] array = new int[1000];									// our list of values (randomly generated)
 		for(int i=0;i<1000;i++) array[i] = g.nextInt(100000);
 		int[] searchItems = new int[100];
 		for(int i=0;i<100;i++) searchItems[i] = array[i*10];			// our list of search values will be every 10th value of the random list
 		int seq, bin, sequential=0, binary=0, mostSeq=0, mostBin=0;		// seq/bin = number of search steps for this array value for sequential and binary searches, sequential/binary = total search steps so far, mostSeq/mostBin = largest number of steps so far
 		sort(array, 1000);												// sort the list now that we have our random search elements
 		for(int i=0;i<100;i++)											// for each element in our search list
 		{
 			seq=sequentialSearch(array, 1000, searchItems[i]);			// get the number of steps to find it in sequential and binary searches
 			bin=binarySearch(array, 1000, searchItems[i]);
 			if(seq>mostSeq) mostSeq=seq;								// update maximum number of steps if needed
 			if(bin>mostBin) mostBin=bin;
 			sequential+=seq;											// add to running totals to retain total number of steps so far
 			binary+=bin;	
 		}
 		double seqAvg = sequential/100.0;								// compute averages
 		double binAvg = binary/100.0;
 		System.out.printf("Sequential search average = %.2f\n", seqAvg);	// output results
 		System.out.printf("Binary search average     = %.2f\n", binAvg);
 		System.out.printf("Longest sequential search = %d\n", mostSeq);
 		System.out.printf("Longest binary     search = %d\n", mostBin);
 	}
 	
 	public static int sequentialSearch(int[] a, int n, int s)			// search for s in a[ ] using sequential search
 	{
 		int count=1;													// count is both our array index and the number of steps, we find the item at a[count] having gone through count+1 searhces
 		while(count<n&&a[count]!=s) count++;							
 		return count;
 	}
 	
 	public static int binarySearch(int[] a, int n, int s)				// search for s in a[ ] using binary search
 	{
 		int mid=n/2, high=n-1, low=0, count=1;							// count is the number of searches
 		while(a[mid]!=s&&high>=low)
 		{
 			count++;
 			if(a[mid]>s) high=mid-1;
 			else low=mid+1;
 			mid=(high+low)/2;
 		}
 		return count;
 	}
 	
 	public static void sort(int[] a, int n)								// (inefficient) bubble sort
 	{
 		boolean sorted=false;
 		int temp;
 		while(!sorted)
 		{
 			sorted=true;
 			for(int i=0;i<n-1;i++)
 				if(a[i]>a[i+1]) {
 					sorted=false;
 					temp=a[i];
 					a[i]=a[i+1];
 					a[i+1]=temp;
 				}
 		}
 	}
 }