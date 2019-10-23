import java.io.*;
import java.util.*;

public class A {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;

    public void solve() throws IOException {
        int n = in.nextInt();
        int x = in.nextInt();
        int y = in.nextInt();
        int a = in.nextInt();
        int m = in.nextInt();
        int z = in.nextInt();
        int t = in.nextInt();
        int b = in.nextInt();
        long arr[] = new long[n + 1];
        for(int i = 0; i < n; i++){
            arr[i + 1] = arr[i] + a;
            a = (a * x + y) & ((1 << 16) - 1);
        }
        long sum = 0;
        for(int i = 0; i < m; i++){
            int left = b % n;
            b = (b * z + t) & ((1 << 30) - 1);
            int right = b % n;
            b = (b * z + t) & ((1 << 30) - 1);
            if(left > right){
                int temp = left;
                left = right;
                right = temp;
            }
            sum += arr[right + 1] - arr[left];
        }
        out.print(sum);

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
        new A().run();
    }
}