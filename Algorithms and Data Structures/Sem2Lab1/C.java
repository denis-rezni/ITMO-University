import java.io.*;
import java.util.*;

public class C {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
    int trueN;
    int n;

    public void solve() throws IOException {
        n = in.nextInt();
        trueN = 1;
        while (trueN < n) {
            trueN *= 2;
        }
        Node arr[] = new Node[2 * trueN - 1];
        for (int i = trueN - 1; i < n + trueN - 1; i++) {
            if (i - (trueN - 1) > n) {
                arr[i] = new Node(Integer.MAX_VALUE);
            } else {
                arr[i] = new Node(in.nextInt());
            }
        }
        for(int i = n + trueN - 1; i < 2 * trueN - 1; i++){
            arr[i] = new Node(Long.MAX_VALUE);
        }
        for (int i = trueN - 2; i >= 0; i--) {
            arr[i] = new Node(Math.min(arr[2 * i + 1].value, arr[2 * i + 2].value));
        }

        while (in.br.ready()) {
            String cmd = in.next();
            if (cmd.equals("min")) {
                long min = min(0, 0, trueN - 1, in.nextInt() - 1, in.nextInt() - 1, arr);
                out.println(min);
            } else if (cmd.equals("set")) {
                set(0, 0, trueN - 1, in.nextInt() - 1, in.nextInt() - 1, in.nextLong(), arr);
            } else {
                add(0, 0, trueN - 1, in.nextInt() - 1, in.nextInt() - 1, in.nextLong(), arr);
            }
        }


    }

    long min(int v, int l, int r, int a, int b, Node arr[]) {
        if(v >= trueN - 1 + n){
            return Long.MAX_VALUE;
        }
        if (l > b || r < a) {
            return Long.MAX_VALUE;
        }
        push(v, arr);
        if (l >= a && r <= b) {
            return operated(arr[v]);
        }
        int m = l + (r - l) / 2;
        return Math.min(min(2 * v + 1, l, m, a, b, arr), min(2 * v + 2, m + 1, r, a, b, arr));
    }

    long set(int v, int l, int r, int a, int b, long toSet, Node arr[]) {
        push(v, arr);
        if(v > trueN - 1 + n){
            return Long.MAX_VALUE;
        }
        if (l > b || r < a) {
            return operated(arr[v]);
        }
        if (l >= a && r <= b) {
            arr[v].markedToSet = true;
            arr[v].toSet = toSet;
            arr[v].toAdd = 0;
            return operated(arr[v]);
        }
        int m = l + (r - l) / 2;
        arr[v].value = Math.min(set(2 * v + 1, l, m, a, b, toSet, arr), set(2 * v + 2, m + 1, r, a, b, toSet, arr));
        return arr[v].value;
    }

    long add(int v, int l, int r, int a, int b, long toAdd, Node arr[]) {
        push(v, arr);
        if(v > trueN - 1 + n){
            return Long.MAX_VALUE;
        }
        if (l > b || r < a) {
            return operated(arr[v]);
        }
        if (l >= a && r <= b) {
            if (arr[v].markedToSet) {
                arr[v].toSet += toAdd;
            } else {
                arr[v].toAdd += toAdd;
            }
            return operated(arr[v]);
        }
        int m = l + (r - l) / 2;
        arr[v].value = Math.min(add(2 * v + 1, l, m, a, b, toAdd, arr), add(2 * v + 2, m + 1, r, a, b, toAdd, arr));
        return arr[v].value;
    }


    void push(int i, Node[] arr) {
        if (i > arr.length / 2 - 2) {
            return;
        }
        Node v = arr[i];
        operateNode(v.toAdd, v.toSet, v.markedToSet, arr[2 * i + 1]);
        operateNode(v.toAdd, v.toSet, v.markedToSet, arr[2 * i + 2]);
        if (v.markedToSet) {
            v.value = v.toSet;
            v.markedToSet = false;
            v.toAdd = 0;
        } else {
            v.value += v.toAdd;
            v.toAdd = 0;
        }
    }

    long operated(Node v) {
        if (v.markedToSet) {
            return v.toSet;
        } else {
            return v.value + v.toAdd;
        }
    }

    void operateNode(long toAdd, long toSet, boolean set, Node v) {
        if (set) {
            v.toSet = toSet;
            v.markedToSet = true;
            v.toAdd = 0;
        } else {
            if (v.markedToSet) {
                v.toSet += toAdd;
            } else {
                v.toAdd += toAdd;
            }
        }

    }

    private void printTree(Node arr[]){
        System.out.println();
        int w = 2;
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i].value + " ");
            if(i == w - 2){
                w *= 2;
                System.out.println();
            }
        }
        System.out.println();
    }

    class Node {
        long value;
        long toAdd;
        long toSet;
        boolean markedToSet;

        public Node(long value) {
            this.value = value;
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
        public BufferedReader br;
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