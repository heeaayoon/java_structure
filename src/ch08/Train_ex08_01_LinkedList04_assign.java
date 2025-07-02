package ch08;
/*
 * 정수 리스트 > 객체 리스트 > 04.객체 이중리스트 
 * 헤드 노드가 있는 원형 리스트, 헤드 노드가 없는 원형 리스트 구현
 * merge 구현: in-place 구현, 새로운 노드를 생성하여 합병 구현 
 * 원형 이중 리스트로 동일하게 적용
 */
//Tail이 없으면 원형임
//정수로 운용되는 더블링크드리스트를 짜줘. 원형구조가 아니면 좋겠어
//
//1.링크드 리스트는 노드를 운용하는 자료구조
//2.데이터는 '노드'에 들어감
//3.일반적으로 링크드리스트는 양방향을 의미함 
//4.add, delete가 가장 중요
//4-1.맨 앞 add, 맨 뒤 add, 맨 앞 delete, 맨 뒤 delete 
//5.순회

import java.util.Comparator;
import java.util.Scanner;

class SimpleObject2 {
	static final int NO = 1; // 번호를 읽어 들일까요?
	static final int NAME = 2; // 이름을 읽어 들일까요?
	String no; // 회원번호
	String name; // 이름
	String expire;//  유효기간
	
	//기본 생성자
	public SimpleObject2() {
		this.no = null;
		this.name = null;
	}
	
	//생성자
	public SimpleObject2(String sno, String sname, String expire) {
		this.no = sno;
		this.name = sname;
		this.expire = expire;
	}
	//문자열 표현을 반환
	@Override
	public String toString() {
		return "[" + no + "]" + name;
	}
	
	//데이터를 입력받는 메소드
	void scanData(String guide, int sw) {
		Scanner sc = new Scanner(System.in);
		System.out.println(guide + "할 데이터를 입력하세요."+ sw);

		if ((sw & NO) == NO) { //& 는 bit 연산자임
			System.out.print("번호: ");
			no = sc.next();
		}
		if ((sw & NAME) == NAME) {
			System.out.print("이름: ");
			name = sc.next();
		}
	}
	
	//회원번호로 순서를 매기는 comparator
	public static final Comparator<SimpleObject2> NO_ORDER = new NoOrderComparator();

	private static class NoOrderComparator implements Comparator<SimpleObject2> {
		@Override
		public int compare(SimpleObject2 d1, SimpleObject2 d2) {
			return (d1.no.compareTo(d2.no) > 0) ? 1 : ((d1.no.compareTo(d2.no) < 0)) ? -1 : 0;
		}
	}

	//이름으로 순서를 매기는 comparator 
	public static final Comparator<SimpleObject2> NAME_ORDER = new NameOrderComparator();

	private static class NameOrderComparator implements Comparator<SimpleObject2> {
		@Override
		public int compare(SimpleObject2 d1, SimpleObject2 d2) {
			return (d1.name.compareTo(d2.name) > 0) ? 1 : ((d1.name.compareTo(d2.name) < 0)) ? -1 : 0;
		}
	}
}

class Node4 {
	SimpleObject2 data; // 데이터
	Node4 llink; // 좌측포인터(앞쪽 노드에 대한 참조)
	Node4 rlink; // 우측포인터(뒤쪽 노드에 대한 참조)
	
	public Node4(SimpleObject2 x) {
		this.data = x;
		this.rlink = null;
		this.llink = null;
	}
	
	//기본 생성자 
	public Node4() {
		this.data = null;
		this.rlink = this;
		this.llink = this;
	}

	public SimpleObject2 getData() {
		return data;
	}

	public void setData(SimpleObject2 data) {
		this.data = data;
	}

	public Node4 getLlink() {
		return llink;
	}

	public void setLlink(Node4 llink) {
		this.llink = llink;
	}

	public Node4 getRlink() {
		return rlink;
	}

	public void setRlink(Node4 rlink) {
		this.rlink = rlink;
	}

}

//class Node<T>{//<T> -> 여러가지 데이터 타입이 전부 들어갈 수 있도록 수정 --> 나중에
//	T data; // 데이터
//	Node<T> llink; // 좌측포인터(앞쪽 노드에 대한 참조)
//	Node<T> rlink; // 우측포인터(뒤쪽 노드에 대한 참조)
//}

class DoubledLinkedList2 {
	private Node4 head; // 머리 포인터(참조하는 곳은 더미노드)

	//생성자(constructor)
	public DoubledLinkedList2() {
		head = new Node4(); // dummy(head) 노드를 생성
	}

