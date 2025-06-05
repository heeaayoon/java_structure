package ch02;
import java.util.Random;

//메소드의 매개변수로 배열 사용하기
public class Train_ex02_04 {
	static int top = 0;
	static final int MAX_LENGTH = 20;

	public static void main(String[] args) {
		int[] data = new int[10];
		inputData(data);
		showData("소스데이터",data);

		int max = findMax(data);
		System.out.println("\nmax = " + max);

		boolean existValue = findValue(data, 3);
		System.out.println("찾는 값 = " + 3 + ", 존재여부 = " + existValue);

		reverse(data);
		showData("역순 데이터", data);
	}

	//난수를 배열에 입력하는 메소드
	static void inputData(int[] arr) {
		Random rd = new Random();
		for(int i=0;i<arr.length;i++) {
			arr[i] = rd.nextInt(50);
		}
	}

	//배열을 출력하는 메소드
	static void showData(String str, int[] arr) {
		System.out.print(str + " : " );
		System.out.print("[");
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.print("]");
	}

	//배열 내에서 최댓값을 찾는 메소드
	static int findMax(int[] arr) {
		int x = arr[0];
		for(int i=1;i<arr.length;i++) {
			if(x<arr[i]) x= arr[i];
		}
		return x;
	}

	//배열 안에 특정 값이 있는지를 검사하는 메소드
	static boolean findValue(int[] arr, int num) {
		for(int i=1;i<arr.length;i++) {
			if(num == arr[i]) return true;
		}
		return false;
	}

	// swap 메소드
	static void swap(int[] arr, int m, int n) {
		int t = arr[m];
		arr[m] = arr[n];
		arr[n] = t;
	}
	
	// 역순으로 출력하는 reverse()메소드 : 교재 65
	static void reverse(int[] arr) {
		for(int i =0; i<arr.length/2;i++) {
			swap(arr, i, (arr.length-i-1));
		}
	}
}