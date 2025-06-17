package ch06;
//책 내용과 다름
//정수배열 구현 실습
//Merge Sort : 분할과 병합
public class Train_ex06_12_MergeSort01 {
	
	//merge() : 정렬
	static void merge(int[] a, int lefta, int righta, int leftb, int rightb ) { //lefta, righta : 그룹a의 시작, 끝 인덱스 //leftb, rightb : 그룹b의 시작, 끝 인덱스
		int temp[] = new int[30]; //병합 결과를 임시로 저장할 temp 배열
		int ix = 0; //temp 배열의 인덱스 포인터
		int p = lefta; //그룹 a의 포인터
		int	q = leftb; //그룹 b의 포인터
		while (p <= righta && q <= rightb) { //그룹 a와 그룹 b에 요소가 모두 남아있을 때 -> 작은 것부터 비교하면서 넣기
			if(a[p]<a[q]) temp[ix++] = a[p++]; 
			else temp[ix++] = a[q++];
		}
		while (p > righta && q <= rightb) { //그룹 b에만 요소가 남아 있을 때 -> b의 요소만 차례대로 집어넣기
			temp[ix++]=a[q++];
		}
		while (p <= righta && q > rightb) { //그룹 a에만 요소가 남아 있을 때 -> a의 요소만 차례대로 집어넣기
			temp[ix++]=a[p++];
		}
		
		//임시배열 temp를 원래 배열인 a에 옮기기 -> 항상 원본 배열의 0번째 인덱스부터 옮겨지는 것은 아님!! 정렬시작 위치 lefta 부터 옮겨짐
		for (int j = 0; j < ix; j++) {
			a[lefta+j]=temp[j];
		}
	}

	//mergeSort() : 분할 
	static void MergeSort(int[] a, int left, int right) { //left : 배열의 시작 인덱스, right : 배열의 끝 인덱스
		//더 이상 쪼갤 수 없는가? 
		//좌우 인덱스가 동일할때 요소는 1개이므로 더 이상 쪼갤 수 없음
		//비이상적으로 좌>우 가 발생할 수 있으므로 -> 이 경우에도 return
		if (left >= right) return;
		
		int mid = (left+right)/2;
		MergeSort(a, left, mid); //왼쪽 절반을 다시 반으로 분할
		MergeSort(a, mid+1, right); //오른쪽 절반을 다시 반으로 분할
		merge(a, left, mid, mid+1, right); //양쪽을 따로 정렬한 것을 합침 -> merge()함수 호출
		return;
	}

	public static void main(String[] args) {
		int nx = 20;
		int[] x = new int[nx];
		for (int ix = 0; ix < 20; ix++) {
			double d = Math.random();
			x[ix] = (int) (d * 50);
		}
		for (int i = 0; i < nx; i++)
			System.out.print(x[i]+" ");
		
		System.out.println();
		MergeSort(x, 0, nx - 1); // 배열 x를 퀵정렬

		System.out.println("오름차순으로 정렬했습니다.");
		for (int i = 0; i < nx; i++)
			System.out.print(x[i]+" ");
	}
}
