import java.io.*;
import java.util.*;

public class F {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        int a = in.nextInt();
        int u = in.nextInt();
        int v = in.nextInt();
        int logs[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            logs[i] = log2(i);
        }
        int st[][] = new int[n][logs[n] + 1];
        st[0][0] = a;
        for (int i = 0; i < n; i++) {
            st[i][0] = a;
            a = (23 * a + 21563) % 16714589;
        }
        for (int j = 1; j <= logs[n]; j++) {
            for (int i = 0; i < n; i++) {
                if (i + (1 << j) <= n) {
                    st[i][j] = Math.min(st[i][j - 1], st[i + (1 << (j - 1))][j - 1]);
                }
            }
        }
//        for(int i = 0; i < n; i++){
//            System.out.println(Arrays.toString(st[i]));
//        }


        int ans = 0;
        for (int i = 1; i < m + 1; i++) {
            int left = Math.min(u, v) - 1;
            int right = Math.max(u, v);
            int d = right - left;
            int k = logs[d];
//            System.out.println(" u: " + u + " v: " + v + " k: " + k);
            ans = Math.min(st[left][k], st[right - (1 << k)][k]);
//            System.out.println(ans);
            if(i == m){
                out.println(u + " " + v + " " + ans);
                return;
            }
            u = ((17 * u + 751 + ans + 2 * i) % n) + 1;
            v = ((13 * v + 593 + ans + 5 * i) % n) + 1;
        }

    }

    int log2(int i) {
        if (i == 1) {
            return 0;
        } else {
            return log2(i / 2) + 1;
        }
    }


    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("skyscraper.in"));
                out = new PrintWriter(new File("skyscraper.out"));
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