package datastruct.tree.binary;

public class BinaryTest {

	public static void main(String[] args) {
		BinaryTree<Integer> binaryTree = new BinaryTree<Integer>();
		
		int [] sample = {1,29,32,3,42,190,22,1,11,11};
		for (int n : sample)
		{
			Integer integer = new Integer(n);
			binaryTree.put(integer);
		}
		
		binaryTree.printAll();
	}
}
 