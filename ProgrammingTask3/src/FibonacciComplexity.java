/* This program illustrates the computational compmlexity of solving the fibonacci problem using
 * iteration, recursive and dynamic programming.  There are methods for each approach.  Each
 * method adds 1 to a specific counter to count the number of actual steps in finding the nth
 * fibonacci value using that approach.  The dynamic programming method is recursive but its
 * base case causes the method to stop recursing a lot sooner than the strict recursive version
 * such that the DP approach has a complexity much more like the iterative version than the
 * recusive version.  Note:  the program slows down greatly once n > 40.  If you increase
 * n, change recursiveCount from an int to a long.   */

public class FibonacciComplexity
{
	private static int iterativeCount, recursiveCount, dynamicProgrammingCount;		// counters, one per approach
	private static int[] dparray;													// the array used in the dynamic programming version to store previously computed values
	
	public static void main(String[] args)
	{
		dparray=new int[100];														// instantiate the array
		
		for(int i=3;i<40;i++)														// find the fib value for each i between 3 and 39
		{
			for(int j=3;j<100;j++) dparray[j]=-1;									// reset the array from whatever was computed for the last i, the array should be all -1 except
			dparray[0]=0;															// the first three elements which should be 0, 1, 1
			dparray[1]=1;
			dparray[2]=1;		
			iterativeCount=0;														// reset the three counters before starting with the new i
			recursiveCount=0;
			dynamicProgrammingCount=0;
			iterative(i);															// call the three methods, note we are not using the return values, we don't care what the
			recursive(i);															//    computed fib values were, we just want to count the number of steps taken
			dp(i);
			System.out.printf("%d\t%d\t%12d\t%d\n", i, iterativeCount, recursiveCount, dynamicProgrammingCount);	// output the counters
		}
	}
	
	public static int iterative(int n)					// iterative version is easiest, report n-2 times
	{
		int first=1, second=1, temp;
		while(n>2) {
			temp=first;
			first=first+second;
			second=temp;
			n--;
			iterativeCount++;
		}
		return first;
	}
	
	public static int recursive(int n)					// recursive version is easy to understand but very poor complexity
	{
		recursiveCount++;
		if(n<=2) return 1;
		else return recursive(n-1) + recursive(n-2);
	}
	
	public static int dp(int n)							// dynamic programming version will first check if dparray[n] has a value, if so then return it, otherwise
	{													//     recurse to get dp(n-1) and dp(n-2) and add them together, storing the result in dparray[n]
		dynamicProgrammingCount++;
		if(dparray[n]>-1) return dparray[n];
		else {
			dparray[n]=dp(n-1)+dp(n-2);
			return dparray[n];
		}
	}
}