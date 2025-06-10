package ch05;

import java.util.Scanner;
//실습 5-6 : recursive의 tail 부분을 non-recursive 코드로 수정  - p.173~174
public class Train_ex05_06 {
	 static void recur(int n) {
		//recur(n-2) 호출을 while 로 변경
		//if(n>0) {
		 while (n > 0) {
	    	 System.out.println("recur(" + n+ " - 1) 호출됨");
	         recur(n - 1);
	         System.out.println("n = " + n);
	         System.out.println("    n = " + n + " - 2으로 계산됨");
	         //recur(n-2)
	         n = n - 2;
	     }
	 }

	 public static void main(String[] args) {
	     Scanner stdIn = new Scanner(System.in);

	     System.out.print("정수를 입력하세요 : ");
	     int x = stdIn.nextInt();

	     recur(x);
	 }
	}
