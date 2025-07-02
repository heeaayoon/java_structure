package ch08;

import java.util.Comparator;

public class LinkedList<T> {
	private final Node<T> head;
	private int size;
	
	public LinkedList() {
		this.head = new Node<>(null); //더미노드
		this.head.setPrev(head);
		this.head.setNext(head);
		this.size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return head.getNext == head;
	}
	
	
	//addFirst() : 맨앞에(head 바로 뒤) 요소 추가
	public void addFirst(T obj) {
		Node<T> newNode = new Node<>(obj);
		Node<T> first = head.getNext();
		
		//새로운 노드 좌우 연결
		newNode.setprev(head);
		newNode.setNext(first);
		
		//기존 노드와 연결
		head.setNext(newNode);
		first.serPrev(newNode);
		
		size++;
	}
	
	//addLast() : 맨뒤에 요소 추가
	public void addLast(T obj) {
		Node<T> newNode = new Node<>(obj);
		Node<T> last = head.getNext();
		//새로운 노드 좌우 연결
		newNode.setprev(last);
		newNode.setNext(head);
		
		//기존 노드와 연결
		last.setNext(newNode);
		head.serPrev(newNode);
		size++;
	}
	
	
	//private final Node<T> head; 에서 final 제거 -> head 조작하기
	//addLast() : 맨뒤에 요소 추가 -- 나중에 해보기
//	public void addLast(T obj) {
//		Node<T> newNode = new Node<>(obj);
//		
//		if(head ==null) {
//			head = newNode;
//		}else {
//			Node<T> current = head;
//			
//			while(current.getNext() != null) {
//				current = current.getNext();
//			}
//			current.setNext(newNode);
//			current.setPrev(current);
//		}
//		size++;
//	}
	
	//삭제 
	public void delete(T obj, Comparator<? super T> c) { //<? super T> T 타입을 상속하는 임의의 자료형(?)
		//Node<T> current(현재노드)
		//반복구문을 이용해 current를 밀기
		//만약 obj와 Node<T> 같으면 삭제 //어떻게 비교하지?
		//아니면 return 해야함
		
		Node<T> current = head.getNext();
		while(current != head) {
			if(c.compare(current.getData(), obj) == 0) { // 같음 = 지워질 값을 찾았음
				current.getPrev().setNext(current.getNext()); // B를 지우고, A와 C를 연결
				current.getNext().setPrev(current.getPrev());
				size--;
				System.out.println("삭제완료 : " + obj);
			}
			current = current.getNext(); // 다르면 뒤로 넘어가기 
		}
		System.out.println("삭제할 데이터를 찾을 수 없습니다.");
	}
	
	
	//l3 = l1.merge(l2); 실행하도록 리턴 값이 리스트임 
	//l.add(위에서 만든 메소드)를 사용하여 구현
	//기존 리스트의 노드를 변경하지 않고 새로운 리스트의 노드들을 생성하여 구현 
	public LinkedList<T> mergeNewList(LinkedList<T> lst2, Comparator<? super T> cc){
		//add를 사용하는 것으로 변경 -> 순서 보장을 위해 
		LinkedList<T> lst3 = new LinkedList<>();
		
		Node<T> ai = head.getNext();
		Node<T> bi = lst2.head.getNext();
		
		// 링크드리스트의 길이가 다름! -> 퀵소트 사용
		// 두 링크드리스트의 길이가 동일한게 아니라면 퀵소트를 사용해야 함
		//if(getData(), getDate() <=0 ) // 순서가 존재함
		//add메소드 이용해서 추가하기 ai
		//else
		//addLast 이용해서 bi 추가해야함 
		//남은 LinkedList ai를 순회해서 뒷부분에 추가
		//남은 LinkedList bi를 순회해서 뒷부분에 추가
		while( ai != head && bi != lst2.head) {
			if(cc.compare(ai.getData(), bi.getDate()) <= 0 ) { // ai < bi
				lst3.addLast((ai.getData());
				ai = ai.getNext();
			}else {
				lst3.addLast(bi.getData());
				bi.getNext();
			}
		}
		while(ai!=head) {
			//lst3.addLast(ai.getData());
			lst3.add(ai.getData());
			ai = ai.getNext();
		}
		
		while(bi!= lst2.head) {
			//lst3.addLast(bi.getData());
			lst3.add(bi.getData());
			bi = bi.getNext();
		}		
		return lst3;
	}
	
	//오름차순 순서를 보장하는 add
	public void add(T obj, Comparator<? super T> c) {
		Node<T> newNode = new Node<>(obj);
		Node<T> current = head.getNext();
		
		//정렬된 위치 찾기
		while(current != head && c.compare(obj, current.getData())>0) {
			current = current.getNext();
		}
		//current 기준으로 앞에 삽입
		//새로운 노드 좌우 연결
		newNode.setprev(current.getPrev());
		newNode.setNext(current);
		//기존 노드와 연결
		current.getPrev().setPrev(newNode);
		current.serPrev(newNode);
		size++;	
	}
	
	//add 오버로딩
	public void add(T obj) {
		@SuppressWarnings("unchecked")
		Comparable<T> compareableObj = (Comparable<T>) obj;
		add(obj, (a,b) -> compareableObj.compareTo(b));
	}

}
