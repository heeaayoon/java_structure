package ch03;import java.util.Arrays;
import java.util.Comparator;

//3장 과제2 : 객체 배열 정렬/검색 - 람다식사용
// class MyComparator implements Comparator<Student>{
// 	//메소드 재정의
// 	int compare(Student s1, Student s2){
// 		return s1.compareTo(s2);
// 	}
// }  

//MyComparator myComparator = new MyComparator();
//Arrays.sort(array, myComparator);
//Collections.sort(list, new MyComparator());

class Fruit4 {
	String name;
	int price;
	String expire;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}

//교재 123~129 페이지 참조하여 구현
class FruitName implements Comparator<Fruit4>{ //이름을 기준으로 비교하는 메소드가 담긴 클래스
	@Override
	public int compare(Fruit4 f1, Fruit4 f2) {
		return f1.name.compareTo(f2.name);
	}
}

class FruitPrice implements Comparator<Fruit4>{ //가격 기준으로 비교하는 메소드가 담긴 클래스
	@Override
	public int compare(Fruit4 f1, Fruit4 f2) {
		if (f1.price>f2.price) return -1;
		return f1.price;
	}
}

public class Train_ex03_07 {
	private static void sortData(Fruit4[] arr, Comparator<Fruit4> cc_price) {
		
	}

	public static void main(String[] args) {
		Arrays.sort(arr, new Comparator<Fruit4>{
			int compare(Fruit4 f1, fruit4 f2) {
				return f1.name.compareTo(f2.name);
			}
		});
		
		Arrays.sort(arr, (f1,f2) -> f1.name.compareTo(f2.name));
		
		Fruit4[] arr = {new Fruit4("사과", 200, "2023-5-8"), 
				new Fruit4("감", 500, "2023-6-8"),
				new Fruit4("대추", 200, "2023-7-8"), 
				new Fruit4("복숭아", 50, "2023-5-18"), 
				new Fruit4("수박", 880, "2023-5-28"),
				new Fruit4("산딸기", 10, "2023-9-8") };
		
		System.out.println("\n정렬전 객체 배열: ");
		showData("정렬전 객체", arr);
		
		FruitName cc = new FruitName();
		System.out.println("\n comparator cc 객체를 사용:: ");
		Arrays.sort(arr, cc);
		showData("Arrays.sort(arr, cc) Name 정렬 후", arr);
		
		sortData(arr, new FruitPrice());
		showData("Arrays.sort(arr, cc)  Price 정렬 후", arr);
		
		// 람다식은 익명클래스 + 익명 객체이다
		Comparator<Fruit4> cc_expire = (a, b) -> a.expire.compareTo(b.expire);
		Arrays.sort(arr, cc_expire); // 람다식으로 만들어진 객체를 사용
		showData("람다식 변수 cc_expire을 사용한 Arrays.sort(arr, cc) 정렬 후", arr);
		
		Arrays.sort(arr, (a, b) -> a.getPrice() - b.getPrice()); 
		showData("람다식: (a, b) -> a.getPrice() - b.getPrice()을 사용한 Arrays.sort(arr, cc) 정렬 후", arr);

		System.out.println("\n익명클래스 객체로 정렬(가격)후 객체 배열: ");
		Arrays.sort(arr, new Comparator<Fruit4>() {
			@Override
			public int compare(Fruit4 a1, Fruit4 a2) {
				return a1.getName().compareTo(a2.getName());
			}
		});
		System.out.println("\ncomparator 정렬(이름)후 객체 배열: ");
		showData("name comparator - 익명 객체를 사용한 정렬:", arr);
		
		//익명 클래스를 사용한 comparator 객체
		Comparator<Fruit4> cc_name = new Comparator<Fruit4>() {// 익명클래스 사용

			@Override
			public int compare(Fruit4 f1, Fruit4 f2) {
				// TODO Auto-generated method stub
				return (f1.name.compareTo(f2.name));
			}

		};
		Comparator<Fruit4> cc_price = new Comparator<Fruit4>() {

			@Override
			public int compare(Fruit4 f1, Fruit4 f2) {
				return f1.getPrice() - f2.getPrice();
			}// 익명클래스 사용

		};

		Fruit4 newFruit4 = new Fruit4("수박", 880, "2023-5-18");
		/*
		 * 교재 115 Arrays.binarySearch에 의한 검색
		 */
		int result3Index = Arrays.binarySearch(arr, newFruit4, cc_name);
		System.out.println("\nArrays.binarySearch([수박,880,2023-5-18]) 조회결과::" + result3Index);
		
		result3Index = binarySearch(arr, newFruit4, cc_price); 
		System.out.println("\nbinarySearch([수박,880,2023-5-18]) 조회결과::" + result3Index);

		sortData(arr, cc_price);
		System.out.println("\ncomparator 정렬(가격)후 객체 배열: ");
		showData("comparator를 사용한 정렬후:", arr);	
	}
}

