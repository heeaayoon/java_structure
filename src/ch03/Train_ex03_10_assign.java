package ch03;

import java.util.Arrays;
import java.util.Comparator;

class Student {
	private String sid;		
	private String sname;
	private String dept; 
		
		
	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	//생성자
	public Student(String sid, String sname, String dept) {
		this.sid = sid;
		this.sname = sname;
		this.dept = dept;
	}
 		
	//출력 메소드 : 출력 형식은 "S002, 철수, Physics"
	public String toString() {
		return sid+","+sname+","+dept;
	}	
}
	
public class Train_ex03_10_assign {
	public static void show(String msg,Student[] arr) {
		System.out.println(msg);
		for(int i=0;i<arr.length;i++) {
			System.out.println(arr[i]);
		}
	}
	public static void main(String[] args) {
		Student[] students = { new Student("S001", "영희", "Math"),
				new Student("S003", "민수", "Computer"),
				new Student("S002", "철수", "Physics"),
				new Student("S005", "지영", "Biology"),
				new Student("S004", "준호", "Chemistry") };

		show("=== 정렬 전 학생 목록 ===",students);		

//		정렬과 이진 탐색에 사용할 Comparator<Student> 객체를 람다식 또는 익명 클래스로 구현하기
//		Arrays.sort(students, new Comparator<Student>() {
//			@Override
//			public int compare(Student s1, Student s2) {
//				return s1.getSid().compareTo(s2.getSid());
//			}
//		});
		
		//람다식 표현
		Comparator<Student> sidCompare = (s1, s2) -> s1.getSid().compareTo(s2.getSid());
		Arrays.sort(students, sidCompare);
		
		show("=== 이름 정렬 후 학생 목록 ===",students);

		//탐색 대상 객체 배열을 정의하고 반복문으로 탐색
		Student[] targets = {
				new Student("S002", "철수", "Physics"),
				new Student("S006", "홍길동", "Law"),
				new Student("S004", "준호", "Chemistry")
		};
		
		//위 배열을 확장형 for문으로 순회하면서 Arrays.binarySearch()를 통해 각 객체를 탐색하고 결과를 출력한다.
		//출력 형식 : "찾은 학생: S002, 철수, Physics" or "학번 S006인 학생은 존재하지 않습니다."
		//Arrays.binarySearch(검색하고자 하는 배열, 비교대상, comparator)	
		System.out.println("\n=== 이진 탐색 결과 ===");
		for(Student t : targets) {
			int idx = Arrays.binarySearch(students, t, sidCompare);
			if(idx>=0) {
				System.out.println("찾은 학생 : "+ students[idx]);				
			}
			if(idx<0) {
				System.out.println("학번 "+t.getSid()+"인 학생은 존재하지 않습니다.");
			}
		}	
	}
}		