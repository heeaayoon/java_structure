package ch06;
//버블 정렬 - p.202, 그림 6-3, 6-4
//버블 정렬(단순 교환 정렬) (버전 1)
//시간복잡도를 줄여서 알고리즘 성능을 강화하는 것이 목표임
import java.util.Random;
import java.util.Scanner;

public class Train_ex06_01_BubbleSort01 {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.println("버블 정렬(버전 1)");
		System.out.print("요솟수: ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];
		Random rand = new Random(42);//고정된 난수가 생성 - seed 값 사용 
		
		for (int i = 0; i < nx; i++) {
			x[i] = rand.nextInt(999);
		}
		System.out.println("정렬전:");
		showData(x);
		bubbleSort(x, nx);                // 배열 x를 단순교환정렬
		
		System.out.println("정렬후:");
		showData(x);
	}
	
	//배열 요소 a[idx1]와 a[idx2]의 값을 교환
	 static void swap(int[] a, int idx1, int idx2) {
	     int t = a[idx1]; a[idx1] = a[idx2]; a[idx2] = t;
	 }

	 //버블 정렬
	 static void bubbleSort(int[] a, int n) {
		 int count = 0;
	     for (int i = 0; i < n - 1; i++)//패스
	         for (int j = n - 1; j > i; j--){ //끝에서 앞쪽으로 이동하면서 버블 처리
	        	 count++;
	             if (a[j - 1] > a[j]) //버블
	                 swap(a, j - 1, j); //단순교환 정렬
	         }
	     System.out.println("\n비교 횟수 = " + count);
	 }
	 
	 static void showData(int[] d) {
	     for (int i = 0; i < d.length; i++)
	         System.out.print(d[i] + " ");
	 }
}
