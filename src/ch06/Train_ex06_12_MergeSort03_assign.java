package ch06;
//6장 구현과제3

// Polynomial은 순서(by 지수)가 있는 선형다항식
class Polynomial3 implements Comparable<Polynomial3>{
    double coef;           // 계수
    int    exp;            // 지수

    //기본 생성자
    Polynomial3(){}
    
    //생성자 오버로딩
    Polynomial3(double coef, int exp) {
        this.coef = coef;  
        this.exp = exp; 
    }
    
    //인터페이스 상속 -> 오버라이드로 메소드를 재정의 
    //대소 비교 결과값을 return
	@Override
	public int compareTo(Polynomial3 o) {
		return Integer.compare(o.exp, this.exp);
	}
}

public class Train_ex06_12_MergeSort03_assign {
	//merge()
	static void merge(Polynomial3[] a, int lefta, int righta, int leftb, int rightb ) { 
		Polynomial3 temp[] = new Polynomial3[30];
		int i = lefta;
		int j = leftb;
		int k = 0;
		
		//1.두 배열 비교
		while(i<=righta && j<=rightb) {
			if(a[i].compareTo(a[j])<=0) {
				temp[k++] = a[i++];
			}else temp[k++] = a[j++];
		}
		//2.남은 배열 처리
		//A그룹만 남았을 때
		while(i<=righta) {
			temp[k++] = a[i++];
		}
		//B그룹만 남았을 때
		while(j<=rightb) {
			temp[k++] = a[j++];
		}
		
		//3.임시배열을 원래 배열로 복사
		for(i=0;i<k;i++) {
			a[lefta+i] = temp[i];
		}
	}

	//mergeSort()
	static void MergeSort(Polynomial3[] a, int left, int right) {
		int mid = (left+right)/2;
		//요소가 1개가 될 때까지 반으로 쪼갬
		if (left == right) return;
		
		MergeSort(a, left, mid);
		MergeSort(a, mid+1, right);
		
		merge(a, left, mid, mid+1, right);
	}
	
	static void ShowPolynomial(String str, Polynomial3[] p, int count) {
		//count는 유효한 다항식 항의 갯수를 알려줌
		//count = -1이면 배열 전체의 길이를 사용하고,
		//count != -1이면 전달받은 count 값(실제 항의 갯수)을 사용함
		//정렬후 다항식 x = 2.5x**7  + 3.8x**5  + 3.1x**4  + 1.5x**3  + 3.3x**2  + 4.0x**1  + 2.2x**0 -> 즉,지수 기준으로 정렬
		int n = 0;
		if (count < 0)
			n = p.length;
		else
			n = count;
		
		System.out.print(str);
		for(int i=0;i<n;i++) {
			if(i>0) { // 첫번째 항이 아닐때만 + 출력
				System.out.print("+");
			} 
			//지수에 따라 출력되는 값이 다름
			if(p[i].exp ==0) { // 지수가 0이면 -> 상수항 
				System.out.printf("%.1f",p[i].coef);
			}else if(p[i].exp==1) { //지수가 1일 때 
				System.out.printf("%.1fx",p[i].coef);
			}else { //지수가 2 이상일 때
				System.out.printf("%.1fx**%d",p[i].coef, p[i].exp);
			}
		}
		System.out.println();
	}
	
