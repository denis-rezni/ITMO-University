import java.io.*;
import java.util.*;

public class E {
	FastScanner in;
	PrintWriter out;
	boolean systemIO = true;

	public void solve() throws IOException {
		String[] commands = in.nextLine().split(" ");
		ArrayStack stack = new ArrayStack(new Object[commands.length + 1]);
		for(String cmd : commands) {
			boolean pushedCmd = false;
			if(cmd.equals("+") || cmd.equals("-") || cmd.equals("*")) {
				stack.push(cmd.charAt(0));
				pushedCmd = true;
			} else {
				stack.push(Integer.parseInt(cmd));
			}
			if(pushedCmd) {
				char ch = (char)stack.pop();
				int s = (int)stack.pop();
				int f = (int)stack.pop();
				if(ch == '+') {
					stack.push(f + s);
				} else if (ch == '-') {
					stack.push(f - s);
				} else {
					stack.push(f * s);
				}
			}
		}
		System.out.println(stack.pop());
		
	}

	public class ArrayStack {
		Object[] stack;
		int head;
		
		public ArrayStack(Object[] arr) {
			this.stack = arr;
		}
		 public void push(Object o) {
			 stack[++head] = o;
		 }
		 
		 public Object pop() {
			 return stack[head--];
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
		new E().run();
	}
}