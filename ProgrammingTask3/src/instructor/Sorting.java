package instructor;

/*  This program demonstrates the complexity of various sorting algorithms.  It inputs a text file of numbers into
 *  an array and then copies that array into 5 additional arrays.  Each of these arrays is passed to a separate
 *  method to sort it using a different sorting algorithm. The algorithms covered are:  bubble, insertion, merge,
 *  quick and selection.  Merge and quick sorts use recursion.  Each "comparison" or "swap" is counted so that we
 *  can see the relative number of operations given the size and order of the original data.  These are stored
 *  in instance data so that they are available in the methods (for merge and quick sort, this is required since
 *  they are recursive methods).  */

import java.util.*;
import java.io.*;

public class Sorting
{
	private static int bubble=0;				// the count of operations for each of the 5 sorting algorithms
	private static int insertion=0;
	private static int selection=0;
	private static int merge=0;
	private static int quick=0;
	
	public static void main(String[] args) throws IOException	// using File so we have to handle IOExceptions
	{
		int[] array = fillArray();				// the array to be filled through input, fillArray opens and closes the input file, inputs the data, returns this as an array
		int n = array.length;					// use n to create the ohter 5 arrays whose size will be identical to the number of values to sort
		int[] barray=new int[n];				// create the 5 arrays to store the list of unsorted numbers into
		int[] iarray=new int[n];
		int[] sarray=new int[n];
		int[] marray =new int[n];
		int[] qarray=new int[n];
		copy(array, barray, iarray, sarray, marray, qarray);		// copies array into the other 5 arrays
		bubbleSort(barray);						// invoke the 5 sorting algorithms (note:  n is not needed as the array's length is equal to n)
		insertionSort(iarray);
		selectionSort(sarray);
		mergeSort(marray);
		quickSort(qarray);
		System.out.println("n = " + n);			// output the results of the 5 sorts
		System.out.println("Bubble sort    " + bubble);
		System.out.println("Insertion sort " + insertion);
		System.out.println("Selection sort " + selection);
		System.out.println("Merge sort     " + merge);
		System.out.println("Quick sort     " + quick);
	}

	// method to open an input file, input the first value (the number of data) and input those data into an array, close the file and return the array
	public static int[] fillArray() throws IOException
	{
		Scanner in = new Scanner(new File("input2.txt"));		// hardcoded the file name, we could input this from the user for flexibility
		int x = in.nextInt();					// the first datum is the number of data elements to follow
		int[] temp = new int[x];				// create an array that will precisely fit the data
		for(int i=0;i<x;i++)					// input the data
			temp[i] = in.nextInt();
		in.close();								// close the file and return the array
		return temp;
	}
	
	// copy array a into the other 5 arrays
	public static void copy(int[] a, int[] b, int[] c, int[] d, int[] e, int[] f)
	{
		for(int i=0;i<a.length;i++)
		{
			b[i] = c[i] = d[i] = e[i] = f[i] = a[i];
		}
	}
	
	// bubble sort works by making repeated passes down the array, swapping pairs of elements (a[i], a[i+1]) if the element on the left < element on the right
	// with each pass, the largest element "bubbles up" to the top (right) of the array, so with each pass, we can make 1 fewer passes down the array
	// we use a boolean to indicate whether we need another pass (array was not yet sorted because at least 2 elements had to be swapped)
	// we cound the number of comparisons as the number of operations for this sort
	public static void bubbleSort(int[] a)
	{
		boolean done = false;			// assume array is not yet sorted
		int n = a.length, temp;
		while(!done)					// continue while array hasn't yet be sorted
		{
			done = true;				// assume this will be the last pass through the array
			for(int i=0;i<n-1;i++)		// iterate down the length of the array, use < n-1 because we want to stop when i=n-2 otherwise we will try a[n-1] and a[n]
			{
				if(a[i]>a[i+1]) {		// compare pair of values, if needed, swap them
					temp = a[i];
					a[i] = a[i+1];
					a[i+1] = temp;
					done = false;		// since we swapped two elements, the array is not yet sorted
				}
				bubble++;				
			}
			n--;					// with a pass successfully completed, the largest value is at the end of the array, we no longer need to consider it, shorten n by 1
		}
	}
	
