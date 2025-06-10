package ch05;
/*
generate all the permuations of arr[0],..., arr[m] where m은 사이즈
    if (k ==m) {//permutation 출력
    	for (char c : arr) {
    		sysout
    	}
    }
    else {
    	for (int i = k; i <= m; i++) {
    		swap(arr, k,i);
    		permutate(arr, k+1);
    		swap(arr, k,i);
    	}
    }
 */
public class Train_ex05_07_permutate {

	    // 주어진 문자 배열의 순열을 생성하는 함수
	    public static void permutate(char[] arr, int index) {
	        
	    }

	    // 두 요소를 교환하는 함수
	    public static void swap(char[] arr, int i, int j) {

	    }

	    public static void main(String[] args) {
	        // 문자열을 문자 배열로 변환
	        String l = "abcde";
	        char[] arr = l.toCharArray();

	        // 순열 생성 및 출력
	        permutate(arr, 0);
	    }
	}