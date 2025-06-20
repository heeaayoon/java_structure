package ch06;
//heap의 full, empty에 대한 예외처리 구현 권장 
import java.util.Random;
import java.util.Scanner;

interface MaxHeap2 { // 인터페이스는 구현클래스에서 반드시 메소드 오버라이드가 필요함
	public void Insert(int x);
	public int DeleteMax();
}

class Heap2 implements MaxHeap {
	final int heapSize = 100;
	private int[] heap;
	private int n; // MaxHeap의 현재 입력된 element 개수
	private int maxSize; // Maximum allowable size of MaxHeap
	
	//생성자 : 필드를 초기화할 때 사용됨
	public Heap2(int sz) {
		this.maxSize = sz;
		heap = new int [maxSize+1]; //인덱스 1부터 사용하도록 수정함
		this.n = 0;		
	}
	
	//heap 배열을 출력하는 메소드 : 배열 인덱스와 heap[]의 값을 출력
	public void display() {
		int i;
	
	}
	
	@Override
	//leaf 노드에 값을 insert 
	//삽입후 complete binary tree가 유지되어야 한다.
	public void Insert(int x) {
		//1. isFull?
		if(isFull()) {
			HeapFull();
			return; //공간 없으면 종료
		}
		//2. 맨 마지막 공간 생성
		n++;
		int i = n;
		//3. 재배치 -> 부모보다 큰지 작은지 물어보고, 부모보다 크면 자리 바꾸기(자신만 바꿈, 나머지는 그냥 둠)
		//i 값을 수정 : 부모보다 클 동안은 부모랑 자식을 교체해야함
		while(i>1 && heap[i/2]<x) {
			heap[i]=heap[i/2];
			i = i/2; //까먹지 말기 //i 값을 수정해야해
		}
		//결론적으로는 적당한 위치에 x를 넣을 수 있음
		heap[i] = x;
	}
	
	@Override
	//root 노드(가장 큰 값)를 삭제하고 리턴한다. 
	public int DeleteMax() {
		int x = heap[1]; //최종 반환값
		int lastE = heap[n]; //배열의 마지막값을 루트로 옮기기 위해 필요함
		//isEmpty?
		if (n == 0) {
			HeapEmpty();
			int elm = -1;
			return elm;
		}
		
		//1.heap 공간 줄이기
		n--; //heap 길이 -1
		
		//2. i,j 루트와 왼쪽
		int i = 1; // 루트 인덱스 : 1
		int j = 2; // 왼쪽 자식인덱스 : 2
		
	
		//3. 재배치 시작 while( //가장 큰 값을 위로 옮기기
		while(j<=n) {
			if(j<n && heap[j]<heap[j+1]) { //3-1 오른쪽 자식이 존재하면서, 왼쪽 자식보다 큰가?
				j++; //크면 오른쪽 값이 됨
			}
			if(lastE>=heap[j]){ //3-2 마지막 원소가 자식보다 크거나 같은가?
				break;
			}
			//3-3 자식을 위로 이동 
			heap[i] = heap[j];
			i=j;
			j=2*1;
		}
		//)
		//4. 적절한 위치에 특정 값을 배치
		heap[i] = lastE;
		
		//반환되는 x는 최댓값
		return x;
	}

	//n의 크기를 반환하는 메소드 
	public int size() {
		return n;
	}
	
	public int peek() {
		if(isEmpty()) {
			System.out.println("아무것도 없음");
		}
		return heap[1]; //인덱스 1부터 시작
	}

	//Exception 터뜨리기 위해
	public boolean isEmpty() {
		return n==0;
	}
	
	public boolean isFull() {
		return n==maxSize;
	}
	
	//사용자 출력을 위해 
	private void HeapEmpty() {
		System.out.println("Heap Empty");
	}

	private void HeapFull() {
		System.out.println("Heap Full");
	}
}
public class Train_ex06_17_HeapSort_sol {
	
	static void showData(int[] d) {

	 }
	
	public static void main(String[] args) {
		Random rnd = new Random();
		int select = 0;
		Scanner stdIn = new Scanner(System.in);
		Heap heap = new Heap(20);
	    final int count = 10; //난수 생성 갯수
	    int[] x = new int[count+1]; //x[0]은 사용하지 않으므로 11개 정수 배열을 생성한다 
	    int[] sorted = new int[count]; //heap을 사용하여 deleted 정수를 배열 sorted[]에 보관후 출력한다

		do {
			System.out.println("Max Tree. Select: 1 insert, 2 display, 3 sort, 4 exit => ");
			select = stdIn.nextInt();
			switch (select) {
			case 1://난수를 생성하여 배열 x에 넣는다 > heap에 insert한다.

			     showData(x);
				break;
			case 2:	//heap 트리구조를 배열 인덱스를 사용하여 출력한다.
				heap.display();
				break;
			case 3://heap에서 delete를 사용하여 삭제된 값을 배열 sorted에 넣는다 > 배열 sorted[]를 출력하면 정렬 결과를 얻는다 
	
				break;

			case 4:
				return;

			}
		} while (select < 5);

		return;
	}
}