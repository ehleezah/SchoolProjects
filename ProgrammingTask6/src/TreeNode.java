//This is the tree node class that 
public class TreeNode {
	private int type = 0;
	private int data1;
	private int data2;
	private int data3;
	private TreeNode left;
	private TreeNode mid1;
	private TreeNode mid2;
	private TreeNode right;
	
	public TreeNode(int newData) {
		data1 = newData;
		data2 = -1;
		data3 = -1;
		left = null;
		mid1 = null;
		mid2 = null;
		right = null;
	}
	
	public TreeNode(int newData1, int newData2) {
		data1 = newData1;
		data2 = newData2;
		data3 = -1;
		left = null;
		mid1 = null;
		mid2 = null;
		right = null;
		type = 2;
	}
	
	public TreeNode(int newData1, int newData2, int newData3) {
		data1 = newData1;
		data2 = newData2;
		data3 = newData3;
		left = null;
		mid1 = null;
		mid2 = null;
		right = null;
		type = 3;
	}
	
	public TreeNode(int newData, TreeNode newLeft, TreeNode newMid1) {
		data1 = newData;
		data2 = -1;
		data3 = -1;
		left = newLeft;
		mid1 = newMid1;
		mid2 = null;
		right = null;
	}
	
	public TreeNode(int newData1, int newData2, TreeNode newLeft, TreeNode newMid1, TreeNode newMid2) {
		data1 = newData1;
		data2 = newData2;
		left = newLeft;
		mid1 = newMid1;
		mid2 = newMid2;
		right = null;
	}
	
	public TreeNode(int newData1, int newData2, int newData3, TreeNode newLeft, TreeNode newMid1, TreeNode newMid2, TreeNode newRight) {
		data1 = newData1;
		data2 = newData2;
		data3 = newData3;
		left = newLeft;
		mid1= newMid1;
		mid2 = newMid2;
		right = newRight;
	}

	public int getFirstDatum() {
		return data1;
	}
	
	public int getSecondDatum() {
		return data2;
	}
	
	public int getThirdDatum() {
		return data3;
	}
	
	public TreeNode getFirstChild() {
		return left;		
	}
	
	public TreeNode getSecondChild() {
		return mid1;
	}
	
	public TreeNode getThirdChild() {
		return mid2;
	}
	
	public TreeNode getFourthChild() {
		return right;
	}
	
	public void setFirstDatum(int newData1) {
		data1 = newData1;
	}
	
	public void setSecondDatum(int newData2) {
		data2 = newData2;
	}
	
	public void setThirdDatum(int newData3) {
		data3 = newData3;
	}
	
	public void setFirstChild(TreeNode newLeft) {
		left = newLeft;
	}
	
	public void setSecondChild(TreeNode newMid1) {
		mid1 = newMid1;
	}
	
	public void setThirdChild(TreeNode newMid2) {
		mid2 = newMid2;
	}
	
	public void setFourthChild(TreeNode newRight) {
		right = newRight;
	}
	
	public int getType() {
		if (type == 2) return 2;
		else if (type == 3) return 3;
		else return 4;
	}
}

