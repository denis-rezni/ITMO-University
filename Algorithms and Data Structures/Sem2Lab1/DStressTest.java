import java.io.*;
import java.util.*;

public class DStressTest {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
    int n = 1048576;
//    int n = 16;

    public void solve() {
        Random random = new Random(112);
        for(int tries = 0; tries < 100; tries++) {
            Node arr[] = new Node[2 * n - 1];
            for (int i = 0; i < 2 * n - 1; i++) {
                arr[i] = new Node(0);
            }
            int m = random.nextInt(10);
            Request reqs[] = new Request[m];
            for (int i = 0; i < m; i++) {
                String color;
                if (random.nextBoolean()) {
                    color = "W";
                } else {
                    color = "B";
                }
                int left = 1 + random.nextInt(n);
                int delta = 1 + random.nextInt(n - left + 1);
                reqs[i] = new Request(color, left, delta);
            }


            int res[][] = new int[m][2];
            for (int i = 0; i < m; i++) {
                int sum;
                int segments;
                if (reqs[i].color.equals("W")) {
                    int left = reqs[i].left - 1;
                    int delta = reqs[i].delta - 1;
                    sum = set(0, 0, n - 1, left, left + delta, 0, arr);
                    segments = arr[0].segments;
                } else {
                    int left = reqs[i].left - 1;
                    int delta = reqs[i].delta - 1;
                    sum = set(0, 0, n - 1, left, left + delta, 1, arr);
                    segments = arr[0].segments;
                }
                res[i][0] = segments;
                res[i][1] = sum;
            }
            int[][] rightRes = countRightAnswer(reqs);
            if (!isEqualAnswer(rightRes, res, m)) {
                System.out.println("Ah shit");
                System.out.println("expected: ");
                for (int i = 0; i < m; i++) {
                    System.out.println(rightRes[i][0] + " " + rightRes[i][1]);
                }
                System.out.println("actual: ");
                for (int i = 0; i < m; i++) {
                    System.out.println(res[i][0] + " " + res[i][1]);
                }
                System.out.println("request: ");
                System.out.println(reqs.length);
                for (int i = 0; i < m; i++) {
                    System.out.println(reqs[i].color + " " + reqs[i].left + " " + reqs[i].delta);
                }
                break;
            }
        }

    }

    boolean isEqualAnswer(int arr1[][], int arr2[][], int m){
        for(int i = 0; i < m; i++){
            if((arr1[i][0] != arr2[i][0]) || (arr1[i][1] != arr2[i][1])){
                return false;
            }
        }
        return true;
    }

    int set(int v, int l, int r, int a, int b, int toSet, Node arr[]) {
        push(v, arr, l, r);
        if (l > b || r < a) {
            return operated(arr[v], l, r).sum;
        }
        if (l >= a && r <= b) {
            arr[v].markedToSet = true;
            arr[v].toSet = toSet;
            arr[v].segments = operated(arr[v], l, r).segments;
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
        return arr[v].sum;
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
            System.out.print(arr[i].segments + " ");
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
        new DStressTest().run();
    }
}