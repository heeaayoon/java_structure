package ch05;

import java.util.Stack;

//미로 찾기 문제
//미로 테이블 (12,15)을 상하좌우 울타리를 친 maze[14][17]을 만들고 입구는 (1,1)이며 출구는(12,15)
//stack을 사용한 backtracking 구현
//성공한 경로는 2로 표시, 나머지는 0이나 1로 표현한 최종 미로 지도 출력

enum Directions {N, NE, E, SE, S, SW, W, NW} //8방향의 이름에 각각 의미를 부여함 

//Items : 스택에 저장할 정보 
class Items {
	int x;
	int y;
	int dir; //dir은 다음에 시도할 방향 -> enum Directions의 값을 저장함
	
	public Items(int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
}

//offsets : 좌표 변화량
class Offsets {
	int a; //x좌표 변화량
	int b; //y좌표 변화량
	
	public Offsets(int x, int y) {
		a=x;
		b=y;
	}
}

public class Train_ex05_11_Maze_assign {
	
	static Offsets[] moves = new Offsets[8]; //8개의 방향을 담을 moves배열 생성

	//미로 찾기 핵심 메소드
	static void path(int maze[][], int mark[][], int m, int p){ //maze[][] : 미로 지도, mark[][] : 방문기록 표시 지도, m : 출구의 x좌표, p : 출구의 y 좌표
		//Items를 담을 스택 생성 
		Stack<Items> st = new Stack<>();
		//맨 처음에 시작점(1,1)과 시작이동방향 dir = 2(E)을 스택에 푸시 & mark[][]에 1로 마킹
		Items startItem = new Items(1,1,Directions.E.ordinal());
		mark[1][1]=1;
		st.push(startItem);
		
		while(!st.isEmpty()) { 
			//스택에서 최근에 저장된 정보를 pop
			Items current = st.pop();
			int i = current.x;
			int j = current.y;
			int dir = current.dir;
			
			while(dir<8) { //남은 8가지 방향을 전부 돌기
				int g = i + moves[dir].a; // 다음위치 (g,h) 
				int h = j + moves[dir].b;
				
				//출구를 발견 했을 때 -> 성공 경로를 2로 표시
				if ((g == m) && (h == p)) { 
					mark[i][j]=2;
					mark[g][h]=2;
					//스택에 남은 모든 경로를 2로 변경
					while(!st.isEmpty()) {
						Items pathItem = st.pop();
						mark[pathItem.x][pathItem.y] = 2;
					}	
					System.out.println("경로를 찾음");
					return;
				}
				
				//이동이 가능할 때(길이 0이고, 아직 방문하지 않은 곳)
				if((maze[g][h]==0)&&(mark[g][h]==0)) {
					 mark[g][h] = 1;
					 //현재 위치와 다음 시도할 방향을 스택에 미리저장(백트래킹 포인트 저장)
					 st.push(new Items(i, j, dir+1)); //새로운 객체를 생성해서 푸시
					 //실제로 이동하기
					 i=g;
					 j=h;
					 dir = 0; //새로운 위치(g,h)로 이동했으므로 dir=0부터 다시 탐색을 시작
				}else { //이동이 불가할 때 -> 다음 방향을 시도하기
					dir++;					
				}
			}
			//현재 (i,j)에서 8방향이 모두 막힌 경우(한번도 루프를 돌지 못한 경우) -> mark 0으로 지우기
			mark[i][j] = 0;				
		}
		//스택이 모두 빌때까지 출구를 찾지 못함
		System.out.println("No path found");
	}
	
	public static void main(String[] args) {
		int[][] maze = new int[14][17];
		int[][] mark = new int[14][17];

		int input[][] = { // 12 x 15
				{ 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1 },
				{ 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1 },
				{ 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1 },
				{ 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0 },
				{ 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1 },
				{ 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1 },
				{ 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1 },
				{ 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1 },
				{ 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0 },
				{ 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 },
				{ 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0 }};
		
		for (int ia = 0; ia < 8; ia++)
			moves[ia] = new Offsets(0, 0); //일단 moves 배열을 전부 Offsets(0, 0)으로 채워두기
		moves[0].a = -1;	moves[0].b = 0; //moves 배열을 수정함
		moves[1].a = -1;	moves[1].b = 1;
		moves[2].a = 0;		moves[2].b = 1;
		moves[3].a = 1;		moves[3].b = 1;
		moves[4].a = 1;		moves[4].b = 0;
		moves[5].a = 1;		moves[5].b = -1;
		moves[6].a = 0;		moves[6].b = -1;
		moves[7].a = -1;	moves[7].b = -1;

		
		//확장 미로 생성 -> 벽 부분은 1로 만들기
		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 17; j++) {
				if(i==0||i==13||j==0||j==16) {
					maze[i][j]=1;
				}else { //input[][]을 maze[][]로 복사
					maze[i][j] = input[i-1][j-1];
				}
			}
		}
		show("초기 미로 상태", maze);
		show("초기 mark", mark);

		path(maze, mark, 12, 15);
		//show("미로찾기 결과", maze);
		show("결과 mark", mark);	
	}
	
	private static void show(String str, int[][] arr) {
		System.out.println(str);
		for(int i =0;i<arr.length;i++) {
			for(int j =0; j<arr[0].length;j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
}