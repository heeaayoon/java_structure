package ch02;

import java.util.Random;

//교재 83 - 배열 처리 + function parameter 전달 숙달 훈련 
//함수에서 배열을 리턴할 때 리턴 타입 정의할 수 있어야 한다

public class Train_ex02_06 {
	public static void main(String[] args) {
		int[][] A = new int[2][3];
		int[][] B = new int[3][4];
		int[][] C = new int[2][4];

		inputData(A);
		inputData(B);
		int[][] D = A.clone(); //교재83 - 배열 복제
		
		System.out.print("A[2][3] = ");
		showData("행렬 A", A);
		System.out.print("B[3][4] = ");
		showData("행렬 B", B);
		System.out.print("D[2][3] = ");
		showData("행렬 D", D);
		System.out.println();
		
		
		int [][]E = addMatrix(A,D);
		System.out.print("E[2][3] = ");
		showData("행렬 E", E);
		
		C = multiplyMatrix(A,B);
		System.out.print("C[2][4] = ");
		showData("행렬 C", C);

		int [][]F = transposeMatrix(B);
		System.out.print("F[4][3] = ");
		showData("행렬 F", F);
		
		C= multiplyMatrixTransposed(A,F);
		System.out.print("C[2][4] = ");
		showData("행렬 곱셈 결과-전치행렬 사용", C);
		boolean result = equals(A,C);
		if (result)
			System.out.println("행렬 A,C는 equal이다");
		else
			System.out.println("행렬 A,C는 equal 아니다");
	}
	
	
	static void inputData(int[][] data) {
		Random rd = new Random();
		for(int i=0;i<data.length;i++) {
			for(int j=0;j<data[i].length;j++) {
				data[i][j] = rd.nextInt(50);
			}
		}
	}
	static void showData(String msg, int[][] items) {
		//System.out.println(msg);
		System.out.print("[");
		for(int i=0;i<items.length;i++) {
			System.out.print("[");
			for(int j=0;j<items[i].length;j++) {				
				System.out.print(items[i][j]+" ");
			}
			System.out.print("]");
		}
		System.out.println("]");
	}
	
	//행렬 a,b의 행의 수, 열의 수가 같아야 하고 각 원소가 같아야 한다.
	static boolean equals(int[][] a, int[][] b) {
		for(int i=0;i<a.length;i++) {
			for(int j=0;j<a[i].length;j++) {				
				if(a[i][j]==b[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
	
	//행렬의 합
	static int[][] addMatrix(int[][] X, int[][] Y) {
		int[][] Z = new int[X.length][X[0].length]; //2차원 배열의 길이로 초기화하는 방법?
		for(int i=0; i<X.length;i++) {
			for(int j=0;j<X[i].length;j++) {
				Z[i][j]=X[i][j]+Y[i][j];
			}
		}
		return Z;
	}
	
	//행렬의 곱
	static int[][] multiplyMatrix(int[][] X, int [][] Y) {
		int[][] Z = new int[X.length][Y[0].length];
		for (int i = 0; i < X.length; i++) {
			for (int j = 0; j < Y[0].length; j++) {
				for (int k = 0; k < X[0].length; k++) {
					Z[i][j] += X[i][k] * Y[k][j];
				}
			}
		}
		return Z;
	}
	
	//행렬의 전치 
	static int[][] transposeMatrix(int[][] X) {
		int[][] Z = new int[X[0].length][X.length];
		for(int i=0;i<Z.length;i++) {
			for(int j =0;j<Z[i].length;j++) {
				Z[i][j] = X[j][i];
			}
		}
		return Z;
	}
	
	
	static int[][] multiplyMatrixTransposed(int[][] X, int[][] Y){
		int[][] Z = new int[X.length][Y.length];
		for (int i = 0; i < X.length; i++) {
			for (int j = 0; j < Y[0].length; j++) {
				for (int k = 0; k < Y.length; k++) {
					Z[i][j] += X[i][j] * Y[k][j];
				}
			}
		}
		return Z;
	}
}