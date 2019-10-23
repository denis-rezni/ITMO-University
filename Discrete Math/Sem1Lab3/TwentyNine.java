import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class TwentyNine {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = false;


    public void solve() throws IOException {
        String s = in.next();
        String[] input = s.split("\\D");
        int n = Integer.parseInt(input[0]);
        int part[] = Arrays.stream(input).skip(1).mapToInt(Integer::parseInt).toArray();
        out.print(nextPartition(part, n));
    }

    private String nextPartition(int[] part, int n){
        if(part.length == 1){
            return "No solution";
        }
        int size = part.length;
        int[] res = Arrays.copyOf(part, n);
        res[size - 1]--;
        res[size - 2]++;
        if(res[size - 2] > res[size - 1]){
            res[size - 2] += res[size - 1];
            size--;
        } else {
            while (res[size - 2] * 2 <= res[size - 1]){
                res[size] = res[size - 1] - res[size - 2];
                size++;
                res[size - 2] = res[size - 3];
            }
        }
        return n + "=" + Arrays.stream(res).limit(size).mapToObj(String::valueOf).collect(Collectors.joining("+"));

    }


    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("nextpartition.in"));
                out = new PrintWriter(new File("nextpartition.out"));
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
        new TwentyNine().run();
    }

}
