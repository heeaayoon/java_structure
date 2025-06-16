package ch11;
/*
 * 해밀턴 경로 (Hamiltonian Path)란?
 * 모든 정점을 정확히 한 번씩 방문하는 경로
 */

import java.util.Arrays;
import java.util.Stack;

class Sets {
    private int[] parent;
    private int n;

    public Sets(int sz) {
        n = sz;
        parent = new int[sz + 1]; // Don't want to use parent[0]
        Arrays.fill(parent, -1);  // 0 for Simple versions
    }

    public void display() {
        System.out.print("display:index= ");
        for (int i = 1; i <= n; i++) System.out.print(" " + i);
        System.out.println();

        System.out.print("display: value= ");
        for (int i = 1; i <= n; i++) System.out.print(" " + parent[i]);
        System.out.println();
    }
    void RemoveSet(int i) {
    	//set에서 i를 제거하는 알고리즘 구현 - 중간 노드로 i가 있을 때, root로 i가 있을 때 해결 방법이 필요
    }
    public void SimpleUnion(int i, int j) {
       
    }

    public int SimpleFind(int i) {
       
    }

    public void WeightedUnion(int i, int j) {
       
    }

}
class Graph2 {
    private int numVertex; // 노드 숫자
    private int[][] graph; // Adjacency matrix
    private int[] path; // 해밀턴 경로를 배열로 저장

    Graph2(int[][] graph) {
        this.numVertex = graph.length;
        this.graph = graph;
        this.path = new int[numVertex];
        
        // 미방문 경로는 -1로 초기화
        for (int i = 0; i < numVertex; i++) {
            path[i] = -1;
        }
    }
    void show() {
    	for (int n: path)
    		System.out.print(" " + n);
    	System.out.println();
    }
    boolean findPath(int start) {
        Stack<Integer> stack = new Stack<>();
        path[start] = 0;
        stack.push(start); // 노드 p부터 시작
        Sets sts = new Sets(8);
        int pos = 1;
        int direction = -1;
        while (!stack.isEmpty()) {
        	
        }

        System.out.println("해밀턴 경로 없다.");
        return false;
    }

    boolean isSafe(int currentNode, int nextNode) {
        // 새로 방문할 노드 v가 이전에 방문할 노드에 연결되었는지 검사
   
        return true;
    }

    void showPath() {
        System.out.println("해밀턴 경로:");
        for (int i = 0; i < numVertex; i++) {
            System.out.print(path[i] + " ");
        }
        System.out.println(path[0]); // 해밀턴 경로
    }
}
public class Train_ex11_04_HamiltonianPath {
	public static void main(String[] args) {
        int[][] graph = {
            {0, 1, 0, 1, 0, 0},
            {1, 0, 1, 1, 1, 0},
            {0, 1, 0, 0, 1, 1},
            {1, 1, 0, 0, 1, 0},
            {0, 1, 1, 1, 0, 1},
            {0, 0, 1, 0, 1, 0}
        };
        
        Graph2 g = new Graph2(graph);
        g.findPath(0);
    }
}
