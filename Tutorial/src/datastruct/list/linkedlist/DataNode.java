package datastruct.list.linkedlist;

public class DataNode <T> {
	private T value;
	private DataNode <T> next = null;
	
	public DataNode(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public DataNode<T> getNext() {
		return next;
	}

	public void setNext(DataNode<T> next) {
		this.next = next;
	}
	
	

}
