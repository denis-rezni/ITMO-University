import java.io.*;
import java.util.*;

public class TaskB {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = false;

    public void solve() throws IOException {
        int n = in.nextInt();
        int start = in.next().charAt(0) - 'A' + 1;
        List<Rule> rules = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String check = in.nextLine();
            if(check.toUpperCase().equals(check)){
               if(check.length() == 4){
                   rules.add(new Rule(check.charAt(0) - 'A' + 1, ""));
               } else {
                   rules.add(new Rule(check.charAt(0) - 'A' + 1, check.substring(5)));
               }
            }
        }
        n = rules.size();
        Set<Integer> epsilon = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if(rules.get(i).to.equals("")){
                epsilon.add(rules.get(i).from);
                rules.get(i).added = true;
            }
        }
        int counter = 0;
        loop: while(counter != n){
            for (int i = 0; i < n; i++) {
                Rule rule = rules.get(i);
                if(isEpsilon(epsilon, rule) && !rule.added){
                    epsilon.add(rule.from);
                    rule.added = true;
                    counter = 0;
                    continue loop;
                } else {
                    counter++;
                }
            }
        }
        epsilon.stream().sorted().forEach(i -> out.println(Character.valueOf((char)(i + 'A' - 1))));
    }

    boolean isEpsilon(Set<Integer> epsilon, Rule rule){
        for(int i = 0; i < rule.to.length(); i++){
            int cur = rule.to.charAt(i) - 'A' + 1;
            if(!epsilon.contains(cur)){
                return false;
            }
        }
        return true;
    }

    class Rule{
        int from;
        String to;
        boolean added;

        public Rule(int from, String to) {
            this.from = from;
            this.to = to;
            added = false;
        }
    }


    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("epsilon.in"));
                out = new PrintWriter(new File("epsilon.out"));
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
        new TaskB().run();
    }
}
