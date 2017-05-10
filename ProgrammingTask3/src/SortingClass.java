//Eliza Karki CSC 501

//This class is called SortingClass where we output the number of operations that each sort took to compare them.
//The sorting algorithms we are using here are: Insertion, Bubble, Selection, Merge and Quick.
//It inputs a list of int numbers from a file into an array.
//We make copies of that array into 5 other arrays. We then sort each of these arrays using the above mentioned sorting algorithms one by one.
//To count the number of instructions for insertion, bubble and selection, we count 1 for each comparison made, i.e 1 operation = array[i] > array[j].
//For merge sort, we count 1 for each comparison made when merging two sorted arrays AND when copying the remainder of one array to the other.
//For quick sort, we count 1 for each comparison between an array value and the pivot value.


import java.io.File; //for the use of the text file in our class
import java.io.IOException; //for the throwing of an exception
import java.util.Arrays; //for the use of the copyOf method in our class for copying the original array into 5 arrays
import java.util.Scanner; //for the use of the scanner in our class

public class SortingClass //our sorting class starts here
{
	public static int numMerge=0, numQuick=0; // the counter variables for merge sort and quick sort algorithm are initialized here since they used different methods,
	//we need a global variable for the counter variables here
	
	public static void main(String[] args) throws IOException //since it uses the file, it may throw an exception, if the file is not found
	{
		Scanner in = new Scanner(new File("input4.txt")); //we open our text file here
		int size = in.nextInt(); //we get the size of the int elements in the file, which is the first number in our text file
		int[] a = new int[size]; //we create an array to input a list of numbers from the file 
		for (int i = 0; i < size; i++) {
			a[i] = in.nextInt(); //we insert the numbers from the file one by one into the array
		}
		in.close(); //we close the file now as we are done inserting all the number elements into the array from the file
		
		//we now make 5 copies of the original arrays here for the sorting in 5 different sorting algorithms
		int[] a1 = Arrays.copyOf(a, size); //this array is for sorting the array using insertion sort
		int[] a2 = Arrays.copyOf(a, size); //this array is for sorting the array using bubble sort
		int[] a3 = Arrays.copyOf(a, size); //this array is for sorting the array using selection sort
		int[] a4 = Arrays.copyOf(a, size); //this array is for sorting the array using the merge sort 
		int[] a5 = Arrays.copyOf(a, size); //this array is for sorting the array using the quick sort
		
		int numInsertion = 0, numBubble = 0, numSelection = 0; //these are the counter variables to store the number of operations that each sort took to compare them
		
		//sorting each of these arrays using the above mentioned sorting algorithms here one by one
		
		//insertion sort - this sorts any array of int elements. This is implemented here using an outer for loop with a nested while loop.
		//It works by repeatedly inserting a new element into a sorted partial array until the whole array is sorted
		//The outer for loop(with the loop control variable i is iterated in order to obtain a sorted list 
		int temp; //a temporary variable
		for (int i = 1; i < size; i++) { //this starts at i = 1, since the first element is already in the sorted position to size - 1 so that we can compare it with next elements and so on
			int location = i - 1; //we need to set the location at the first position of the array to compare with the next element in the array
			int current = a1[i]; //we set our current element to be the second element in order to be compared with the first element
			while (location >= 0 && a1[location] > current) { //when the location is greater than 0 or equal to 0 and first element is greater than second element, we swap them
				temp = a1[location]; // we store the first element in a temporary variable
				a1[location] = a1[location+1]; //now the first element(which is the smallest element) is the second element, 
				a1[location+1] = temp; //the second element is now our first element, stored in temp
				location--; //location is decreased now and set it to less than 0 so that it comes out of this while loop
				numInsertion++; //increase to 1 each time we make comparison, i.e a1[location] > a[current]
			}
		}
		
		//bubble sort - it sorts the array in multiple phases. Each pass successively swaps the neighboring elements if the elements are not in order.
		//If a pair is in decreasing order, its values are swapped otherwise; the values remain unchanged
		boolean needNextPass = true; //we set the boolean variable as true here for checking if the next pass is needed or not 
		for (int k = 1; k < a3.length && needNextPass; k++) //the outer for loop here is starting at 1 because the first element is assumed 
		{
			//array may be sorted and next pass not needed
			needNextPass = false;
			for (int i = 0; i < a3.length - k; i++) { //the nested for loop for the comparison of the adjacent elements
				numBubble++; //we increment our counter variable for counting the number of operations
				if (a3[i] > a3[i+1]) { //if the first element in the first pair of the array is greater than the second element of the pair, we do the swapping
					//swap the a3[i] with a3[i+1]
					int temp1 = a3[i]; //a temp variable which is storing the value for first element of the pair
					a3[i] = a3[i+1]; //the element at first position now becomes the next adjacent element
					a3[i+1] = temp1; //the element at the adjacent position is now before the first position element
					needNextPass = true; //Next pass still needed
				}
			}
		}
		
		
		//selection sort - This sorting algorithm repeatedly selects the smallest number and swaps it with the first number in the array
		int n = size; //this variable stores the size of the array
		int temp2, minIndex; //the temp2 is our temporary variable and minIndex holds the index of the minimum element found in the array
		//the outer for loop finds the minimum index in the array by moving the element's position one by one of the unsorted array
		for (int i = 0; i < n-1; i++ ) { //starts with the first element but ends before the last element
			minIndex = i; // initially, the minimum index is set to the first index of the array, to find the minimum element in the unsorted array
			for (int k = i+1; k < n; k++) { //the inner loop here does the comparison between the next adjacent element and the element at the minimum index to check for minimum element in the array
				numSelection++; //we increment our counter variable for each comparison made
				if (a2[k] < a2[minIndex]) { //if the next element is less than the element at the lowest index
					minIndex = k; //we set the new minimum index to be that index where the minimum element was found 
				}
			}
			//we swap the found minimum element with the first element of the unsorted portion in the array
			temp2 = a2[i]; //our temporary variable stores the first element in the array and then on each iteration it stores second, third and so on 
			a2[i] = a2[minIndex]; //our first element is then swapped with the minimum element at position minimum index and
			a2[minIndex] = temp2; //now the element at position minimum index is swapped with the element initially at the first position stored in the temporary variable
		}	
		
		//merge sort - This algorithm follows the divide and conquer method. It divides the array into two halves and applies a merge sort on each half recursively.
		//After the two halves are sorted, finally we merge them calling the merge method
		mergeSort(a4); //we are calling the merge sort method here
		
		//quick sort - This algorithm selects and element, called pivot, in the array.
		//It divides the array into two parts, so that all the elements in the first part are less than or equal to the pivot
		// and all the elements in the second part are greater than the pivot. The quick sort algorithm is then recursively 
		//applied to first part and then the second part 
		quickSort(a5); //calling the quick sort method here

		
		//now we output the number of operations that each sort took to compare them(and the size of the array)
		System.out.println("n = " + size); //prints the size of the array
		//prints the number of operations it took to compare for bubble sort, insertion sort, selection sort, merge sort and quick sort
		System.out.println("Bubble sort " + numBubble + "\nInsertion sort " + numInsertion + "\nSelection sort " + numSelection + "\nMerge sort " + numMerge + "\nQuick sort " + numQuick);
		
	
	}
	
