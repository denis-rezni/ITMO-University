import java.util.Scanner;

public class CBoolean {
	public static boolean isZeroPreserving(int results[]) {
		return (results[0] == 0);
	}

	public static boolean isOnePreserving(int results[]) {
		return (results[results.length - 1] == 1);
	}

	public static boolean isSelfDual(int results[]) {
		if(results.length == 1) {
			return false;
		}
		boolean counter = true;
		for (int i = 0; i < results.length / 2; i++) {
			if (results[i] == results[results.length - i - 1]) {
				counter = false;
				break;
			}
		}
		return counter;
	}

	public static boolean isGreater(int prev, int next) {
		boolean counter = true;
		for (int i = 0; i < Integer.toBinaryString(((prev > next) ? prev : next)).length(); i++) {
			if (((prev >> i) & 1) > ((next >> i) & 1)) {
				counter = false;
				break;
			}
		}
		return counter;
	}

	public static boolean isMonotonous(int results[]) {
		boolean counter = true;
		a: for (int i = 0; i < results.length - 1; i++) {
			for (int j = i + 1; j < results.length; j++) {
				if (isGreater(i, j)) {
					if (results[i] > results[j]) {
						counter = false;
						break a;
					}
				}
			}
		}
		return counter;
	}

	public static boolean isLinear(int results[]) {
		boolean counter = true;
		int n = Integer.toBinaryString(results.length).length() - 1;
		for (int i = 0; i < (1 << n + 1); i++) {
			counter = true;
			a: for (int j = 0; j < results.length; j++) {
				int trueRes = results[j];
				int checking = i & 1;
				for (int k = 1; k < results.length + 1; k++) {
					int a = (i >> k) & 1;
					int x = (j >> (k - 1)) & 1;
					checking = checking ^ (a & x);
				}
				if (checking != trueRes) {
					counter = false;
					break a;
				}
			}
			if (counter) {
				return true;
			}
		}
		return false;
	}
	
	public static String toBinary(int i, int n) {
		StringBuilder s = new StringBuilder();
		for (int j = 0; j < (1 << n) - Integer.toBinaryString(i).length(); j++) {
			s.append("0");
		}
		s.append(Integer.toBinaryString(i));
		return s.toString();
	}
	public static String isFull(boolean [] isFull) {
		if (isFull[0] && isFull[1] && isFull[2] && isFull[3] && isFull[4]) {
			return("YES");
		} else {
			return("NO");
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean[] isFull = new boolean[5];
		int functions = in.nextInt();
		for (int i = 0; i < functions; i++) {
			int n = in.nextInt();
			int[] results = new int[(1 << n)];
			String func = in.next();
			for (int j = 0; j < (1 << n); j++) {
				results[j] = Character.getNumericValue(func.charAt(j));
			}
			if (!isZeroPreserving(results)) {
				isFull[0] = true;
			}
			if (!isOnePreserving(results)) {
				isFull[1] = true;
			}
			if (!isSelfDual(results)) {
				isFull[2] = true;
			}
			if (!isMonotonous(results)) {
				isFull[3] = true;
			}
			if (!isLinear(results)) {
				isFull[4] = true;
			}
		}
		if (isFull[0] && isFull[1] && isFull[2] && isFull[3] && isFull[4]) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
		in.close();
	}

}