import java.io.*;
import java.util.*;

public class TaskD {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = false;

    Set<Set<Integer>> knownInSets = new HashSet<>();

    private void solve() {
        knownInSets.add(new HashSet<>());
        int n = in.nextInt();
        int m = in.nextInt();
        Set<Set<Integer>> sets = new HashSet<>();
        for (int i = 0; i < m; i++) {
            int size = in.nextInt();
            Set<Integer> curSet = new HashSet<>();
            for (int j = 0; j < size; j++) {
                curSet.add(in.nextInt());
            }
            sets.add(curSet);
        }

        //checking the first axiom
        if (!sets.contains(new HashSet<Integer>())) {
//            out.println("NO1");
            out.println("NO");
            return;
        }

        //checking the second axiom
        for (Set<Integer> setToCheck : sets) {
            if (!allSubsetsAreInSets(setToCheck, sets)) {
                out.println("NO");
                return;
            }

        }

        Set<Set<Integer>> done = new HashSet<>();
        //checking the third axiom
        for (Set<Integer> set1For : sets) {
            Set<Integer> set1 = new HashSet<>(set1For);
            innerLoop:
            for (Set<Integer> set2For : sets) {
                if (done.contains(set2For)) {
                    continue;
                }
                Set<Integer> set2 = new HashSet<>(set2For);
                if (set1.size() == set2.size()) {
                    continue;
                }
                if (set1.size() < set2.size()) {
                    Set<Integer> temp = set1;
                    set1 = set2;
                    set2 = temp;
                }
                //trying to put one elem from 1 to 2, check whether it's present in sets
                for (int elem : set1) {
                    if (!set2.contains(elem)) {
                        set2.add(elem);
                        if (sets.contains(set2)) {
                            //third axiom works for these two sets
                            continue innerLoop;
                        }
                        set2.remove(elem);
                    }
                }
                //no such element was found
//                out.println("NO3");
                out.println("NO");
                return;
            }
            done.add(set1For);
        }
        out.println("YES");

    }


    private boolean allSubsetsAreInSets(Set<Integer> givenSet, Set<Set<Integer>> sets) {
        if (knownInSets.contains(givenSet)) {
            return true;
        }
        boolean inSets = sets.contains(givenSet);
        List<Integer> set = new ArrayList<>(givenSet);
        for (int i = 0; i < set.size(); i++) {
            Set<Integer> only = new HashSet<>();
            only.add(set.get(i));
            set.remove(set.get(i));
            Set<Integer> rest = new HashSet<>(set);
            boolean onlyInSets = sets.contains(only);
            boolean restInSets = sets.contains(rest);
            boolean restSubsetsInSets = allSubsetsAreInSets(rest, sets);
            inSets &= onlyInSets & restInSets & restSubsetsInSets;
            if (!inSets) {
                return false;
            }
        }
        knownInSets.add(givenSet);
        return true;
    }

    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("check.in"));
                out = new PrintWriter(new File("check.out"));
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
        new TaskD().run();
    }
}

