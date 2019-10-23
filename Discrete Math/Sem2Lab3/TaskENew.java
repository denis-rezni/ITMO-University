import java.io.*;
import java.util.*;

public class TaskENew {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
    int[][][] letterToSegment;
    int[][][][] prefixToSegment;
    List<List<Rule>> rulesByFrom;
    List<Rule> rulesByIndex;
    //haven't been here = 0
    //was here, false = 1
    //was here, true = 2
    String word;
    int alpha = 26;

    public void solve() {
        int n = in.nextInt();
        int startLit = in.next().charAt(0) - 'A';
        rulesByFrom = new ArrayList<>();
        for (int i = 0; i < alpha; i++) {
            rulesByFrom.add(new ArrayList<>());
        }
        rulesByIndex = new ArrayList<>();
        int ruleIndex = 0;
        for (int i = 0; i < n; i++) {
            String line = in.nextLine();
            int from = line.charAt(0) - 'A';
            String to;
            if (line.length() == 4) {
                to = "";
            } else {
                to = line.substring(5);
            }
            if (newRule(from, to)) {
                Rule rule = new Rule(from, to, ruleIndex++);
                rulesByFrom.get(from).add(rule);
                rulesByIndex.add(rule);
                //these are references!!!
            }
        }
        word = in.next();
        int len = word.length();
        letterToSegment = new int[alpha][len + 2][len + 2];
        prefixToSegment = new int[rulesByIndex.size()][len + 2][len + 2][6];
//        if(computeLetterToSegment(startLit, 0, word.length() - 1) == 2){
//            out.println("yes");
//        } else {
//            out.println("no");
//        }
        int res = computeLetterToSegment(startLit, 0, word.length() - 1);
//        int res = computeLetterToSegmentNikita(startLit, 0, word.length() - 1);
        System.out.println(res);
    }



    int computeLetterToSegmentNikita(int lit, int l, int r) {
        if (letterToSegment[lit][l + 1][r + 1] != 0) {
            return letterToSegment[lit][l + 1][r + 1];
        }
        letterToSegment[lit][l + 1][r + 1] = 1;
        for (int i = 0; i < rulesByFrom.get(lit).size(); i++) {
            Rule rule = rulesByFrom.get(lit).get(i);
            if (computePrefixToSegmentNikita(rule.index, l, r, 0) == 2) {
                letterToSegment[lit][l + 1][r + 1] = 2;
                System.out.println("lit: " + lit + " l: " + (l + 1) + " r: " + (r + 1));
                return 2;
            }
        }
        return letterToSegment[lit][l + 1][r + 1];
    }

    int computePrefixToSegmentNikita(int ruleIndex, int l, int r, int prefix) {
        if (prefixToSegment[ruleIndex][l + 1][r + 1][prefix] != 0) {
            return prefixToSegment[ruleIndex][l + 1][r + 1][prefix];
        }
        prefixToSegment[ruleIndex][l + 1][r + 1][prefix] = 1;
        Rule rule = rulesByIndex.get(ruleIndex);
        int pointer = prefix;
        int newL = l;
        while (pointer < rule.to.length() && Character.isLowerCase(rule.to.charAt(pointer))) {
            if (newL > r || word.charAt(l) != rule.to.charAt(pointer)) {
                prefixToSegment[ruleIndex][l + 1][r + 1][prefix] = 1;
                return 1;
            }
            newL++;
            pointer++;
        }
        if (pointer == rule.to.length()) {
            if (newL > r) {
                prefixToSegment[ruleIndex][l + 1][r + 1][prefix] = 2;
                System.out.println("ruleIndex: " + ruleIndex + " l: " + (l + 1) + " r: " + (r + 1) + " prefix: " + prefix);
                return 2;
            } else {
                System.out.println("SHIET");
                prefixToSegment[ruleIndex][l + 1][r + 1][prefix] = 1;
                return 1;
            }
        }
        for (int first = 0; first <= r - newL + 1; first++) {
            if (computeLetterToSegmentNikita(rule.to.charAt(pointer) - 'A', newL, newL + first - 1) == 2
                    && computePrefixToSegmentNikita(rule.index, newL + first, r, pointer + 1) == 2) {
                prefixToSegment[ruleIndex][l + 1][r + 1][prefix] = 2;
                System.out.println("ruleIndex: " + ruleIndex + " l: " + (l + 1) + " r: " + (r + 1) + " prefix: " + prefix);
                return 2;
            }
        }
        return prefixToSegment[ruleIndex][l + 1][r + 1][prefix];
    }