	// insertion sort works by starting with a 1-element array and insering the next element either before or after it, and then taking the next element and inserting it before
	// the first, between the first two, or after the second, etc
	public static void insertionSort(int[] a)
	{
		int current, j;					// current represents the value we are looking to insert, j is used to work our way right to left from point i-1 to 0 to stop us from working left of a[0]
		for(int i=1;i<a.length;i++)		// iterate n-1 times
		{								
			current = a[i];				// get the next array element to insert
			for(j=i-1;j>=0&&a[j]>current;j--)		// working from i-1 to 0, shift a[j] to a[j+1] (shift it right) until we find the proper insertion point (when current <= a[j]
			{
				a[j+1] = a[j];			// shift
				insertion++;			// count this as an operation -- note in counting here, we are actually counting the number of swaps, not the number of comparisons
			}
			a[j+1] = current;			// we shifted a[j] to a[j+1] and then decremented j, we have reached the location where to insert current, but already decremented j, so insert at a[j+1]
		}
	}
	
	
	// selection sort works by making repeated passes down the array, finding the minimum value and swapping it with the value at the front of the array.  With each pass, we look at the entire
	// array other than what has already been sorted (at the beginning).  So for i=0, we find the min between a[0] and a[n-1] and swap it with a[0].  Then, for i=1, we find the min between
	// a[1] and a[n-1] and swap it with a[1]. This continues until we have successfully swapped with i=n-2.  At that point, all of the elements are sorted.
	public static void selectionSort(int[] a)
	{
		int min, minposition, temp;		// min is the minimum value found so far, minposition is that value's index in the array, temp is used for swapping
		for(int i=0;i<a.length-1;i++)	// iterate down the length of the array until through n-2 (we don't need to do i=n-1 because we would just be comparing a[n-1] with a[n-1])
		{
			min = a[i];					// remember the item at a[i] and its position for swapping
			minposition = i;
			for(int j=i+1;j<a.length;j++)	// work down the rest of the array looking for the smallest value 
			{
				selection++;			// count each comparison				
				if(a[j]<min)			// if the current location, a[j], is smaller than min, remember a[j] as the new min and j as the new minposition
				{
					min=a[j];
					minposition=j;
				}
			}
			temp=a[i];					// swap a[i] and a[minposition]
			a[i]=a[minposition];
			a[minposition]=temp;
		}
	}
	
	// the following is the non-recursive starter method for mergesort.	
	public static void mergeSort(int[] a)
	{
		callMerge(a, 0, a.length-1);	// 0 represents the leftmost point in the array we are sorting and length-1 the rightmost
	}
	
	// this is the recursive method for mergesort, it divides the current array in half, recursively calls itself on those two halves, and merges the result
	// the base case is once the first index equals the last index in which case we have an array of 1 element which does not need sorting/merging with respect to itself
	public static void callMerge(int[] a, int first, int last)
	{
		if(first<last)
		{
			int mid=first+(last-first)/2;
			callMerge(a, first, mid);
			callMerge(a, mid+1, last);
			merge(a, first, mid, last);
		}
	}
	
