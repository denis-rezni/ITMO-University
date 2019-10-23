import java.util.Scanner;

public class F {
	public static int binarySearchClosest(int arr[], int x) {
		int l = 0;
		int r = arr.length - 1;
		while (l + 1 < r) {
			int m = l + (r - l) / 2;
			if (arr[m] >= x) {
				r = m;
			} else {
				l = m;
			}
		}
//		System.out.println(arr[l] + " l " + arr[r] + " r");
		if(Math.abs((long)arr[r] - (long)x) < Math.abs((long)arr[l] - (long)x)) {
			return arr[r];
		} else {
			return arr[l];
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int req = in.nextInt();
		int arr[] = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = in.nextInt();
		}
		int k;
		for (int i = 0; i < req; i++) {
			k = in.nextInt();
			System.out.println(binarySearchClosest(arr, k));
		}
		in.close();
	}

}
