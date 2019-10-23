import java.util.Locale;
import java.util.Scanner;

public class I {
	public static double binarySearchReal(double c) {
		double l = 1;
		double r = c;
		double x;
		for (int i = 0; i < 80; i++) {
			x = (l + r) / 2;
			if(SqrtPlusSqr(x) > c) {
				r = x;
			}else {
				l = x;
			}
		}
		return l;
	}
	public static double SqrtPlusSqr(double x) {
		return (double) (Math.sqrt(x) + x*x);
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		in.useLocale(Locale.US);
		double c = in.nextDouble();
		System.out.println(binarySearchReal(c));
		in.close();
	}

}
