import java.io.*;
import java.util.*;

public class A {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
    Node root;

    public void solve() throws IOException {
        String cmd = in.next();
        int i = 0;
        while (FastScanner.br.ready()) {
            if (i != 0) {
                cmd = in.next();
            }
            if ("insert".equals(cmd)) {
                insert(in.nextInt());
            } else if ("delete".equals(cmd)) {
                delete(in.nextInt());
            } else if ("exists".equals(cmd)) {
                System.out.println(exists(root, in.nextInt()));
            } else if ("next".equals(cmd)) {
                OptionalInt res = next(in.nextInt());
                if (res.isPresent()) {
                    System.out.println(res.getAsInt());
                } else {
                    System.out.println("none");
                }
            } else if ("prev".equals(cmd)) {
                OptionalInt res = prev(in.nextInt());
                if (res.isPresent()) {
                    System.out.println(res.getAsInt());
                } else {
                    System.out.println("none");
                }
            }
//            System.out.println("i: " + i + " cmd: " + cmd);
//            System.out.println("startTree");
//            printTree(root);
//            System.out.println();
//            System.out.println("endTree");
            i++;
        }


    }

    public class Node {
        Node left;
        Node right;
        Node parent;
        int value;

        public Node(int value) {
            this.value = value;
        }


    }

    public void setParent(Node child, Node parent) {
        if (child != null) {
            child.parent = parent;
        }
    }

    public void rotate(Node parent, Node child) {
        Node grandParent = parent.parent;
        if (grandParent != null) {
            if (grandParent.left == parent) {
                grandParent.left = child;
            } else {
                grandParent.right = child;
            }
        }
        if (parent.left == child) {
            parent.left = child.right;
            child.right = parent;
        } else {
            parent.right = child.left;
            child.left = parent;
        }
        setParent(child.left, child);
        setParent(child.right, child);
        setParent(parent.left, parent);
        setParent(parent.right, parent);
        child.parent = grandParent;
    }

    public Node splay(Node v) {
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

    public boolean exists(Node v, int x) {
        return !(find(x, v) == null);
    }

    public Node insertInLeaf(int x, Node v) {
        if (root == null) {
            root = new Node(x);
            return root;
        }
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

    public void insert(int x) {
        root = splay(insertInLeaf(x, root));
    }

    public Node merge(Node left, Node right) {
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
        return cur;
    }

    public void delete(int x) {
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

    public Node find(int x, Node v) {
        if (v == null || v.value == x) {
            return v;
        } else if (x < v.value) {
            return find(x, v.left);
        } else {
            return find(x, v.right);
        }
    }

    public void printTree(Node v) {
        if (v.left != null) {
            printTree(v.left);
        }
        System.out.print(v.value + " ");
        if (v.right != null) {
            printTree(v.right);
        }
    }

    public OptionalInt nextImpl(Node cur) {
        if (cur.right == null) {
            return OptionalInt.empty();
        } else {
            cur = cur.right;
            while (cur.left != null) {
                cur = cur.left;
            }
            return OptionalInt.of(cur.value);
        }
    }

    public OptionalInt next(int x) {
        Node cur = find(x, root);
        boolean exists = (cur != null);
        if (exists) {
            splay(cur);
            return nextImpl(cur);
        } else {
            insert(x);
            cur = root;
            OptionalInt res = nextImpl(cur);
            delete(x);
            return res;
        }
    }

    public OptionalInt prevImpl(Node cur) {
        if (cur.left == null) {
            return OptionalInt.empty();
        } else {
            cur = cur.left;
            while (cur.right != null) {
                cur = cur.right;
            }
            return OptionalInt.of(cur.value);
        }
    }

    public OptionalInt prev(int x) {
        Node cur = find(x, root);
        boolean exists = (cur != null);
        if (exists) {
            splay(cur);
            return prevImpl(cur);
        } else {
            insert(x);
            cur = root;
            OptionalInt res = prevImpl(cur);
            delete(x);
            return res;
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
        new A().run();
    }
}