	// merge must first make a copy of the array as we will be copying elements from the first half and the second half, back into itself, thus overwriting values.  So instead,
	// we copy a into temp and then we pick elements from temp to put back into a
	// merge works as follows:  assume we have these two half of arrays in temp:  5   8   10   15       2   3   9   11
	//      compare 5 and 2, move the smaller (2) into a[0], then compare 3 and 5, move the smaller into (3) a[1], then compare 5 and 9, move the smaller (5) into a[2], then compare
	//      8 and 9, move the smaller (8) into a[3], then compare 9 and 10, move the smaller (9) into a[4], then compare 10 and 11, move the smaller (10) into a[5], then compare
	//      11 and 15, move the smaller (11) into a[6], and now that we have finished with one half of the array, copy the rest of the remaining half into a (15 gets copied into a[7])
	public static void merge(int[] a, int first, int mid, int last)
	{
		int[] temp = new int[a.length];				// create our temp array to copy a into
		for(int i=first;i<=last;i++) {
			temp[i]=a[i];							// we will now merge the two halves of temp into a
			merge++;								// count each copy as an operation for mergesort
		}
		int i=first, j=mid+1, k=first;				// i is the index of where we are in temp on the left side, j is where we are in temp on the right, k is where we are in a
		while(i<=mid&&j<=last) {					// continue to compare elements until we have either reached the end of the first half or the end of the second half
			if(temp[i]<=temp[j])					// which is smaller, the item on the left side (temp[i]) or on the right side (temp[j])?  Move it to a[k] and increment the i or j index
				a[k]=temp[i++];
			else a[k]=temp[j++];
			k++;									// now that we have copied an element into a, increment the a index (k)
			merge++;								// count each copy as an operation for mergesort
		}
		while(i<=mid)								// if we finished the right hand side, copy the rest of the left hand side of temp
		{
			a[k++]=temp[i++];
			merge++;								// count each copy as an operation for mergesort
		}			
		while(j<=last) {							// if we finished the left hand side, copy the rest of the right hand side of temp
			a[k++]=temp[j++];
			merge++;								// count each copy as an operation for mergesort			
		}
	}
	
	// quick sort selects a pivot value, and then moves up and down the array looking for values < pivot from right to left and values > pivot from left to right
	// and swapping them.  When done, it has met somewhere in between (the actual location will vary depending on the order of the values to begin with) and inserts
	// the pivot there.  The idea is that when done with one pass, the pivot value is to the right of all values > pivot and to the left of all values < pivot.  Then,
	// quicksort is called recursively with the left hand side of the array (those values < pivot) and the right hand side (those values > pivot).
	
	// This is the non-recursive starter method to invoke quick sort on array a from indices 0 to length-1
	public static void quickSort(int[] a)
	{
		callQuickSort(a, 0, a.length-1);
	}
	
	// the recursive method tests the base case (first is still < last) and if so, calls partition to rearrange values so that a pivot is inserted somewhere in between
	// those < it and those > it, and it returns this index so that we can recursively call this method with the left side and the right side
	public static void callQuickSort(int[] a, int first, int last)
	{
		int index;
		if(first<last) {							// once first >= last, we are done sorting this portion of the array
			index=partition(a, first, last);		// rearrange the values between a[first] and a[last], remembering in index where the pivot is inserted
			callQuickSort(a, first, index-1);		// recursively call on the left side of a
			callQuickSort(a, index+1, last);		// recursively call on the right side of a
		}
	}
	
	// the partition method is where all the action takes place for quick sort.  Here, we select a pivot point (we will use a[first].  We then search a[first+1] through a[last]
	// looking for values < pivot and values > pivot and swapping them so that somewhere in the middle, we will insert pivot such that all elements to its left are < it and all
	// elements to its right are > it, we then return the index of where we inserted pivot
	// note that quick is the counter for the number of operations, we will compare the number of items we compare as we compare elements to pivot left to right and right to left
	public static int partition(int[] a, int first, int last)
	{
		int pivot = a[first];						// we will use a[first] as our pivot value
		int left = first, right = last, temp;		// start searching from first to last (note we could use first+1)
		while(left<right) {							// while the two indices have not yet met in the middle
			while(left<=right&&a[left]<=pivot) {left++;quick++;}		// search left to right for a value > pivot
			while(left<=right&&a[right]>=pivot) {right--;quick++;}		// search right to left for a value < pivot
			if(left<right) {						// if we are still at a point where left < right, swap the two
				temp=a[left];a[left]=a[right];a[right]=temp;
			}
		}
		// finally we have reached the mid point, swap the item at the beginning (our pivot) with the item at the middle and return pivot's index
		a[first]=a[right];
		a[right]=pivot;
		return right;
	}
}