import java.io.*;
import java.util.*;

public class DRight {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
    //    int n = 1048576;
    int n = 16;
    //
    public void solve() throws IOException {

        int m = in.nextInt();
        Request reqs[] = new Request[m];
        for(int i = 0; i < m; i++){
            reqs[i] = new Request(in.next(), in.nextInt(), in.nextInt());
        }
        int res[][] = countRightAnswer(reqs);
        for(int i = 0; i < m; i++){
            System.out.println(res[i][0] + " " + res[i][1]);
        }
    }

    class Request{
        String color;
        int left;
        int delta;

        public Request(String color, int left, int delta) {
            this.color = color;
            this.left = left;
            this.delta = delta;
        }
    }

    int[][] countRightAnswer(Request reqs[]){
        int m = reqs.length;
        int res[][] = new int[m][2];
        int arr[] = new int[n];
        for(int i = 0; i < reqs.length; i++){
            Request req = reqs[i];
            if(req.color.equals("W")){
                for(int j = req.left - 1; j < req.left + req.delta - 1; j++){
                    arr[j] = 0;
                }
            } else {
                for(int j = req.left - 1; j < req.left + req.delta - 1; j++){
                    arr[j] = 1;
                }
            }
            System.out.println();
            for(int j = 0; j < n; j++){
                System.out.print(arr[j] + " ");
            }
            System.out.println();
            int segments = 0;
            int sum = 0;
            for(int j = 0; j < n; j++){
                sum += arr[j];
            }
            if(arr[0] == 1){
                segments++;
            }
            for(int j = 1; j < n; j++){
                if(arr[j - 1] == 0 && arr[j] == 1){
                    segments++;
                }
            }
            res[i][0] = segments;
            res[i][1] = sum;
        }
        return res;
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
        if (i > arr.length / 2 - 1) {
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
        new DRight().run();
    }
}