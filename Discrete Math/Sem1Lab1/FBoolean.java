import java.util.Scanner;

public class FBoolean {
	
	public static String toBinary(int i, int n) {
		StringBuilder s = new StringBuilder();
		for (int j = 0; j < n - Integer.toBinaryString(i).length(); j++) {
			s.append("0");
		}
		s.append(Integer.toBinaryString(i));
		return s.toString();
	}
	
	public static int[] triangle(int arr[]) {
		int res[] = new int[arr.length];
		for(int i = 0; i < arr.length; i++) {
			res[i] = arr[0];
			for(int j = 1; j < arr.length - i; j++) {
				arr[j-1] = arr[j-1] ^ arr[j];
			}
		}
		return res;
	}
	
	public static void main (String args[]) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int arr[] = new int[1 << n];
		for(int i = 0; i < (1 << n); i++) {
			in.next();
			arr[i] = in.nextInt();
		}
		arr = triangle(arr);
		for(int i = 0; i < 1 << n; i++) {
			System.out.println(toBinary(i, n) + " " + arr[i]);
		}
		in.close();
	}
}
