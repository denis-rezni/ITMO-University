import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class TwentyFive {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = false;

    public void solve() throws IOException {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] comb = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        out.print(nextComb(comb, n, k));
    }

    private String nextComb(int[] comb, int n, int k) {
        int[] res = Arrays.copyOf(comb, k + 1);
        res[k] = n + 1;
        int pointer = k - 1;
        while(pointer >= 0){
            if(res[pointer + 1] - res[pointer] > 1){
                break;
            } else {
                pointer--;
            }
        }
        if(pointer == -1){
            return "-1";
        }
        res[pointer]++;
        pointer++;
        while(pointer < k+1){
            res[pointer] = res[pointer - 1] + 1;
            pointer++;
        }
        return Arrays.stream(res).limit(k).mapToObj(String::valueOf).collect(Collectors.joining(" "));
    }

    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("nextchoose.in"));
                out = new PrintWriter(new File("nextchoose.out"));
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
        new TwentyFive().run();
    }

}
