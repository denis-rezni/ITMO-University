import java.io.*;
import java.util.*;

public class C {
	FastScanner in;
	PrintWriter out;
	boolean systemIO = true;

	public void solve() throws IOException {
		int n = in.nextInt();
		ArrayQueue q = new ArrayQueue(new int[n], new HashMap<>());
		int cmd;
		for (int i = 0; i < n; i++) {
			cmd = in.nextInt();
			if (cmd == 1) {
				q.push(in.nextInt());
			} else if (cmd == 2) {
				q.pop();
			} else if (cmd == 3) {
				q.rageQuit();
			} else if (cmd == 4) {
				out.println(q.inFront(in.nextInt()));
			} else {
				out.println(q.whoIsTheFirst());
			}
		}

	}

	public class ArrayQueue {
		int head;
		int tail;
		int q[];
		HashMap<Integer, Integer> fromNumToIndex;

		public ArrayQueue(int arr[], HashMap<Integer, Integer> fromNumToIndex) {
			this.q = arr;
			this.fromNumToIndex = fromNumToIndex;
		}

		public void push(int x) {
			q[tail] = x;
			fromNumToIndex.put(x, tail);
			tail = (tail + 1) % q.length;
		}

		public void pop() {
			head = (head + 1) % q.length;
		}

		public void rageQuit() {
			tail = (tail - 1) % q.length;
		}

		public int inFront(int num) {
			int index = fromNumToIndex.get(num);
			if (head <= index) {
				return index - head;
			} else {
				return q.length - (head - index);
			}
		}

		public int whoIsTheFirst() {
			return q[head];
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
		new C().run();
	}
}