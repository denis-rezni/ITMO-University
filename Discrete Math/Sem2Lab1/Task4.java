import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class Task4 {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = false;
    int n;

    public void solve() throws IOException {
        Locale.setDefault(Locale.US);
        n = in.nextInt();
        double[][] matrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = in.nextDouble();
            }
        }
        matrix = matrixToInf(matrix);
        double sum = 0;
        for(int i = 0; i < n; i++){
            sum += matrix[0][i];
        }
        for(int i = 0; i < n; i++){
            out.println(matrix[0][i] / sum);
        }

    }

    double[][] matrixToInf(double[][] arr){
        for(int times = 0; times < 30; times++){
            arr = multiply(arr);
        }
        return arr;
    }

    double[][] multiply(double[][] arr){
        double res[][] = new double[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < n; k++){
                    res[i][j] += arr[i][k] * arr[k][j];
                }
            }
        }
        return res;
    }


    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("markchain.in"));
                out = new PrintWriter(new File("markchain.out"));
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
        new Task4().run();
    }
}