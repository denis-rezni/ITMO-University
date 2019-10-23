import java.io.*;
import java.util.*;

public class H {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        int trueN = 1;
        while(trueN < n){
            trueN *= 2;
        }
        MarkedInt arr[] = new MarkedInt[2 * trueN - 1];
        for(int i = 0; i < 2 * trueN - 1; i++){
            arr[i] = new MarkedInt(0, false);
        }


    }

    void setMin(int v, int l, int r, int a, int b, MarkedInt arr[], int min) {
        if (l > b || r < a) {
            return;
        }
        if (l >= a && r <= b) {
            if(arr[v].marked){
                if(min > arr[v].value){
                    arr[v].value = min;
                }
            } else {
                arr[v].value = min;
                arr[v].marked = true;
            }
        }
        int m = l + (r - l) / 2;
        setMin(v * 2 + 1, l, m, a, b, arr, min);
        setMin(v * 2 + 2, m + 1, r, a, b, arr, min);
    }

    class MarkedInt{
        int value;
        boolean marked;

        public MarkedInt(int value, boolean marked) {
            this.value = value;
            this.marked = marked;
        }
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
        new H().run();
    }
}