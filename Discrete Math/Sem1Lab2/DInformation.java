import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class DInformation {
		
	public static boolean alphabetHasNext(HashMap<String, Integer> alphabet, String s, int left, int right) {
		return alphabet.containsKey(s.substring(left, right));
	}
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("lzw.in"));
//		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(new File("lzw.out"));
		String s = in.next();
		int sPointer = 0;
		HashMap<String, Integer> alphabet = new HashMap<>();
		for (int i = 0; i < 26; i++) {
			alphabet.put((char) ('a' + i) + "", i);
		}
		int alphCounter = 26;
		while (sPointer < s.length()) {
			int left = sPointer;
			int right = sPointer + 2;
			while(right < s.length() + 1 && alphabetHasNext(alphabet, s, left, right)) {
				right++;
			}
			out.print(alphabet.get(s.substring(left, right - 1)) + " ");
			if(right < s.length() + 1) {
				alphabet.put(s.substring(left, right), alphCounter++);
			}
			sPointer += right - left - 1;
		}

		out.close();
		in.close();
	}

}
