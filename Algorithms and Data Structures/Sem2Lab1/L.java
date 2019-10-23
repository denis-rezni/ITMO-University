import java.io.*;
import java.util.*;

public class L {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
    int n;

    public void solve() throws IOException {
        n = in.nextInt();
        int cmd;
        long arr[][][] = new long[n][n][n];
        while ((cmd = in.nextInt()) != 3) {
            if (cmd == 1) {
                add(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), arr);
            } else {
                out.println(get(in.nextInt(), in.nextInt(), in.nextInt(),
                        in.nextInt(), in.nextInt(), in.nextInt(), arr));
            }
        }


    }

    void add(int x, int y, int z, int delta, long[][][] arr) {
        for (int i = x; i < n; i |= (i + 1)) {
            for (int j = y; j < n; j |= (j + 1)) {
                for (int k = z; k < n; k |= (k + 1)) {
                    arr[i][j][k] += delta;
                }
            }
        }
    }

    long sum(int x, int y, int z, long[][][] arr) {
        long res = 0;
        for (int i = x; i >= 0; i = (i & (i + 1)) - 1) {
            for (int j = y; j >= 0; j = (j & (j + 1)) - 1) {
                for (int k = z; k >= 0; k = (k & (k + 1)) - 1) {
                    res += arr[i][j][k];
                }
            }
        }
        return res;
    }

    long get(int x1, int y1, int z1, int x2, int y2, int z2, long arr[][][]) {
        long res = 0;
        res += sum(x2, y2, z2, arr);
        res -= sum(x1 - 1, y2, z2, arr);
        res -= sum(x2, y1 - 1, z2, arr);
        res -= sum(x2, y2, z1 - 1, arr);
        res += sum(x2, y1 - 1, z1 - 1, arr);
        res += sum(x1 - 1, y2, z1 - 1, arr);
        res += sum(x1 - 1, y1 - 1, z2, arr);
        res -= sum(x1 - 1, y1 - 1, z1 - 1, arr);
        return res;
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
        new L().run();
    }
}