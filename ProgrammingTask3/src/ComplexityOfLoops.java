import java.util.*;

public class ComplexityOfLoops
{
	public static void main(String[] args)
	{
		Scanner in=new Scanner(System.in);
		System.out.print("Enter n:  ");
		int n = in.nextInt();
		int count=0;
		System.out.println("\nCounting loop body instructions:\n");
		for(int i=0;i<n;i++)
			count++;
		System.out.println("For single loop, count = " + count);
		count=0;
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				count++;
		System.out.println("For nested loop, count = " + count);
		count=0;
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				for(int k=0;k<n;k++)
					count++;
		System.out.println("For three loops, count = " + count);
		count=0;
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				for(int k=0;k<n;k++)
					for(int l=0;l<n;l++)
						count++;
		System.out.println("For four loops, count = " + count);
		System.out.println("\nCounting every instruction:\n");
		count=1;					// i = 0
		for(int i=0;i<n;i++)
		{
			count+=3;				// i<n, i++, branch to top of loop
		}
		System.out.println("For single loop, count = " + count);
		count=1;					// i = 0
		for(int i=0;i<n;i++)
		{
			count+=4;				// i<n, i++, j=0 to start the next loop, branch
			for(int j=0;j<n;j++)
			{
				count+=3;			// j<n, j++, branch
			}
		}
		System.out.println("For nested loop, count = " + count);
		count=1;
		for(int i=0;i<n;i++)
		{
			count+=4;
			for(int j=0;j<n;j++)
			{
				count+=4;
				for(int k=0;k<n;k++)
				{
					count+=3;
				}
			}
		}
		System.out.println("For three loops, count = " + count);
		count=1;
		for(int i=0;i<n;i++)
		{
			count+=4;
			for(int j=0;j<n;j++)
			{
				count+=4;
				for(int k=0;k<n;k++)
				{
					count+=4;
					for(int l=0;l<n;l++)
					{
						count+=3;
					}
				}
			}
		}
		System.out.println("For four loops, count = " + count);
	}
}
