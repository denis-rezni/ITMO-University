import java.io.*;
import java.util.*;

public class TwentyOne {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;

    long s[][];


    public void solve() throws IOException {
        int n = in.nextInt();
        long k = in.nextLong();
        s = new long[n + 1][n + 1];
        dp(n);
        out.print(numberToPart(n, k));
    }


    private String numberToPart(int n, long k){
        int left = n;
        StringBuilder res = new StringBuilder();
        int last = 1;
        while(left > 0){
            for(int i = last; i <= n; i++){
                if(left >= i){
                    if(k >= s[left - i][left - i]){
                        k -= s[left - i][left - i];
                        System.out.println("not!");
                        System.out.println("i: " + i);
                        System.out.println("k: " + k);
                        System.out.println("arr: " + s[left - i][left - i]);
                    } else {
                        res.append(i).append("+");
                        System.out.println("i: " + i);
                        System.out.println("k: " + k);
                        System.out.println("arr: " + s[left - i][left - i]);
                        left -= i;
                        last = i;
                        break;
                    }
                }
            }
        }
        return res.toString().substring(0, res.length() - 1);
    }

    private void dp(int n){
        s[0][0] = 1;
        for(int k1 = 1; k1 < n + 1; k1++){
            for(int n1 = 0; n1 < n + 1; n1++){
                if(k1 <= n1){
                    s[n1][k1] = s[n1][k1 - 1] + s[n1 - k1][k1];
                }else{
                    s[n1][k1] = s[n1][n1];
                }
            }
        }
    }


    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("num2part.in"));
                out = new PrintWriter(new File("num2part.out"));
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
        new TwentyOne().run();
    }

}
