package ch11;

/*
#최단거리 (heap 사용)
#choose() 메소드 필요 X

1. Node 객체 생성(vertex, distance)
	-최소거리 비교를 위해 campator 등을 활용
2. PriorityQueue<Node>
public void shortestPath(int startNode) {
	Arrays.fill(s, false);
	Arrays.fill(dist, MAX_WEIGHT);
	PriorityQueue<Node> pq = new PriorityQueue<>();
	s[startNode] = true;
	pq.offer(new Node(startNode,0));
	while(!pq.isEmpty()) {
		//1. 거리가 가장 짧은 노드 선택
		//2. 이미 선택된 노드이면 제외
		//3. 선택되지 않은 노드이면 방문처리
		//4. 인접한 모든 노드에 대해 거리 갱신
		//4-1. 더 짧은 거리를 발견하면 heap에 추가
	}
	printDistances(startNode);
}
*/

//그래프 최단 경로 : BFS문제
//1. 벨만포드
//2. 다익스트라
//3. 무방향
//4. 행렬

import java.util.Arrays;
import java.util.Scanner;


//Graph6는 최단거리에 사용되는 자료구조 
class Graph6 {
    private static final int NMAX = 10;
    private static final int MAX_WEIGHT = 999999;
    private int[][] length = new int[NMAX][NMAX]; //노드가 10개인 10X10 행렬
    private int[] dist = new int[NMAX]; //거리의 기본값 : 999999 //최단거리를 구할 때 => (디폴트)최대값으로 초기화
    
    private boolean[] s = new boolean[NMAX]; //왜 필요한지?
    private final int n; //왜 필요한지? 필드인데 초기화가 안되어 있음 -> 생성자에서 초기화
    
    //생성자의 가장 큰 역할 => 필드 초기화 
    public Graph6(int nodeSize) {
    	this.n = nodeSize;
    	
    	//그래프 초기화
    	for(int i=0;i<n;i++) {
    		Arrays.fill(length[i], MAX_WEIGHT); //배열 한 줄씩 MAX_WEIGHT으로 채우기
    		length[i][i] = 0;  //주대각선을 전부 0으로 
    	}
    }

    //간선을 추가하는 메소드
    public void insertEdge(int start, int end, int weight) {
    	length[start][end]= weight;
    	//length[end][start]= weight; //여기선 무향그래프이기 때문에 주대각선 위만 사용하려면 이 코드가 필요없음
    }

    //행렬을 출력하는 메소드 
    public void displayConnectionMatrix() {
    	for(int i=0;i<n;i++) {
    		for(int j=0;j<n;j++) {
    			if(length[i][j]==MAX_WEIGHT) { //이 경로로는 갈 수 없음
    				System.out.print("∞"+" ");
    			}else {
    				System.out.print(length[i][j]+" ");
    			}
    		}
    		System.out.println();
    	}
    }
    
    //음수인지 아닌지? -> 벨만코드 알고리즘을 사용해야 하는지?
    public boolean isNonNegativeEdgeCost() {
    	for(int i=0;i<n;i++) {
    		for(int j=0;j<n;j++) {
    			//음수를 찾으면 false 반환
    			if(length[i][j]!=MAX_WEIGHT && length[i][j]<0) return false;
    		}
    	}
    	//음수를 못찾으면 true 반환
    	return true;
    }
    
    //핵심 메소드 : 최단거리 구하기
    public void shortestPath(int startNode) {
        Arrays.fill(s, false);
        for (int i = 0; i < n; i++) {
            dist[i] = length[startNode][i]; //dist[b] = length[a][b], dist[c] = length[a][c] //특정노드 a에서 갈 수 있는(a~b/a~c/... 모든 경로
        }
        s[startNode] = true;
        dist[startNode] = 0;
        
        //전역탐색으로 최단거리 구하기(다익스트라 알고리즘)
        for (int i = 0; i < n - 1; i++) {
        	//가장 작은 값 가져오기(choose 메소드 이용)
        	int u = choose();
        	//s[인덱스] = true 로 방문기록 남기기
        	s[u] = true;
        	//최단거리를 합산해서 비교하기
        	for(int j=0;j<n;j++) {
        		if(!s[j]&&dist[i]+length[u][j]<dist[j]) { //기존 값보다 더 작으면 
        			dist[i] = dist[i]+length[u][j]; //갈아끼우기
        		}
        	}
        }
        printDistances(startNode);
    }

    //가장 작은 값의 인덱스를 반환
    private int choose() {
        int minDist = MAX_WEIGHT;
        int minPos = -1;
        for(int i=0;i<n;i++) {
        	if(!s[i]&&dist[i]<minDist) {
        		minDist = dist[i];
        		minPos = i;
        	}
        }
        return minPos;
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


public class Train_ex11_05_MinimalRoute_Professor {
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
                    if (matrix[i][j] != 0) { //왜 0이 아닌 경우에만? 
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
