package ch05;

import java.util.Arrays;
import java.util.Optional;

//8*8의 경우에는 이동경우의 수를 전역으로 두지 않고, 휴리스틱(heuristic)한 방법으로 경우의 수를 최소한으로 줄이기
public class Knight {
	private static final int SIZE = 8;
	private static final int[][] board = new int[SIZE][SIZE];
	private static final int[] xMoves = { 2, 1, -1, -2, -2, -1, 1, 2 };
	private static final int[] yMoves = { 1, 2, 3, 1, -1, -2, -2, -1 };

	private record Point(int x, int y) {
	}

	public static void main(String[] args) {

		initBoard();

		if (solve(0, 0)) {
			System.out.println("기사의 여행 성공");
			printBoard(); // 경로 제출
		} else
			System.out.println("기사의 여행 실패");

		// 백트래킹 이후에 보드판도 물리기
	}

	// 보드판을 초기화
	private static void initBoard() {
		for (int i = 0; i < SIZE; i++) {
			Arrays.fill(board[i], 0);
		}
	}

	// 문제를 해결
	private static boolean solve(int startX, int startY) {
		//1. 현재위치 가져오기
		Point current = new Point(startX,startY);
		//2. 보드에 추가 
		board[current.y()][current.x()]=1;
		//3. 가능한 경우의 수를 다 순회
		for(int movecount =2;movecount<=SIZE*SIZE;movecount++) {
			final Point finalCurrent = current;
			Optional<Point> nextmove = getPossibleMoves(finalCurrent)
					.min(Comparator.comparing(p->(int)getPossibleMoves(p).count()));
			//이동
			//isSafe 
			//둘다 확인
			
			if(nextMove.isEmpty()) {
				return false;
			}
			
			current = nextMove.get();
			board[current.y()][current.x()]=movecount;
		}
		
		private static stream<Point> getPossibleMoves(Point p) {
			return Stream.iterate(0, i->i+1)
					.limit(xMoves.length)
					.map(i->new Point(p.x()+xMoves[i], p.y()+yMoves[i]))
					.filter(Knight::isVaild);
		}
		
		private static boolean isValid(Point p) {
			return p.x()>=0 && p.x()<SIZE &&
				p.y()>=0 && p.y()<SIZE &&
				board[p.y()][p.x()] == 0;
		}
}

	// 보드판을 출력
	private static void printBoard() {
		for (int[] row : board) {
			for (int num : row) {
				System.out.println(num);
			}
		}
	}

}
