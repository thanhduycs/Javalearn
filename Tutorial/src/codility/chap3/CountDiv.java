package codility.chap3;

//https://codility.com/demo/results/demoEAGV8T-9X2/
public class CountDiv {
	public int solution(int A, int B, int K) {
        return B/K - (int)Math.floor((A-1)/(double)K);
    } 
	
	public static void main(String[] args) {
		CountDiv countdiv = new CountDiv();
		
		System.out.println(countdiv.solution(0, 1, 11));
	}
}
