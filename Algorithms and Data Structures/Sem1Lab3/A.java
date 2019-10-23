import java.io.*;
import java.util.*;

public class A {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = false;

    public void solve() throws IOException {
        int n = in.nextInt();
        int k = in.nextInt();

        Pair arr[] = new Pair[n];
        arr[0] = new Pair(0, 0);
        for(int i = 1; i < n - 1; i++){
            arr[i] = new Pair(in.nextInt(), 0);
        }
        List<Integer> visits = new ArrayList<>();
        arr[n - 1] = new Pair(0, 0);
        for(int i = 1; i < n; i++){
            int pointer = i - 1;
            int max = Integer.MIN_VALUE;
            while(pointer >= 0 && i - pointer <= k){
                if(max < arr[pointer].max + arr[i].val){
                    max = arr[pointer].max + arr[i].val;
                }
                pointer--;
            }
            arr[i].max = max;
        }
        out.println(arr[n - 1].max);
        int i = n - 1;
        while(i != 0){
            int pointer = i - 1;
            int maxIndex = 0;
            int max = Integer.MIN_VALUE;
            while(pointer >= 0 && i - pointer <= k){
                if(max < arr[pointer].max){
                    max = arr[pointer].max;
                    maxIndex = pointer;
                }
                pointer--;
            }
            visits.add(maxIndex);
            i = maxIndex;
        }
        out.println(visits.size());
        for(int j = visits.size() - 1; j >= 0; j--){
            out.print(visits.get(j) + 1 + " ");
        }
        out.print(n);
    }



    private class Pair{
        int val;
        int max;

        public Pair(int val, int max) {
            this.val = val;
            this.max = max;
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
        new A().run();
    }
}