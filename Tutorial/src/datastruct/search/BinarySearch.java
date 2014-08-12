package datastruct.search;

public class BinarySearch extends Searcher {

	public BinarySearch(int size, boolean inorder) {
		super(size, inorder);
	}

	@Override
	public int search(int value) {
		this.countStep = 0;
		return searchRecursive(value, 0, this.data.length);
	}
	
	public int searchRecursive(int value, int begin, int end)
	{
		this.countStep++;
		if (end-begin < 2)
		{
			return -1;
		}
		int middle = (end+begin)/2;
		if (this.data[middle] == value)
		{
			while(middle >= 0 && this.data[middle] == value)
				middle--;
			return middle+1; //first
		} else if (value < this.data[middle])
		{
			return searchRecursive(value, begin, middle);
		}
		return searchRecursive(value, middle+1, end);
	}

	public static void main(String[] args) {
		BinarySearch searcher = new BinarySearch(10000, true);
		
		int findValue = searcher.getItem(100);
		int x = searcher.search(findValue);
		searcher.printArr(95, 15, x);
		System.out.println(findValue + " --> " + x);
	}

}
