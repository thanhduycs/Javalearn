package codility.chap8;

public class Theory {
	
	public int coin(int n)
	{
		int result = 0;
		int[] coins = new int [n+1];
		
		for (int i = 1; i <= coins.length; i++) {
			int k = i;
			while(k <= n) {
				coins[k] = (coins[k] + 1) %2;
				k += i;
			}
			result += coins[i];
		}
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
