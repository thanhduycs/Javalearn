package codility.chap6;

public class DominatorSolution {
	public int solution(int[] A) {
		int val = 0, count = 0;
		for (int i = 0; i < A.length; i++) {
			if (count == 0) {
				val = A[i];
				count++;
			} else if (val == A[i]) {
				count++;
			} else {
				count--;
			}
		}
		
		if (count == 0)
			return -1;

		int pos = -1;

		for (int i = 0; i < A.length; i++) {
			if (val == A[i]) {
				count++;
				if (pos == -1)
					pos = i;
			}
		}
		
		if (count <= A.length/2)
			return -1;
		
		return pos;
	}
}
