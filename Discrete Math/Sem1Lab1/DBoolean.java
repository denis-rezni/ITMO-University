import java.util.Scanner;

public class DBoolean {

	public static String toBinary(long i, int n) {
		StringBuilder s = new StringBuilder();
		for (int j = 0; j < n - Long.toBinaryString(i).length(); j++) {
			s.append("0");
		}
		s.append(Long.toBinaryString(i));
		return s.toString();
	}

	public static int howManyOnes(String wanted) {
		int counter = 0;
		for (int i = 0; i < wanted.length(); i++) {
			if (wanted.charAt(i) == '1') {
				counter++;
			}
		}
		return counter;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		long nums[] = new long[n];
		for (int i = 0; i < n; i++) {
			nums[i] = in.nextLong();
		}
		String numStr[] = new String[n];
		for (int i = 0; i < n; i++) {
			numStr[i] = toBinary(nums[i], 64);
		}
		String str = in.next();
		long s = 0;
		try {
			s = Long.parseLong(str);
		} catch (NumberFormatException e) {
			System.out.println("Impossible");
			in.close();
			return;
		}
		if (s == 0) {
			System.out.println("1&~1");
			in.close();
			return;
		}
		String wanted = toBinary(s, 64);
		StringBuilder res = new StringBuilder();
		long or = 0;
		int onesCounter = 0;
		int ones = howManyOnes(wanted);
//		System.out.println("ones: " + ones);
		for (int i = 0; i < 64; i++) {
			if (wanted.charAt(i) == '1') {
				onesCounter++;
				long tempAnd = ~0;
				for (int k = 0; k < n; k++) {
					if (numStr[k].charAt(i) == '1') {
						res.append(k + 1);
						tempAnd &= nums[k];
					} else {
						res.append("~" + (k + 1));
						tempAnd &= ~nums[k];
					}
					if (k != n - 1) {
						res.append("&");
					}
				}
				if(ones != onesCounter) {
					res.append("|");
				}
				or |= tempAnd;
			}
		}
		if (or == s) {
			System.out.println(res.toString());
		} else {
			System.out.println("Impossible");
//			System.out.println("wanted: " + s + " (int)");
//			System.out.println("res: " + or + " and");
		}

		in.close();
	}

}
