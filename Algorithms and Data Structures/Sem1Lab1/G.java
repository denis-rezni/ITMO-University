import java.util.Scanner;

public class G {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int x = in.nextInt();
		int y = in.nextInt();

		int l = 0;
		int r = (n - 1) * Math.max(x, y);

		long min = Math.min(x, y);
		long lists;

		while (l != r) {
			int time = (l + r) / 2;
			lists = time / x + time / y;
			if (lists < n - 1) {
				l = time + 1;
			} else {
				r = time;
			}
		}
		System.out.println(r + min);

		in.close();
	}
}