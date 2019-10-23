import java.io.*;
import java.util.*;

public class D {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
    int dx[] = new int[]{1, 2, 2, 1, -1, -2, -2, -1};
    int dy[] = new int[]{2, 1, -1, -2, -2, -1, 1, 2};

    public void solve() throws IOException {
        int n = in.nextInt();
        int arr[][] = new int[n][10];
        int field[][] = new int[4][3];
        int cnt = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = cnt++;
            }
        }
        field[3][0] = -1;
        field[3][2] = -1;
        for (int i = 0; i < 10; i++) {
            if (i != 0 && i != 8) {
                arr[0][i] = 1;
            }
        }
//        int curRow = 1;
        for (int row = 1; row < n; row++) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {
                    if (field[i][j] != -1) {
                        int number = field[i][j];
                        for (int d = 0; d < 8; d++) {
                            if (j + dx[d] >= 0 && j + dx[d] <= 2) {
                                if (i + dy[d] >= 0 && i + dy[d] <= 3) {
                                    if (field[i + dy[d]][j + dx[d]] != -1) {
                                        arr[/*curRow*/row][number] = (arr[/*curRow*/row][number] + arr[/*curRow ^ 1*/ row - 1][field[i + dy[d]][j + dx[d]]]) % 1_000_000_000;
                                    }
                                }
                            }
                        }
                    }
                }
            }
//            curRow ^= 1;
        }
//        curRow ^= 1;

//        for(int i = 0; i < n; i++){
//            for(int j = 0; j < 10; j ++){
//                System.out.print(arr[i][j] + " ");
//            }
//            System.out.println();
//        }


        int res = 0;
        for (int j = 0; j < 10; j++) {
            res = (res + arr[n - 1][j]) % 1_000_000_000;
        }
        out.print(res);
    }


    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("input.txt"));
                out = new PrintWriter(new File("output.txt"));
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
        new D().run();
    }
}