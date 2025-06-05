package ch03;
//3장 3번 실습과제 : 객체 배열의 정렬과 이진검색 Comparable Interface구현

//public interface Comparable<T> {
// 	   //추상 메소드
//     int compareTo(T o);
// }

/*
 * binarySearch(T[], T key)는 API에서 직접 지원하지 않음 ->배열이 Comparable<T>을 구현하는 객체라면 정상적으로 동작함.
 * binarySearch(Comparable<T>[] a, T key)는 직접 지원함.
 * 만약 비교 기준을 다르게 설정하고 싶다면 binarySearch(T[], T key, Comparator<? super T> c)를 사용해야 함
 * 
 * Arrays.sort(Object[])가 존재하는 이유 > 
 * 배열의 원소가 Comparable을 구현하면, Object[] 배열에서도 정렬 가능
 * String[], Integer[], Double[] 등 Comparable을 구현한 기본 래퍼 클래스의 배열을 정렬하는 데 유용
 * 
 *  Object[] numbers = {5, 2, 9, 1, 3}; // Integer 배열 (Integer는 Comparable<Integer> 구현)
 *  Arrays.sort(numbers); // 정상 작동
 *  
 *  Arrays.sort(T[]) (제네릭 버전)
 *  제네릭 배열 T[]을 정렬할 때 더 적합한 방식.
 *  T가 Comparable<T>를 구현했을 경우, 자동으로 compareTo()가 호출됨.
 *  T[] 배열의 원소가 Comparable<T>를 구현하면 Arrays.sort(T[])를 사용할 수 있음.
 */
import java.util.Arrays;

//객체 배열의 정렬 구현
class PhyscData2 implements Comparable<PhyscData2> {
	String name;
	int height;
	double vision;

	// 생성자
	public PhyscData2(String name, int height, double vision) {
		this.name = name;
		this.height = height;
		this.vision = vision;
	}
	
	// [홍길동,162,0.3] 형태로 리턴하는 메소드
	@Override 
	public String toString() {
		return "[" + name + "," + height + "," + vision + "]";
	}

//	@Override
//	public int compareTo(PhyscData2 p) {
//		int n = this.name.compareTo(p.name); // name 비교 버젼 -> 만약 이름이 같다면 키를 비교한다.
//		if (n==0) {
//			return height-p.height;
//		} else return n;
//	}
	
	@Override
	public int compareTo(PhyscData2 p) {
		if(this.height>p.height) return 1;
		if(this.height<p.height) return -1;
		else return 0; // height 비교 버젼
	}
}

public class Train_ex03_06_01 {
	public static void showData(String msg, PhyscData2[] data) {
		System.out.print(msg + "[");
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + " ");
		}
		System.out.println("]");
	}

	public static void swap(PhyscData2[] arr, int n, int m) {
		PhyscData2 t = arr[n];
		arr[n] = arr[m];
		arr[m] = t;
	}

	public static void sortData(PhyscData2[] data) {
		// 버블 정렬 //오름차순 정렬
		for (int i = 0; i < data.length; i++) {
			for (int j = i + 1; j < data.length; j++) {
				if (data[i].compareTo(data[j]) > 0) {
					swap(data, i, j);
				}
			}
		}
	}

	public static void reverse(PhyscData2[] data) {
		// 버블 정렬 //내림차순 정렬
		for (int i = 0; i < data.length; i++) {
			for (int j = i + 1; j < data.length; j++) {
				if (data[i].compareTo(data[j]) < 0) {
					swap(data, i, j);
				}
			}
		}
	}
	
	public static int linearSearch(PhyscData2[] data, PhyscData2 key) { //compareTo()를 사용하여 구현
		for(int i = 0;i<data.length;i++) {
			if(data[i].compareTo(key)==0) return i;
		}
		return -1;
	}
	
	public static int binarySearch(PhyscData2[] data, PhyscData2 key) { //compareTo()를 사용하여 구현
		int pl = 0;
		int pr = data.length;

		do {	
			int pc = (pl+pr)/2;
			if(data[pc].compareTo(key)==0) return pc;
			else if(data[pc].compareTo(key)>0) pr = pc-1;
			else pl = pc+1;
		}
		while(pl<=pr);
		return -1;
	}

	public static void main(String[] args) {
		PhyscData2[] data = { new PhyscData2("홍길동", 162, 0.3), new PhyscData2("나동", 164, 1.3),
				new PhyscData2("최길", 152, 0.7), new PhyscData2("홍길동", 162, 0.3), 
				new PhyscData2("박동", 182, 0.6), new PhyscData2("박동", 167, 0.2), 
				new PhyscData2("길동", 167, 0.5), };

		showData("정렬전", data);
		sortData(data);
		showData("정렬후", data);
		reverse(data);
		showData("역순 재배치후", data);
		
		Arrays.sort(data); // 사용가능한 이유 이해하기 - compareTo() 구현을 변경하여 실행결과를 확인
		showData("Arrays.sort() 정렬후", data);

		PhyscData2 key1 = new PhyscData2("길동", 167, 0.5);
		int resultIndex = linearSearch(data, key1);
		System.out.println("\nlinearSearch([길동,167,0.5]): result index = " + resultIndex);
		
		PhyscData2 key2 = new PhyscData2("박동", 167, 0.6);
		int resultIndex2 = binarySearch(data, key2);
		System.out.println("\nbinarySearch([박동,167,0.6]): result index = " + resultIndex2);
		
		PhyscData2 key3 = new PhyscData2("최길", 152, 0.7);
		//교재 115 Arrays.binarySearch에 의한 검색
		int resultIndex3 = Arrays.binarySearch(data, key3); //compareTo()를 사용되는지를 확인-이해할 수 있어야 한다 
		System.out.println("\nArrays.binarySearch([최길,152,0.7]): result index = " + resultIndex3);
	}
}
