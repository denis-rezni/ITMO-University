import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class A {
	FastScanner in;
	PrintWriter out;
	boolean systemIO = true;

	public void solve() throws IOException {
		
		int n = in.nextInt();
		ArrayList<Pair> stack = new ArrayList<>();
		int cmd;
		int toAdd;
		for(int i = 0; i < n; i++) {
			cmd = in.nextInt();
			if(cmd == 1) {
				toAdd = in.nextInt();
				if(stack.size() != 0) {
					stack.add(new Pair(toAdd, Math.min(toAdd, stack.get(stack.size() - 1).min)));
				} else {
					stack.add(new Pair(toAdd, toAdd));
				}
			} else if(cmd == 2) {
				stack.remove(stack.size() - 1);
			} else {
				out.println(stack.get(stack.size() - 1).min);
			}
			
		}

	}

	public void run() {
		try {
			if (systemIO) {
				in = new FastScanner(System.in);
				out = new PrintWriter(System.out);
			} else {
				in = new FastScanner(new File("segments.in"));
				out = new PrintWriter(new File("segments.out"));
			}
			solve();

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public class Pair {
		int val;
		int min;

		public Pair(int val, int min) {
			this.val = val;
			this.min = min;
		}
	}

	class FastScanner {
		BufferedReader br;
		StringTokenizer st;

		FastScanner(File f) {
			try {
				br = new BufferedReader(new FileReader(f));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		FastScanner(InputStream f) {
			br = new BufferedReader(new InputStreamReader(f));
		}

		String nextLine() {
			try {
				return br.readLine();
			} catch (IOException e) {
				return null;
			}
		}

		String next() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		long nextLong() {
			return Long.parseLong(next());
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

	}

	public static void main(String[] arg) {
		new A().run();
	}
}
