// This program compares log n, n, n log n and n^2 for a number of values i

public class Complexity2
{
	public static void main(String[] args)
	{
		for(int i=1;i<=100000;i*=10)									// print out the values for n = 1, 10, 100, 1000, 10000, 100000
		{
			System.out.printf("%8d%8d%12d%20d\n", log(i), i, nlog(i), n2(i));
		}
	}
	
	public static int log(int n)										// for a given n, compute log n by continually dividing n by 2 and counting the number of times
	{																	// until n = 0
		int count=0;
		while(n>0) {
			count++;
			n=n/2;
		}
		return count;
	}
	
	public static int nlog(int n)										// for a given n, compute n * log n by doing the same activity as above but n times
	{
		int temp, count=0;
		for(int i=0;i<n;i++)
		{
			temp=i;
			while(temp>0) {
				count++;
				temp=temp/2;
			}
		}
		return count;
	}
	
	public static int n2(int n)											// use nested for loops to count up to n^2
	{
		int count=0;
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				count++;
		return count;
	}
}