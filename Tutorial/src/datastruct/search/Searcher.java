package datastruct.search;

import datastruct.sort.merge.MergeSort;

public abstract class Searcher {
	protected int[] data;
	public int countStep = 0;

	public Searcher(int size, boolean inorder) {
		this.data = new int[size];
		this.generateData();
		if (inorder)
			this.sort();
	}

	public void generateData() {
		for (int i = 0; i < data.length; i++)
			data[i] = (int) (Math.random() * 100000);
	}

	public void sort() {
		new MergeSort().mergeSort(this.data);
	}

	public int getItem(int index) {
		try {
			return data[index];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(String.format(
					"ArrayIndexOutOfBoundsException: get %d but max is %d",
					index, data.length));
			throw e;
		}
	}

	public void printArr(int begin, int count, int sepcial) {
		for (int i = Math.max(begin,0); i < data.length && count > 0; i++, count--) {
			if (i == sepcial)
				System.out.print("*");
			System.out.print(getItem(i) + ", ");
		}
		System.out.println();
	}

	public abstract int search(int value);

}
