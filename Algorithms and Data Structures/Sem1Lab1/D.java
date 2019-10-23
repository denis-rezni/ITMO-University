import java.util.ArrayList;
import java.util.Scanner;

public class D {

	public static void insert(int x, ArrayList<Integer> a) {
		a.add(x);
		int i = a.size() - 1;
		while (i > 0 && a.get((i - 1) / 2) < a.get(i)) {
			int temp = a.get((i - 1) / 2);
			a.set((i - 1) / 2, a.get(i));
			a.set(i, temp);
			i = (i - 1) / 2;
		}
	}

	public static int extract(ArrayList<Integer> a) {
		int res = a.get(0);
		a.set(0, a.get(a.size() - 1));
		a.remove(a.size() - 1);
		int i = 0;
		int n = a.size();
		while (2 * i + 1 < n) {
			int j = 2 * i + 1;
			if (2 * i + 2 < n && a.get(2 * i + 2) > a.get(j)) {
				j = 2 * i + 2;
			}
			if (a.get(j) > a.get(i)) {
				int temp = a.get(i);
				a.set(i, a.get(j));
				a.set(j, temp);
				i = j;
			} else {
				break;
			}
		}
		return res;
	}

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int req = in.nextInt();
		ArrayList<Integer> a = new ArrayList<Integer>();
		for(int i = 0; i < req; i++) {
			if(in.nextInt() == 0) {
			insert(in.nextInt(), a);	
			} else {
			System.out.println(extract(a));
			}
//			System.out.println(a.toString());
		}
		in.close();
	}
}