	//리스트가 비어있는가?
	public boolean isEmpty() {
		return head.rlink == head; //리스트가 비어있으면 head.rlink와 head.llink는 자기자신(head)을 가리킴
	}

	//전체 노드 표시
	public void show() {
		if(isEmpty()) {
			System.out.println("빈 리스트");
			return; //종료
		}
		//첫번째 노드 설정
		Node4 n = head.rlink; //head에는 데이터가 담겨있지 않음 -> 리스트의 시작과 끝을 알려주는 더미노드임
		
		//fist(더미노드)로 돌아올때까지 반복
		while(n!=head) {
			if(n.rlink == head) System.out.print(n.data+" ");
			else System.out.print(n.data+" -> ");
			n = n.rlink;
		}
		System.out.println();
	}

	//노드를 검색 -> 찾은 객체를 직접 반환, 찾지 못하면 null반환
	public SimpleObject2 search(SimpleObject2 obj, Comparator<? super SimpleObject2> c) {
		if(isEmpty()) {
			System.out.println("빈 리스트");
			return null; //종료
		}
		//첫번째 노드 설정
		Node4 n = head.rlink;
		
		//fist(더미노드)로 돌아올때까지 반복하면서 동일한 데이터를 가진 노드가 있는지를 검색
		while(n!=head) {
			if(c.compare(obj, n.data) == 0) {
				return n.data; //데이터가 동일한 노드를 찾음
			}
			n= n.rlink; //못찾으면 다음노드로 넘어가기
		}
		return null; //데이터가 동일한 노드를 끝까지 못찾음
	}

	//addFirst : 맨 앞에 요소를 추가. 정렬은 X
	public void addFirst(SimpleObject2 obj) {
		Node4 newNode = new Node4(obj);
		Node4 first = head.getRlink();
		
		//새로운 노드 좌우 연결
		newNode.setLlink(head);
		newNode.setRlink(first);
		
		//기존 노드와 연결
		head.setRlink(newNode);
		first.setLlink(newNode);
	}
	
	//addLast : 맨 뒤에 요소를 추가. 정렬은 X
	public void addLast(SimpleObject2 obj) {
		Node4 newNode = new Node4(obj);
		Node4 last = head.getLlink();
		
		//새로운 노드 좌우 연결
		newNode.setLlink(last);
		newNode.setRlink(head);
		
		//기존 노드와 연결
		last.setRlink(newNode);
		head.setLlink(newNode);
	}
	
	
	//올림차순으로 정렬하면서 insert
	public void add(SimpleObject2 obj, Comparator<? super SimpleObject2> c) {
		//첫번째 노드 설정
		Node4 p = first.rlink;
		//새로운 노드 설정
		Node4 newNode = new Node4(obj);
		
		//첫번쨰 노드부터 while 루프를 돌면서 insert할 위치 찾기
		//빈 리스트가 아니고, obj데이터>p데이터 인 동안만 돌기
		while(p!=first && c.compare(obj, p.data)>0) {
			p = p.rlink; //다음 노드로 이동
		}
		
		//위치를 찾으면 newNode삽입 -> 왼,오를 어떻게 연결 -> p.llink - newNode - p 순으로 연결됨
		newNode.llink=p.llink;
		newNode.rlink = p;
		
		//주변 노드들도 newNode로 연결
		p.llink.rlink = newNode;
		p.llink = newNode;
	}

	//해당 노드를 삭제
	public void delete(SimpleObject2 obj, Comparator<? super SimpleObject2> c) {
		//일단 기준 노드(current)를 설정 -> obj와 비교해서 같으면 삭제
		Node4 current = head.rlink; //값이 유효한 첫 노드를 선택
		while(current != null) {  //current 노드를 한 칸씩 이동하면서 , obj와 동일한 것이 있는지 확인
			if(c.compare(current.getData(), obj) == 0) { //동일함 = 삭제할 데이터를 찾음
				current.getLlink().setRlink(current.getRlink()); // A - B - C => A - C 
				current.getRlink().setLlink(current.getLlink()); // B를 삭제하고 A와 C를 연결
				System.out.println("삭제완료 : " + obj);
			}
			current = current.getRlink(); //같지 않으면 다음 노드로 넘어가서 다시 if문 검사
		}
		//끝까지 돌았는데도(head로 복귀) 동일한 값이 없음
		System.out.println("삭제할 데이터를 찾을 수 없습니다.");
	}
	

