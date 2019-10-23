import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class TaskG {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = false;

    public void solve() throws IOException {
        int n1 = in.nextInt();
        int m1 = in.nextInt();
        int k1 = in.nextInt();
        boolean isTerminal1[] = new boolean[n1 + 1];
        for (int i = 0; i < k1; i++) {
            isTerminal1[in.nextInt()] = true;
        }
        int aut1[][] = new int[n1 + 1][26];
        for (int i = 0; i < m1; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.next().charAt(0) - 97;
            aut1[a][c] = b;
        }


        int n2 = in.nextInt();
        int m2 = in.nextInt();
        int k2 = in.nextInt();
        boolean isTerminal2[] = new boolean[n2 + 1];
        for (int i = 0; i < k2; i++) {
            isTerminal2[in.nextInt()] = true;
        }
        int aut2[][] = new int[n2 + 1][26];
        for (int i = 0; i < m2; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.next().charAt(0) - 97;
            aut2[a][c] = b;
        }

        ArrayDeque<Pair> queue = new ArrayDeque<>();
        boolean used[][] = new boolean[n1 + 1][n2 + 1];
//        boolean used1[] = new boolean[n1 + 1];
//        boolean used2[] = new boolean[n2 + 1];
//        used1[0] = true;
//        used2[0] = true;
        queue.addFirst(new Pair(1, 1));
        while (!queue.isEmpty()) {
            Pair cur = queue.pollLast();
            if (isTerminal1[cur.q1] != isTerminal2[cur.q2]) {
                out.println("NO");
                return;
            }
            used[cur.q1][cur.q2] = true;
//            used1[cur.q1] = true;
//            used2[cur.q2] = true;
            for (int c = 0; c < 26; c++) {
                if (!used[aut1[cur.q1][c]][aut2[cur.q2][c]]) {
                    queue.addFirst(new Pair(aut1[cur.q1][c], aut2[cur.q2][c]));
                }
            }
        }
        out.println("YES");


    }


    class Pair {
        int q1;
        int q2;

        public Pair(int q1, int q2) {
            this.q1 = q1;
            this.q2 = q2;
        }
    }

    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("equivalence.in"));
                out = new PrintWriter(new File("equivalence.out"));
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
        new TaskG().run();
    }
}
