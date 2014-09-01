//https://codility.com/demo/results/demo3MGZXA-28B/

package codility.chap14;

public class TieRopes {
    public int solution(int K, int[] A) {
        int count  = 0;
        int rope_length = 0;
        
        for(int i=0; i<A.length; i++)
        {
            rope_length += A[i];
            if (rope_length >= K) {
                rope_length = 0;
                count ++;
            }
        }
        return count;
    }
}
