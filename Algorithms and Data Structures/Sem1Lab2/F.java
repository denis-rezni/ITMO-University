import java.io.*;
import java.util.*;

public class F {
	FastScanner in;
	PrintWriter out;
	boolean systemIO = true;

	public void solve() throws IOException {
		int n = in.nextInt();
		int counter = 1;
		ArrayStack st = new ArrayStack(new int[n]);
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < n; i++) {
			int toPush = in.nextInt();
			res.append("push\n");
			if (toPush == counter) {
				counter++;
				res.append("pop\n");
				while (st.size() != 0 && (toPush = st.pop()) == counter) {
					counter++;
					res.append("pop\n");
				}
			}
				st.push(toPush);
		}
		if(counter - 1 == n) {
			out.print(res.toString());
		} else {
			out.print("impossible");
		}
		
	}

	public class ArrayStack {
		int[] stack;
		int head;

		public ArrayStack(int[] arr) {
			this.stack = arr;
		}

		public void push(int x) {
			stack[head++] = x;
		}

		public int pop() {
			return stack[--head];
		}

		public int size() {
			return head;
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
		new F().run();
	}
}