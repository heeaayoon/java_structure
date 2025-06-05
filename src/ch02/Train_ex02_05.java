package ch02;

import java.util.Arrays;
import java.util.Random;

//정수 배열 정렬
//교재 67 : 실습 2-5
public class Train_ex02_05 {
	public static void main(String[] args) {
		int[] data = new int[10];
		inputData(data);
		showData("난수 입력", data);
		
		// sortData(data);
		// showData("정렬후", data);

		reverse(data);
		showData("역순 재배치", data);

		reverseSort(data);
		showData("내림차순 정렬", data);

		Arrays.sort(data); // API 사용
		sortData(data); // sortData() 메소드를 만들어서 사용

		int realData[] = { 5, 15, 99 };

//		for (int newD : realData) {
//			//int[] result = insertData(data, newD);
//			data = insertData(data, newD); //중요: 반환된 새 배열을 data에 다시 할당
//			System.out.print("새로운 데이터 : " + newD );
//			showData(" 삽입후", data);
//		}

		for (int newD : realData) {
		//int[] result = insertData(data, newD);
		data = insertData_On(data, newD); //중요: 반환된 새 배열을 data에 다시 할당
		System.out.print("새로운 데이터 : " + newD );
		showData(" 삽입후", data);
		}
	}

	//10 ~ 60 사이의 난수 생성해 배열에 입력하는 메소드
	static void inputData(int[] data) {
		Random rd = new Random();
		for (int i = 0; i < data.length; i++) {
			data[i] = rd.nextInt(50) + 10;
		}
	}

	//배열을 출력하는 메소드
	static void showData(String msg, int[] data) {
		System.out.print(msg+" : ");
		System.out.print("[");
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + " ");
		}
		System.out.println("]");
	}

	//swap 메소드
	static void swap(int[] arr, int m, int n) {// 교재 67
		int t = arr[m];
		arr[m] = arr[n];
		arr[n] = t;
	}

	static void sortData(int[] arr) {
		// 버블 정렬
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] > arr[j])
					swap(arr, i, j);
			}
		}
	}

	// 역순으로 출력하는 reverse()메소드
	static void reverse(int[] a) {// 교재 67페이지
		for (int i = 0; i < a.length / 2; i++) {
			swap(a, i, a.length - i - 1);
		}
	}

	// 내림차순 정렬하는 reverseSort()메소드
	static void reverseSort(int[] arr) {
		// 역버블 정렬
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] < arr[j])
					swap(arr, i, j);
			}
		}
	}



	//데이터를 추가하는 메소드 (내 방법)
	static int[] insertData(int[] data, int value) {
		int[] newData = new int[data.length + 1]; // 크기가 +1인 새로운 배열을 만들기
		System.arraycopy(data, 0, newData, 0, data.length);
		newData[newData.length - 1] = value;
		sortData(newData);
		
		return newData;
	}
	
	//정렬이 미리 되어있는 기존 배열에 임의 값을 추가하는 알고리즘 
	//insert되는 실수 값이 insert될 위치를 찾아 보다 큰 값은 우측으로 이동(교수님 코드)
	static int[] insertData_On(int[] data, int value) {
		int[] newData = new int[data.length + 1]; // 크기가 +1인 새로운 배열을 만들기
		int i = 0; //기존배열의 인덱스
		int j = 0; //새 배열의 인덱스
		
		//value보다 작은 요소를 newData에 복사
		while(i<data.length&&data[i]<value) {
				newData[j++] = data[i++];
		}

		//value를 삽입
		newData[j++] = value;
		
		//나머지 요소들을 newData에 복사
		while(i<data.length) {
			newData[j++] = data[i++];
		}
		return newData;
	}
}