import java.io.*;
import java.util.*;

 
public class B {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
 
    public void solve() throws IOException {
    	int n = in.nextInt();
		ArrayList<Integer> stack = new ArrayList<>();
		int i = 0;
		int deleteCounter = 0;
		int toAdd = 0;
		int size;
		while(i < n) {
			i++;
			if(stack.size() < 3) {
				stack.add(in.nextInt());
			} else {
				size = stack.size();
				if(stack.get(size - 1) == stack.get(size - 2) && stack.get(size - 2) == stack.get(size - 3)){
					int equal = stack.get(size - 1);
					int delCounter = 3;
					while(i <= n && (toAdd = in.nextInt()) == equal) {
						delCounter++;
						i++;
					}
					remove(stack, 3);
					deleteCounter += delCounter;
					if(toAdd != equal) {
						stack.add(toAdd);
					}
				} else {
					toAdd = in.nextInt();
					stack.add(toAdd);
				}
			}
		}
		
		if(stack.size() > 2 && stack.get(stack.size() - 1) == stack.get(stack.size() - 2) && stack.get(stack.size() - 2) == stack.get(stack.size() - 3)){
			remove(stack, 3);
			deleteCounter += 3;
		}
		System.out.println(deleteCounter);
		
    }
    
    public void remove(ArrayList<Integer> stack, int toDel) {
    	int size = stack.size();
    	for(int i = 0; i < toDel; i++) {
    		stack.remove(size - i - 1);
    	}
    }
 
    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("segments.in"));
                out = new PrintWriter(new File("segments.out"));
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
        new B().run();
    }
}