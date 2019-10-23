import java.io.*;
import java.util.*;

public class Five {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = false;

    public void solve() throws IOException {
        int n = in.nextInt();
        int k = in.nextInt();
        int arr[][] = new int[(int) Math.pow(k, n)][n];
        for (int i = 0; i < n; i++) {
            fill(k, n, i, arr);
        }
        for(int i = 0; i <(int) Math.pow(k, n); i++){
            StringBuilder res = new StringBuilder();
            for(int j = 0; j < n; j++){
                res.append(arr[i][j]);
            }
            out.println(res.toString());
            res.delete(0, res.length());
        }
    }

    public void fill(int k, int n, int column, int arr[][]){
        int pointer = 0;
        boolean reversed = false;
        for(int i = 0; i < (int)Math.pow(k, n - column - 1); i++){
            for(int j = 0; j < k; j++){
                for(int h = 0; h < (int)Math.pow(k, column); h++){
                    if(reversed){
                        arr[pointer][column] = k - j - 1;
                    } else {
                        arr[pointer][column] = j;
                    }
                    pointer++;
                }
            }
            reversed = !reversed;
        }
    }


    public String toBinary(int i, int n) {
        StringBuilder s = new StringBuilder();
        for (int j = 0; j < n - Integer.toBinaryString(i).length(); j++) {
            s.append("0");
        }
        s.append(Integer.toBinaryString(i));
        return s.toString();
    }


    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("telemetry.in"));
                out = new PrintWriter(new File("telemetry.out"));
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
        new Eight().run();
    }
}
