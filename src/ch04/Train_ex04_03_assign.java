package ch04;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

//실습 4_3번 - point(x,y) 객체를 스택에 저장
//좌표를 스택에 구현 소스 코드 - 5장에서 활용
//스택을 ArrayList로 구현

class Point2 {
	private int ix; //ix = 행
	private int iy; //iy = 열

	//생성자
	public Point2(int x, int y) {
		ix = x;
		iy = y;
	}

	@Override
	public String toString() {
		return "x : "+ix+", y : "+iy;
	}

//	@Override
//	public boolean equals(Object p) {
//		return false;
//	}
}

class ObjectStack{
    private List<Point2> data; //스택용 리스트
	private int capacity; // 스택의 전체 크기
	private int top; // 스택 꼭대기

	// 실행시 예외: 스택이 비어있음
	// generic class는 Throwable을 상속받을 수 없다 - 지원하지 않는다
	public class EmptyGenericStackException extends Exception {
		public EmptyGenericStackException(String s) {
			super(s);
		}
	}
	
	//실행시 예외: 스택이 가득 참
	public class OverflowGenericStackException extends RuntimeException {
		public OverflowGenericStackException(String s) {
			super(s);
		}
	}
	
	//생성자(constructor) 
	public ObjectStack(int capacity) {
		this.capacity = capacity;
		top=0;
		data = new ArrayList<Point2>(capacity);
	}

	//스택에 x를 푸시
	public boolean push(Point2 x) throws OverflowGenericStackException {
		if(isFull())
			throw new OverflowGenericStackException("push : stack overflow");
		top++;
		return data.add(x);
	}

	//스택에서 데이터를 팝(정상에 있는 데이터를 꺼냄)
	public Point2 pop() throws EmptyGenericStackException  {
		if(isEmpty())
			throw new EmptyGenericStackException("pop: stack empty");
		return data.remove(--top);
	}

	//스택에서 데이터를 피크(peek, 정상에 있는 데이터를 들여다봄)
	public Point2 peek() throws EmptyGenericStackException  {
		if (isEmpty()) // 스택이 빔
			throw new EmptyGenericStackException("peek: stack empty");
		return data.get(top-1);
	}

	//스택을 비움
	public void clear() {
		top = 0;
	}

	//스택에서 x를 찾아 인덱스(없으면 –1)를 반환
	public int indexOf(Point2 x) {
		if(data.contains(x)) return data.indexOf(x);
		return -1;
	}

	//스택의 크기를 반환 
	public int getCapacity() {
		return capacity;
	}

	//스택에 쌓여있는 데이터 갯수를 반환
	public int size() {
		return top;
	}

	//스택이 비어있는가?
	public boolean isEmpty() {
		return top <= 0;
	}

	//스택이 가득 찼는가?
	public boolean isFull() {
		return top >= capacity;
	}

	//스택 안의 모든 데이터를 바닥 → 꼭대기 순서로 출력
	public void dump() {
		if (isEmpty()) {
			System.out.println("스택이 비어있습니다.");
		}
		else {
			for(int i =0;i<=data.size()-1;i++) {
				System.out.println(data.get(i)+" ");
			}
			System.out.println();
		}
	}
}
public class Train_ex04_03_assign {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		ObjectStack s = new ObjectStack(4); // 최대 4 개를 push할 수 있는 stack
		Random random = new Random();
		int rndx = 0, rndy = 0;
		Point2 p = null;
		while (true) {
			System.out.println(); // 메뉴 구분을 위한 빈 행 추가
			System.out.printf("현재 데이터 개수: %d / %d\n", s.size(), s.getCapacity());
			System.out.print("(1)push　(2)pop　(3)peek　(4)dump　(0)종료: ");

			int menu = stdIn.nextInt();
			if (menu == 0)
				break;

			switch (menu) {
			case 1: // 푸시
				rndx = random.nextInt(20);
				rndy = random.nextInt(20);
				System.out.print("데이터 x : "+rndx+", 데이터 y : "+rndy);
				p = new Point2(rndx,rndy);
				try {
					s.push(p);
				} catch(ObjectStack.OverflowGenericStackException e) {
					System.out.println("stack이 가득찼습니다."+e.getMessage());
				}
				break;

			case 2: // 팝
				try {
					p = s.pop();
					System.out.println("pop한 데이터는 " + p + "입니다.");
				} catch(ObjectStack.EmptyGenericStackException e) {
					System.out.println("stack이 비어있습니다."+e.getMessage());
				}
				break;

			case 3: // 피크
				try {
					p = s.peek();
					System.out.println("peek한 데이터는 " + p + "입니다.");
				} catch (ObjectStack.EmptyGenericStackException e) {
					System.out.println("stack이 비어있습니다."+e.getMessage());
				}
				break;

			case 4: // 덤프
				s.dump();
				break;
			}
		}
	}
}