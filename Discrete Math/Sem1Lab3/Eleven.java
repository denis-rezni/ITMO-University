import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Eleven {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;


    public void solve() throws IOException {
        int n = in.nextInt();
        int curFirst = 0;
        int[][] arr = new int[1 << n][n];
        for(int i = 0; i < (1 << n); i++){
            String bin = Integer.toBinaryString(i);
            int pointer = 0;
            for(int j = 0; j < bin.length(); j++){
                if(((i >> j) & 1) == 1){
                    arr[i][pointer] = j + 1;
                    pointer++;
                }
            }
        }
        Arrays.sort(arr, (o1, o2) -> {
            for(int i = 0; i < o1.length; i++){
                if(o1[i] > o2[i]){
                    return 1;
                } else if(o1[i] < o2[i]){
                    return -1;
                }
            }
            return 0;
        });

        for(int i = 0; i < 1 << n; i++){
            out.println(Arrays.stream(arr[i]).filter(j -> j != 0)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(" ")));
        }
    }


    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("subsets.in"));
                out = new PrintWriter(new File("subsets.out"));
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
        new Eleven().run();
    }

}
