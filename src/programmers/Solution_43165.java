package programmers;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//-1+1+1+1+1 = 3
//+1-1+1+1+1 = 3
//+1+1-1+1+1 = 3
//+1+1+1-1+1 = 3
//+1+1+1+1-1 = 3

//입출력 예
//numbers			target	return
//[1, 1, 1, 1, 1]	 3		 5
//[4, 1, 2, 1]	 4		 2

//DFS문제 - 스택
class StackSolution {
	private static class State {
		int index;
		int currentSum;

		State(int index, int currentSum) {
			this.index = index;
			this.currentSum = currentSum;
		}
	}

	public int solution(int[] numbers, int target) {
		int count = 0;
		Stack<State> stk = new Stack<>();
		stk.push(new State(0, 0));
		while (!stk.empty()) { // 스택이 비어야 함
			State current = stk.pop();
			if (current.index == numbers.length) { // 끝까지 도달했는지?
				if (current.currentSum == target) { // target과 동일한 결과인지?
					count++;
				}
				continue;
			}
			stk.push(new State(current.index + 1, // 덧셈
					current.currentSum + numbers[current.index]));
			stk.push(new State(current.index + 1, // 뺄셈
					current.currentSum - numbers[current.index]));
		}
		return count;
	}
}

//DFS문제 - 큐 
class QueueSolution {
	private static class State {
		int index;
		int currentSum;

		State(int index, int currentSum) {
			this.index = index;
			this.currentSum = currentSum;
		}
	}

	public int solution(int[] numbers, int target) {
		int count = 0;
		Queue<State> que = new LinkedList<>(); // 인터페이스 Queue는 new로 객체 생성 불가 -> LinkedList class를 대신 사용
		que.offer(new State(0, 0));
		while (!que.isEmpty()) { // 큐가 비어야 함
			State current = que.poll();
			if (current.index == numbers.length) { // 끝까지 도달했는지?
				if (current.currentSum == target) { // target과 동일한 결과인지?
					count++;
				}
				continue;
			}
			que.offer(new State(current.index + 1, // 덧셈
					current.currentSum + numbers[current.index]));
			que.offer(new State(current.index + 1, // 뺄셈
					current.currentSum - numbers[current.index]));
		}
		return count;
	}
}

//DFS문제 - 재귀
class RecurSolution {
	public int solution(int[] numbers, int target) {
		return dfs(numbers, target, 0, 0);
	}

	public int dfs(int[] numbers, int target, int index, int currentSum) {
		int count = 0;

		if (index == numbers.length) { // 끝까지 도달했는지?
			return currentSum == target ? 1 : 0;
		}

		// 인덱스를 +1하기
		int add = dfs(numbers, target, index + 1, currentSum + numbers[index]); // 덧셈
		int sub = dfs(numbers, target, index + 1, currentSum - numbers[index]); // 뺄셈
		return add + sub;
	}
}

public class Solution_43165 {
	public static void main(String[] args) {
//    	StackSolution stackSol = new StackSolution();
//    	QueueSolution queSol = new QueueSolution();
    	RecurSolution recurSol = new RecurSolution();
    	
    	int[][] testNumbers = {{1,1,1,1,1},{4,1,2,1}};
    	int[] testTarget = {3,4};
    	int[] expectedResults = {5,2};
    	
    	for(int i =0;i<testNumbers.length;i++) {
    		int[] numbers = testNumbers[i];
    		int target = testTarget[i];
    		int expectResult = expectedResults[i];
    		
//    		int stackResult=stackSol.solution(numbers, target); 
//    		if(expectResult==stackResult) System.out.println("정답");
    		
//    		int queueResult=queSol.solution(numbers, target); 
//    		if(expectResult==queueResult) System.out.println("정답");
    		
    		int recurResult=recurSol.solution(numbers, target); 
    		if(expectResult==recurResult) System.out.println("정답");
    		
    	}
	}
}