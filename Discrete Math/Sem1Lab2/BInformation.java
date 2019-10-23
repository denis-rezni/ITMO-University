import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BInformation {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("bwt.in"));
		PrintWriter out = new PrintWriter(new File("bwt.out"));
		String s = in.next();
		int n = s.length();
		List<String> list = new ArrayList<>();
		list.add(s);
		StringBuilder b = new StringBuilder();
		for(int i = 1; i < n; i++) {
			b.append(list.get(i-1).charAt(n-1));
			for(int j = 1; j < n; j++) {
				b.append(list.get(i-1).charAt(j-1));
			}
			list.add(b.toString());
			b.delete(0, b.length());
		}
		Collections.sort(list);
		for(int i = 0; i < n; i++) {
			String cur = list.get(i);
			b.append(list.get(i).charAt(cur.length() - 1));
		}
		out.println(b.toString());
		out.close();
		in.close();
	}

}
