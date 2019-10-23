import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class K {
	static double m;
	static Random random = new Random();

	static class Jewel {
		int v;
		int w;
		int index;

		public Jewel(int v, int w, int i) {
			this.index = i;
			this.v = v;
			this.w = w;
		}

		public double coef(double m) {
			return (v - m * w);
		}
	}


	public static void findNonRecursive(Jewel arr[], int k) {
		int l = 0;
		int r = arr.length;
		while (true) {
			int mid = split(arr, l, r, arr[random.nextInt(r - l) + l].coef(m));
			if (mid == k) {
				return;
			} else if (k < mid) {
				r = mid;
			} else {
				l = mid + 1;
			}
		}
	}

	public static int split(Jewel arr[], int l, int r, double x) {
		int i = l;
		int j = r - 1;
		while (i <= j) {
			while (i <= j && arr[i].coef(m) < x) {
				i++;
			}
			while (i <= j && arr[j].coef(m) > x) {
				j--;
			}
			if (i >= j) {
				break;
			}
			Jewel temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			i++;
			j--;
		}
		return j;
	}

	public static double binarySearchKBest(Jewel arr[], int k) {
		double l = 0;
		double r = Integer.MAX_VALUE / 2;
		int n = arr.length;
		for (int i = 0; i < 80; i++) {
			m = (l + r) / 2;
			findNonRecursive(arr, n - k);
			double sum = 0;
			for (int j = 1; j <= k; j++) {
				sum += arr[n - j].coef(m);
			}
			if (sum >= 0) {
				l = m;
			} else {
				r = m;
			}
		}
		return r;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("kbest.in"));
//		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(new File("kbest.out"));
		int n = in.nextInt();
		int k = in.nextInt();
		Jewel arr[] = new Jewel[n];
		for (int i = 0; i < n; i++) {
			arr[i] = new Jewel(in.nextInt(), in.nextInt(), i);
		}
//		double opt = binarySearchKBest(arr, k);
		binarySearchKBest(arr, k);
//		Arrays.sort(arr, (x, y) -> (y.v - opt * y.w) - (x.v - opt * x.w) < 0 ? 1 : -1);
		for (int i = 1; i <= k; i++) {
//			System.out.println(arr[n - i].index + 1);
			out.println(arr[n - i].index + 1);
		}
		out.close();
		in.close();
	}

}
