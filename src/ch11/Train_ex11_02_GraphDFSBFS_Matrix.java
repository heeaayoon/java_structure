package ch11;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

class Graph2 {
	int[][] matrix;
	int n;
	boolean[] visited;

	public Graph2(int vertices) {

	}

	// Insert an edge into the graph
	public void insertEdge(int start, int end) {

	}

	// Display adjacency matrix
	public void displayMatrix() {

	}

	// BFS implementation using a queue
	void BFS(int v) {
		visited = new boolean[n]; // Reset visited array
		Queue<Integer> q = new LinkedList<>();
		/*
		 * 자바에서는 Queue는 **인터페이스(interface)**로 정의 public class LinkedList<E> implements
		 * List<E>, Deque<E>, Cloneable, Serializable Deque<E>는 Queue<E>의 하위 인터페이스.
		 * LinkedList는 Queue 인터페이스를 구현, 큐처럼 사용 q.add(10); // 큐에 삽입 q.offer(20); // 큐에 삽입
		 * (add와 비슷, 차이는 있음) q.poll(); // 큐에서 제거하고 반환 q.peek(); // 큐의 맨 앞 요소 확인 (제거 X)
		 */
		visited[v] = true;
		q.add(v);

		System.out.print("BFS traversal: ");
		while (!q.isEmpty()) {

		}
		System.out.println();
	}

	// Recursive DFS
	void DFS(int v) {
		visited = new boolean[n]; // Reset visited array
		System.out.print("DFS traversal (recursive): ");
		_DFS(v);
		System.out.println();
	}

	private void _DFS(int v) {
		visited[v] = true;
		System.out.print(v + " ");
		for (int i = 0; i < n; i++) {

		}
	}

	// Non-recursive DFS using a stack
	void NonRecursiveDFS(int v) {
		visited = new boolean[n]; // Reset visited array
		Stack<Integer> stack = new Stack<>();
		stack.push(v);

		System.out.print("DFS traversal (non-recursive): ");
		while (!stack.isEmpty()) {

		}
		System.out.println();
	}
}

public class Train_ex11_02_GraphDFSBFS_Matrix {

	static final int N = 8;

	static int[][] makeGraph() {
		return new int[][] { { 0, 1, 1, 0, 0, 0, 0, 0 }, { 1, 0, 0, 1, 1, 0, 0, 0 }, { 1, 0, 0, 0, 0, 1, 1, 0 },
				{ 0, 1, 0, 0, 0, 0, 0, 1 }, { 0, 1, 0, 0, 0, 0, 0, 1 }, { 0, 0, 1, 0, 0, 0, 0, 1 },
				{ 0, 0, 1, 0, 0, 0, 0, 1 }, { 0, 0, 0, 1, 1, 1, 1, 0 } };
	}

	static void showMatrix(int[][] m) {
		System.out.println("adjacency matrix::");
		for (int[] row : m) {
			for (int num : row) {
				System.out.print(num + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int[][] matrix = makeGraph();
		showMatrix(matrix);
		Scanner sc = new Scanner(System.in);
		int select;
		int startNode = 0; // Default start node for BFS/DFS

		Graph2 g = new Graph2(N);

		// Initialize the graph with edges from the matrix
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == 1) {
					g.insertEdge(i, j);
				}
			}
		}

		while (true) {
			System.out.println("\n명령 선택 1: 그래프 행렬 출력, 2: BFS, 3: DFS (Recursive), 4: DFS (Non-Recursive), 5: 종료 => ");
			select = sc.nextInt();
			switch (select) {
			case 1:
				g.displayMatrix();
				break;
			case 2:
				System.out.println("Start BFS from node: " + startNode);
				g.BFS(startNode);
				break;
			case 3:
				System.out.println("Start DFS (recursive) from node: " + startNode);
				g.DFS(startNode);
				break;
			case 4:
				System.out.println("Start DFS (non-recursive) from node: " + startNode);
				g.NonRecursiveDFS(startNode);
				break;
			case 5:
				System.out.println("Exiting...");
				sc.close();
				System.exit(0);
			default:
				System.out.println("WRONG INPUT. Re-Enter.");
				break;
			}
		}
	}
}
