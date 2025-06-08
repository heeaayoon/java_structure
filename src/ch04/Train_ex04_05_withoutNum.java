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
	boolean isEmptyTag; // 비었을 때 t/ 차 있을 때 f -> enque시에 false 로, deque 후에 큐가 비면 다시 true로 설정함
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

	// 인큐, 디큐 하기전에 갯수를 세어 front==rear 조건을 체크(isFull(), isEmpty()함수 없다고 가정)
	
	// 큐에 데이터를 인큐
	public void enque(int x) throws OverflowIntQueue3Exception {
		//인큐할 때는 rear를 사용
		if (front == rear & !isEmptyTag)
			throw new EmptyIntQueue3Exception("enque : 큐가 가득참");
		
		que[rear] = x;
		rear = (rear + 1) % capacity;
		
		isEmptyTag = false; //데이터를 하나라도 넣으면 더 이상 비어있지 않음
	}

	// 큐에서 데이터를 디큐
	public int deque() throws EmptyIntQueue3Exception {
		// 디큐할 때는 front를 사용
		if (front == rear & isEmptyTag)
			throw new EmptyIntQueue3Exception("deque : 큐가 비었음");
		
		int x = que[front];
		front = (front + 1) % capacity;
		
		if (front == rear) { // 디큐 후 front와 rear가 같아지면 큐가 비게 된 것
            isEmptyTag = true;
        }
		return x;
	}

	//큐에서 데이터를 피크(프런트 데이터를 들여다봄)
	public int peek() throws EmptyIntQueue3Exception {
		if(front == rear & isEmptyTag) {  //que.length는 큐의 전체 길이를 의미함(큐에서 데이터가 들어있는 부분의 길이가 X)
			throw new EmptyIntQueue3Exception("peek : 큐가 비었음"); 
		}
		return que[front];
	}

	//큐를 비움
	public void clear() {
		front = rear = 0;
		isEmptyTag = true;
	}

	// 큐에서 x를 검색하여 인덱스(찾지 못하면 –1)를 반환
	public int indexOf(int x) {
		for (int i = 0; i <size(); i++) {
			int idx = (i + front) % capacity; // 스캔은 프론트에서 시작함
			if (que[idx] == x)
				return idx;
		}
		return -1;
	}

	// 큐의 전체 크기(용량)를 반환
	public int getCapacity() {
		return capacity;
	}

	// 큐에 쌓여 있는 데이터 개수를 반환
	public int size() {
		//front == rear 인 두 상황을 먼저 고려 
		if(front == rear) {
			if(isEmptyTag) return 0; //비어 있는 상태
			else return capacity; //가득 찬 상태
		}
		//front!=rear 인 일반적인 상황에서 
		return (rear-front+capacity)%capacity; //큐가 한바퀴 돈 경우, (rear-front)는 음수가 되므로 capacity를 더해주고 %를 이용해 너무 커진 수를 바로잡음
	}

	// 큐 안의 모든 데이터를 프런트 → 리어 순으로 출력
	public void dump() {
		if(front==rear&&isEmptyTag) {
			System.out.println("큐가 비었습니다.");
		}else {
			for(int i=0;i<size();i++) {
				System.out.print(que[(front+i%capacity)]+" "); //front의 위치가 0이 아닐 수도 있음!
			}
		}System.out.println();
	}
}

public class Train_ex04_05_withoutNum {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		IntQueue5 oq = new IntQueue5(4); // 최대 4개를 인큐할 수 있는 큐
		Random random = new Random();
		int rndx = 0, p = 0;
		while (true) {
			System.out.println(" "); // 메뉴 구분을 위한 빈 행 추가
			System.out.printf("현재 데이터 개수: %d / %d\n", oq.size(), oq.getCapacity());
			System.out.print("(1)인큐　(2)디큐　(3)피크　(4)덤프　(0)종료: ");
			int menu = stdIn.nextInt();
			
			 if (menu == 0) {
	                System.out.println("프로그램을 종료합니다.");
	                break;
	            }
			
			switch (menu) {
			case 1: // 인큐
				try {
					rndx = random.nextInt(20);
					System.out.print("입력데이터: " + rndx );
					oq.enque(rndx);
				} catch (ch04.IntQueue5.OverflowIntQueue3Exception e) {
					System.out.println("stack이 가득찼습니다.");
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
				
			default: //위의 어느 case에도 해당하지 않을 때 실행되는 부분
				System.out.println("번호를 다시 선택하시오");
				break;
			}
		}
	}
}