import java.io.*;
import java.util.*;

public class C {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
    Node root;
    Node res[];

    public void solve() throws IOException {
        int n = in.nextInt();
        Node[] arr = new Node[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Node(in.nextInt(), in.nextInt(), i + 1);
        }
        Arrays.sort(arr, Comparator.comparingInt(a -> a.x));
        out.println("YES");
        for (int i = 0; i < n; i++) {
            addNode(arr[i], (i == 0) ? null : arr[i - 1]);
        }
        res = new Node[n];
        toRes(root);
        for(int i = 0 ; i < n; i++){
            out.println(res[i].toString());
        }

    }

    void toRes(Node v) {
        if (v.left != null) {
            toRes(v.left);
        }
        res[v.index - 1] = v;
        if (v.right != null) {
            toRes(v.right);
        }
    }

    void printTree(Node v) {
        if (v.left != null) {
            printTree(v.left);
        }
        if(root == v){
            System.out.print("!" + v.index + "! ");
        } else {
            System.out.print(v.index + " ");
        }
        if (v.right != null) {
            printTree(v.right);
        }
    }


    void addNode(Node v, Node last) {
        if (root == null) {
            root = v;
            return;
        }
        if (last.y < v.y) {
            last.right = v;
            v.parent = last;
        } else {
            Node cur = last;
            while (cur != null && cur.y > v.y) {
                cur = cur.parent;
            }
            if (cur == null) {
                v.left = root;
                root.parent = v;
                root = v;
            } else {
                v.left = cur.right;
                cur.right.parent = v;
                v.parent = cur;
                cur.right = v;
            }
        }
    }


    class Node {
        Node left;
        Node right;
        Node parent;
        int x;
        int y;
        int index;

        public Node(int x, int y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
        }

        public String toString() {
            return indexOf(parent) + " " + indexOf(left) + " " + indexOf(right);
        }
    }

    int indexOf(Node v) {
        return (v == null) ? 0 : v.index;
    }


    void run() {
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
        new C().run();
    }
}