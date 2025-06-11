package ch05;
/*
문제 설명
Knight's Tour 문제는 체스판에서 나이트(Knight) 말이 모든 체스판의 칸을 한 번씩만 방문하면서 체스판의 모든 방을 방문하면 종료. 
나이트는 체스에서 "L" 모양으로 움직이는데, 두 칸 직진하고 한 칸 옆으로 이동하는 방식입니다. 단, 임의 위치에서 시작

종료조건: 모든 칸이 방문하였을 때 종료 > 방문한 순서를 출력

구현조건: (x,y)를 저장하는 point 객체를 사용하여 스택으로 non-recursive backtracking 알고리즘으로 구현
 */

import java.util.Arrays;
import java.util.Stack;
//enum : 각 방향의 이름에 의미를 부여
//enum knightMoves {NW, NE, EN, ES, SE, SW, WS, WN}

// Point1 객체로 나이트의 위치(x,y)를 저장
class Point1 {
	int x, y, moveCount;
	int nextDirection;
	
	Point1(int x, int y, int moveCount) {
		this.x = x;
		this.y = y;
		this.moveCount = moveCount;
		this.nextDirection = 0;
	}
}

//현재위치에서 a,b 더해서 다음 위치를 결정함
class Offsets4 { 
	int a;
	int b;
	
	public Offsets4(int a, int b) {
		this.a = a; this.b = b;
	}
}

public class Train_ex05_09_KnightTracking {

	// 5X5 체스판 만들기 
	static final int N = 5;
	// 체스판 배열
	private static int[][] board = new int[N][N];
	// 나이트가 이동할 수 있는 방향의 수 8가지
	static Offsets4[] moves = new Offsets4[8];
	
	public static void main(String[] args) {
		initializeBoard();	
		initializeMoves();
		if (solveKnightTracking(0, 0)) { // 나이트가 (0, 0)에서 시작해서 성공하면 
			showTracking(); //그 경로를 보여줌
		} else {
			System.out.println("해결할 수 없습니다."); //실패한 경우
		}
	}

	// 8가지 이동방향을 초기화 
	private static void initializeMoves() {
		moves = new Offsets4[] {
				new Offsets4(-1, -2),
				new Offsets4(-2, -1), 
	            new Offsets4(-2,  1), 
	            new Offsets4(-1,  2), 
	            new Offsets4( 1,  2), 
	            new Offsets4( 2,  1), 
	            new Offsets4( 2, -1), 
	            new Offsets4( 1, -2)  
		};
	}
	
	// 체스판을 초기화 -> 보드를 모두 -1로 초기화
	private static void initializeBoard() {
		for (int i = 0; i < N; i++) {
			Arrays.fill(board[i], -1);
		}
	}
	
	// 체스판의 범위 내에서 유효한 움직임(체스판의 범위 내인가? + 아직 방문하지 않은 칸인가?)인지 확인
	private static boolean isSafe(int x, int y) {
		return (x >= 0 && x < N && y >= 0 && y < N && board[x][y] == -1);
	}    

    // 나이트 투어 알고리즘 (비재귀적으로 스택 사용)
    private static boolean solveKnightTracking(int startX, int startY) {
        Stack<Point1> stk = new Stack<>();
        // 시작 위치를 스택에 푸시하고 보드에 방문표시 -> 0
        stk.push(new Point1(startX, startY, 0));
        board[startX][startY] = 0;

        while (!stk.isEmpty()) {
        	Point1 current = stk.peek(); //스택 맨 윗부분을 사용해 
        	//모든 칸을 방문했는지를 확인 (moveCount가 전체 이동가능횟수와 동일한지?) 
        	if(current.moveCount== N*N-1) {
        		return true; //동일하면 성공한 것
        	}
        	
        	//아닌 경우, 8가지 방향 for문 돌면서 나이트 이동이 가능한지 시도
        	//먼저 플래그 설정 : 다음 위치로 이동이 가능한지 여부
        	boolean nextMove = false;
        	
        	for(int i=current.nextDirection;i<moves.length;i++) {
        	     int nextX = current.x + moves[i].a;
                 int nextY = current.y + moves[i].b;
                 
                 if (isSafe(nextX, nextY)) { //다음 위치로 이동이 가능하다면 스택에 푸시하고 보드에 방문표시 -> moveCount -> for문 종료
                     current.nextDirection = i+1;
                	 Point1 nextPos = new Point1(nextX, nextY, current.moveCount + 1);
                     stk.push(nextPos);
                     board[nextX][nextY] = nextPos.moveCount;
                     nextMove = true;
                     break;
                 }
             }
        	
        	//8방향 모두 이동이 불가능하다면
             if (!nextMove) {
                 Point1 back = stk.pop();
                 board[back.x][back.y] = -1; //보드에 moveCount로 마킹했던 것을 -1로 지워버림
             }
         }
        return false; //스택이 전부 비었는데도 해결하지 못함 -> 실패
    }

    
    // 결과 출력
    private static void showTracking() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%2d ", board[i][j]);
            }
            System.out.println();
        }
    }
}