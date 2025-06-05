package ch03;

import java.util.Arrays;
import java.util.Random;

//3장 1번 실습과제: 정수배열이진검색
//3장 - 1번 실습 과제 > 2번 실습: 스트링 객체의 정렬과 이진 탐색 > 3번 실습: 객체 정렬과 이진 탐색

public class Train_ex03_04 {
	static void inputData(int[] data) {
		Random rd = new Random();
		for(int i=0;i<data.length;i++) {
			data[i] = rd.nextInt(33);
		}
	}
	static void showList(String msg, int[] data) {
		System.out.print(msg+"[");
		for(int i=0;i<data.length;i++) {
			System.out.print(data[i]+",");
		}
		System.out.println("]");
	}
	
	static int linearSearch(int[] data, int key) {
		for(int i =0; i<data.length;i++) {
			if(key==data[i]) {
				return i;
			}
		}
		return -1;
	}
	
	static int binarySearch(int[] data, int key) { //교재 106
		int size = data.length;
		int pl =0;
		int pr = size-1; //마지막 인덱스= 배열길이-1
		int pc;
	
		do {
			pc = (pl+pr)/2;
			if(key == data[pc]) {
				//System.out.println("해당 값의 인덱스는 : "+pc);
				return pc;
			}else if( key < data[pc]) pr = pc-1;
			 else pl = pc+1;			
		}while(pl<=pr);
		return -1;
	}	
	
	public static void main(String[] args) {
		int[] data = new int[30];
		inputData(data); //100 이하 난수를 생성

		showList("정렬 전 배열: ", data);
		Arrays.sort(data);
		showList("정렬 후 배열: ", data);

		int key1 = 30;//난수 입력
		//linearSearch()함수에 의한 검색
		int resultIndex1 = linearSearch(data, key1); 
		System.out.println("linearSearch: key = " + key1 + ", result = " + resultIndex1);

		int key2 = 30;//난수 입력
		//binarySearch()함수에 의한 검색
		int resultIndex2 = binarySearch(data, key2); 
		System.out.println("binarySearch : key = " + key2 + ", result = " + resultIndex2);
		
		int key3 = 30;//난수 입력
		// Arrays.binarySearch에 의한 검색 : 해당 값의 인덱스 번호를 리턴함
		int resultIndex = Arrays.binarySearch(data, key3);
		System.out.println("Arrays.binarySearch: result = " + resultIndex);
		//System.out.println(Arrays.binarySearch(data, 30));
	
	}
}
