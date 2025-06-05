package ch04;
//실습 4_4번 - 정수 선형 큐를 ArrayList로 구현
//교재 148-  페이지는 원형큐를 배열로 구현한 코드 실습 4번에 활용
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Queue4 { 
	private List<Integer> que; //ArrayList 타입의 선형 큐
	private int capacity; // 큐의 크기
	private int front; // 맨 처음 요소 커서 : 데이터가 나오는 쪽
	private int rear; // 맨 끝 요소 커서 : 데이터가 들어가는 쪽

	//실행시 예외: 큐가 비어있음
	public class EmptyQueueException extends RuntimeException {
		public EmptyQueueException() {}
	}

	//실행시 예외: 큐가 가득 찼음
	public class OverflowQueueException extends RuntimeException {
		public OverflowQueueException() {}
	}

	//생성자(constructor)
	public Queue4(int maxlen) {
		capacity = maxlen;
		que = new ArrayList<Integer>(capacity);
		front = rear = 0;
	}

	// 큐에 데이터를 인큐(데이터 넣기)
	public int enque(int x) throws OverflowQueueException {
		if(isFull()) throw new OverflowQueueException(); //큐가 가득찬 경우 예외처리
		que[rear++]= x;
		
		return que;
	}

//--- 큐에서 데이터를 디큐 ---//
	public int deque() throws EmptyQueueException {

	}

//--- 큐에서 데이터를 피크(프런트 데이터를 들여다봄) ---//
	public int peek() throws EmptyQueueException {

	}

//--- 큐를 비움 ---//
	public void clear() {
		front = rear = 0;
	}

//--- 큐에서 x를 검색하여 인덱스(찾지 못하면 –1)를 반환 ---//
	public int indexOf(int x) {

	}

//--- 큐의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;
	}

//--- 큐에 쌓여 있는 데이터 개수를 반환 ---//
	public int size() {

	}

//--- 큐가 비어있는가? ---//
	public boolean isEmpty() {
	
	}

//--- 큐가 가득 찼는가? ---//
	public boolean isFull() {

	}

//--- 큐 안의 모든 데이터를 프런트 → 리어 순으로 출력 ---//
	public void dump() {

	}
}
public class Train_ex04_04 {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		Queue4 oq = new Queue4(4); // 최대 64개를 인큐할 수 있는 큐
		Random random = new Random();
		int rndx = 0, p = 0;
		while (true) {
			System.out.println(" "); // 메뉴 구분을 위한 빈 행 추가
			System.out.printf("현재 데이터 개수: %d / %d\n", oq.size(), oq.getCapacity());
			System.out.print("(1)인큐　(2)디큐　(3)피크　(4)덤프　(0)종료: ");
			int menu = stdIn.nextInt();
			switch (menu) {
			case 1: // 인큐
				rndx = random.nextInt(20);
				System.out.print("입력데이터: (" + rndx +")");
				try {
					oq.enque(rndx);
				} catch(ch04.Queue4.OverflowQueueException e) {
					System.out.println("stack이 가득찼있습니다.");
				}
				break;

			case 2: // 디큐
				try {
					p = oq.deque();
					System.out.println("디큐한 데이터는 " + p + "입니다.");
				} catch (ch04.Queue4.EmptyQueueException e) {
					System.out.println("큐가 비어 있습니다.");
				}
				break;

			case 3: // 피크
				try {
					p = oq.peek();
					System.out.println("피크한 데이터는 " + p + "입니다.");
				} catch (ch04.Queue4.EmptyQueueException e) {
					System.out.println("큐가 비어 있습니다.");
				}
				break;

			case 4: // 덤프
				oq.dump();
				break;
			default:
				break;
			}
		}
	}
}