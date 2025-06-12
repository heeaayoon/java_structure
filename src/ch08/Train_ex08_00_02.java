package ch08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Train_ex08_00_02 {
	public static String[] removeElement1(String[] arr, String item) {
		List<String> list = new ArrayList<>(Arrays.asList(arr));
		list.remove(item);
		//기존 배열을 리턴하면 안된다 - 이유는 참조 변수 이므로 
		return list.toArray(String[]::new);//toArray()의 parameter가 함수형 인터페이스를 사용: IntFunction<T[]> g
		//String[]::new는 배열을 생성하는 메소드 reference: 함수형 인터페이스의 구현이다 이것이 람다식으로 사용될 수 있는 이유다 
	}
	/*
	       // x의 요소를 포함하는 새로운 String 배열을 생성하여 변수 y에 할당합니다.
        String[] y = x.toArray(new IntFunction<String[]>() {
            @Override
            public String[] apply(int value) {
                // 새로운 String 배열을 생성하고 x의 요소를 복사합니다.
                return new String[value];
            }
        }.apply(x.size()));
	 */

	static void getList(List<String> list) {
		list.add("서울");	list.add("북경");
		list.add("상해");	list.add("서울");
		list.add("도쿄");	list.add("뉴욕");


		list.add("런던");	list.add("로마");
		list.add("방콕");	list.add("북경");
		list.add("도쿄");	list.add("서울");

		list.add(1, "LA");
	}
	static void showList(String topic, List<String> list) {
		System.out.println(topic + "::");
		for ( String city: list)
			System.out.print("  "+ city);
	}
	static List<String> sortList(List<String> list) {
		// 리스트를 배열로 변환후 정렬 > 결과 값을 리스트로 만들기 때문에 리턴 값으로 반환해야 
		String cities[] = new String[0];
		cities = list.toArray(cities);
		int count = cities.length;
		for (int i = 0; i < count; i++)
			for (int j = i +1; j < count; j++) {
				if (cities[i].compareTo(cities[j]) > 0)
				{
					String temp; temp = cities[i]; cities[i] = cities[j]; cities[j] = temp;
				}
			}
		List<String> lst = Arrays.asList(cities);//새로운 리스트이므로 리턴해야 한다
		return lst;
	}
	static void swap(List<String> list, int i, int j) {
		String temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j,  temp);
	}
	static void sortList2(List<String> list) {
		//리스트 자체를 정렬
		int listSize = list.size();
		for (int i = 0; i < listSize; i++) 
			for (int j = i + 1; j < listSize; j++) {
				if (list.get(i).compareTo(list.get(j))>0) {
					swap(list, i, j);
				}
			}
	}
	static String[] removeDuplicateList(List<String> list) {
		String cities[] = new String[0];//배열의 크기가 0인 배열
		cities = list.toArray(cities);//list의 결과로서 배열의 크기를 잡은 후에 동적으로 배열의 크기를 정한다 
		int count = cities.length;
		for (int i = 0; i< count; i++) {
			int j = i +1;
			while (j < count) {
				if ((cities[i].compareTo(cities[j])) == 0) {
					cities = removeElement1(cities, cities[j]);//중복제거된 새로운 배열을 받아야 
					count = cities.length;
				}
				else j++;
			}
		}
		// 대체 코드
		/*
		    int m = 0, n = 1;
		    while (n < count) {
		    	if (cities[m].compareTo(cities[n]) == 0) {
		    		cities = removeElement1(cities, cities[m]);
		    		count--;
		    		continue;
		    	}
		    	m++; n++;

		    }
		 */
		//
		return cities;
	}
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();
		getList(list);
		showList("입력후", list);
		//sort - 오름차순으로 정렬, 내림차순으로 정렬, 중복 제거하는 코딩

		//		    Collections.sort(list);

		//배열의 정렬
		//List<String> ls =  sortList(list);//배열로 변환하여 정렬
		sortList2(list);//리스트 자체로 정렬
		System.out.println();
		showList("정렬후", list);
		// 배열에서 중복제거
		System.out.println();
		System.out.println("중복제거::");

		String[] cities = removeDuplicateList(list);
		ArrayList<String> lst = new ArrayList<>(Arrays.asList(cities));
		showList("중복제거후", lst);
	}
}


