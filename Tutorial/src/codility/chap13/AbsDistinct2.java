//https://codility.com/demo/results/demoWDHZRU-3RR/

package codility.chap13;

public class AbsDistinct2 {
    public int solution(int[] A) {
        int left = 0;
        int right = A.length - 1;
        
        int mark = A[0] - 1;
        int distinct = 0;

        while (left <= right) {
            int lf_val = Math.abs(A[left]);
            int rt_val = Math.abs(A[right]);

            if (lf_val >= rt_val) {
                if(lf_val != mark) {
                    //System.out.println("lf:" + lf_val);
                    mark = lf_val;
                    distinct++;
                }
                left++;
            } else {
                if(rt_val != mark) {
                    //System.out.println("rt:" + rt_val);
                    mark = rt_val;
                    distinct++;
                }
                right--;
            }
        }
        //System.out.println("distinct = " + distinct);
        return distinct;
    }
}
