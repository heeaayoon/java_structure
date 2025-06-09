package ch04;
//실습 4_6번 - point3(x,y)객체를 원형 큐 배열로 구현
//객체 생성을 위한 point3 class가 추가됨

import java.util.Random;
import java.util.Scanner;

class Point3 {
	private int ix;
	private int iy;

	// 생성자
	public Point3(int x, int y) {
		this.ix = x;
		this.iy = y;
	}

	@Override
	// 객체를 문자열로 예쁘게 출력하기 위해 toString() 오버라이드
	public String toString() {
		return "Point3 [x=" + ix + ", y=" + iy + "]";
	}
	
	@Override
	//equals() 메서드를 직접 만들어주지 않으면, 자바는 가장 기본적인 equals (메모리 주소가 같은지 비교)를 사용
	//따라서 override 해줘야함
	public boolean equals(Object obj) {
	    if (this == obj) return true; // 같은 메모리 주소를 가리키면 무조건 true
	    if (obj == null || getClass() != obj.getClass()) return false; // obj가 null이거나 클래스 타입이 다르면 false
	    Point3 other = (Point3) obj; // 같은 클래스임이 확인되었으므로 형변환
	    // ix와 iy 값이 모두 같을 때만 true를 반환
	    return this.ix == other.ix && this.iy == other.iy;
	}
}

//point3 타입의 객체를 저장하는 원형 큐 클래스
class objectQueue2 {
	private Point3[] que; // Point3 타입의 객체를 저장할 배열
	private int capacity; // 큐의 크기
	private int front; // 맨 처음 요소 커서
	private int rear; // 맨 끝 요소 커서
	private int num; // 현재 데이터 개수

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
	public objectQueue2(int maxlen) {
		capacity = maxlen;
		que = new Point3[capacity];
		rear = front = num = 0;
	}

	//큐에 데이터를 인큐
	public Point3 enque(Point3 x) throws OverflowQueueException {
		if(isFull()) {
			throw new OverflowQueueException();
		}
		que[rear]=x;
		num++;
		rear = (rear+1)%capacity;
		return x;
	}

	//큐에서 데이터를 디큐
	public Point3 deque() throws EmptyQueueException {
		if(isEmpty()) {
			throw new EmptyQueueException();
		}
		Point3 x = que[front];
		num--;
		front = (front+1)%capacity;
		return x;
	}

	//큐에서 데이터를 피크(프런트 데이터를 들여다봄)
	public Point3 peek() throws EmptyQueueException {
		if(isEmpty()) {
			throw new EmptyQueueException();
		}
		return que[front];
	}

	//큐를 비움
	public void clear() {
		front = rear = num = 0;
	}

	//큐에서 x를 검색하여 인덱스(찾지 못하면 –1)를 반환
	public int indexOf(Point3 x) {
		for(int i=0;i<size();i++) {
			int idx = (front+i)%capacity;
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
		if(isEmpty()) {
			System.out.println("큐가 비었습니다.");
		}else {
			for(int i=0;i<size();i++) {
				System.out.print(que[(front+i)%capacity]+" ");
			}
			System.out.println();
		}

	}
}

public class Train_ex04_06_useNum {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		objectQueue2 oq = new objectQueue2(4); // 최대 4개를 인큐할 수 있는 큐
		Random random = new Random();
		
		int rndx = 0, rndy = 0;
		Point3 p = null;
		
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
                rndx = random.nextInt(20);
				rndy = random.nextInt(20);
				System.out.print("입력데이터: (" + rndx + ", " + rndy + ")");
				p = new Point3(rndx,rndy);
				try {
					oq.enque(p);
				} catch(objectQueue2.OverflowQueueException e) {
					System.out.println("큐가 가득 차있습니다.");
				}
				break;

			case 2: // 디큐
				try {
					p = oq.deque();
					System.out.println("디큐한 데이터는 " + p + "입니다.");
				} catch (objectQueue2.EmptyQueueException e) {
					System.out.println("큐가 비어 있습니다.");
				}
				break;

			case 3: // 피크
				try {
					p = oq.peek();
					System.out.println("피크한 데이터는 " + p + "입니다.");
				} catch (objectQueue2.EmptyQueueException e) {
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