	//z = x + y 
	//다항식 덧셈 결과를 z로 주고 z의 항의 수 terms을 리턴한다 
	static int AddPolynomial(Polynomial3[] x,Polynomial3[] y,Polynomial3[] z) {
		int p=0;
		int q=0;
		int terms = 0;
		
		//1. 두 배열을 비교
		while(p<x.length && q<y.length) {
			if(x[p].exp>y[q].exp) { //x의 지수가 클 때
				terms = addTerm(z, x[p], terms); //x의 항은 덧셈할 상대가 없으므로, z에 그대로 추가함
				p++;
			}else if(x[p].exp<y[q].exp) { //y의 지수가 클 때
				terms = addTerm(z, y[q], terms); //y의 항은 덧셈할 상대가 없으므로, z에 그대로 추가함
				q++;
			}else { //두 항의 지수가 동일할 때
				//덧셈
				//terms = addTerm(z, 여기엔 덧셈한 값이 들어감, terms);
				Polynomial3 sum = new Polynomial3(x[p].coef+y[q].coef, x[p].exp);
				terms = addTerm(z, sum, terms); //x의 항과, y의 항을 덧셈한 값을 z에 추가함
				p++;
				q++;
			}
		}
		
		//2. 남은 항을 추가
		while(p<x.length) {
			terms = addTerm(z, x[p], terms);
			p++;
		}
		
		while(q<y.length) {
			terms = addTerm(z, y[q], terms);
			q++;
		}
		
		//최종 항의 갯수
		return terms;
		
	}
	
//	static int addTerm(Polynomial3[]z, Polynomial3 term, int terms) {
//		//다항식 z에 새로운 항 term을 추가한다. 
//		//terms : 총 항의 갯수
//		
//		//z는 0부터 차례대로 채워짐
//		z[terms] = new Polynomial3(term.coef, term.exp);
//		return ++terms;	
//	}
	
	
	//다항식 곱셈을 위해 addTerm()메소드 수정
	static int addTerm(Polynomial3[]z, Polynomial3 term, int terms) {
		//만약, 계수가 0이면 추가하지 않음
		if(term.coef==0) return terms; //항의 갯수는 변화 X
		
		//동일한 지수의 항을 찾아서 더하기
		for(int i=0;i<terms;i++) {
			if(z[i].exp==term.exp) { //만약 지수가 동일하다면 해당항끼리 더해주기
				z[i].coef += term.coef;
				return terms; //항의 갯수는 변화 X
			}
		}
		
		//동일한 지수가 없다면 새로운 항을 추가 해주기
		z[terms] = new Polynomial3(term.coef, term.exp);
		return ++terms;	
	}
	
	
	//z = x * y
	//다항식 곱셈 결과를 z로 주고, 다항식 z의 항의 수는 terms으로 리턴한다 
	static int MultiplyPolynomial(Polynomial3[] x,Polynomial3[] y,Polynomial3[] z) {
		int terms = 0; //초기 항의 갯수는 0개로 지정
		
		//두 배열을 비교할 필요가 있나? 
		
		for(int p=0;p<x.length;p++) { //p는 x의 지수
			//항이 null이거나 계수가 0일 때는 continue
			if(x[p]==null||x[p].coef==0) continue;
			for(int q=0;q<y.length;q++) {
				//항이 null이거나 계수가 0일 때는 continue
				if(y[q]==null||y[q].coef==0) continue;
				
				//새로운 항의 계수 = 곱
				double newCoef = x[p].coef * y[q].coef;
				//새로운 항의 지수 = 합
				int newExp = x[p].exp + y[q].exp;
				
				//z에 addTerm()으로 값을 넣어주기
				//terms = addTerm(z,새로운 지수와 계수로 만든 항, terms);
				Polynomial3 newTerm = new Polynomial3(newCoef, newExp);
				terms = addTerm(z, newTerm, terms);
			}
		}
		//최종 항의 갯수
		return terms;
	}
	
	static double EvaluatePolynomial(Polynomial3[]z, int zTerms, int value) {
		//zTerms는 다항식 z의 항의 수
		//value는 f(x)를 계산하기 위한 x 값
		//다항식 계산 결과는 double로 리턴한다 
		double result = 0.0;
		for(int i=0; i<zTerms;i++) { //총 항의 갯수만큼 루프를 돌면서 더함
			double termValue = z[i].coef*Math.pow(value,z[i].exp);
			
			result += termValue;
		}
		return result;
	}
	
	
	private static double pow(int value, int exp) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void main(String[] args) {
		Polynomial3[] x = {
		         new Polynomial3(1.5, 3), //(계수,지수)
		         new Polynomial3(2.5, 7),
		         new Polynomial3(3.3, 2),
		         new Polynomial3(4.0, 1),
		         new Polynomial3(2.2, 0),
		         new Polynomial3(3.1, 4),
		         new Polynomial3(3.8, 5),
		     };
		Polynomial3[] y = {
		         new Polynomial3(1.5, 1),
		         new Polynomial3(2.5, 2),
		         new Polynomial3(3.3, 3),
		         new Polynomial3(4.0, 0),
		         new Polynomial3(2.2, 4),
		         new Polynomial3(3.1, 5),
		         new Polynomial3(3.8, 6),
		     };
		int nx = x.length;


		ShowPolynomial("다항식 x = ", x, -1);
		ShowPolynomial("다항식 y = ", y, -1);
		MergeSort(x, 0, x.length - 1); // 배열 x를 정렬
		MergeSort(y, 0, y.length - 1); // 배열 y를 정렬
		ShowPolynomial("정렬후 다항식 x = ", x, -1);
		ShowPolynomial("정렬후 다항식 y = ", y, -1);
		
		System.out.println("----z 객체 생성----");
		Polynomial3[] z = new Polynomial3[20]; //새로운 객체 생성
		// 배열 초기화
		for (int i =0; i < z.length; i++)
			z[i] = new Polynomial3();
	
		//다항식 덧셈 z = x + y
		int zTerms = AddPolynomial(x,y,z);
		ShowPolynomial("덧셈후 다항식 z = ", z, zTerms);

		//다항식 곱셈 z = x * y
		zTerms = MultiplyPolynomial(x,y,z);
		//MergeSort(z, 0, zTerms); // 배열 z를 정렬하는 이유 -> 보기 좋게
		ShowPolynomial("곱셈후 다항식 z = ", z, zTerms);
		
		//다항식 값 계산
		double result = EvaluatePolynomial(z, zTerms, 10);
		System.out.println(" result = " + result );
	}
}
