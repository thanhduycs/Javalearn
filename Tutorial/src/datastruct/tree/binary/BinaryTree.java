package datastruct.tree.binary;

public class BinaryTree<T extends Comparable<T>> {
	private BinaryNode<T> root;
	
	public void put(T value)
	{
		BinaryNode<T> newNode = new BinaryNode<T>(value);
		if (root == null) {
			root = newNode;
			return;
		}
		
		BinaryNode<T> parent = null;
		BinaryNode<T> current = root;
		while(current != null)
		{
			int cmpr = current.getValue().compareTo(value);
			if (cmpr <= 0){
				parent = current;
				current = current.getLeft();
				if (current == null) {
					 parent.setLeft(newNode);
				}
			} else {
				parent = current;
				current = current.getRight();
				if (current  == null) {
					 parent.setRight(newNode);
				}
			}
		}
	}
	
	public void printAll()
	{
		this.printNode(this.root);
	}
	
	private void printNode(BinaryNode<T> node)
	{
		if (node == null)
			return;
		printNode(node.getLeft());
		System.out.println(node.getValue());
		printNode(node.getRight());
		
	}
	
}
