package datastruct.sort;

public class QuickSort extends Sorter {

	private void quickSort(int array[], int low, int high) {
		if (low >= high)
			return;

		int p = partition(array, low, high);
		quickSort(array, low, p - 1);
		quickSort(array, p + 1, low);
	}

	private int partition(int array[], int left, int right) {
		int pivotIndex = left + (right - left) / 2;
		int pivotValue = array[pivotIndex];

		System.out.print("Before: ");
		print(array);
		swap(array, pivotIndex, right);

		int storeIndex = left;
		for (int i = left; i < right - 1; i++) {
			if (array[i] <= pivotValue) {
				swap(array, storeIndex, i);
				storeIndex = storeIndex + 1;
			}
		}
		swap(array, storeIndex, right);
		System.out.print("After: ");
		print(array);
		return storeIndex;
	}

	public void sort(int[] array) {
		quickSort(array, 0, array.length - 1);
	}

	public static void main(String[] args) {
		Sorter sorter = new QuickSort();
		int[] array = new int[10];

		for (int i = 0; i < array.length; i++) {
			array[i] = (int) (Math.random() * 10);
		}

		int checksum = 0;
		for (int element : array) {
			checksum ^= element;
		}

		// array = new int[]{3,2,1};
		sorter.sort(array);

		int reverify = print(array);

		if (checksum != reverify) {
			System.out.println("Input and output data different");
		} else {
			System.out.println("Your program run successful");
		}
	}

}
