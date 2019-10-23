import java.util.Random;

public class CBooleanStressTest {

	public static boolean isZeroPreserving(int results[]) {
		return (results[0] == 0);
	}

	public static boolean isOnePreserving(int results[]) {
		return (results[results.length - 1] == 1);
	}

	public static boolean isSelfDual(int results[]) {
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

	private static boolean isZeroPreservingAndrew(int[] f, int n) {
		return f[0] == 0;
	}

	private static boolean isOnePreservingAndrew(int[] f, int n) {
		return f[(1 << n) - 1] == 1;
	}

	private static boolean isSelfDualAndrew(int[] f, int n) {
		for (int i = 0; i < (1 << n); i++) {
			if (f[i ^ ((1 << n) - 1)] == f[i]) {
				return false;
			}
		}
		return true;
	}

	private static boolean isBiggerAndrew(int a, int b, int n) {
		for (int i = 0; i < n; i++) {
			if (((a >> i) & 1) > ((b >> i) & 1)) {
				return true;
			}
		}
		return false;
	}

	private static boolean isMonotonousAndrew(int[] f, int n) {
		for (int i = 0; i < (1 << n); i++) {
			for (int j = i + 1; j < (1 << n); j++) {
				if (!isBiggerAndrew(i, j, n)) {
					if (f[i] > f[j]) {
						return false;
					}
				}
			}
		}
		return true;
	}

	private static boolean isLinearAndrew(int[] table, int n) {
		int arg = 1 << n;
		int[][] triangle = new int[arg][arg];
		System.arraycopy(table, 0, triangle[0], 0, arg);
		for (int i = 1; i < arg; i++) {
			for (int j = 0; j < arg; j++) {
				if (i + j < arg) {
					triangle[i][j] = (triangle[i - 1][j] ^ triangle[i - 1][j + 1]);
				}
			}
		}
		int[] result = new int[arg];
		for (int i = 0; i < arg; i++) {
			result[i] = triangle[i][0];

		}
		for (int i = 0; i < (1 << n); i++) {
			if ((i & (i - 1)) != 0 && result[i] == 1) {
				return false;
			}
		}
		return true;
	}

	public static String randomString(int n, Random r) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < 1 << n; i++) {
			if (r.nextBoolean()) {
				b.append(0);
			} else {
				b.append(1);
			}
		}
		return b.toString();
	}

	public static String myIsFull(int seed) {
		Random r = new Random(seed);
		boolean[] isFull = new boolean[5];
		int functions = r.nextInt(1000) + 1;
		for (int i = 0; i < functions; i++) {
			int n = r.nextInt(5);
			int[] results = new int[(1 << n)];
			String func = randomString(n, r);
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
			return ("YES");
		} else {
			return ("NO");
		}
	}

	public static String andrewIsFull(int seed) {
		Random r = new Random(seed);
		int totalFunctions = r.nextInt(1000) + 1;
		boolean hasNotZeroPr = false;
		boolean hasNotOnePr = false;
		boolean hasNotSelfDual = false;
		boolean hasNotMon = false;
		boolean hasNotLin = false;
		for (int q = 0; q < totalFunctions; q++) {

			int n = r.nextInt(5);
			String s = randomString(n, r);
			int[] f = new int[1 << n];
			for (int i = 0; i < (1 << n); i++) {
				f[i] = s.charAt(i) - '0';
			}
			if (!isZeroPreservingAndrew(f, n)) {
				hasNotZeroPr = true;
			}
			if (!isOnePreservingAndrew(f, n)) {
				hasNotOnePr = true;
			}
			if (!isSelfDualAndrew(f, n)) {
				hasNotSelfDual = true;
			}
			if (!isMonotonousAndrew(f, n)) {
				hasNotMon = true;
			}
			if (!isLinearAndrew(f, n)) {
				hasNotLin = true;
			}
		}
		boolean isFull = hasNotZeroPr && hasNotOnePr && hasNotSelfDual && hasNotMon && hasNotLin;
		return (isFull ? "YES" : "NO");
	}

	public static void main(String args[]) {
		Random r = new Random(2);
		for (int tries = 0; tries < 100000; tries++) {
			int seed = r.nextInt();
			String my = myIsFull(seed);
			String andrew = andrewIsFull(seed);
			if(!my.equals(andrew)) {
				System.out.println(tries + " fail.");
			}

		}
	}
}
