package ch04;
/*
 * 실습 4_1번 - 정수 배열 스택
 * 스택을 정수 배열로 구현시에 교재 코드를 보지 않고 구현 완성하기 -> 예외 처리 코드 이해가 필요
 * 교재 133 - 실습 4-1 소스코드를 읽어보고 가능하면 책을 보지 않고 소스코드 구현완성 노력이 좋다 
 */

import java.util.Scanner;

class IntStack3 {
	private int[] stk; // 스택용 정수 배열
	private int capacity; // 스택의 전체용량
	private int top; // 스택의 꼭대기(탑) : 현재 스택에 들어있는 갯수

	/* API 가 제공하는 RuntimeException 클래스의 생성자 중 하나임
	public class RuntimeException extends Exception {
	    // 생성자 중 하나: 메시지를 받는 생성자
	    public RuntimeException(String message) {
	        // 부모 클래스인 Throwable 클래스의 생성자 호출
	        super(message);
	    }
	}
	*/
	
	//예외1. top이 0일때 pop()을 시도하면 -> 예외 발생
	public class EmptyIntStackException extends RuntimeException {
		//생성자 : 부모 클래스의 메세지를 받는 생성자를 호출함
		public EmptyIntStackException(String s) {
			super(s);
		}
	}
	
	//예외2. top이 capacity일 때 push()를 시도하면 -> 예외 발생
	public class OverflowIntStackException extends RuntimeException {
		public OverflowIntStackException(String s) {
			super(s);
		}
	}
	
	//생성자
	public IntStack3(int maxlen) {
		capacity = maxlen;
		/*
		OutOfMemoryError 클래스는 자바에서 메모리 부족 시 발생하는 에러를 나타내는 클래스,
		이 클래스는 Error 클래스의 하위 클래스이며, 메모리가 부족하여 객체를 생성할 수 없는 경우에 발생
		 */
		try {
			stk = new int[capacity]; //heap에 메모리를 할당			
		}catch(OutOfMemoryError e) {
			System.out.println("메모리 없음");
			capacity=0;
			return;
		}
		top = 0;
	}

	//스택에 x를 푸시
	public int push(int x) throws OverflowIntStackException {
		if (top >= capacity) // 스택이 가득 참
			throw new OverflowIntStackException("push: stack overflow");
		return stk[top++]=x; //stk[0]부터 차례대로 데이터를 push함 + top을 1씩 증가시킴
	}

	//스택에서 데이터를 팝(정상에 있는 데이터를 꺼냄)
	public int pop() throws EmptyIntStackException {
		if (top <= 0 ) // 스택이 빔
			throw new EmptyIntStackException("pop : stack underflow");
		return stk[--top]; //top을 1씩 감소시킴
	}

	//스택에서 데이터를 피크(스택의 꼭대기에 있는 데이터를 들여다봄
	public int peek() throws EmptyIntStackException {
		if(top<=0)
			throw new EmptyIntStackException("peek : stack underflow");
		return stk[top-1]; //꼭대기에 있는 요소를 반환
	}

	//스택을 비움
	public void clear() {
		top = 0;
	}

	//스택에서 x를 찾아 인덱스(없으면 –1)를 반환
	public int indexOf(int x) {
		for(int i=top-1;i>=0;i--) { //꼭대기 쪽부터 선형 검색
			if(stk[i]==x) return i; //x가 있을 경우에 해당 인덱스를 반환
		}
		return -1; //x가 없을 경우에는 -1을 반환
	}

	//스택의 전체 용량을 반환
	public int getCapacity() {
		return capacity;
	}

	//스택에 쌓여있는 데이터 갯수를 반환
	public int size() {
		return top;
	}

	//스택이 비어있는가?(비어있으면 t/차 있으면 f)
	public boolean isEmpty() {
		if(top==0) return true;
		return false;
	}

	//스택이 가득 찼는가?(가득 차면 t/아니면 f)
	public boolean isFull() {
		if(top==capacity) return true;
		return false;
	}
	
	//스택 안의 모든 데이터를 바닥 → 정상 순서로 표시
	public void dump() throws EmptyIntStackException{
		if(top==0) System.out.println("스택이 비었습니다.");
		else {
			for(int i =0;i<stk.length;i++) {
				System.out.println(stk[i]+" ");
			}
			System.out.println();
		}
	}
}

public class Train_ex04_01 {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		IntStack3 s = new IntStack3(4); // 최대 4 개를 푸시할 수 있는 스택

		while (true) {
			System.out.println(); // 메뉴 구분을 위한 빈 행 추가
			System.out.printf("현재 데이터 개수: %d / %d\n", s.size(), s.getCapacity());
			System.out.print("(1)push　(2)pop　(3)peek　(4)dump　(0)종료: ");

			int menu = stdIn.nextInt();
			if (menu == 0)
				break;

			int x;
			switch (menu) {
			case 1: // 푸시
				System.out.print("데이터: ");
				x = stdIn.nextInt();
				try {
					s.push(x);
				} catch (IntStack3.OverflowIntStackException e) {
					System.out.println("스택이 가득 찼습니다." + e.getMessage());
					e.printStackTrace();
				}
				break;

			case 2: // 팝
				try {
					x = s.pop();
					System.out.println("팝한 데이터는 " + x + "입니다.");
				} catch (IntStack3.EmptyIntStackException e) {
					System.out.println("스택이 비어있습니다." + e.getMessage());
					e.printStackTrace();
				}
				break;

			case 3: // 피크
				try {
					x = s.peek();
					System.out.println("피크한 데이터는 " + x + "입니다.");
				} catch (IntStack3.EmptyIntStackException e) {
					System.out.println("스택이 비어있습니다." + e.getMessage());
					e.printStackTrace();
				}
				break;

			case 4: // 덤프
				try {
					s.dump();
				} catch (IntStack3.EmptyIntStackException e) {
					System.out.println("스택이 비어있습니다." + e.getMessage());
					e.printStackTrace();
				}
				s.dump();
				break;
			}
		}
	}
}