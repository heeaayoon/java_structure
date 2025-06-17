package ch05;
//8-queen 문제 - p.185만 보기 

//https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/?ref=lbp 참조

import java.util.Stack;

/*
* 8-Queen 문제는 체스판 위에 8개의 퀸을 서로 공격할 수 없도록 배치함
* Queen은 가로세로대각선에 같은 퀸을 둘 수 없음
* 비재귀적(스택 기반) 알고리즘으로 구현 -> backtracking 사용

개요
1. 스택을 사용하여 백트래킹을 구현합니다. 각 스택의 요소는 체스판의 각 열에 대한 퀸의 배치 상태를 나타냅니다.
2. 퀸을 한 줄씩 배치한 후, 유효한지 확인하고, 다음 줄로 이동합니다.
3. 유효하지 않으면 스택을 이용해 이전 상태로 돌아가서 다른 경로를 시도합니다.

알고리즘
1. 스택을 이용하여 백트래킹을 구현하기 때문에, 현재 상태를 스택에 저장합니다. -> 스택의 각 원소는 퀸의 배치를 나타냅니다.
2. 체스판의 각 열에 대해 가능한 위치를 하나씩 확인하면서 퀸을 배치하고, 충돌이 발생하지 않는다면 다음 열로 넘어갑니다.
3. 더 이상 유효한 위치가 없으면, 스택에서 이전 상태로 되돌아가서 새로운 경로를 탐색합니다.
4. 퀸을 8개 다 배치하면, 해를 찾은 것이므로 스택을 이용해 해결책을 저장합니다.
*/

//Point 객체로 퀸의 위치(ix,iy)를 저장
class Point {
	private int ix;
	private int iy;

	public Point(int x, int y) {
		ix = x;
		iy = y;
	}

	public int getIx() {
		return ix;
	}

	public int getIy() {
		return iy;
	}
}

public class Train_ex05_10_8Queen_assign {

	public static void main(String[] args) {
		int row = 8, col = 8;
		// 8X8 체스판 배열
		int[][] data = new int[8][8];
		// 체스판을 0으로 초기화
		for (int i = 0; i < data.length; i++)
			for (int j = 0; j < data[0].length; j++)
				data[i][j] = 0;
		EightQueen(data);
	}

	public static void EightQueen(int[][] d) {
		int numberOfSolutions = 0; // 92개 나오면 정답
		int count = 0; // 퀸 배치 갯수
		int ix = 0, iy = 0;// 행 ix, 열 iy
		
		//시작 -> (0,0)에 퀸을 놓으면서 시작함 
		//이 부분 지우고 0번째 행부터 검사 후 배치하도록 수정
//		Point p = new Point(ix, iy);// 현 위치를 객체로 만들고
//		d[ix][iy] = 1;// 현 위치에 queen을 넣었다는 표시를 하고
//		count++;

		Stack<Point> st = new Stack<>(); // 퀸의 위치(x,y)를 저장할 스택을 만들고
//		st.push(p);// 스택에 현 위치를 push
//		ix++; // 다음 행으로 넘어가서 퀸을 배치
//		iy = 0; // 다음 행의 0번째 열부터 퀸을 어디에 배치할 것인지 검사

		//해답을 전부 찾을 때까지 무한 반복
		while (true) {
			if ((iy = nextMove(d, ix, iy)) != -1) { // 다음 이동할 열이 -1이 아니면, 안전한 열을 찾은 것 -> 그 열에 퀸을 배치하고, (행,열)을 스택에 push
				d[ix][iy]=1; //보드에 퀸을 배치하고
				st.push(new Point(ix,iy)); //그 (행,열)을 스택에 push
				//다음 행으로 내려가서 열은 0부터 탐색 시작
				ix++;
				iy=0;
				//만약 8번째 행까지 도달한다면 -> 완성된 배치 1가지를 찾은것
				if(ix==8) {
					numberOfSolutions++;
					//showQueens(d); //그 배치를 보여줌
					//System.out.println();
					//다른 배치를 찾기 위해서는 일부러 한 번 백트래킹함, 마지막에 놓은 퀸도 지워야함
					Point tempP = st.pop(); //8번째 퀸의 위치 정보를 tempP에 저장
					ix=tempP.getIx(); //8번째 퀸이 놓여 있던 행의 위치(7행)을 ix에 저장
					iy=tempP.getIy()+1; //8번째 퀸이 놓여 있던 열의 위치+1로 돌아감 -> 0이 아니라 이전의 열의 값, 그 다음부터 검색을 시작함 (0부터 이전의 열의 값까지는 이미 검사한 것이기 때문에, 이전의 열의값+1부터 검사를 진행하면됨)
					d[ix][iy-1]=0; //8번째 퀸을 제거
				}
			} else { //안전한 열을 찾지 못한다면 (-1이면) 
				//-> 두가지 경우가 존재함 
				//1.스택에 값이 여전히 남아있고, 백트래킹이 필요함
				//2.백트랙킹을 계속 해서 0번째 행에서 마지막 열까지 모두 검사를 했지만 안전한 경로를 못찾은 경우엔 스택까지 완전히 비게됨
				if(st.isEmpty()) { //2번 경우, while문을 완전히 종료
					System.out.println("해답 :" + numberOfSolutions);
					break;
				}
				//1번의 경우에는 백트래킹을 진행 
				Point tempP = st.pop(); //마지막 퀸의 위치 정보를 tempP에 저장
				ix=tempP.getIx(); //마지막 퀸이 놓여 있던 행의 위치를 ix에 저장
				iy=tempP.getIy()+1; //마지막 퀸이 놓여 있던 열의 위치+1로 돌아감 
				d[ix][iy-1]=0; //마지막 퀸을 제거
			}
		}
	}