	/**merge sort method - The mergeSort method here splits the original array into two arrays.
	 * We apply merge sort on these two subarrays recursively. This process continues until the subarray has only one element.
	 * Then we finally merge them by calling the merge method to get the final sorted array. 
	 * It takes as an argument the list, or array to be sorted and does not return anything. **/
	public static void mergeSort(int[] list) {
		int n = list.length; // the variable here stores the length of the array
		if (n > 1) { //if the length of the array is greater than one, we do the mergesort, if there is one or less element, it is already sorted
			int mid = n/2; //this variable stores the middle index of the array, so that it is divided into two halves
			int[] first = new int[mid]; // we are initializing the array for the first half of the original array here
			int[] last = new int[n-mid]; //we are initializing the array for the second half of the orginial array here
		
			System.arraycopy(list, 0, first, 0, mid); //getting the first half of the array, by calling the copy method
			System.arraycopy(list, mid, last, 0, n-mid); //getting the second half of the array
		
			mergeSort(first); //merge sort the first half 
			mergeSort(last); //merge sort the second half
			merge(first, last, list); //calling the merge method here to finally merge back the sorted arrays
			}
		}
	/**merge method - The merge method merges the two sorted arrays, list1 and list2 into an array, temp taken as the three arguments here.
	 * The method repeatedly compares the current elements from list1 and list2 and moves the smaller one to the temp. 
	 * Finally, all the elements in one of the lists are moved to temp. If there are still unmoved elements in list1, 
	 * copy them to temp. If there are still unmoved elements in list2, copy them to temp.
	 * **/
	public static  void merge(int[] list1, int[] list2, int[] temp) {
		int index1 = 0, index2 = 0, index3 = 0; //current indexes of list1, list2 and temp arrays respectively, points to the current element to be considered here.
		while (index1 < list1.length && index2 < list2.length) { //when the index of the first array, list1 is less than it's length and the index of the second array list2 is less than its length, keep going
			numMerge++; //we increment our counter variable here for counting the number of operations as we are doing the comparisons
			if(list1[index1] < list2[index2]) { //if the first element of the list1 is less than the first element of list2,
				temp[index3++] = list1[index1++]; //store the first element of the temp to be the first element of list1
				numMerge++;//we increment the counter variable for counting the number of operations here
			}
			else { //otherwise if the first element of the list2 is smaller than the first element of the list1,
				temp[index3++] = list2[index2++]; //store the first element of the temp to be the first element of the list2
				numMerge++; //we increment the counter variable here
			}
		}
		while(index1 < list1.length) { //we use this if we reach the end of second list, 
			temp[index3++] = list1[index1++]; //so we copy the remaining element of the first array list1 at the end of the temp array
			numMerge++; //we increment the counter here as well as we are copying the remainder here
		}
		while(index2 < list2.length) {//we use this if we reach the end of first list,
			temp[index3++] = list2[index2++]; //so we copy the remaining element of the second array list2 at the end of the temp array
			numMerge++; //we increment the counter here as well as we are copying here
		}
	}
	
