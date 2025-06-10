package ch05;

import java.util.Scanner;

//실습 5-3: 피보나치 수열 
//https://oeis.org/A000045 참조
//삼항 조건 연산자를 사용한 대표적 사례로서 기억해야 함
//f(n) = f(n-1) + f(n-2), f(0)=0, f(1) =1
//f(n) = f(n-1) + f(n-2) + f(n-3), f(0)=0, f(1) =1, f(2)=2


public class Train_ex05_03_Fibonacci {
	 static int fibonacci(int n) {
//	     return (n > 1) ? fibonacci(n - 1) + fibonacci(n - 2) : n;
		 return (n>2)? fibonacci(n-1)+fibonacci(n-2)+fibonacci(n-3):n;
	 }

	 public static void main(String[] args) {
	     Scanner stdIn = new Scanner(System.in);

	     System.out.print("정수를 입력하세요 : ");
	     int x = stdIn.nextInt();

	     System.out.println("피보나치 수열의 "+ x +"번째 값은" + fibonacci(x) + "입니다.(0번째 부터 시작)");
	 }
	}
