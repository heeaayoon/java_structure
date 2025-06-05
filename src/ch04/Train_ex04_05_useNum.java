package ch04;
//실습 4_5번 - 정수 배열 원형 큐 - 교재 148 ~ 157 페이지
//num 변수를 사용

import java.util.Random;
import java.util.Scanner;

class IntQueue3 {
	private int[] que; // 큐용 배열
	private int capacity; // 큐의 전체 크기
	private int front; // 맨 처음 요소 커서
	private int rear; // 맨 끝 요소 커서
	private int num; //현재 데이터 갯수
		
	//실행시 예외: 큐가 비어있음
	public class EmptyIntQueue3Exception extends RuntimeException {
		public EmptyIntQueue3Exception(String s) {
			super(s);
		}
	}

	//실행시 예외: 큐가 가득 찼음
	public class OverflowIntQueue3Exception extends RuntimeException {
		public OverflowIntQueue3Exception(String s) {
			super(s);
		}
	}

	//생성자(constructor)
	public IntQueue3(int maxlen) {
		capacity = maxlen;
		que = new int[capacity];
		num = front = rear = 0; //num은 que에 들어있는 수
	}

	//큐에 데이터를 인큐
	public int enque(int x) throws OverflowIntQueue3Exception {
		//push할 때는 rear를 사용
		if (num>=capacity)
			throw new OverflowIntQueue3Exception("enque : 큐가 가득참");
		que[rear]=x; //rear에 매개값을 넣고
		rear++; //rear를 1 증가시킴
		num++;  //num도 1 증가
		if(rear == capacity) rear =0;
		//rear = (rear+1)%capacity;
		return x;
	}

	//큐에서 데이터를 디큐
	public int deque() throws EmptyIntQueue3Exception {
		//pop할 때는 front를 사용
		if (num<=0)
			throw new EmptyIntQueue3Exception("deque : 큐가 비었음");
		int y = que[front]; //front에서 값을 빼고
		front++; //front를 1 증가
		num--; //num은 1 감소
		if(front == capacity) front =0;
		//front = (front+1) % capacity;
		return y;
	}

	//큐에서 데이터를 피크(프런트 데이터를 들여다봄)
	public int peek() throws EmptyIntQueue3Exception {
		if(num<=0) {
			throw new EmptyIntQueue3Exception("peek : 큐가 비었음"); 
		}
		return que[front];
	}

	//큐를 비움
	public void clear() {
		num = front = rear = 0;
	}

	//큐에서 x를 검색하여 인덱스(찾지 못하면 –1)를 반환
	public int indexOf(int x) {
		for(int i=0;i<num;i++) {
			int idx = (i+front)%capacity; //스캔은 프론트에서 시작함
			if(que[idx]==x) return idx;
		}
		return -1;
	}

	//큐의 전체 크기를 반환
	public int getCapacity() {
		return capacity;
	}

	//큐에 쌓여 있는 데이터 개수를 반환
	public int size() {
		return num;
	}

	//큐가 비어있는가?
	public boolean isEmpty() {
		return num<=0;
	}

	//큐가 가득 찼는가?
	public boolean isFull() {
		return num>=capacity;
	}

	//큐 안의 모든 데이터를 프런트 → 리어 순으로 출력
	public void dump() {
		if(num<=0) {
			System.out.println("큐가 비었습니다.");
		}else {
			for(int i=0;i<num;i++) {
				int idx = (i+front)%capacity;
				System.out.println(que[idx]);
			}
		}
	}
}
public class Train_ex04_05_useNum {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		IntQueue3 oq = new IntQueue3(4); // 최대 4개를 인큐할 수 있는 큐
		Random random = new Random();
		int rndx = 0, p = 0;
		while (true) {
			System.out.println(" "); // 메뉴 구분을 위한 빈 행 추가
			System.out.printf("\n현재 데이터 개수: %d / %d\n", oq.size(), oq.getCapacity());
			System.out.print("(1)인큐　(2)디큐　(3)피크　(4)덤프　(0)종료: ");
			int menu = stdIn.nextInt();
			switch (menu) {
			case 1: // 인큐
				rndx = random.nextInt(20);
				System.out.print("입력데이터: " + rndx );
				try {
					oq.enque(rndx);
				} catch(ch04.IntQueue3.OverflowIntQueue3Exception e) {
					System.out.println("큐가 가득차 있습니다.");
				}
				break;

			case 2: // 디큐
				try {
					p = oq.deque();
					System.out.println("디큐한 데이터는 " + p + "입니다.");
				} catch (ch04.IntQueue3.EmptyIntQueue3Exception e) {
					System.out.println("큐가 비어 있습니다.");
				}
				break;

			case 3: // 피크
				try {
					p = oq.peek();
					System.out.println("피크한 데이터는 " + p + "입니다.");
				} catch (ch04.IntQueue3.EmptyIntQueue3Exception e) {
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