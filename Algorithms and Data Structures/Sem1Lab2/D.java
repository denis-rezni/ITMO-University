import java.io.*;
import java.util.*;

public class D {
	FastScanner in;
	PrintWriter out;
	boolean systemIO = true;

	public void solve() throws IOException {
		int n = in.nextInt();
		LinkedQueue q = new LinkedQueue();
		int num;
		for (int i = 0; i < n; i++) {
			String sign = in.next();
			if (sign.equals("+")) {
				num = in.nextInt();
				q.push(new Node(num));
			} else if (sign.equals("*")) {
				num = in.nextInt();
				q.insertMid(new Node(num));
			} else {
				out.println(q.pop().num);
			}
		}
	}

	public class LinkedQueue {
		Node head;
		Node tail;
		int size;
		Node mid;

		public void push(Node node) {
			if (size != 0 && size % 2 == 0) {
				mid = mid.right;
			}
			if (size == 0) {
				head = node;
				mid = node;
			} else {
				node.left = tail;
				tail.right = node;
			}
			size++;
			tail = node;
		}

		public Node pop() {
			if (size % 2 == 0) {
				mid = mid.right;
			}
			size--;
			Node res = head;
			head = head.right;
			return res;
		}

		public void insertMid(Node node) {
			if (size == 0 || size == 1) {
				push(node);
			} else {
				Node right = mid.right;
				Node left = mid;
				left.right = node;
				right.left = node;
				node.left = left;
				node.right = right;
				if (size % 2 == 0) {
					mid = node;
				}
				size++;
			}
		}
	}

	public class Node {
		int num;
		Node left;
		Node right;

		public Node(int num) {
			this.num = num;
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
		new D().run();
	}
}