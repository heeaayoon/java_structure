package ch06;
//비교횟수가 아닌 동작 과정을 이해하기 위한 버젼 p.221~223

//그림 6-18: shell 정렬이 그룹이 섞이지 않는 문제: 4그룹 > 2그룹
//해결: h가 서로 배수가 안되게 셸정렬(버전 2 : h = …, 40, 13, 4, 1) 
//[8 1 4 2 7 6 3 5] 정렬 실습 코드 

import java.util.Random;
import java.util.Scanner;

public class Train_ex06_06_ShellSort02 {
	// --- 셸 정렬 ---//
	static void shellSort(int[] a, int n) {
		// int count = 0;
		int h;
		for (h = 1; h < n; h = h * 3 + 1)// h 초기 값을 구한다 //1->4=>13
			;
		System.out.println("h = " + h);
		for (; h > 0; h /= 3) //13->4->1
			for (int i = h; i < n; i++) {// 1회차 13<8이므로 skip한 후에 2회차 4로 실행
				System.out.println("h1 = " + h);
				int j;
				int tmp = a[i];
				for (j = i - h; j >= 0 && a[j] > tmp; j -= h) {// i,j 값의 변화를 따라가기가 어렵다 - 디버거를 사용한 추적이 빠르다
					a[j + h] = a[j];// count++;
				}
				System.out.println("h2 = " + h + ", tmp = " + tmp + ", a[j+h] = " + a[j + h]);
				a[j + h] = tmp;
				for (int na : a)
					System.out.print(" " + na);
				System.out.println();
			}
		// System.out.println("\n비교횟수 = " + count);
	}

	static void showData(int[] d) {
		for (int i = 0; i < d.length; i++)
			System.out.print(d[i] + " ");
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("셸 정렬(버전 2)");
		System.out.print("요솟수: ");
		// int nx = stdIn.nextInt();

		int nx = 8;

		int[] x = { 8, 1, 4, 2, 7, 6, 3, 5 };
		/*
		 * int[] x = new int[nx]; Random rand = new Random(42);
		 * 
		 * for (int i = 0; i < nx; i++) { x[i] = rand.nextInt(10000); }
		 * System.out.println("정렬전:"); showData(x);
		 */
		// *
		for (int na : x)
			System.out.print(" " + na);
		// */
		System.out.println();
		shellSort(x, nx); // 배열 x를 셸정렬

		System.out.println("오름차순으로 정렬했습니다.");
		for (int i = 0; i < nx; i++)
			System.out.print(" " + x[i]);
	}
}
