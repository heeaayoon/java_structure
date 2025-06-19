package ch06;
//stack 1개를 사용한 non-recursve QuickSort() 구현

import java.util.Stack;

//left/right를 저장할 Point 객체 
class Point {
	private int left;
	private int right;

	public Point(int x, int y) {
		left = x;
		right = y;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

}

public class Train_ex06_09_QuickSort03_nonRecursive_assign {

	// 퀵 정렬(비재귀 버전)

	// --- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}

	// --- 퀵 정렬(비재귀 버전)---//
	static void quickSort(int[] a, int left, int right) {

		Stack<Point> st = new Stack<>(); // 자바 Stack api 사용
		Point pt = new Point(left, right);
		st.push(pt);
		while (!st.isEmpty()) {
			Point pt = st.pop(); 
			int pl = left = pt.getLeft(); // 왼쪽 커서
			int pr = right = pt.getRight(); // 오른쪽 커서
			int x = a[(left + right) / 2]; // 피벗은 가운데 요소

			do {
				while (a[pl] < x)
					pl++;
				while (a[pr] > x)
					pr--;
				if (pl <= pr)
					swap(a, pl++, pr--);
			} while (pl <= pr);
			
			showData(a);
			System.out.println();
			
			if (left < pr) {
				System.out.println("left = " + left + ", pr = " + pr);
				pr.push(left); // 왼쪽 그룹 범위의
				ss.push(pr); // 인덱스를 푸시
			}
			if (pl < right) {
				System.out.println("pl = " + pl + ", right = " + right);
				lstack.push(pl); // 오른쪽 그룹 범위의
				rstack.push(right); // 인덱스를 푸시
			}
		}

	}

	private static void showData(int[] a) {
	}

	public static void main(String[] args) {
		int nx = 10;
		int[] x = new int[10];
		for (int ix = 0; ix < 10; ix++) {
			double d = Math.random();
			x[ix] = (int) (d * 20);
		}
		for (int i = 0; i < nx; i++)
			System.out.print(" " + x[i]);
		System.out.println();

		quickSort(x, 0, nx - 1); // 배열 x를 퀵정렬

		System.out.println("오름차순으로 정렬했습니다.");
		for (int i = 0; i < nx; i++)
			System.out.print(" " + x[i]);
	}
}
