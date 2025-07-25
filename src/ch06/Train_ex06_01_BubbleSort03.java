package ch06;

//버블 이동 - 교재 208, 그림 6-9
//버블 정렬(버전 3: 교환 횟수에 따른 멈춤)
//last를 사용한 성능 개선 - 코딩 시험에서 성능 개선 요구 조건
import java.util.Random;
import java.util.Scanner;

public class Train_ex06_01_BubbleSort03 {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("단순교환정렬(버블정렬)");
		System.out.print("요솟수 : ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];

		Random rand = new Random(42);// seed 값 사용

		for (int i = 0; i < nx; i++) {
			x[i] = rand.nextInt(999);
		}
		System.out.println("정렬전:");
		showData(x);
		bubbleSort(x, nx); // 배열 x를 단순교환정렬

		System.out.println("정렬후:");
		showData(x);
	}

	// --- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}

	// --- 버블 정렬(버전 3: 스캔 범위를 한정)---//
	static void bubbleSort(int[] a, int n) {
		int count = 0;
		int k = 0; // a[k]보다 앞쪽은 정렬을 마침 > 0은 1st pass에서 모든 숫자를 검사
		while (k < n - 1) { // for 문을 while 문으로 변경함
			int last = n - 1; // 마지막으로 교환한 위치
			for (int j = n - 1; j > k; j--) // j>k을 변경함
			{
				count++;
				if (a[j - 1] > a[j]) {
					swap(a, j - 1, j);
					last = j;// last을 사용한 성능 개선 구현 -> 마지막에 교환한 인덱스를 의미하는 last
				}
			}
			k = last;// 각 패스에서 마지막 교환 위치
		}
		System.out.println("\n비교횟수 = " + count);
	}

	static void showData(int[] d) {
		for (int i = 0; i < d.length; i++)
			System.out.print(d[i] + " ");
	}
}