	// 퀸의 공격 경로에 다른 퀸이 있는지 검사하는 코드
	// 1.가로행에 이미 퀸이 배치되어 있는지 검사하는 메소드
	public static boolean checkRow(int[][] d, int crow) { // 배열에서 crow 행에 퀸을 배치할 수 있는지 조사
		for (int i = 0; i < d.length; i++) {
			if (d[crow][i] == 1)
				return false;
		}
		return true;
	}

	// 2.세로열에 이미 퀸이 배치되어 있는지 검사하는 메소드
	public static boolean checkCol(int[][] d, int ccol) {// 배열에서 ccol 열에 퀸을 배치할 수 있는지 조사
		for (int i = 0; i < d[0].length; i++) {
			if (d[i][ccol] == 1)
				return false;
		}
		return true;
	}

	// 3.좌상향, 우하향 대각선을 검사하는 메소드
	public static boolean checkDiagSW(int[][] d, int cx, int cy) { // x++, y-- or x--, y++ where 0<= x,y <= 7
		// 좌상향 대각선
		for (int a = cx - 1, b = cy - 1; a >= 0 && b >= 0; a--, b--) {
			if (d[a][b] == 1)
				return false;
		}
		// 우하향 대각선
		for (int a = cx + 1, b = cy + 1; a < d.length && b < d[0].length; a++, b++) {
			if (d[a][b] == 1)
				return false;
		}
		return true;
	}

	// 4.우상향, 좌하향 대각선을 검사하는 메소드
	public static boolean checkDiagSE(int[][] d, int cx, int cy) {// x++, y++ or x--, y--
		// 우상향 대각선
		for (int a = cx - 1, b = cy + 1; a >= 0 && b < d[0].length; a--, b++) {
			if (d[a][b] == 1)
				return false;
		}
		// 좌하향 대각선
		for (int a = cx + 1, b = cy - 1; a < d.length && b >= 0; a++, b--) {
			if (d[a][b] == 1)
				return false;
		}
		return true;
	}

	// 위의 4가지 메소드를 모두 호출해서 최종적으로 이동이 가능하지 검사, 이동이 가능하지 않으면 -1를 리턴
	public static boolean checkMove(int[][] d, int x, int y) {// (x,y)로 이동 가능한지를 check
		return checkRow(d, x) && checkCol(d, y) && checkDiagSE(d, x, y) && checkDiagSW(d, x, y);
	}

	//특정행에서 퀸을 놓을 수 있는 유효한 열을 찾아 그 열의 위치를 반환하는 메소드 //백트래킹으로 이전 행으로 돌아왔을 때에는 이전에 퀸을 놓았던 열의 다음 열부터 검사를 시작하는 것이 효율적임
	public static int nextMove(int[][] d, int row, int col) {
		for(int colPos = col; colPos<d.length;colPos++) { //주어진 열부터 끝까지 순회하면서
			if(checkMove(d, row, colPos)) return colPos; //안전한 열이 발견되면 그 열을 리턴함
		}
		return -1; //안전한 열이 발견되지 않으면 -1을 반환
	}

	//배열을 출력하는 메소드
	static void showQueens(int[][] data) {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				System.out.print(" " + data[i][j] + " ");
			}
			System.out.println();
		}
	}

}