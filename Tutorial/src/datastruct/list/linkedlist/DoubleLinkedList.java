package datastruct.list.linkedlist;

public class DoubleLinkedList<T> {
	
	//Imple
	private DoubleDataNode<T> head = null;
	private DoubleDataNode<T> tail = null;
	private int size = 0;
	
	public DoubleLinkedList()
	{
		
	}
	
	public void addLast(T value)
	{
		DoubleDataNode<T> node = tail;
		DoubleDataNode<T> newNode = new DoubleDataNode<T>(value);
		
		if (head == null)
		{
			head = newNode;
			tail = newNode;
			return;
		}
		
		newNode.setPre(node);
		node.setNext(newNode);
		
		tail = newNode;
		size++;
	}
	
	public void addFirst(T value)
	{
		DoubleDataNode<T> node = head;
		DoubleDataNode<T> newNode = new DoubleDataNode<T>(value);
		
		if (head == null)
		{
			head = newNode;
			tail = newNode;
			return;
		}
		
		node.setPre(newNode);
		newNode.setNext(node);
		
		head = newNode;
		size++;
	}
	
	public void insert(int index, T value)
	{
		
	}

}
