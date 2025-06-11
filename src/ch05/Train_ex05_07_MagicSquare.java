package ch05;

//매직 스퀘어(Magic Square) : n*n 크기의 정사각형 배열에 숫자를 배치하되, 모든 행, 열, 대각선의 숫자 합이 동일하게 되는 배열을 말합니다. 
//이때 이 동일한 합을 매직 상수(Magic Constant)라고 합니다.
//n은 3,5,7 등 홀수일 때

//루벤스의 방법 단계:
//1. 첫 번째 숫자를 첫 번째 행의 가운데 열(N/2의 정수값)에 배치합니다.
//2. 다음 숫자는 항상 대각선 위 오른쪽(북동쪽)으로 이동하여 배치합니다.
//    2.1 만약 배열의 경계를 벗어나면(음수) 반대편으로 이동합니다.
//        열(c)이 배열의 오른쪽 끝(N-1)을 벗어나면 맨 왼쪽 열(0)로 이동하고, -N
//        행(r)이 배열의 맨 위(0)를 벗어나면 맨 아래(N-1)로 이동합니다. +N
//3. 이미 숫자가 있는 칸에 도달한 경우, 현재 위치 바로 아래의 행으로 이동하여 다음 숫자를 배치합니다.

public class Train_ex05_07_MagicSquare {
	public static void main(String[] args) {
        int n = 3; // 마방진의 크기
        int[][] magicSquare = new int[n][n];
       
        int row = 0, col = n / 2; // 시작 위치
        for (int num = 1; num <= n * n; num++) {
        	magicSquare[row][col] = num; //현재 위치에 숫자 배치
        	
        	int nextRow = row-1;
            int nextCol = col+1;
            
            if(nextRow<0) nextRow=n-1; 
            if(nextCol>=n) nextCol=0;
            
            if(magicSquare[nextRow][nextCol]!=0) {
            	nextRow = row+1;
            	nextCol = col;
            }
            if(nextRow>=n) nextRow=0;
       
            row = nextRow;
            col = nextCol;
            //System.out.println(row+","+col);
            }

        // 마방진 출력
        showSquare(magicSquare);

        // 마방진의 합 확인
        int magicSum = n * (n * n + 1) / 2;
        System.out.println("가로, 세로, 대각선의 합 =  " + magicSum );
        System.out.println("마방진 검사 = " + checkSquare(magicSquare, magicSum));
    }

    // 마방진 출력 메서드
    static void showSquare(int[][] ms) {
    	for(int i=0;i<ms.length;i++) {
    		for(int j=0;j<ms[0].length;j++) {
    			System.out.print("["+ms[i][j]+"]");    			
    		}
    		System.out.println();
    	}
    }

    // 마방진 유효성 검증 메서드 -> 가로 세로 대각선의 합이 동일한지?
    static boolean checkSquare(int[][] ms, int magicSum) {
    	//가로 검사
    	for(int i=0;i<ms.length;i++) {
    		int sumrow =0;
    		for(int j=0;j<ms[0].length;j++) {
    			sumrow +=ms[i][j];
    		}
    		 if (sumrow != magicSum) return false;
    	}
    	//세로 검사
    	for(int i=0;i<ms.length;i++) {
    		int sumcol =0;
    		for(int j=0;j<ms[0].length;j++) {
    			sumcol +=ms[j][i];
    		}
    		if (sumcol != magicSum) return false;
    	}
    	
    	//대각선 우하향
    	int diagSum1 = 0;
        for (int i = 0; i < ms.length; i++) {
            diagSum1 += ms[i][i];
        }
        if (diagSum1 != magicSum) return false;
    	
        //대각선 좌하향
        int diagSum2 = 0;
        for (int i = 0; i < ms.length; i++) {
            diagSum2 += ms[i][ms.length - 1 - i];
        }
        if (diagSum2 != magicSum) return false;
        
    	return true;
    }
}
