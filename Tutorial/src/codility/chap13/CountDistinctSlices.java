//https://codility.com/demo/results/demoDEP9QK-TW4/

package codility.chap13;

public class CountDistinctSlices {
    public int solution(int M, int[] A) {
        int result = 0;
        int[] stats = new int[M + 1];
        
        int z = 0;
        for(int i=0; i<A.length; i++) {
            while(z < A.length && stats[A[z]] == 0) {
                stats[A[z]]++;
                z++;
            }
            result += z - i;
            stats[A[i]]--;
        }
        return result;
    }
}
