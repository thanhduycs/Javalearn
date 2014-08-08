package datastruct.list.linkedlist;

public class SingleLinkedList<T> {
	private DataNode<T> root;
	private int size = 0;
	
	public void put(T value)
	{
		if (root == null)
		{
			root = new DataNode<T>(value);
			return;
		}
		
		DataNode<T> node = root;
		while(node.getNext() != null)
			node = node.getNext();
		node.setNext(new DataNode<T>(value));
		size++;
	}
	
	public void removeAt(int index)
	{
		if (index == 0)
		{
			root = root.getNext();
			return;
		}
		DataNode<T> node = root;
		for (int i=0; i<index-1; i++)
		{
			node = node.getNext();
		}
		
		node.setNext(node.getNext().getNext());
	}
	
	public int remove(final T value)
	{
		DataNode<T> parent = root;
		DataNode<T> node = root.getNext();
		int count = 0;
		while(node != null)
		{
			if (node.getValue().equals(value) )
			{
				parent.setNext(node.getNext());
				node = parent.getNext();
				count++;
				size--;
				continue;
			}
			parent = node;
			node = node.getNext();
		}
		if (root.getValue().equals(value))
		{
			root = root.getNext();
		}
		return count;
	}
	
	public void printAll()
	{
		DataNode<T> node = root;
		while(node != null) 
		{
			System.out.print(node.getValue() + ", ");
			node = node.getNext();
		}
		System.out.println();
	}
}
