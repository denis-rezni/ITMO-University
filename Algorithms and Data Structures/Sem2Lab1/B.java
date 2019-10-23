import java.io.*;
import java.util.*;

public class B {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
    int trueN;

    public void solve() throws IOException {
        //LONG!!!!
        int n = in.nextInt();
        trueN = (int) Math.pow(2, Math.ceil(Math.log(n) / Math.log(2)));
        long arr[] = new long[trueN * 2 - 1];
        for(int i = 0; i < n; i++){
            arr[trueN - 1 + i] = in.nextLong();
        }
        for(int i = trueN - 2; i >= 0; i--){
            arr[i] = arr[2 * i + 1] + arr[2 * i + 2];
        }
//        printTree(arr);
        while(FastScanner.br.ready()){
            String cmd = in.next();
            if(cmd == null){
                break;
            }
            if(cmd.equals("sum")){
                int a = in.nextInt();
                int b = in.nextInt();
                out.println(sum(0, 0, trueN - 1, a - 1, b - 1, arr));
            } else {
                int i = in.nextInt();
                int x = in.nextInt();
                setX(i - 1, x, arr);
//                printTree(arr);
            }
        }

    }

    long sum(int v, int l, int r, int a, int b, long arr[]){
        if(l > b || r < a){
            return 0;
        }
        if(l >= a && r <= b){
            return arr[v];
        }
        int m = l + (r - l) / 2;
        return sum(v * 2 + 1, l, m, a, b, arr) + sum(v * 2 + 2, m + 1, r, a, b, arr);
    }



    void setX(int i, int x, long arr[]){
        i = trueN - 1 + i;
        arr[i] = x;
        while(i != 0){
            i = (i - 1) / 2;
            arr[i] = arr[2 * i + 1] + arr[2 * i + 2];
        }
    }


    private void printTree(long arr[]){
        System.out.println();
        int w = 2;
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
            if(i == w - 2){
                w *= 2;
                System.out.println();
            }
        }
        System.out.println();
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

    static class FastScanner {
        public static BufferedReader br;
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
        new B().run();
    }
}