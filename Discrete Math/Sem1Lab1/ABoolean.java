import java.util.Scanner;

public class ABoolean {
	static int n;

	private static int toIndex(int number) {
		if (number > 0) {
			return number + n - 1;
		} else {
			return number + n;
		}
	}

	private static int toNumber(int index) {
			return index - n;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		int closes = in.nextInt();
		int matrix[][] = new int[2 * n][2 * n];
		for (int i = 0; i < 2 * n; i++) {
			for (int j = 0; j < 2 * n; j++) {
				matrix[i][j] = Integer.MAX_VALUE / 2;
			}
		}
		for (int i = 0; i < closes; i++) {
			int first = in.nextInt();
			int second = in.nextInt();
			matrix[toIndex(-1 * first)][toIndex(second)] = 0;
			matrix[toIndex(-1 * second)][toIndex(first)] = 0;
		}
//		for (int i = 0; i < 2 * n; i++) {
//			for (int j = 0; j < 2 * n; j++) {
//				if (matrix[i][j] != 0) {
//					System.out.print(1 + " ");
//				} else {
//					System.out.print(0 + " ");
//				}
//			}
//			System.out.println();
//		}
		for (int k = 0; k < 2 * n; k++) {
			for (int i = 0; i < 2 * n; i++) {
				for (int j = 0; j < 2 * n; j++) {
					if (matrix[i][j] > matrix[i][k] + matrix[k][j]) {
						matrix[i][j] = matrix[i][k] + matrix[k][j];
					}
				}
			}
		}
		boolean flag = true;
		for (int i = 0; i < n; i++) {
//			System.out.println(toNumber(i));
			int posI = toIndex((toNumber(i) * -1));
			if ((matrix[posI][i] == 0) && (matrix[i][posI] == 0)) {
				System.out.println("YES");
				flag = false;
				break;
			}
		}
		if (flag) {
			System.out.println("NO");
		}
		in.close();
	}

}
