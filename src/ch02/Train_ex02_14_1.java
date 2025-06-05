package ch02;

import java.util.Arrays;

class PhyscData2 implements Comparable<PhyscData2>{
	String name;
	int height;
	double vision;
	
	public PhyscData2(String name, int height, double vision) {
		this.name = name;
		this.height = height;
		this.vision = vision;
	}
	
	@Override
	public int compareTo(PhyscData2 o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
public class Train_ex02_14_1 {
	public static void main(String[] args) {
		PhyscData2[] data = {
				new PhyscData2("홍길동", 162, 0.3),
				new PhyscData2("홍동", 164, 1.3),
				new PhyscData2("홍길", 152, 0.7),
				new PhyscData2("김홍길동", 172, 0.3),
				new PhyscData2("이길동", 182, 0.6),
				new PhyscData2("박길동", 167, 0.2),
				new PhyscData2("최길동", 169, 0.5),
		};
		
		showData("정렬전",data);
		sortData(data);
		showData("정렬후", data);

		Arrays.sort(data); //comparator가 필요하다 
		showData("Arrays.sort() 실행후", data);
		
//		int resultIndex = binarySearch(data, "이길동");
//		if (resultIndex >= 0)
//			System.out.println("사이다가 존재하면 인덱스 = "+resultIndex);
//		else
//			System.out.println("사이다가 존재하지 않는다");
//		
//		PhyscData2[] newData= insertObject(data, new PhyscData2("소주다", 179, 1.5));
//		//배열의 사이즈를 1개 증가시킨후 insert되는 객체 보다 큰 값들은 우측으로 이동, 사이즈가 증가된 객체 배열을 리턴
//		showData("삽입후", newData);
	}
	
	private static void sortData(PhyscData2[] arr) {
		
	}

	static void showData(String msg, PhyscData2[]arr) {
		System.out.println(msg);
		for (PhyscData2 pd: arr) {
			System.out.printf("%-8s%3d%5.1f\n", pd.name, pd.height, pd.vision);
		}
		System.out.println();
	}

}