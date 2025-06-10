package ch05;
//알고리즘 코딩 훈련에 좋은 소재 : 최대공약수와 최소공배수
//최대 공약수 GCD(a,b)는 유클리드 호제법으로 구한다 //Greatest Common Divisor
//GCD(a,b) = GCD(b,r) r = a % b
//GCD(b, r) = 0 이 될 때 나누는 수 r이 최대 공약수

//최소공배수 LCM(a,b) = a x b /GCD(a,b) //Least Common Multiple
import java.util.Scanner;
public class Train_ex05_04_GCD {
	   //--- 정수 x, y의 최대공약수를 구하여 반환 ---//
    static int gcd(int x, int y) {
        System.out.println("x = " + x + ", y = " + y);
        
        // 비재귀적으로 유클리드 알고리즘 구현
        while (y != 0) {
            int temp = y;
            y = x % y; // 나머지 계산
            x = temp;  // x를 이전 y로 업데이트
        }
        
        return Math.abs(x); // 결과는 절댓값을 취함
    }

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);

        System.out.println("두 정수의 최대공약수를 구합니다.");

        System.out.print("정수를 입력하세요 : ");  
        int x = stdIn.nextInt();
        System.out.print("정수를 입력하세요 : ");  
        int y = stdIn.nextInt();

        System.out.println("최대공약수는 " + gcd(x, y) + "입니다.");
    }
}