	/** There are two overloaded quick sort methods here, 
	 * the first is used to sort an array and the second is a helper method that sorts the partial array with a specified range
	 */
	/** quick sort method - This is the first quick sort method that is using the second, quick sort method as a helper method for the quick sort algorithm
	 * It takes one argument as the list, that is to be sorted here
	 * @param list
	 */
			public static void quickSort(int[] list) {
				quickSort(list, 0, list.length-1); //helper method being called here for sorting the array 
			}
			
			/**The helper method for quick sort algorithm 
			 * There are three arguments here, the list to be sorted, indexes used as first and last
			 * @param list
			 * @param first
			 * @param last
			 */
			public static void quickSort(int[] list, int first, int last) {
				if (last > first) { //if the last element is greater than the first element, we do the partition and apply quick sort recursively
					int pivotIndex = partition(list, first, last); //stores the index of the pivot, after the partition is complete we get the correct position of the pivot
					quickSort(list, first, pivotIndex-1); //recursively call the quick sort on the first half of the partitioned array
					quickSort(list, pivotIndex+1, last); //recursively call the quick sort on the second half of the partitioned array
				}
			}
			/** The partition method partitions the array list using the pivot. It takes the three arguments, 
			 * list, the array to be partitioned, the first element and the last element in the array. 
			 * The method returns the new index for the pivot that divides the partial array into two parts if the pivot has been moved.
			 * Otherwise it returns the original index for the pivot. **/
			public static int partition(int[] list, int first, int last) {
				int pivot = list[first]; //choose the first element as the pivot
				int low = first + 1; //index for forward search, initially, low points to the second element in the partial array 
				int high = last; //index for backward search, high points to the last element in the partial array initially
				
				while (high > low) {//keep searching until the high is no longer greater than low					
					while (low <= high && list[low] <= pivot) //search forward in the array for the first element that is greater than the pivot
					{
						numQuick++;//we increment our counter for the quick sort
						low++; //advance the index low forward to find the first value greater than the pivot
					}
					//search backward from right for the first element in the array that is less than or equal to the pivot
					while (low <= high && list[high] > pivot) 
					{
						numQuick++; //we increment our counter for the quick sort
						high--; //move the index high backward to find the first value less than or equal to the pivot
					}
					//now swap these two elements, high and low in the list
					if (high > low) { 
						int temp3 = list[high]; //store the element at index high in a temporary variable
						list[high] = list[low]; //make the element at the index high as the element at the low index
						list[low] = temp3;  //then reset the element at the index at low to be the element at the high index
					}
				}
				while (high > first && list[high] >= pivot) //we decrement high until high is no longer greater than the first and the element at index high is no longer greater than the pivot
				{
					numQuick++; //we increment our counter variable here
					high--;  //we decrement the high index here
				}
					
				//swap pivot with list[high]
				if (pivot > list[high]) { //if pivot is greater than the element at the index high, we swap
					numQuick++;  //we increment our counter variable here
					list[first] = list[high]; //set our the element at first index to be the element at the high index
					list[high] = pivot; //set the element at high index to be the pivot
					return high; //we return the high index 
				}
				else {  //otherwise if the pivot is less than the element at the index high, we return the first index
					numQuick++; //we increment our counter variable here as well
					return first;
				}
			}
}
