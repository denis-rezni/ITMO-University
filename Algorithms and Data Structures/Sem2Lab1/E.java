import java.io.*;
import java.util.*;

public class E {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
    int trueN;
    int mod;

    public void solve() throws IOException {
        mod = in.nextInt();
        int n = in.nextInt();
        int m = in.nextInt();
        trueN = (int) Math.pow(2, Math.ceil(Math.log(n) / Math.log(2)));
        int[][] arr[] = new int[trueN * 2 - 1][2][2];
        for (int i = 0; i < n; i++) {
            arr[trueN - 1 + i] = new int[2][2];
            arr[trueN - 1 + i][0][0] = in.nextInt() % mod;
            arr[trueN - 1 + i][0][1] = in.nextInt() % mod;
            arr[trueN - 1 + i][1][0] = in.nextInt() % mod;
            arr[trueN - 1 + i][1][1] = in.nextInt() % mod;
        }

        for(int i = n; i < trueN; i++){
            arr[trueN - 1 + i] = matrixE();
        }

        for (int i = trueN - 2; i >= 0; i--) {
            arr[i] = multiply(arr[2 * i + 1], arr[2 * i + 2]);
        }

        for(int i = 0; i < m; i++){
            int l = in.nextInt() - 1;
            int r = in.nextInt() - 1;
            printMatrix(sum(0, 0, trueN - 1, l, r, arr));
        }



    }

    void printMatrix(int arr[][]){
        out.println();
        out.println(arr[0][0] + " " + arr[0][1]);
        out.println(arr[1][0] + " " + arr[1][1]);
    }

    int [][] sum(int v, int l, int r, int a, int b, int arr[][][]){
        if(l > b || r < a){
            return matrixE();
        }
        if(l >= a && r <= b){
            return arr[v];
        }
        int m = l + (r - l) / 2;
        return multiply(sum(v * 2 + 1, l, m, a, b, arr), sum(v * 2 + 2, m + 1, r, a, b, arr));
    }

    int[][] multiply(int a[][], int b[][]) {
        int res[][] = new int[2][2];
        res[0][0] = (a[0][0] * b[0][0] + a[0][1] * b[1][0]) % mod;
        res[0][1] = (a[0][0] * b[0][1] + a[0][1] * b[1][1]) % mod;
        res[1][0] = (a[1][0] * b[0][0] + a[1][1] * b[1][0]) % mod;
        res[1][1] = (a[1][0] * b[0][1] + a[1][1] * b[1][1]) % mod;
        return res;
    }

    int[][] matrixE() {
        int res[][] = new int[2][2];
        res[0][0] = 1;
        res[1][0] = 0;
        res[0][1] = 0;
        res[1][1] = 1;
        return res;
    }


    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("crypto.in"));
                out = new PrintWriter(new File("crypto.out"));
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