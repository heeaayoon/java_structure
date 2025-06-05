package ch04;
/*
 * 실습 4_2번 - 실습 4_1의 배열 대신 ArrayList을 사용한 구현하기 
 * 객체스택과 큐에 대한 구현도 정수 스택의 예외처리 방식을 반복 적용함 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class IntStack4 {
	private List<Integer> stk; // 스택용 리스트
	private int capacity; // 스택의 전체 크기
	private int top; // 스택의 꼭대기

	//실행시 예외: 스택이 비어있음
	public class EmptyIntStackException extends RuntimeException {
		public EmptyIntStackException(String s) {
			super(s);
		}
	}
	//실행시 예외: 스택이 가득 참
	public class OverflowIntStackException extends RuntimeException {
		public OverflowIntStackException(String s) {
			super(s);
		}
	}

	//생성자(constructor)
	public IntStack4(int maxlen) {
		top = 0;
		capacity = maxlen;
		try {
			stk = new ArrayList<Integer>(capacity);
		} catch (OutOfMemoryError e) { // 생성할 수 없음
			capacity = 0;
		}
	}

	//스택에 x를 푸시
	public int push(int x) throws OverflowIntStackException {
		if (isFull()) // 스택이 가득 참
			throw new OverflowIntStackException("push: stack overflow");
		stk.add(x);
		top++;
		return stk.get(top-1);
	}

	//스택에서 데이터를 팝(정상에 있는 데이터를 꺼냄)
	public int pop() throws EmptyIntStackException {
		if (isEmpty()) // 스택이 빔
			throw new EmptyIntStackException("pop: stack empty");
		return stk.remove(--top);
	}

	//스택에서 데이터를 피크(peek, 정상에 있는 데이터를 들여다봄)
	public int peek() throws EmptyIntStackException {
		if (isEmpty()) // 스택이 빔
			throw new EmptyIntStackException("peek: stack empty");
		return stk.get(top-1);
	}

	//스택을 비움 -> pop()으로 구현하지 않고 대신에 while 문으로 remove()를 반복 실행
	public void clear() throws EmptyIntStackException {
		if (isEmpty()) // 스택이 빔
			throw new EmptyIntStackException("clear: stack already empty");
//		while(!stk.isEmpty()) 
//			{
//				stk.remove(top-1);
//				top--;
//			}
		while(!stk.isEmpty()) 	stk.remove(--top); //위의 while문과 동일
		}

	//스택에서 x를 찾아 인덱스(없으면 –1)를 반환
	public int indexOf(int x) {
		if(stk.contains(x)) return stk.indexOf(x);
		return -1;
	}

	//스택의 전체 크기를 반환
	public int getCapacity() {
		return capacity;
	}

	//스택에 쌓여있는 데이터 갯수를 반환
	public int size() {
		return stk.size();
	}

	//스택이 비어있는가?
	public boolean isEmpty() {
		return stk.isEmpty();
	}

	//스택이 가득 찼는가?
	public boolean isFull() {
		if(top==capacity) return true;
		return false;
	}
	
	//스택 안의 모든 데이터를 바닥 → 정상 순서로 표시
	public void dump() throws EmptyIntStackException{
		if (isEmpty()) {
			System.out.println("스택이 비어있습니다.");
			throw new EmptyIntStackException("peek: stack empty");
		}
		else {
			for(int i =0;i<=stk.size()-1;i++) {
				System.out.println(stk.get(i)+" ");
			}
			System.out.println();
		}
	}
}

public class Train_ex04_02 {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		IntStack4 s = new IntStack4(4); // 최대 4 개를 푸시할 수 있는 스택
		Random rnd = new Random();
		while (true) {
			System.out.println(); // 메뉴 구분을 위한 빈 행 추가
			System.out.printf("현재 데이터 개수: %d / %d\n", s.size(), s.getCapacity());
			System.out.print("(1)push　(2)pop　(3)peek　(4)dump　(5)clear  (0)종료: ");

			int menu = stdIn.nextInt();
			if (menu == 0)
				break;

			int x;
			switch (menu) {

			case 1: // 푸시
				x = rnd.nextInt(10); //stk에 랜덤한 수를 push 하기
				System.out.print("데이터: "+x);
				try {
					s.push(x);
				} catch (IntStack4.OverflowIntStackException e) {
					System.out.println("스택이 가득 찼습니다." + e.getMessage());
					e.printStackTrace();
				}
				break;

			case 2: // 팝
				try {
					x = s.pop();
					System.out.println("팝한 데이터는 " + x + "입니다.");
				} catch (IntStack4.EmptyIntStackException e) {
					System.out.println("스택이 비어있습니다." + e.getMessage());
					e.printStackTrace();
				}
				break;

			case 3: // 피크
				try {
					x = s.peek();
					System.out.println("피크한 데이터는 " + x + "입니다.");
				} catch (IntStack4.EmptyIntStackException e) {
					System.out.println("스택이 비어있습니다." + e.getMessage());
					e.printStackTrace();
				}
				break;

			case 4: // 덤프
				try {
					s.dump();
				} catch (IntStack4.EmptyIntStackException e) {
					System.out.println("스택이 비어있습니다." + e.getMessage());
					e.printStackTrace();
				}
				break;
			case 5: //clear
				try {
					s.clear();
				} catch (IntStack4.EmptyIntStackException e) {
					System.out.println("스택이 비어있습니다." + e.getMessage());
					e.printStackTrace();
				}
				break;
				
			}
		}
	}
}