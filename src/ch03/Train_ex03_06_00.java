package ch03;

import java.util.Arrays;

//3장 2번 실습과제 : 스트링 배열의 정렬과 이진검색 
//교재 121 실습 3-6 
public class Train_ex03_06_00 {
	public static void showData(String msg, String[] data) {
		System.out.print(msg+"[");
		for(int i=0; i<data.length;i++) {
			System.out.print(data[i]+" ");
		}
		System.out.println("]");
	}
	
	public static void swap(String[] arr, int n, int m) {
		String t = arr[n];
		arr[n] = arr[m];
		arr[m] = t;
	}
	
	public static void sortData(String[] data) { //교재211-212 단순 선택 정렬 알고리즘으로 구현
		//버블 정렬 //오름차순 정렬
		for(int i=0;i<data.length;i++) {
			for(int j = i+1; j<data.length;j++) {
				if(data[i].compareTo(data[j])>0) {
					swap(data,i,j);
				}
			}
		}
	}
	
	public static int linearSearch(String[] arr, String key) {
		for(int i=0;i<arr.length;i++) {
			if(arr[i]==key) {
				return i;
			}
		}
		return -1;
	}
	
	public static int binarySearch(String[] arr, String key) {
		int pl = 0;
		int pr = arr.length-1;
		
		do {			
			int pc = (pl+pr)/2;
			if(key==arr[pc]) return pc;
			else if(key.compareTo(arr[pc])>0)  pl = pc+1;
			else pr = pc-1;
		}while(pl<=pr);
		
		return -1;
	}
	
	public static void main(String[] args) {
		String[] data = {"사과","포도","복숭아", "감", "산딸기", "블루베리", "대추", "수박", "참외"};
		showData("정렬전", data);
		sortData(data);
		showData("정렬후", data);

		String key = "포도";
	    //key.compareTo(key);
	    //linearSearch()함수에 의한 검색
		int resultIndex = linearSearch(data, key);
		System.out.println("\nlinearSearch: key = " + key + ", result 인덱스 = " + resultIndex);

		key = "사과";
		//binarySearch()함수에 의한 검색
		resultIndex = binarySearch(data, key);
		System.out.println("\nbinarySearch:key = " + key + ",  result 인덱스 = " + resultIndex);

		key = "산딸기";
		resultIndex = Arrays.binarySearch(data, key);//교재 120 페이지 API 참조
//		교재 115 Arrays.binarySearch에 의한 검색
//		public final class String implements java.io.Serializable, Comparable<String>, CharSequence {
//		     @Override
//		     public int compareTo(String anotherString) {
//		            return this.compareTo(anotherString);
//		     }
//		}
//		 
//		 binarySearch(String[], String key)를 호출하면, 
//		 내부적으로 String 배열이 자동으로 Comparable<String>[]로 해석.
//		 교재 116 표 3-3: static int binarySearch(Object[] a, Object key)가 사용됨 - 단 배열 a는 Comparable을 구현한 객체들로 정렬되어 있어야 함
		
		System.out.println("\nArrays.binarySearch: key = " + key + ", result 인덱스 = " + resultIndex);
	}
}
