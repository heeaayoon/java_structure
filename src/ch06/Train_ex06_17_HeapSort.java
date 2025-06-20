package ch06;
//heap의 full, empty에 대한 예외처리 구현 권장 
import java.util.Random;
import java.util.Scanner;

interface MaxHeap { // 인터페이스는 구현클래스에서 반드시 메소드 오버라이드가 필요함
	public void Insert(int x);
	public int DeleteMax();
}

class Heap implements MaxHeap {
	final int heapSize = 100;
	private int[] heap;
	private int n; // MaxHeap의 현재 입력된 element 개수
	private int maxSize; // Maximum allowable size of MaxHeap
	
	//생성자 : 필드를 초기화할 때 사용됨
	public Heap(int sz) {
		this.maxSize = sz;
		heap = new int [maxSize+1]; //인덱스 1부터 사용하도록 수정함
		this.n = 0;		
	}
	
	//heap 배열을 출력하는 메소드 : 배열 인덱스와 heap[]의 값을 출력
	public void display() {
		for(int i =1; i<=n;i++) {
			System.out.println(heap[i]+" ");
		}
	
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
//		int x;
//		int i, j;
		//isEmpty?
		if (n == 0) {
			HeapEmpty();
			int elm = 0;
			return elm;
		}
		
		//1.루트를 삭제하면서 공간 줄이기
		int max = heap[1]; //루트
		
		heap[1]=heap[n]; //heap의 맨 마지막 요소를 루트로 가져옴
		n--; //(heap 길이-1)
		
		int current = 1; //현재 인덱스 = 1
		
		while(current<=n/2) { //current가 리프노드가 아닐 동안은 계속 돔 -> 위치변환 검사를 위해서
			int left = 2*current;//왼쪽 자식 
			int right = 2*current+1;//오른쪽 자식 
			
			int largest = current; //일단 셋 중에 current가 가장 크다고 가정하기
			
			//largest와 왼쪽 자식 비교 -> 왼쪽 자식이 더 크면 바꾸기
			if(left<=n && heap[largest]<heap[left]) {
				largest = left;
			}
		
			//largest와 오른쪽 자식 비교 -> 오른쪽 자식이 더 크면 바꾸기
			if(right<=n && heap[largest]<heap[right]) {
				largest = right;
			}
			
			//만약 largest 가 바꼈다면
			if(largest != current) {
				swap(current, largest);
				current = largest; //current 인덱스를 largest로 바꿔주기
			} else { //largest가 안 바꼈다면,
				break; //제자리에 위치한 것
			}
		}
		//반환되는 max는 최댓값
		return max;
	}

	private void swap(int current, int largest) {
		int tmp;
		tmp = heap[current];
		heap[current] = heap[largest];
		heap[largest] = tmp;
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
public class Train_ex06_17_HeapSort {
	
	static void showData(int[] d, int count) {
		for(int i = 1; i<=count;i++) {
			System.out.println(d[i]+" ");
		}
	 }
	
	public static void main(String[] args) {
		Random rnd = new Random();
		int select = 0;
		Scanner stdIn = new Scanner(System.in);
		Heap heap = new Heap(20);
	    final int count = 10; //난수 생성 갯수
	    int[] x = new int[count+1]; //x[0]은 사용하지 않으므로 11개 정수 배열을 생성한다 
	    int[] sorted = new int[count+1]; //heap을 사용하여 deleted 정수를 배열 sorted[]에 보관후 출력한다

		do {
			System.out.println("Max Tree. Select: 1 insert, 2 display, 3 sort, 4 exit => ");
			select = stdIn.nextInt();
			switch (select) {
			case 1://난수를 10개 생성하여 배열 x에 넣는다 > heap에 insert한다.
				
				for(int i =1;i<=count;i++) {
					x[i] = rnd.nextInt(100);
					heap.Insert(x[i]);
				}
			     showData(x, count);
				break;
			case 2:	//heap 트리구조를 배열 인덱스를 사용하여 출력한다.
				heap.display();
				break;
			case 3: //heap에서 delete를 사용하여 삭제된 값을 배열 sorted에 넣는다 > 배열 sorted[]를 출력하면 정렬 결과를 얻는다 
				//힙 안에 든 요소의 수(고정)
				int heapSize = heap.size();
				
				if(heapSize==0) break;
				
				for (int i = 1; i <= heapSize; i++) {
					sorted[i] = heap.DeleteMax(); 
				}
				showData(sorted, heapSize);
				
				break;
			case 4:
				return;
			}
		} while (select < 5);
		return;
	}
}


