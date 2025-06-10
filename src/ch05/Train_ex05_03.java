package ch05;

import java.util.Scanner;

//실습 5-3: 피보나치 수열 
//삼항 조건 연산자를 사용한 대표적 사례로서 기억해야 함
//f(n) = f(n-1) + f(n-2)
//f(n) = f(n-1) + f(n-2) + f(n-3)를 구현하는 실습: 현재 코드를 수정하여 완료 

public class Train_ex05_03 {
	 static int fibonacci(int n) {
//	     return (n > 1) ? fibonacci(n - 1) + fibonacci(n - 2) : 1;
		 return (n>2)? fibonacci(n-1)+fibonacci(n-2)+fibonacci(n-3):1;
	 }

	 public static void main(String[] args) {
	     Scanner stdIn = new Scanner(System.in);

	     System.out.print("정수를 입력하세요 : ");
	     int x = stdIn.nextInt();

	     System.out.println(x + "의 피보나치 수열은 " + fibonacci(x) + "입니다.");
	 }
	}
