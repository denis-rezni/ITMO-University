import java.io.*;
import java.util.*;

public class B {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = false;

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        Pair arr[][] = new Pair[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = new Pair(in.nextInt(), 0);
            }
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    arr[i][j].max = arr[i][j].val;
                } else if (i - 1 < 0) {
                    arr[i][j].max = arr[i][j - 1].max + arr[i][j].val;
                    arr[i][j].turn = 'R';
                } else if (j - 1 < 0) {
                    arr[i][j].max = arr[i - 1][j].max + arr[i][j].val;
                    arr[i][j].turn = 'D';
                } else {
                    if (arr[i - 1][j].max > arr[i][j - 1].max) {
                        arr[i][j].max = arr[i - 1][j].max + arr[i][j].val;
                        arr[i][j].turn = 'D';
                    } else {
                        arr[i][j].max = arr[i][j - 1].max + arr[i][j].val;
                        arr[i][j].turn = 'R';
                    }
                }

            }
        }

        out.println(arr[n - 1][m - 1].max);
        int i = n - 1;
        int j = m - 1;
        StringBuilder res = new StringBuilder();
        while (i != 0 || j != 0) {
            if(arr[i][j].turn == 'R'){
                j--;
                res.append('R');
            } else {
                i--;
                res.append('D');
            }
        }
        out.print(res.reverse().toString());


    }


    private class Pair {
        int val;
        int max;
        char turn;

        public Pair(int val, int max) {
            this.val = val;
            this.max = max;
        }
    }

    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("input.txt"));
                out = new PrintWriter(new File("output.txt"));
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
        new B().run();
    }
}