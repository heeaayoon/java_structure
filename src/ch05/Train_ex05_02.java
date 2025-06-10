package ch05;

import java.util.Scanner;
//실습 5-2: factorial() 함수를 간결한 코딩으로 해결
//삼항 연산자
public class Train_ex05_02 {
	 static int factorial(int n) {
		 System.out.println(n + "* factorial(" + n + "- 1)5");
	     return (n > 0) ? n * factorial(n - 1) : 1;
	 }

	 public static void main(String[] args) {
	     Scanner stdIn = new Scanner(System.in);

	     System.out.print("정수를 입력하세요 : ");
	     int x = stdIn.nextInt();

	     System.out.println(x + "의 팩토리얼은 " + factorial(x) + "입니다.");
	 }
	}
