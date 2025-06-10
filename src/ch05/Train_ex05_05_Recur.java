package ch05;

import java.util.Scanner;
//실습 5-5  재귀 알고리즘 -p.170~172
//재귀에 대한 이해를 돕는 순수 재귀 메서드
//stack frame : https://www.tcpschool.com/c/c_memory_stackframe

public class Train_ex05_05_Recur {
	 static void recur(int n) {
	     if (n > 0) {
	    	 System.out.println("recur(" + n + " - 1) 호출됨");
	         recur(n - 1);
	         System.out.println("n = " + n);
	         System.out.println("recur(" + n + " - 2) 호출됨");
	         recur(n - 2);
	     }
	 }

	 public static void main(String[] args) {
	     Scanner stdIn = new Scanner(System.in);
	     System.out.print("정수를 입력하세요 : ");
	     int x = stdIn.nextInt();

	     recur(x);
	 }
	}
