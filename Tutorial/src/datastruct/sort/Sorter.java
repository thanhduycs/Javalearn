package datastruct.sort;

public abstract class Sorter implements SortInterface {

	protected static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public static void verifyInOrder(int[] array) {
		if (array.length < 2)
			return;

		int i = 0;
		for (i = 0; i < array.length - 1 && array[i] == array[i + 1]; i++)
			;

		if (i >= array.length)
			return;

		final boolean sign = array[i] < array[i + 1];

		for (i++; i < array.length-1; i++) {		
			if ((array[i] < array[i + 1]) != sign && array[i] != array[i + 1])
			{
				throw new RuntimeException("not in order");
			}
		}

	}

	public static int print(int[] array) {
		int c = 0;
		int checksum = 0;
		for (int element : array) {
			checksum ^= element;
			System.out.print(element + ", ");
			if (++c % 20 == 0) {
				System.out.println();
			}
		}
		System.out.println();
		return checksum;
	}

}
