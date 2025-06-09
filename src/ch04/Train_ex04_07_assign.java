package ch04;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//실습 7번 : 원형 큐로서 큐에 Point 객체를 저장
//num 변수를 사용하지 않음

class Point5 {
	private int ix;
	private int iy;
	
	//생성자
	public Point5(int x, int y) {
		ix = x;
		iy = y;
	}

	@Override
	public String toString() {
		return "[ix=" + ix + ", iy=" + iy + "]";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
}

class CircularQueue {
	static int QUEUE_SIZE = 0;
	Point5[] que;//배열로 객체원형 큐 구현
	int front, rear;
	boolean isEmptyTag;
	
	//실행시 예외: 큐가 비어있음
	public class EmptyQueueException extends RuntimeException {
		public EmptyQueueException(String s) {
			super(s);
		}
	}

	//실행시 예외: 큐가 가득 찼음
	public class OverflowQueueException extends RuntimeException {
		public OverflowQueueException(String s) {
			super(s);
		}
	}
	
	public CircularQueue(int count) {
		QUEUE_SIZE = count;
		que = new Point5[count];
		front = rear = 0;
		isEmptyTag = true;
	}
	
	//큐에 데이터를 인큐
	void push(Point5 x) throws OverflowQueueException{
		if(isFull()) {
			throw new OverflowQueueException("push: circular queue overflow"); 
		}
		que[rear] = x;
		rear = (rear+1)%QUEUE_SIZE;
		
		isEmptyTag = false; //하나라도 들어있으면 false
	}
	
	//큐에서 데이터를 디큐
	Point5 pop() throws EmptyQueueException{
		if(isEmpty()) {
			throw new EmptyQueueException("pop: circular queue overflow"); 
		}
		Point5 x = que[front];
		front = (front+1)%QUEUE_SIZE;
		
		if(front == rear) {
			isEmptyTag = true;
		}
		return x;
	}
	
	//큐에서 데이터를 피크(프런트 데이터를 들여다봄)
	 public Point5 peek() throws EmptyQueueException {
		 if (isEmpty())
			 throw new EmptyQueueException("peek: queue empty"); // 큐가 비어있음
		 return que[front];
	 }
	 
	 //큐를 비움
	 void clear() throws EmptyQueueException{
		 if(isEmpty()) {
			 throw new EmptyQueueException("enque: circular queue overflow"); 
		 }
		 rear = front = 0;
		 isEmptyTag = true;
	 }
	 
	//큐의 전체 크기를 반환
	public int getCapacity() {
		return QUEUE_SIZE; 
	}

	//큐에 쌓여 있는 데이터 개수를 반환
	public int size() {
		if(rear==front) {
			if (isEmptyTag) return 0;
			else return QUEUE_SIZE;
		}
		return (rear-front+QUEUE_SIZE)%QUEUE_SIZE;
		
	}
	
	//원형 큐가 비어있는가? 
	public boolean isEmpty() {
		if(isEmptyTag) return true;
		else return false;
	}

	//원형 큐가 가득 찼는가?
	public boolean isFull() {
		if(front == rear && !isEmptyTag) return true;
		else return false;
	}

	public void dump() throws EmptyQueueException{
		if (isEmpty())
				throw new EmptyQueueException("dump: queue empty");
		else {
			for(int i=0;i<size();i++) {
				System.out.print(que[(front+i)%QUEUE_SIZE]+" ");
			}
			System.out.println();
		}
	}
}


public class Train_ex04_07_assign {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		CircularQueue oq = new CircularQueue(4); // 최대 4개를 인큐할 수 있는 큐
		Random random = new Random();
		int rndx = 0, rndy = 0;
		Point5 p = null;
		while (true) {
			System.out.println(" "); // 메뉴 구분을 위한 빈 행 추가
			System.out.printf("현재 데이터 개수: %d / %d\n", oq.size(), oq.getCapacity());
			System.out.print("(1)인큐　(2)디큐　(3)피크　(4)덤프　(5) clear  (0)종료: ");
			int menu = stdIn.nextInt();
			if (menu == 0)
				break;
			
			if (menu == 0) {
	             System.out.println("프로그램을 종료합니다.");
	             break;
	        }
			
			
			//예외처리 유무의 기준 : 해당 작업을 실패하는 것이 프로그램의 정상적인 흐름을 더 진행할 수 없을 정도로 치명적이고 예측 가능한 '오류'인가?

			//Case 1 (인큐): 작업의 '실패'가 명확하고 예측 가능한 경우
			//사용자 의도: "큐에 새로운 데이터를 추가하고 싶다."
			//예외 상황: 큐가 가득 차서 더 이상 데이터를 추가할 수 없다
			
			//Case 4 (덤프): 작업의 '실패'가 아닌 '다른 결과'인 경우
			//사용자 의도: "큐에 들어있는 모든 내용을 보고 싶다."
			//예외 처리 필요 X : 작업이 실패한 것이 아니라, 결과가 '0개'임
			
			//Case 5 (클리어): 작업의 '실패'가 불가능한 경우
			//사용자 의도: "큐를 깨끗하게 비우고 싶다."
			//예외 처리 필요 X: 작업의 실패가 불가능함. 이미 큐가 비워져 있든말든 초기화시키면 됨

			switch (menu) {
			case 1: // 인큐 
				rndx = random.nextInt(20);
				rndy = random.nextInt(20);
				System.out.print("입력데이터: (" + rndx + ", " + rndy + ")");
				p = new Point5(rndx,rndy);
				try {
					oq.push(p);
				} catch(CircularQueue.OverflowQueueException e) {
					System.out.println("queue가 full입니다.");
				}
				break;

			case 2: // 디큐
				try {
					p = oq.pop();
					System.out.println("pop한 데이터는"+ p + "입니다.");
				} catch (CircularQueue.EmptyQueueException e) {
					System.out.println("queue가 비어있습니다.");
				}
				break;

			case 3: // 피크
				try {
					p = oq.peek();
					System.out.println("피크한 데이터는 " + p + "입니다.");
				} catch (CircularQueue.EmptyQueueException e) {
					System.out.println("queue가 비어있습니다.");
				}
				break;
				
			case 4: // 덤프
				oq.dump();
				break;
				
			case 5: //clear
				oq.clear();
				System.out.println("queue를 비웠습니다.");
				break;
			
			default:
				System.out.println("잘못된 메뉴 선택입니다.");
				break;
			}
		}
	}
}