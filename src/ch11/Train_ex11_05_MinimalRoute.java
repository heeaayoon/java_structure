package ch11;

//그래프 최단 경로 : BFS문제
//다익스트라 알고리즘
import java.util.Arrays;
import java.util.Scanner;

class Graph5 {
    private static final int NMAX = 10;
    private static final int MAX_WEIGHT = 999999;
    private int[][] length = new int[NMAX][NMAX];
    private int[] dist = new int[NMAX];
    private boolean[] s = new boolean[NMAX];
    private final int n;
    
    //생성자
    public Graph5(int nodeSize) {
    	this.n = nodeSize;
    	
    	//배열 초기화
    	for(int i=0;i<n;i++) {
    		for(int j=0;j<n;j++) {
    			if(i==j) //자신으로 가는 경로 -> 0으로 초기화
    				length[i][j]=0;
    			else //자신으로 가는 경로가 아니면 //최단거리를 구할 때는 비교할 디폴트값을 최댓값으로 두기
    				length[i][j]=MAX_WEIGHT;
    		}
    	}	
    }

    //노드 사이 연결
    public void insertEdge(int start, int end, int weight) { //시작노드, 끝노드, 가중치
    	this.length[start][end]=weight;
    }

    //현재 연결된 정보를 출력
    public void displayConnectionMatrix() {
    	for(int i=0;i<n;i++) {
    		for(int j=0;j<n;j++) {
    			System.out.print((length[i][j]==MAX_WEIGHT? "∞" : length[i][j])+" \t");
    		}
    		System.out.println();
    	}
 
    }

    //음수가중치를 가진 길이 있는지 검사 
    public boolean isNonNegativeEdgeCost() {
    	for(int i=0;i<n;i++) {
    		for(int j=0;j<n;j++) {
    			//음수가 존재하면 false 반환
    			if(length[i][j]!=MAX_WEIGHT && length[i][j]<0) return false;
    		}
    	}
    	return true;
    }

    //최종최단거리를 찾는 메소드
    public void shortestPath(int startNode) {
        //초기화
    	Arrays.fill(s, false);
        for (int i = 0; i < n; i++) {
            dist[i] = length[startNode][i];
        }
        s[startNode] = true;
        dist[startNode] = 0;
        
        //노드 최단경로 찾기
        for (int i = 0; i < n - 1; i++) {
        	int next = choose();
        	s[next] = true; //해당 노드를 기록하기
            for (int w = 0; w < n; w++) { 
                if (!s[w] && length[next][w] != MAX_WEIGHT && dist[next] + length[next][w] < dist[w]) { 
                    dist[w] = dist[next] + length[next][w]; 
                } 
            }
        }
        printDistances(startNode);
    }

    //최단 거리의 노드를 선택(인덱스 반환)
    private int choose() {
        int minDist = MAX_WEIGHT;
        int node = -1;
        
        //전체 노드를 순회하면서 -> 검사
        for (int i=0;i<n;i++) {
        	if(!s[i]&&dist[i]<minDist) { //아직 최단경로가 미정 && minDist보다 단거리인가?
        		minDist = dist[i];
        		node = i;
        	}	
        }
        return node; //최단경로 없으면 -1반환
    }

    private void printDistances(int startNode) {
        System.out.print("출발노드 " + startNode + ": ");
        for (int i = 0; i < n; i++) {
        	System.out.print("->("+i+")");
            System.out.print((dist[i] == MAX_WEIGHT ? "∞" : dist[i]) + " ");
        }
        System.out.println();
    }
}


public class Train_ex11_05_MinimalRoute {
	static void showMatrix(int[][] m) {
		System.out.println("Adjacency matrix:");
		for (int[] row : m) {
			for (int num : row) {
				System.out.print(num + " ");
			}
			System.out.println();
		}
	}
    public static int[][] makeGraph1() {
        return new int[][]{ //무향 그래프임(주대각선 기준 대칭) 
            {0, 6, 5, 7, 0, 0, 0},
            {6, 0, -2, 0, -1, 0, 0},
            {5, -2, 0, -2, 1, 0, 0},
            {7, 0, -2, 0, 0, -1, 0},
            {0, -1, 1, 0, 0, 0, 3},
            {0, 0, 0, -1, 0, 0, 8},
            {0, 0, 0, 0, 3, 8, 0}
        };
    }

    public static int[][] makeGraph2() {
        return new int[][]{
            {0, 300, 1000, 0, 0, 0, 0, 1700},
            {300, 0, 800, 0, 0, 0, 0, 0},
            {1000, 800, 0, 1200, 0, 0, 0, 0},
            {0, 0, 0, 1200, 1500, 1000, 0, 0},
            {0, 0, 0, 1500, 0, 250, 0, 0},
            {0, 0, 0, 1000, 250, 0, 900, 1400},
            {0, 0, 0, 0, 0, 900, 0, 1000},
            {1700, 0, 0, 0, 0, 1400, 1000, 0}
        };
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Graph5 g = null;

        System.out.println("1: 그래프(가중치 마이너스), 2: 그래프(도시간 거리)");
        int select = scanner.nextInt();
        if (select == 1) {
            int[][] matrix = makeGraph1();
            showMatrix(matrix);
            g = new Graph5(7);
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j] != 0) {
                        g.insertEdge(i, j, matrix[i][j]);
                    }
                }
            }
        } else if (select == 2) {
            int[][] matrix = makeGraph2();
            showMatrix(matrix);
            g = new Graph5(8);
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j] != 0) {
                        g.insertEdge(i, j, matrix[i][j]);
                    }
                }
            }
        } else {
            System.out.println("Invalid input. Exiting.");
            scanner.close();
            return;
        }

        while (true) {
            System.out.print("\nCommands: 1: Display Matrix, 2: Dijkstra (non-negative), 3: Quit => ");
            select = scanner.nextInt();
            if (select == 3) break;

            switch (select) {
                case 1:
                    g.displayConnectionMatrix();
                    break;
                case 2:
                    if (g.isNonNegativeEdgeCost()) {
                        System.out.print("Start node: ");
                        int startNode = scanner.nextInt();
                        g.shortestPath(startNode);
                    } else {
                        System.out.println("Negative edge가 존재하므로 Bellman-Ford 알고리즘 사용해야 한다.");
                    }
                    break;
                default:
                    System.out.println("Invalid input. Try again.");
            }
        }
        scanner.close();
    }
}
