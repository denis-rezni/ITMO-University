import java.util.ArrayList;
import java.util.Scanner;

public class BBoolean {
	private static boolean hasOnes(ArrayList<ArrayList<Integer>> list) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).size() == 1) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int closes = in.nextInt();
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		for(int i = 0; i < closes; i++) {
			list.add(new ArrayList<>());
		}
		for (int i = 0; i < closes; i++) {
			for (int j = 1; j < n + 1; j++) {
				int ind = in.nextInt();
				if (ind == 1) {
					list.get(i).add(j);
				} else if (ind == 0) {
					list.get(i).add(-j);
				}
			}
		}
		boolean isZero = false;
		loop: while (hasOnes(list)) {
			int index = 0;
			while (list.get(index).size() != 1) {
				index++;
			}
			int variable = list.get(index).get(0);
			for (int i = 0; i < closes; i++) {
				ArrayList<Integer> cur = list.get(i);
				if (cur.size() != 0) {
					if (cur.contains(-variable) && cur.size() == 1) {
						isZero = true;
						break loop;
					}
					if(cur.contains(variable)) {
						cur.clear();
					} 
					cur.remove((Integer)(-variable));
				}
			}
		}
		if (isZero) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}

		in.close();
	}

}
