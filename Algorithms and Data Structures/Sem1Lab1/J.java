import java.util.Locale;
import java.util.Scanner;

public class J {
	static int fieldSpeed;
	static int forestSpeed;
	static double border;

	public static double ternarySearch() {
		double l = 0;
		double r = 1;
		double a;
		double b;
		for (int i = 0; i < 80; i++) {
			a = l + (r - l) / 3;
			b = l + 2 * (r - l) / 3;
			if (time(a) < time(b)) {
				r = b;
			} else {
				l = a;
			}
		}
		return r;
	}

	public static double time(double x) {
		return Math.sqrt((1 - border) * (1 - border) + x * x) / fieldSpeed
				+ Math.sqrt((1 - x) * (1 - x) + border * border) / forestSpeed;
	}

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		in.useLocale(Locale.US);
		fieldSpeed = in.nextInt();
		forestSpeed = in.nextInt();
		border = in.nextDouble();
		System.out.println(ternarySearch());
		in.close();
	}
}
