package ds.generate;

public class GenPermutationNK {
	public void gen(final int N, final int K) {
		int[] arr = new int[K];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i + 1;
		}

		GenCombinations gc = new GenCombinations();
		GenPermutation gp = new GenPermutation();

		boolean hasNext = true;
		int count = 0;
		while (hasNext) {
			int[] copies = arr.clone();
			boolean hasNext2 = true;
			while (hasNext2) {
				System.out.println(count++ + ": " + GenCombinations.join(copies));
				hasNext2 = gp.next(copies);
			}
			hasNext = gc.next(arr, N);
		}
	}

	public static void main(String[] args) {
		new GenPermutationNK().gen(5, 3);
	}
}
