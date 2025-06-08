package ch04;
//실습 4_4번 - 정수 선형 큐를 ArrayList로 구현
//배열 대신 arrayList 를 이용하면 front, rear 포인터 대신 내장메소드인 add()로 맨 뒤에 데이터를 추가하고, remove(0)로 맨 앞 데이터를 제거할 수 있음
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Queue4 { 
	private List<Integer> que; //ArrayList 타입의 선형 큐
	private int capacity; // 큐의 크기
//	private int front; // 맨 처음 요소 커서 : 데이터가 나오는 쪽
//	private int rear; // 맨 끝 요소 커서 : 데이터가 들어가는 쪽

	//실행시 예외: 큐가 비어있음
	public class EmptyQueueException extends RuntimeException {
		public EmptyQueueException() {
			 super("큐가 비어 있습니다.");
		}
	}

	//실행시 예외: 큐가 가득 찼음
	public class OverflowQueueException extends RuntimeException {
		public OverflowQueueException() {
			super("큐가 가득 찼습니다.");
		}
	}

	//생성자(constructor)
	public Queue4(int maxlen) {
		capacity = maxlen;
		que = new ArrayList<Integer>(capacity);
	}

	// 큐에 데이터를 인큐(데이터 넣기)
	public int enque(int x) throws OverflowQueueException {
		if(isFull()) throw new OverflowQueueException(); //큐가 가득찬 경우 예외처리
		
		que.add(x); //ArrayList의 끝에 데이터 하나 추가
		return x;
	}

	//큐에서 데이터를 디큐
	public int deque() throws EmptyQueueException {
		if(isEmpty()) throw new EmptyQueueException(); //큐가 가득찬 경우 예외처리
		
		return que.remove(0); // ArrayList의 첫 번째 요소를 제거하고 그 값을 반환
	}

	//큐에서 데이터를 피크(프런트 데이터를 들여다봄)
	public int peek() throws EmptyQueueException {
		if(isEmpty()) throw new EmptyQueueException(); //큐가 가득찬 경우 예외처리
		
		return que.get(0); // ArrayList의 첫 번째 요소를 반환 : ArrayList 타입의 큐의 첫 번째 데이터는 항상 인덱스 0에 있기 때문에 가능
	}

	//큐를 비움
	public void clear() {
		que.clear();
	}

	//큐에서 x를 검색하여 인덱스(찾지 못하면 –1)를 반환
	public int indexOf(int x) {
		return que.indexOf(x); //ArrayList의 내장메소드인 indexOf()는 인덱스를 찾으면 해당 값을, 못찾으면 -1을 반환
	}

	//큐의 최대용량을 반환
	public int getCapacity() {
		return capacity;
	}

	//큐에 쌓여 있는 데이터 개수를 반환
	public int size() {
		return que.size();
	}

	//큐가 비어있는가?
	public boolean isEmpty() {
		return que.isEmpty();
	}

	//큐가 가득 찼는가?
	public boolean isFull() {
		return que.size()>=capacity; //내장메소드 없음 //현재 쌓여있는 데이터의 갯수가 전체용량보다 같거나 크면 가득찬 것
	}

	//큐 안의 모든 데이터를 프런트 → 리어 순으로 출력
	public void dump() {
		if(isEmpty()) {
			System.out.println("큐가 비었습니다.");
		}else {
			for(Integer item : que) {
                System.out.print(item + " ");
            }
            System.out.println();
	}
}
	
public class Train_ex04_04 {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		Queue4 oq = new Queue4(4); // 최대 4개를 인큐할 수 있는 큐
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
					System.out.print("입력데이터: " + rndx);
					oq.enque(rndx);
				} catch(ch04.Queue4.OverflowQueueException e) {
					System.out.println("큐가 가득찼습니다.");
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
				System.out.println("잘못된 메뉴입니다. 다시 선택해주세요.");
				break;
			}
		}
	}
}