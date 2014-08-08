package datastruct.list.linkedlist;

public class TestSingleLinkedList {

	public static void main(String[] args) {
		SingleLinkedList<Integer> singleLinkedList = new SingleLinkedList<Integer>();
		
		for (int i=0; i< 100; i++)
		{
			singleLinkedList.put(new Integer(i%10));
		}
		
		singleLinkedList.remove(3);
		singleLinkedList.remove(7);
		singleLinkedList.printAll();
	}

}
