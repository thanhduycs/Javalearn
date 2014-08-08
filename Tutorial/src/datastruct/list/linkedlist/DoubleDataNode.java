package datastruct.list.linkedlist;

public class DoubleDataNode<T> {
	private T value;
	private DoubleDataNode<T> next, pre;
	
	public DoubleDataNode(T value)
	{
		this.value = value;
	}
	
	
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	public DoubleDataNode<T> getNext() {
		return next;
	}
	public void setNext(DoubleDataNode<T> next) {
		this.next = next;
	}
	public DoubleDataNode<T> getPre() {
		return pre;
	}
	public void setPre(DoubleDataNode<T> pre) {
		this.pre = pre;
	}

	
}
