package datastruct.sort;

public class BubbleSort extends Sorter {

	public void sort(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i] < array[j]) {
					swap(array, i, j);
				}
			}
		}

	}

}
