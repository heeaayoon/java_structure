package ch04;
//실습 4_6번 - point4(x,y)객체를 원형 큐 배열로 구현
//객체 생성을 위한 point4 class가 추가됨
//num 변수를 사용하지 않고 front == rear 일 때 queue가 full인지 empty 인지를 판단

import java.util.Random;
import java.util.Scanner;

class Point4 {
	private int ix;
	private int iy;

	// 생성자
	public Point4(int x, int y) {
		this.ix = x;
		this.iy = y;
	}

	@Override
	// 객체를 문자열로 예쁘게 출력하기 위해 toString() 오버라이드
	public String toString() {
		return "Point4 [x=" + ix + ", y=" + iy + "]";
	}
	
	@Override
	//equals() 메서드를 직접 만들어주지 않으면, 자바는 가장 기본적인 equals (메모리 주소가 같은지 비교)를 사용
	//따라서 override 해줘야함
	public boolean equals(Object obj) {
	    if (this == obj) return true; // 같은 메모리 주소를 가리키면 무조건 true
	    if (obj == null || getClass() != obj.getClass()) return false; // obj가 null이거나 클래스 타입이 다르면 false
	    Point4 other = (Point4) obj; // 같은 클래스임이 확인되었으므로 형변환
	    // ix와 iy 값이 모두 같을 때만 true를 반환
	    return this.ix == other.ix && this.iy == other.iy;
	}
}

//point3 타입의 객체를 저장하는 원형 큐 클래스
class objectQueue3 {
	private Point4[] que; // Point4 타입의 객체를 저장할 배열
	private int capacity; // 큐의 크기
	private int front; // 맨 처음 요소 커서
	private int rear; // 맨 끝 요소 커서
	private boolean isEmptyTag; // 비었을 때 t/ 차 있을 때 f -> enque시에 false 로, deque 후에 큐가 비면 다시 true로 설정함
	// private int num; //현재 데이터 개수를 삭제

	// 실행시 예외: 큐가 비어있음
	public class EmptyQueueException extends RuntimeException {
		public EmptyQueueException() {
			super("큐가 비어 있습니다.");
		}
	}

	// 실행시 예외: 큐가 가득 찼음
	public class OverflowQueueException extends RuntimeException {
		public OverflowQueueException() {
			super("큐가 가득 찼습니다.");
		}
	}

	//생성자(constructor)
	public objectQueue3(int maxlen) {
		capacity = maxlen;
		que = new Point4[capacity];
		rear = front = 0;
		isEmptyTag = true;
	}
	
	// 인큐, 디큐 하기전에 갯수를 세어 front==rear 조건을 체크(isFull(), isEmpty()함수 없다고 가정)

	//큐에 데이터를 인큐
	public void enque(Point4 x) throws OverflowQueueException {
		if(front == rear && !isEmptyTag) {
			throw new OverflowQueueException();
		}
		que[rear]=x;
		rear = (rear+1)%capacity;
		
		isEmptyTag = false; //데이터를 하나라도 넣으면 더 이상 비어있지 않음
	}

	//큐에서 데이터를 디큐
	public Point4 deque() throws EmptyQueueException {
		if(front == rear && isEmptyTag) {
			throw new EmptyQueueException();
		}
		Point4 x = que[front];
		front = (front+1)%capacity;

		if (front == rear) { // 디큐 후 front와 rear가 같아지면 큐가 비게 된 것
            isEmptyTag = true;
        }
		return x;
	}

	//큐에서 데이터를 피크(프런트 데이터를 들여다봄)
	public Point4 peek() throws EmptyQueueException {
		if(front == rear && isEmptyTag) {
			throw new EmptyQueueException();
		}
		return que[front];
	}

	//큐를 비움
	public void clear() {
		front = rear = 0;
		isEmptyTag = true;
	}

	//큐에서 x를 검색하여 인덱스(찾지 못하면 –1)를 반환
	public int indexOf(Point4 x) {
		for(int i=0;i<size();i++) {
			int idx = (front+i)%capacity; //front가 0이 아닐 수도 있음
			if(que[idx].equals(x)) return idx;
		}
		return -1;
	}

	//큐의 전체크기를 반환
	public int getCapacity() {
		return capacity;
	}

	//큐에 쌓여 있는 데이터 개수를 반환
	public int size() {
		//front == rear 인 두 상황을 먼저 고려 
		if(front == rear) {
			if(isEmptyTag) return 0; //비어 있는 상태
			else return capacity; //가득 찬 상태
		}
		//front!=rear 인 일반적인 상황에서 
		return (rear-front+capacity)%capacity; //큐가 한바퀴 돈 경우, (rear-front)는 음수가 되므로 capacity를 더해주고 %를 이용해 너무 커진 수를 바로잡음
	}
	
	//큐 안의 모든 데이터를 프런트 → 리어 순으로 출력
	public void dump() {
		if(front == rear && isEmptyTag) {
			System.out.println("큐가 비었습니다.");
		}else {
			for(int i=0;i<size();i++) {
				System.out.print(que[(front+i)%capacity]+" ");
			}
			System.out.println();
		}

	}
}

public class Train_ex04_06_withoutNum {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		objectQueue3 oq = new objectQueue3(4); // 최대 4개를 인큐할 수 있는 큐
		Random random = new Random();
		
		int rndx = 0, rndy = 0;
		Point4 p = null;
		
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
				// 큐의 상태를 직접 확인하여 try-catch 제거
			    if (oq.size() == oq.getCapacity()) { // 가득 찼는지 직접 확인
			        System.out.println("큐가 가득 찼습니다.");
			    } else {
			        // 가득 차지 않았을 때만 객체를 생성하고 인큐
			        rndx = random.nextInt(20);
			        rndy = random.nextInt(20);
			        p = new Point4(rndx, rndy);
			        oq.enque(p);
			        System.out.println("입력 데이터: " + p);
			    }
			    break;

			case 2: // 디큐
				try {
					p = oq.deque();
					System.out.println("디큐한 데이터는 " + p + "입니다.");
				} catch (objectQueue3.EmptyQueueException e) {
					System.out.println("큐가 비어 있습니다.");
				}
				break;

			case 3: // 피크
				try {
					p = oq.peek();
					System.out.println("피크한 데이터는 " + p + "입니다.");
				} catch (objectQueue3.EmptyQueueException e) {
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