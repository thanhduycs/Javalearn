package codility.chap13;

public class TheoryCaterpillar {
    public boolean caterpillarMethod(int[] arr, int sum) {
        int front = 0, total = 0;
        for (int back = 0; back < arr.length; back++) {
            while (front < arr.length && total + arr[front] <= sum) {
                total += arr[front];
                front++;
            }

            if (total == sum) {
                return true;
            }
            total -= arr[back];
        }
        return false;
    }

    //O(n^2)
    public int trianglesExercise(int[] arr) {
        int result = 0;
        for (int x = 0; x < arr.length; x++) {
            int z = 0;
            for (int y = x + 1; y < arr.length; y++) {
                while (z < arr.length && arr[x] + arr[y] > arr[z])
                    z++;
                result += z - y - 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = { 6, 2, 7, 4, 1, 3, 6 };
        TheoryCaterpillar t = new TheoryCaterpillar();

        boolean r = t.caterpillarMethod(arr, 12);
        System.out.println(r);
    }
}
