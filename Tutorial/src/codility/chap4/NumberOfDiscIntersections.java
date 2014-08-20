package codility.chap4;

import static org.junit.Assert.*;

import org.junit.Test;

//https://codility.com/demo/results/demo7VTZHC-XTT/
public class NumberOfDiscIntersections {

	class Disc2D {
		public long startPoint;
		public long endPoint;
		public int orgIndex;
		public int radius;

		public Disc2D(int orgIndex, int radius) {
			this.orgIndex = orgIndex;
			this.radius = radius;
			this.startPoint = (long) orgIndex - (long) radius;
			this.endPoint = (long) orgIndex + (long) radius;
		}

		public String toString() {
			return String
					.format("[%d, %d, %d]", startPoint, endPoint, orgIndex);
		}
	}

	class Disc2DComparator implements java.util.Comparator<Disc2D> {
		public int compare(Disc2D o1, Disc2D o2) {
			return ((Long) o1.startPoint).compareTo(o2.startPoint);
		}
	}

	public int binarySearch(Disc2D[] discs, final long searchEndPoint,
			int istart, int iend) {
		if (iend - istart < 2) {
			return iend;
		}

		int imiddle = (istart + iend) / 2;
		long startPointOfMiddle = discs[imiddle].startPoint;

		if (searchEndPoint >= startPointOfMiddle) {
			return binarySearch(discs, searchEndPoint, imiddle, iend);
		} else {
			return binarySearch(discs, searchEndPoint, istart, imiddle);
		}
	}

	public int binarySearch(Disc2D[] discs, final long searchEndPoint) {
		return binarySearch(discs, searchEndPoint, 0, discs.length);
	}

	public void printArr(Disc2D[] discs) {
		System.out.print("{ ");
		for (Disc2D d : discs)
			System.out.print(d + ", ");
		System.out.println("}");
	}

	public int solution(int[] A) {
		Disc2D[] discs = new Disc2D[A.length];

		for (int i = 0; i < A.length; i++) {
			discs[i] = new Disc2D(i, A[i]);
		}

		java.util.Arrays.sort(discs, new Disc2DComparator()); // mergeSort

		int totalIntersect = 0;
		for (int i = 0; i < A.length - 1; i++) {
			Disc2D d = discs[i];
			// find the first position of disc which doesn't intersect with
			// current disc.
			// It mean: current.endPoint < firstNonIntersectDisk.startPoint

			int pos = binarySearch(discs, d.endPoint);
			int numIntersectToEnd = pos - i - 1;
			totalIntersect += numIntersectToEnd;
			
			if (totalIntersect > 10000000)
				return -1;
//			System.out.println(numIntersectToEnd);
		}
		// printArr(discs);
		return totalIntersect;
	}
	
	@Test
	public void testCase1() throws Exception {
		int[] arr = new int[]{1, 5, 2, 1, 4, 0};
		int result = solution(arr);
		assertEquals(11, result);
	}
}
