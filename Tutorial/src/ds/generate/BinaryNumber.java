package ds.generate;

public class BinaryNumber extends Generator {
	
	public int [] nextBinaryNum(final int [] orgArr)
	{
		int[] arr = orgArr.clone();
		
		int i = 0;
		for (i=arr.length-1; i >= 0 && arr[i] == 1;i--)
		{
			arr[i] = 0;
		}
		
		if (i < 0)
			return null;
		
		arr[i] = 1;
		
		return arr;
	}

	public static void main(String[] args) {
		int [] firstNum = new int[] {0, 0, 0, 0, 0, 0, 0, 0};
		int [] nextNum  = null;
		
		BinaryNumber binaryNumber = new BinaryNumber();
		
		nextNum = firstNum;
		for(int i=0; nextNum != null ;i++)
		{
			System.out.println(i + ": " + join(nextNum));
			nextNum = binaryNumber.nextBinaryNum(nextNum);
		}
		
		System.out.println("The total must: " + Math.pow(2, firstNum.length));
	}

}
