import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class C {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;

    public void solve() throws IOException {
        int n = in.nextInt();
        Pair arr[] = new Pair[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Pair(in.nextInt(), 1);
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j].val < arr[i].val && arr[j].len + 1 > arr[i].len) {
                    arr[i].len = arr[j].len + 1;
                    arr[i].from = j;
                }
            }
        }
        int maxLen = 1;
        int maxIndex = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i].len > maxLen) {
                maxLen = arr[i].len;
                maxIndex = i;
            }
        }
        out.println(maxLen);
        int pointer = maxIndex;
        int res[] = new int[maxLen];
        res[maxLen - 1] = arr[maxIndex].val;
        int counter = maxLen - 1;
        while (counter != 0){
            counter--;
            pointer = arr[pointer].from;
            res[counter] = arr[pointer].val;
        }

        out.print(Arrays.stream(res).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }


    private class Pair {
        int val;
        int len;
        int from;

        public Pair(int val, int len) {
            this.val = val;
            this.len = len;
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
        new C().run();
    }
}