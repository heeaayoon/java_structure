package ch02;
//교재 69~74 기수 변환하기 > 실습 2-6 코드 학습
//N 진법을 10진수로 변경후 M진법으로 변경
//String: immutable(변경불가능) 객체, concat(), substring(), charAt(), equals(), compareTo() 등 문자열 조작과 비교를 위한 메서드
//String s = "hello"; s = s + "world";
//StringBuilder: mutable - 문자열이 자주 변경시 사용, append, insert, delete, reverse 함수 사용

public class Train_ex02_12_1 {

	// n진수를 10진수로 변환하는 함수
	public static int toDecimal(String number, int baseN) {
		/*
		 * charAt()를 사용하여 N 진법(baseN) 스트링을 10진수 바꾼다 
		 * 0<= ch <= 9 : num = ch - '0' 
		 * ch > 9 : num = ch - 'A' + 10 로 계산
		 */
		int decimal = 0;
		int length = number.length();

		for (int i = 0; i < length; i++) {
			char digitChar = number.charAt(i);
			int digitValue;

			// 0-9는 숫자로 처리, A-Z는 문자로 처리
			if (digitChar >= '0' && digitChar <= '9') {
				digitValue = digitChar - '0';
			} else {
				digitValue = digitChar - 'A' + 10;
			}

			// 자리수에 해당하는 값 계산
			decimal = decimal * baseN + digitValue;
		}

		return decimal;
	}

	// 10진수를 m진수로 변환하는 함수
	public static String fromDecimal(int decimal, int baseM) {
		/*
		 * 10진수를 M 진법으로 바꾸려면 decimal % baseM은 나눈 나머지를 스트링으로 표현 decimal / baseM은 몫이므로 반복
		 * 실행 M진법 스트링을 StringBuilder 객체로 append로 저장 reverse()를 사용하여 역순으로 반환
		 */
		if (decimal == 0) {
			return "0";
		}

		StringBuilder baseMNumber = new StringBuilder(); // StringBuilder는 객체내에서 append()로 수정 가능

		while (decimal > 0) {
			int remainder = decimal % baseM;

			// 나머지를 문자로 변환 (0-9는 그대로, 10 이상은 A-Z로 변환)
			if (remainder < 10) {
				baseMNumber.append((char) (remainder + '0'));
			} else {
				baseMNumber.append((char) (remainder - 10 + 'A'));
			}

			decimal /= baseM;
		}

		// 결과를 역순으로 반환
		return baseMNumber.reverse().toString();
	}

	// n진수를 m진수로 변환하는 함수
	public static String convertBase(String number, int baseN, int baseM) {
		// n진수를 10진수로 변환
		int decimal = toDecimal(number, baseN);
		// 10진수를 m진수로 변환
		return fromDecimal(decimal, baseM);
	}

	public static void main(String[] args) {
		String number = "347"; // 10진수 예시
		int baseN = 10; // 입력 진법 (n진법)
		int baseM = 16; // 출력 진법 (m진법)

		// n진수 -> m진수 변환
		String result = convertBase(number, baseN, baseM);
		System.out.println(baseN + "진법 " + number + "-> " + baseM + "진법 숫자: " + result);

		// 8진수 -> 2진수 변환 예시
		baseM = 2;
		result = convertBase(number, baseN, baseM);
		System.out.println(baseN + "진법 " + number + "-> " + baseM + "진법 숫자: " + result);
	}
}
