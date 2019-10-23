import java.io.*;
import java.util.*;

public class D {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
    int n = 1048576;
//    int n = 16;
//
    public void solve() throws IOException {

        Node arr[] = new Node[2 * n - 1];
        for (int i = 0; i < 2 * n - 1; i++) {
            arr[i] = new Node(0);
        }
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int sum;
            int segments;
            if (in.next().equals("W")) {
                int left = in.nextInt() - 1 + 500_001;
//                int left = in.nextInt() - 1;
                int delta = in.nextInt() - 1;
                sum = set(0, 0, n - 1, left, left + delta, 0, arr);
                segments = arr[0].segments;
            } else {
                int left = in.nextInt() - 1 + 500_001;
//                int left = in.nextInt() - 1;
                int delta = in.nextInt() - 1;
                sum = set(0, 0, n - 1, left, left + delta, 1, arr);
                segments = arr[0].segments;
            }
//            printTree(arr);
            out.println(segments + " " + sum);
        }

    }

    int set(int v, int l, int r, int a, int b, int toSet, Node arr[]) {
//        System.out.println("v: " + v + " l " + l + " r " + r);
        push(v, arr, l, r);
        if (l > b || r < a) {
//            System.out.println("case 1");
//            System.out.println("out v: " + v + " sum: " + operated(arr[v], l, r).sum);
            return operated(arr[v], l, r).sum;
        }
        if (l >= a && r <= b) {
//            System.out.println("case 2");
            arr[v].markedToSet = true;
            arr[v].toSet = toSet;
            arr[v].segments = operated(arr[v], l, r).segments;
//            System.out.println("out v: " + v + " sum: " + operated(arr[v], l, r).sum);
            return operated(arr[v], l, r).sum;
        }
        int m = l + (r - l) / 2;
        arr[v].sum = set(2 * v + 1, l, m, a, b, toSet, arr) + set(2 * v + 2, m + 1, r, a, b, toSet, arr);
        arr[v].segments = operated(arr[2 * v + 1], l, r).segments + operated(arr[2 * v + 2], l, r).segments;
        if (operatedLeftRight(arr[2 * v + 1]).right == 1 && operatedLeftRight(arr[2 * v + 2]).left == 1) {
            arr[v].segments--;
        }
        arr[v].left = operatedLeftRight(arr[2 * v + 1]).left;
        arr[v].right = operatedLeftRight(arr[2 * v + 2]).right;
//        System.out.println("out v: " + v + " sum: " + operated(arr[v], l, r).sum);
        return arr[v].sum;
    }


    void push(int i, Node[] arr, int l, int r) {
        if (i >= n - 1) {
            return;
        }
        Node v = arr[i];
        operateNode(v.toSet, v.markedToSet, arr[2 * i + 1]);
        operateNode(v.toSet, v.markedToSet, arr[2 * i + 2]);
        if (v.markedToSet) {
            v.left = v.toSet;
            v.right = v.toSet;
            v.segments = v.toSet;
            v.sum = (r - l + 1) * v.toSet;
            v.markedToSet = false;
        }
    }

    class PairLeftRight {
        int left;
        int right;

        public PairLeftRight(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    PairLeftRight operatedLeftRight(Node v) {
        if (v.markedToSet) {
            return new PairLeftRight(v.toSet, v.toSet);
        } else {
            return new PairLeftRight(v.left, v.right);
        }
    }

    Pair operated(Node v, int l, int r) {
        if (v.markedToSet) {
            return new Pair((r - l + 1) * v.toSet, v.toSet);
        } else {
            return new Pair(v.sum, v.segments);
        }
    }

    void operateNode(int toSet, boolean marked, Node v) {
        if (marked) {
            v.toSet = toSet;
            v.markedToSet = true;
        }

    }

    private void printTree(Node arr[]) {
        System.out.println();
        int w = 2;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i].sum + " ");
            if (i == w - 2) {
                w *= 2;
                System.out.println();
            }
        }
        System.out.println();
    }

    class Pair {
        int sum;
        int segments;

        public Pair(int sum, int segments) {
            this.sum = sum;
            this.segments = segments;
        }
    }

    class Node {
        int left;
        int right;
        int segments;
        int sum;
        int toSet;
        boolean markedToSet;

        public Node(int color) {
            if (color == 0) {
                left = 0;
                right = 0;
            } else {
                left = 1;
                right = 1;
                segments = 1;
                sum = 1;
            }
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
        new D().run();
    }
}