	//l3 = l1.merge(l2); 실행하도록 리턴 값이 리스트 
	//l.add(위에서 만든 메소드-정렬하면서 add)를 사용하여 구현
	//새로운 리스트의 노드들을 생성하여 구현 
	public DoubledLinkedList2 merge_NewList(DoubledLinkedList2 lst2, Comparator<SimpleObject2> cc) {
		DoubledLinkedList2 lst3 = new DoubledLinkedList2(); //새로운 링크드리스트 생성 -> 여기에다가 병합한 리스트 담기
		Node4 ai = this.head.rlink; //내 리스트의 첫번째 노드
		Node4 bi = lst2.head.rlink; //합할 리스트의 첫번째 노드
		
		// 두 리스트에 모두 요소가 남아있는 동안 반복
		while(ai)
		
		
		
		return lst3;
	}
	
//마지막에 추가 	
	//연결리스트 a,b에 대하여 a = a + b
	//회원번호에 대하여 a = (3, 5, 7), b = (2,4,8,9) => a = (2,3,4,5,8,9)가 되도록 구현
	//in-place 방식으로 합병/새로운 노드를 만들지 않고 합병하는 알고리즘 구현
	//새로운 링크드리스트를 만들지는 않고, 노드 저장만 가능한 공간 세팅
//	void merge_InPlace(DoubledLinkedList2 b, Comparator<SimpleObject2> cc) {
//		Node4 p = first.rlink, q = b.first.rlink;
//		Node4 temp = null;
//	}
}

public class Train_ex08_01_LinkedList04_assign {
	enum Menu {
		Add("삽입"), Delete("삭제"), Show("인쇄"), Search("검색"), Merge_NewList("병합-새리스트"), Merge_InPlace("병합-제자리"), Exit("종료");

		private final String message; // 표시할 문자열

		static Menu MenuAt(int idx) { // 순서가 idx번째인 열거를 반환
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}

		Menu(String string) { // 생성자(constructor)
			message = string;
		}

		String getMessage() { // 표시할 문자열을 반환
			return message;
		}
	}

	//메뉴 선택
	static Menu SelectMenu() {
		Scanner sc1 = new Scanner(System.in);
		int key;
		do {
			for (Menu m : Menu.values()) {
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
				if ((m.ordinal() % 3) == 2 && m.ordinal() != Menu.Exit.ordinal())
					System.out.println();
			}
			System.out.print(" : ");
			key = sc1.nextInt();
		} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());
		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Menu menu; // 메뉴
		Scanner sc2 = new Scanner(System.in);
		DoubledLinkedList2 lst1 = new DoubledLinkedList2();
		DoubledLinkedList2 lst2 = new DoubledLinkedList2();
		DoubledLinkedList2 lst3 = new DoubledLinkedList2();
		DoubledLinkedList2 lst4 = new DoubledLinkedList2();
		
		String sno1 = null;
		String sname1 = null;
		SimpleObject2 so;
		boolean result = false;
		int count = 3;
		
