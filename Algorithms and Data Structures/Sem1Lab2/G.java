import java.io.*;
import java.util.*;

public class G {
	FastScanner in;
	PrintWriter out;
	boolean systemIO = true;

	public void solve() throws IOException {
		int players = in.nextInt();
		int req = in.nextInt();
		Player[] arr = new Player[players];
		for (int i = 0; i < players; i++) {
			arr[i] = new Player(0, i);
		}
		DSU sys = new DSU(arr);
		for (int i = 0; i < req; i++) {
			String cmd = in.next();
			if (cmd.equals("join")) {
				sys.join(in.nextInt() - 1, in.nextInt() - 1);
			} else if (cmd.equals("add")) {
				sys.add(in.nextInt() - 1, in.nextInt());
			} else {
				out.println(sys.getVal(in.nextInt() - 1));
			}
		}
	}

	public class DSU {
		Player arr[];
		int rank[];

		public DSU(Player arr[]) {
			this.arr = arr;
			this.rank = new int[arr.length];
		}

		public int getBoss(int x) {
			while (arr[x].parent != x) {
				x = arr[x].parent;
			}
			return x;
		}

		public void add(int x, int v) {
			arr[getBoss(x)].val += v;
		}

		public int getVal(int x) {
			int counter = 0;
			while (arr[x].parent != x) {
				counter += arr[x].val;
				x = arr[x].parent;
			}
			counter += arr[x].val;
			return counter;
		}

		public void join(int first, int second) {
			int firstBoss = getBoss(first);
			int secondBoss = getBoss(second);
			
			if (firstBoss != secondBoss) {
				if(rank[secondBoss] == rank[firstBoss]) {
					rank[firstBoss]++;
				}
				if (rank[firstBoss] < rank[secondBoss]) {
					arr[firstBoss].parent = secondBoss;
					arr[firstBoss].val -= arr[secondBoss].val;
				} else {
					arr[secondBoss].parent = firstBoss;
					arr[secondBoss].val -= arr[firstBoss].val;
				}
				
			}
		}

		public void printDSU() {
			for (int i = 0; i < arr.length; i++) {
				System.out.print(getBoss(i) + " (" + getVal(i) + " exp) ");
			}
			System.out.println();
		}
	}

	public class Player {
		int parent;
		int val;
		int added;

		public Player(int val, int parent) {
			this.parent = parent;
			this.val = val;
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
		new G().run();
	}
}