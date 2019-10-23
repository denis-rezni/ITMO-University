import java.util.Scanner;
 
public class H {
	public static long binarySeacrhSize(long w, long h, long n) {
		long l = Math.min(w, h);
        long r = n * Math.max(w, h);
        while (l + 1 < r) {
            long m = l + (r - l) / 2;
            if (n <= (m / w) * (m / h)) {
                r = m;
            } else {
                l = m;
            }
        }
        return r;
	}
	
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long w = in.nextInt();
        long h = in.nextInt();
        long n = in.nextInt();
        System.out.println(binarySeacrhSize(w, h, n));
 
        in.close();
    }
}