		do {
			switch (menu = SelectMenu()) {
			case Add:
				so =  new SimpleObject2();
				so.scanData("입력", 3);
				lst1.add(so, SimpleObject2.NO_ORDER);
				SimpleObject2 [] simpleobjects = new SimpleObject2[10];
				makeSimpleObjects(simpleobjects);
				for (int i = 0; i < simpleobjects.length;i++)
					lst1.add(simpleobjects[i], SimpleObject2.NO_ORDER );
				break;
			case Delete: // 임의 객체를 삭제
				so =  new SimpleObject2();
				so.scanData("삭제", SimpleObject2.NO);
				lst1.delete(so, SimpleObject2.NO_ORDER);
				break;
			case Show: // 리스트 전체를 출력
				lst1.show();
				break;
			case Search: // 회원 번호 검색
				so =  new SimpleObject2();
				so.scanData("탐색", SimpleObject2.NO);
				SimpleObject2 result = lst1.search(so, SimpleObject2.NO_ORDER);
				if (result==null)
					System.out.println("검색 값 = " + so.toString() + ", 데이터가 없습니다.");
				else
					//찾은 객체 전보 출력
					System.out.println("검색 값 = " + result.toString() + ", 데이터가 존재합니다.");
				break;
			case Merge_NewList://기존 2개의 리스트를 합병하여 새로운 리스트를 생성(새로운 노드를 생성하여 추가)
				for (int i = 0; i < count; i++) { //3개의 객체를 연속으로 입력받아 l2 객체를 만든다 
					so = new SimpleObject2();
					so.scanData("병합", 3);
					lst2.add(so, SimpleObject2.NO_ORDER );				
				}
				SimpleObject2 [] simpleobjects2 = new SimpleObject2[10];
				makeSimpleObjects2(simpleobjects2);
				for (int i = 0; i < simpleobjects2.length;i++)
					lst2.add(simpleobjects2[i], SimpleObject2.NO_ORDER );
				System.out.println("리스트 lst1::");
				lst1.show();
				System.out.println("리스트 lst2::");
				lst2.show();
				lst3= lst1.merge_NewList(lst2, SimpleObject2.NO_ORDER);
				//merge 실행후 show로 결과 확인 - 새로운 노드를 만들지 않고 합병 - 난이도 상
				System.out.println("병합 리스트 lst3::");
				lst3.show();	
				break;
			case Merge_InPlace:
				for (int i = 0; i < count; i++) {//3개의 객체를 연속으로 입력받아 l2 객체를 만든다 
					so = new SimpleObject2();
					so.scanData("병합", 3);
					lst4.add(so, SimpleObject2.NO_ORDER );				
				}
				SimpleObject2 [] simpleobjects3 = new SimpleObject2[10];
				makeSimpleObjects3(simpleobjects3);
				for (int i = 0; i < simpleobjects3.length;i++)
					lst4.add(simpleobjects3[i], SimpleObject2.NO_ORDER );
				System.out.println("리스트 lst2::");
				lst2.show();
				System.out.println("리스트 lst4::");
				lst4.show();
				lst4.merge_NewList(lst2, SimpleObject2.NO_ORDER);
				//merge 실행후 show로 결과 확인 - 새로운 노드를 만들지 않고 합병 - 난이도 상
				System.out.println("병합 리스트 lst4::");
				lst4.show();
			case Exit: // 
				break;
			}
		} while (menu != Menu.Exit);
	}
	static void makeSimpleObjects(SimpleObject2 []simpleobjects) {
        simpleobjects[0] = new SimpleObject2("s8", "hong", "240618");
        simpleobjects[1] = new SimpleObject2("s2", "kim", "240619");
        simpleobjects[2] = new SimpleObject2("s3", "lee", "240601");
        simpleobjects[3] = new SimpleObject2("s1", "park", "240621");
        simpleobjects[4] = new SimpleObject2("s4", "choi", "240622");
        simpleobjects[5] = new SimpleObject2("s6", "jung", "240611");
        simpleobjects[6] = new SimpleObject2("s7", "kang", "240624");
        simpleobjects[7] = new SimpleObject2("s5", "jo", "240615");
        simpleobjects[8] = new SimpleObject2("s19", "oh", "240606");
        simpleobjects[9] = new SimpleObject2("s10", "jang", "240607");
 
	}
	static void makeSimpleObjects2(SimpleObject2 []simpleobjects) {
        simpleobjects[0] = new SimpleObject2("s5", "song", "240608");
        simpleobjects[1] = new SimpleObject2("s2", "Lim", "240609");
        simpleobjects[2] = new SimpleObject2("s3", "kee", "240601");
        simpleobjects[3] = new SimpleObject2("s1", "park", "240611");
        simpleobjects[4] = new SimpleObject2("s8", "choo", "240612");
        simpleobjects[5] = new SimpleObject2("s9", "jong", "240618");
        simpleobjects[6] = new SimpleObject2("s4", "jang", "240614");
        simpleobjects[7] = new SimpleObject2("s7", "go", "240605");
        simpleobjects[8] = new SimpleObject2("s11", "na", "240616");
        simpleobjects[9] = new SimpleObject2("s10", "you", "240617");
 
	}
	static void makeSimpleObjects3(SimpleObject2 []simpleobjects) {
        simpleobjects[0] = new SimpleObject2("s5", "song", "240608");
        simpleobjects[1] = new SimpleObject2("s2", "Lim", "240609");
        simpleobjects[2] = new SimpleObject2("s3", "kee", "240601");
        simpleobjects[3] = new SimpleObject2("s1", "park", "240611");
        simpleobjects[4] = new SimpleObject2("s8", "choo", "240612");
        simpleobjects[5] = new SimpleObject2("s9", "jong", "240618");
        simpleobjects[6] = new SimpleObject2("s4", "jang", "240614");
        simpleobjects[7] = new SimpleObject2("s7", "go", "240605");
        simpleobjects[8] = new SimpleObject2("s11", "na", "240616");
        simpleobjects[9] = new SimpleObject2("s10", "you", "240617");
 
	}
}
