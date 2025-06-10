package ch05;

import java.util.Scanner;
import java.util.Stack;
// 앞쪽에서 호출하는 재귀 메서드를 제거하기 위해 stack을 사용
// stack을 이용한 non-recursive 코드로 구현
// 8-Queen 문제 해결에 스택 사용

public class Train_ex05_06_NonRecursive {
		static void recur(int n) {
			//IntStack s = new IntStack(n);
			Stack<Integer> s = new Stack<Integer>();
			//stack을 사용한 non-recursive 알고리즘 구현 코드 >8-queen 문제 해결에 활용 
			while (true) {
				if (n > 0) {
					s.push(n); // n 값을 푸시
					n = n - 1;
					continue;
				}
				if (!s.isEmpty()) { // 스택이 비어 있지 않으면
					n = s.pop(); // 저장하고 있던 스택의 값을 팝
					System.out.println(n);
//					System.out.println("--"); 
					n = n - 2;
					continue;
				}
				break;
			}
		}

		public static void main(String[] args) {
			Scanner stdIn = new Scanner(System.in);

			System.out.print("정수를 입력하세요 : ");
			int x = stdIn.nextInt();

			recur(x);
		}
	}
