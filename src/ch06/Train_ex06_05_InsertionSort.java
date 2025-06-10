package ch06;

//교재 p.213~216
//단순 삽입(straight insertion) : 정렬 선택한 요소를 그보다 더 앞쪽의 알맞은 위치에 삽입
//https://gyoogle.dev/blog/algorithm/Insertion%20Sort.html
//selection sort에 비하여 성능 개선: 처리시간이 50% 감소
import java.util.Random;
import java.util.Scanner;

public class Train_ex06_05_InsertionSort {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("단순 삽입 정렬");

		System.out.print("요솟수: ");
		int nx = stdIn.nextInt();

		int[] x = new int[nx];

		Random rand = new Random(42);

		for (int i = 0; i < nx; i++) {
			x[i] = rand.nextInt(999);
		}
		System.out.println("정렬전:");
		showData(x);
		insertionSort(x, nx); // 배열 x를 단순삽입정렬

		System.out.println("정렬후:");
		showData(x);
	}

	static void insertionSort(int[] a, int n) {// 코드를 이해하여 알고리즘을 파악한다.
		int count = 0;
		// index i,j를 사용한 알고리즘 작성 skills을 익히기
		for (int i = 1; i < n; i++) {
			int j;
			int tmp = a[i];// a[i]가 선택한 요소 -카드를 하나 꺼내서 //다음차례의 카드
			// 선택한 카드의 좌측에 넣을 곳을 찾는다 - 이미 정렬된 좌측 카드 순서 배열에 삽입
			for (j = i; j > 0 && a[j - 1] > tmp; j--) {// a[j-1]을 기준으로 거꾸로 이동
				count++;
				a[j] = a[j - 1];// 이미 정렬된 부분에 tmp가 들어갈 자리를 찾는다 - 이미 정렬된 효과 덕분에 성능 향상 얻어짐
			}
			count++;
			a[j] = tmp;// tmp를 insert한다 - 선택한 카드 a[i]를 왼쪽에 끼워 놓는다
		}
		System.out.println("\n비교횟수 = " + count);
	}

	static void showData(int[] d) {
		for (int i = 0; i < d.length; i++)
			System.out.print(d[i] + " ");
	}

}
