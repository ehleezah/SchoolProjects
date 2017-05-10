//This implements a driver class which generates random positive numbers 
//less than 10000 and inserts them into the 2-3-4 tree.
//After the insertions, it performs the preorder, inorder and postorder traversals.
//Finally it outputs the height of the tree.
//It does this for a tree of 10 random numbers,
//a tree of 50 random numbers and 
//a tree of 200 random numbers.

import java.util.Random;
public class DriverClass {
	public static void main(String[] args) {
		
		Random rand = new Random();
		
		System.out.println("A tree of 10 random numbers:");
		for (int i = 0; i < 10; i++) {
			int num = rand.nextInt(10000);
			System.out.println(num);
		}
	
		System.out.println("\nA tree of 50 random numbers:");
		for (int i = 0; i < 50; i++) {
			int num = rand.nextInt(10000);
			System.out.println(num);
		}
		
		System.out.println("\nA tree of 200 random numbers:");
		for (int i = 0; i < 200; i++) {
			int num = rand.nextInt(10000);
			System.out.println(num);
		}
	}

}
