package ch02;

import java.util.Arrays;

//스트링 배열 정렬 ->compareTo() 이용
//정렬된 배열에 insert하면 중간에 끼워 넣으면 큰 값들은 이동해야 하고 크기를 1 증가 처리가 필요
public class Train_ex02_14 {
public static void main(String[] args) {
	String[] data = {"apple","grape","persimmon", "pear","blueberry", "strawberry", "melon", "oriental melon"};

//	showData("정렬전", data);
//	sortData(data);
//	showData("정렬후", data);
//	String[] newData = insertString(data, "banana");
//	showData("삽입후 크기가 증가된 정렬 배열", newData);
	
//	showData("정렬전", data);
//	Arrays.sort(data);
//	showData("정렬후", data);
	
	showData("정렬전", data);
	Arrays.sort(data, (a,b)->a.compareTo(b));
	showData("정렬후", data);
}
public static void showData(String str, String[] arr){//확장된 for 문으로 출력 
	System.out.println(str);
	System.out.print("[");
	for(int i=0;i<arr.length;i++) {
		System.out.print(arr[i]+" ");
	}
	System.out.println("]");
}

static void swap(String[] arr, int m, int n) {//스트링의 맞교환 함수로 sortData()에서 호출됨 // 교재 67
	String t = arr[m];
	arr[m] = arr[n];
	arr[n] = t;
}

static void sortData(String[] arr) { //올림차순으로 정렬 - for 문을 사용하여 직접 구현한다 
	for(int i =0; i<arr.length;i++) {
		for(int j=i;j<arr.length;j++) {
			if(arr[i].compareTo(arr[j])>0) swap(arr,i,j);
		}
	}
}
static String[] insertString(String[] arr, String str){//배열의 사이즈를 1개 증가시킨후 insert되는 스트링 보다 큰 값들은 우측으로 이동, 사이즈가 증가된 스트링 배열을 리턴
	String[] newData = new String[arr.length+1];
	System.arraycopy(arr, 0, newData, 0, arr.length);
	newData[newData.length-1] = str;
	sortData(newData);
	return newData;
}
}
