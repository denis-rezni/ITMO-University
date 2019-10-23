import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CInformation {

	public static void main(String[] args) throws FileNotFoundException {
//		Scanner in = new Scanner(System.in);
		Scanner in = new Scanner(new File("mtf.in"));
		PrintWriter out = new PrintWriter(new File("mtf.out"));
		int letterNumber = 26;
		List<Character> alphabet = new LinkedList<>();
		alphabet.add('a');
		for (int i = 1; i < letterNumber; i++) {
			alphabet.add((char) ('a' + i));
		}
		String s = in.next();
		int n = s.length();
		List<String> res = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			char ch = s.charAt(i);
			for (int j = 0; j < letterNumber; j++) {
				if (ch == alphabet.get(j)) {
					res.add((j + 1) + "");
					alphabet.add(0, alphabet.get(j));
					alphabet.remove(j + 1);
				}
			}
		}
		out.println(String.join(" ", res));
		out.close();
		in.close();
	}

}
