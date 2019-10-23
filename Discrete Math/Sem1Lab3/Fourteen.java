import java.io.*;
import java.util.*;

public class Fourteen {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = false;
    int n;
    long fact[] = new long[19];

    public void solve() throws IOException {
        n = in.nextInt();
        int [] perm = new int[n];
        for(int i = 0; i < n; i++){
            perm[i] = in.nextInt() - 1;
        }
        for(int i = 0; i <= 18; i++){
            fact[i] = fact(i);
        }
        out.println(permutationToNumber(perm));
    }

    private long permutationToNumber(int perm[]){
        long res = 0;
        boolean alreadyWas[] = new boolean[n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < perm[i]; j++){
                if(!alreadyWas[j]){
                    res += fact[n - i - 1];
                }
            }
            alreadyWas[perm[i]] = true;
        }
        return res;
    }

    private long fact(long x) {
        long res = 1;
        for (int i = 2; i <= x; i++) {
            res *= i;
        }
        return res;
    }

    private String toBinary(int i, int n) {
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
                in = new FastScanner(new File("perm2num.in"));
                out = new PrintWriter(new File("perm2num.out"));
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
