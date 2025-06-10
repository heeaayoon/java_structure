package ch05;
/*
문제 설명
Knight's Tour 문제는 체스판에서 나이트(Knight) 말이 모든 체스판의 칸을 한 번씩만 방문하면서 체스판의 모든 방을 방문하면 종료. 
나이트는 체스에서 "L" 모양으로 움직이는데, 두 칸 직진하고 한 칸 옆으로 이동하는 방식입니다. 단, 임의 위치에서 시작

종료조건: 모든 칸이 방문하였을 때 종료 > 방문한 순서를 출력

구현조건: (x,y)를 저장하는 point 객체를 사용하여 스택으로 non-recursive backtracking 알고리즘으로 구현
 */

import java.util.Stack;

enum knightMoves {NW, NE, EN, ES, SE, SW, WS, WN}

class Offsets4 { //현재위치에서 a,b 더해서 다음 위치를 결정함
	int a;
	int b;
	public Offsets4(int a, int b) {
		this.a = a; this.b = b;
	}
}

public class Train_ex05_09_KnightTracking {

	public static void main(String[] args) {
		initializeBoard();	
		// 나이트가 (0, 0)에서 시작
		if (solveKnightTracking(0, 0)) {
			showTracking();
		} else {
			System.out.println("해결할 수 없습니다.");
		}
	}
	
	static Offsets4[] moves = new Offsets4[8];
    
	static final int N = 5;

	
    // 체스판 배열
    private static int[][] board = new int[N][N];

    // Point 객체로 나이트의 위치를 저장
    static class Point {
        int x, y, moveToward;

        Point(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.moveToward = move;
        }
    }

    // 체스판을 초기화 (-1로 설정)
    private static void initializeBoard() {
    }

    // 체스판의 범위 내에서 유효한 움직임인지 확인
    private static boolean isSafe(int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < N && board[x][y] == -1);
    }

    // 나이트 투어 알고리즘 (비재귀적으로 스택 사용)
    private static boolean solveKnightTracking(int startX, int startY) {
    	for (int ia = 0; ia < N; ia++)
    		moves[ia] = new Offsets4(0, 0);//배열에 Offsets4 객체를 치환해야 한다.
    	// 나이트가 이동할 수 있는 8가지 방향
    	moves[0].a = -2;	moves[0].b = -1;//NW으로 이동
    	moves[1].a = -2;	moves[1].b = 1;//NE
    	moves[2].a = -1;	moves[2].b = 2;//EN
    	moves[3].a = 1;		moves[3].b = 2;//ES
    	moves[4].a = 2;		moves[4].b = 1;//SE
    	moves[5].a = 2;		moves[5].b = -1;//SW
    	moves[6].a = -1;	moves[6].b = -2;//WS
    	moves[7].a = 1;		moves[7].b = -2;//WN
        
        Stack<Point> stack = new Stack<>();

        // 시작 위치를 스택에 푸시
        stack.push(new Point(startX, startY, 0));
        board[startX][startY] = 0; // 시작 위치는 첫 번째 이동

        while (!stack.isEmpty()) {
            
            // 8가지 방향으로 나이트 이동 시도
        	// stack.peek()
           

            // 더 이상 이동할 곳이 없을 경우
           
        }

        return false; // 해결하지 못함
    }

    // 결과 출력
    private static void showTracking() {

    }

}
