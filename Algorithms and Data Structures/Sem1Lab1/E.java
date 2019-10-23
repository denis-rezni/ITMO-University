import java.util.Arrays;
import java.util.Scanner;

public class E {
	public static int[] merge(int a[], int b[]) {
		int c[] = new int[a.length + b.length];
		int i = 0;
		int j = 0;
		while (i + j < c.length) {
			if (j == b.length || (i < a.length && a[i] < b[j])) {
				c[i + j] = a[i];
				i++;
			} else {
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
	public static int binarySearchLessThan(int arr[], int x) {
		int l = -1;
		int r = arr.length;
		while(l + 1 < r) {
			int mid = l + (r - l)/2;
			if(arr[mid] < x) {
				l = mid;
			}else {
				r = mid;
			}
		}
		return l + 1;
	}
	public static int binarySearchMoreThan(int arr[], int x) {
		int l = -1;
		int r = arr.length;
		while(l + 1 < r) {
			int mid = l + (r - l)/2;
			if(arr[mid] > x) {
				r = mid;
			}else {
				l = mid;
			}
		}
		return arr.length - r;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int arr[] = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = in.nextInt();
		}
		//TODO streams
		arr = mergeSort(arr);
		int req = in.nextInt();
		int l = 0;
		int r = 0;
		for(int i = 0; i < req; i++) {
			l = in.nextInt();
			r = in.nextInt();
			System.out.print((n - binarySearchLessThan(arr, l) - binarySearchMoreThan(arr, r)) + " ");
//			System.out.println((n - binarySearchLessThan(arr, l) - binarySearchMoreThan(arr, r)) + " ");
//			System.out.println(binarySearchLessThan(arr, l) + " less than");
//			System.out.println(binarySearchMoreThan(arr, r) + " more than");
		}

		in.close();	
	}

}
