package ch04;
//실습 4_5번 - 정수 배열 원형 큐

//num 변수를 사용하지 않고 front == rear 일 때 queue가 full인지 empty 인지를 판단

import java.util.Random;
import java.util.Scanner;

class IntQueue5 {
	private int[] que; // 큐용 배열
	private int capacity; // 큐의 전체 크기
	private int front; // 맨 처음 요소 커서
	private int rear; // 맨 끝 요소 커서
	boolean isEmptyTag; // 비었을 때 t/ 차 있을 때 f
	// private int num; //현재 데이터 개수를 삭제

	// 실행시 예외: 큐가 비어있음
	public class EmptyIntQueue3Exception extends RuntimeException {
		public EmptyIntQueue3Exception(String s) {
			super(s);
		}
	}

	// 실행시 예외: 큐가 가득 찼음
	public class OverflowIntQueue3Exception extends RuntimeException {
		public OverflowIntQueue3Exception(String s) {
			super(s);
		}
	}

	// 생성자(constructor)
	public IntQueue5(int maxlen) {
		capacity = maxlen;
		que = new int[capacity];
		front = rear = 0;
		isEmptyTag = true;
	}

	// 인큐 하기전에 갯수를 세어 front==rear 조건을 체크한다
	// deque도 마찬가지임
	
	// 큐에 데이터를 인큐
	public int enque(int x) throws OverflowIntQueue3Exception {
		//인큐할 때는 front를 사용
		if (front == rear & !isEmptyTag)
			throw new EmptyIntQueue3Exception("enque : 큐가 가득참");
		que[rear] = x;
		rear++;
		rear = (rear + 1) % capacity;
		return 0;
	}

	// 큐에서 데이터를 디큐
	public int deque() throws EmptyIntQueue3Exception {
		// 디큐할 때는 front를 사용
		if (front == rear & isEmptyTag)
			throw new EmptyIntQueue3Exception("deque : 큐가 비었음");
		int result = que[front];
		front++;
		front = (front + 1) % capacity;
		return 0;
	}

	//큐에서 데이터를 피크(프런트 데이터를 들여다봄)
	public int peek() throws EmptyIntQueue3Exception {
		if(que.length<=0) {
			throw new EmptyIntQueue3Exception("peek : 큐가 비었음"); 
		}
		return que[front];
	}

	//큐를 비움
	public void clear() {
		front = rear = 0;
	}

	// 큐에서 x를 검색하여 인덱스(찾지 못하면 –1)를 반환
	public int indexOf(int x) {
		for (int i = 0; i < que.length; i++) {
			int idx = (i + front) % capacity; // 스캔은 프론트에서 시작함
			if (que[idx] == x)
				return idx;
		}
		return -1;
	}

	// 큐의 전체 크기를 반환
	public int getCapacity() {
		return capacity;
	}

	// 큐에 쌓여 있는 데이터 개수를 반환
	public int size() {
		return que.length;
	}

	// 큐가 비어있는가?
	public boolean isEmpty() {
		return que.length <= 0;
	}

	// 큐가 가득 찼는가?
	public boolean isFull() {
		return que.length >= capacity;
	}

	// 큐 안의 모든 데이터를 프런트 → 리어 순으로 출력 ---//
	public void dump() {

	}
}

public class Train_ex04_05_withoutNum {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		IntQueue3 oq = new IntQueue3(4); // 최대 64개를 인큐할 수 있는 큐
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
				System.out.print("입력데이터: (" + rndx + ")");
				try {
					oq.enque(rndx);
				} catch (ch04.IntQueue5.OverflowIntQueue3Exception e) {
					System.out.println("stack이 가득찼있습니다.");
				}
				break;

			case 2: // 디큐
				try {
					p = oq.deque();
					System.out.println("디큐한 데이터는 " + p + "입니다.");
				} catch (ch04.IntQueue5.EmptyIntQueue3Exception e) {
					System.out.println("큐가 비어 있습니다.");
				}
				break;

			case 3: // 피크
				try {
					p = oq.peek();
					System.out.println("피크한 데이터는 " + p + "입니다.");
				} catch (ch04.IntQueue5.EmptyIntQueue3Exception e) {
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