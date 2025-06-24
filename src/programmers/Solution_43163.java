package programmers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// BFS 문제 : 가장 짧은, 최단 ~을 구해라
// 큐로 풀기
class QueueSolution2 {
	private static class State {
		String word;
		int depth;
		Set<String> visited;

		public State(String word, int depth, Set<String> visited) {
			super();
			this.word = word;
			this.depth = depth;
			this.visited = visited;
		}
	}

	public int solution(String begin, String target, String[] words) {
		// 가장 선호되는 케이스 : 답이 없을 때(0을 리턴할 떄)
		if (!Arrays.asList(words).contains(target)) { // words에 target이 없으면 return 0
			return 0;
		}

		// 가장 힘든 케이스
		// word, depth, visited -> 최단경로를 위해 이미 방문한 곳은 표시를 해둬야함
		Queue<State> que = new LinkedList<>();
		Set<String> visited = new HashSet<>();

		// 초기화
		que.offer(new State(begin, 0, visited)); // 초기값 설정
		visited.add(begin); // 초기 visited 설정

		// 이제 empty 한지 while문을 돌면서 검사
		while (!que.isEmpty()) {
			State current = que.poll();

			// target과 동일한지?
			if (current.word.equals(target)) {
				return current.depth;
			}

			// 동일하지 않으면?
			for (String word : words) {
				if (!visited.contains(word) && canTransform(current.word, word)) { // 방문하지 않았던 곳에만 && 한글자만 다른 것만
					visited.add(word); // 방문함
					que.offer(new State(word, current.depth + 1, new HashSet<>()));
					// word부터 새로 검사를 시작함
					// depth는 하나씩 증가
					// new HashSet<>()은 그냥 빈 객체임(타입 맞추기용)
				}
			}
		}
		return 0;
	}

	// "hit" -> "hot" -> "dot" -> "dog" -> "cog"
	// current.word->word로 갈 수 있는가?(한글자만 다른가?)
	private boolean canTransform(String word1, String word2) {
		int diffCount = 0;
		for (int i = 0; i < word1.length(); i++) {
			if (word1.charAt(i) != word2.charAt(i)) {
				diffCount++;
				if (diffCount > 1)
					return false; // 다른 글자가 1개 초과면
			}
		}
		return diffCount == 1;
	}
}

public class Solution_43163 {

	public static void main(String[] args) {
		QueueSolution2 queSol = new QueueSolution2();
		String[][] testWords = { { "hot", "dot", "dog", "lot", "log", "cog" }, { "hot", "dot", "dog", "lot", "log" } };
		String[] testBegin = { "hit", "hit" };
		String[] testTarget = { "cog", "cog" };
		int[] expectedResults = { 4, 0 };
		for (int i = 0; i < testWords.length; i++) {
			String[] words = testWords[i];
			String begin = testBegin[i];
			String target = testTarget[i];
			int expectResult = expectedResults[i];
			int result = queSol.solution(begin, target, words);
			if (expectResult == result)
				System.out.println("정답");

		}

	}

}
