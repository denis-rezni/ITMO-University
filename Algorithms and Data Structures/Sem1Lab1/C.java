import java.util.Arrays;
import java.util.Scanner;

public class C {
	static long inv = 0;

	public static int[] merge(int a[], int b[]) {
		int c[] = new int[a.length + b.length];
		int i = 0;
		int j = 0;
		while (i + j < c.length) {
			if (j == b.length || (i < a.length && a[i] <= b[j])) {
				c[i + j] = a[i];
				i++;
			} else {
				if (i < a.length && a[i] != b[j]) {
					inv += a.length - i;
				}
				c[i + j] = b[j];
				j++;
			}
		}
		return c;
	}

	public static int[] mergeSort(int a[]) {
		int n = a.length;
		if (n <= 1) {
			return a;
		}
		int[] left = Arrays.copyOfRange(a, 0, n / 2);
		int[] right = Arrays.copyOfRange(a, n / 2, n);
		left = mergeSort(left);
		right = mergeSort(right);
		return merge(left, right);
	}

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int arr[] = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = in.nextInt();
		}
		mergeSort(arr);
		System.out.println(inv);

		in.close();
	}
}
