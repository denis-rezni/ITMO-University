import java.io.*;
import java.util.*;

public class F {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
    Node root;

    public void solve() throws IOException {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            String cmd = in.next();
            if ("+1".equals(cmd) || "1".equals(cmd)) {
                insert(in.nextInt());
            } else if ("-1".equals(cmd)) {
                delete(in.nextInt());
            } else {
                out.println(findKth(root, in.nextInt()));
            }

//            System.out.println("i: " + i + " cmd: " + cmd);
//            System.out.println("startTree");
//            printTree(root, 0);
//            System.out.println();
//            System.out.println("endTree");
        }


    }

    public class Node {
        Node left;
        Node right;
        Node parent;
        int value;
        int count;

        public Node(int value) {
            this.value = value;
            this.count = 1;
        }


    }

    void setParent(Node child, Node parent) {
        if (child != null) {
            child.parent = parent;
        }
    }

    void rotate(Node parent, Node child) {
        Node grandParent = parent.parent;
        if (grandParent != null) {
            if (grandParent.left == parent) {
                grandParent.left = child;
            } else {
                grandParent.right = child;
            }
        }
        if (parent.left == child) {
            parent.count -= (child.left != null) ? child.left.count + 1 : 1;
            child.count += (parent.right != null) ? parent.right.count + 1 : 1;
            parent.left = child.right;
            child.right = parent;
        } else {
            parent.count -= (child.right != null) ? child.right.count + 1 : 1;
            child.count += (parent.left != null) ? parent.left.count + 1 : 1;
            parent.right = child.left;
            child.left = parent;
        }
        setParent(child.left, child);
        setParent(child.right, child);
        setParent(parent.left, parent);
        setParent(parent.right, parent);
        child.parent = grandParent;

    }

    Node splay(Node v) {
        if (v.parent == null) {
            root = v;
            return v;
        }
        Node parent = v.parent;
        Node grandParent = parent.parent;
        if (grandParent == null) {
            rotate(parent, v);
            root = v;
            return v;
        } else {
            if (grandParent.left == parent && parent.left == v) {
                rotate(grandParent, parent);
                rotate(parent, v);
            } else if (grandParent.right == parent && parent.right == v) {
                rotate(grandParent, parent);
                rotate(parent, v);
            } else {
                rotate(parent, v);
                rotate(grandParent, v);
            }
            return splay(v);
        }
    }

    boolean exists(Node v, int x) {
        return !(find(x, v) == null);
    }

    Node insertInLeaf(int x, Node v) {
        if (root == null) {
            root = new Node(x);
            return root;
        }
        v.count++;
        if (x < v.value) {
            if (v.left == null) {
                v.left = new Node(x);
                setParent(v.left, v);
                return v.left;
            } else {
                return insertInLeaf(x, v.left);
            }
        } else if (x > v.value) {
            if (v.right == null) {
                v.right = new Node(x);
                setParent(v.right, v);
                return v.right;
            } else {
                return insertInLeaf(x, v.right);
            }
        } else {
            return v;
        }
    }

    void insert(int x) {
        root = splay(insertInLeaf(x, root));
    }

    Node merge(Node left, Node right) {
        if (right == null) {
            return left;
        }
        Node cur = right;
        while (cur.left != null) {
            cur = cur.left;
        }
        splay(cur);
        cur.left = left;
        setParent(left, cur);
        if (left != null) {
            cur.count += left.count;
        }
        return cur;
    }

    void delete(int x) {
        Node toDel = find(x, root);
        if (toDel != null) {
            splay(toDel);
        } else {
            return;
        }
        if (toDel.left != null) {
            toDel.left.parent = null;
        }
        if (toDel.right != null) {
            toDel.right.parent = null;
        }
        root = merge(toDel.left, toDel.right);
    }

    Node find(int x, Node v) {
        if (v == null || v.value == x) {
            return v;
        } else if (x < v.value) {
            return find(x, v.left);
        } else {
            return find(x, v.right);
        }
    }

    void printTree(Node v, int i) {
        if (v.left != null) {
            printTree(v.left, i + 1);
        }
        System.out.print("~" + v.value + " " + i + " " + v.count + "~ ");
        if (v.right != null) {
            printTree(v.right, i + 1);
        }
    }

    int findKth(Node v, int k) {
        if (v.right == null && k == 1) {
            return v.value;
        }
        if (v.right != null && v.right.count + 1 == k) {
            return v.value;
        }
        if (v.right != null && v.right.count >= k) {
            return findKth(v.right, k);
        } else {
            return findKth(v.left, (v.right == null) ? k - 1 : k - v.right.count - 1);
        }
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
        new F().run();
    }
}