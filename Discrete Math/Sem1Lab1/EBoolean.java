import java.util.ArrayList;
import java.util.Scanner;

public class EBoolean {
	public static class Node{
		int inputsNumber;
		ArrayList<Integer> inputs;
		ArrayList<Integer> function;
		public Node(int inputsNumber, ArrayList<Integer> inputs, ArrayList<Integer> function) {
			this.inputsNumber = inputsNumber;
			this.inputs = inputs;
			this.function = function;
		}
		public static Node LEAF = new Node(0, new ArrayList<Integer>(), new ArrayList<Integer>());
	}
	
	public static int depth(ArrayList<Node> nodeList) {
		int depth[] = new int[nodeList.size()];
		for(int i = 0; i < nodeList.size(); i++) {
			Node cur = nodeList.get(i);
			if(cur.inputsNumber != 0) {
				int curMaxDepth = 0;
				for(int j = 0; j < cur.inputsNumber; j++) {
					if(depth[cur.inputs.get(j) - 1] > curMaxDepth) {
						curMaxDepth = depth[cur.inputs.get(j) - 1];
					}
				}
				depth[i] = curMaxDepth + 1;
			}
		}
		int maxDepth = 0;
		for(int i = 0; i < depth.length; i++) {
			if(depth[i] > maxDepth) {
				maxDepth = depth[i];
			}
		}
		return maxDepth;
	}
	
	public static String toBinary(int i, int n) {
		StringBuilder s = new StringBuilder();
		for (int j = 0; j < n - Integer.toBinaryString(i).length(); j++) {
			s.append("0");
		}
		s.append(Integer.toBinaryString(i));
		return s.toString();
	}
	
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		ArrayList<Node> nodeList = new ArrayList<>(); 
		int leafCounter = 0;
		for(int i = 0; i < n; i++) {
			int m = in.nextInt();
			if(m == 0) {
				nodeList.add(Node.LEAF);
				leafCounter++;
			} else {
				ArrayList<Integer> inputs = new ArrayList<>();
				for(int j = 0; j < m; j++) {
					inputs.add(in.nextInt());
				}
				ArrayList<Integer> function = new ArrayList<>();
				for(int j = 0; j < 1 << m; j++) {
					function.add(in.nextInt());
				}
				nodeList.add(new Node(m, inputs, function));
			}
		}
		System.out.println(depth(nodeList));
		StringBuilder res = new StringBuilder();
		for(int i = 0; i < 1 << leafCounter; i++) {
			String input = toBinary(i, leafCounter);
			int leafPointer = 0;
			int state[] = new int[nodeList.size()];
			for(int j = 0; j < nodeList.size(); j++) {
				Node cur = nodeList.get(j);
				if(cur.inputsNumber == 0) {
					state[j] = Character.getNumericValue(input.charAt(leafPointer));
					leafPointer++;
				} else {
					StringBuilder curState = new StringBuilder();
					for(int k = 0; k < cur.inputsNumber; k++) {	
						curState.append(state[cur.inputs.get(k) - 1]);
					}
					int functionPointer = Integer.parseInt(curState.toString(), 2);
					state[j] = cur.function.get(functionPointer);
				}
			}
			res.append(state[state.length - 1]);
		}
		System.out.println(res.toString());
		
		in.close();
	}

}