    int computeLetterToSegment(int lit, int l, int r) {
        if (letterToSegment[lit][l + 1][r + 1] != 0) {
            return letterToSegment[lit][l + 1][r + 1];
        }
        letterToSegment[lit][l + 1][r + 1] = 1;
        for (int i = 0; i < rulesByFrom.get(lit).size(); i++) {
            Rule rule = rulesByFrom.get(lit).get(i);
            if (computePrefixToSegment(rule.index, l, r, rule.to.length() - 1) == 2) {
                letterToSegment[lit][l + 1][r + 1] = 2;
                System.out.println("lit: " + lit + " l: " + (l + 1) + " r: " + (r + 1));
                return 2;
            }
        }
        return letterToSegment[lit][l + 1][r + 1];
    }

    int computePrefixToSegment(int ruleIndex, int l, int r, int prefix) {
        if(prefix < 0){
            if(l > r){
                return 2;
            } else {
                return 1;
            }
        }
        if (prefixToSegment[ruleIndex][l + 1][r + 1][prefix] != 0) {
            return prefixToSegment[ruleIndex][l + 1][r + 1][prefix];
        }
        prefixToSegment[ruleIndex][l + 1][r + 1][prefix] = 1;
        Rule rule = rulesByIndex.get(ruleIndex);
        int pointer = prefix;
        int trueR = r;
        while (pointer >= 0 && Character.isLowerCase(rule.to.charAt(pointer))) {
            if (l > trueR || word.charAt(trueR) != rule.to.charAt(pointer)) {
                prefixToSegment[ruleIndex][l + 1][r + 1][prefix] = 1;
                return 1;
            }
            trueR--;
            pointer--;
        }
        if (pointer == -1) {
            if (l > trueR) {
                prefixToSegment[ruleIndex][l + 1][r + 1][prefix] = 2;
                System.out.println("ruleIndex: " + ruleIndex + " l: " + (l + 1) + " r: " + (r + 1) + " prefix: " + prefix);
                return 2;
            } else {
                System.out.println("SHIET");
                prefixToSegment[ruleIndex][l + 1][r + 1][prefix] = 1;
                return 1;
            }
        }
        for (int m = l; m <= trueR + 1; m++) {
            if(computePrefixToSegment(rule.index, l, m - 1, pointer - 1) == 2 &&
                    computeLetterToSegment(rule.to.charAt(pointer) - 'A', m, trueR) == 2){
//            if (computeLetterToSegment(rule.to.charAt(pointer) - 'A', l, m) == 2
//                    && computePrefixToSegment(rule.index, m + 1, trueR, pointer - 1) == 2) {
                prefixToSegment[ruleIndex][l + 1][r + 1][prefix] = 2;
                System.out.println("ruleIndex: " + ruleIndex + " l: " + (l + 1) + " r: " + (r + 1) + " prefix: " + prefix);
                return 2;
            }
        }
        return prefixToSegment[ruleIndex][l + 1][r + 1][prefix];
    }


    class Rule {
        int from;
        String to;
        int index;

        public Rule(int from, String to, int index) {
            this.from = from;
            this.to = to;
            this.index = index;
        }
    }

    boolean newRule(int from, String to) {
        loop:
        for (Rule cur : rulesByIndex) {
            if (cur.from == from && cur.to.equals(to)) {
                return false;
            }
        }
        return true;
    }

    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("cf.in"));
                out = new PrintWriter(new File("cf.out"));
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
        new TaskENew().run();
    }
}
