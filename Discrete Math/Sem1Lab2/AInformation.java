import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class AInformation {
	static class Node {
		int sum;
	}

	static class InternalNode extends Node {
		Node left;
		Node right;

		public InternalNode(Node left, Node right) {
			this.left = left;
			this.right = right;
			this.sum = left.sum + right.sum;
		}
	}

	static class LeafNode extends Node {
		int index;

		public LeafNode(int index, int sum) {
			this.sum = sum;
			this.index = index;
		}
	}

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			map.put(i, in.nextInt());
		}
		PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				if (o1.sum > o2.sum) {
					return 1;
				} else if (o1.sum < o2.sum) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			queue.add(new LeafNode(entry.getKey(), entry.getValue()));
		}
		long sum = 0;
		while (queue.size() > 1) {
			Node first = queue.poll();
			Node second = queue.poll();
			InternalNode node = new InternalNode(first, second);
			sum += node.sum;
			queue.add(node);
		}
		System.out.println(sum);
		in.close();
	}
}
