package ch04;
/* 스택으로 풀기

문제 설명: 
괄호로 이루어진 문자열이 주어졌을 때, 각 괄호가 제대로 짝을 이루고 있는지 확인하는 프로그램
괄호 <>, (), {}, []- 여는 괄호가 있을 때, 반드시 짝이 맞는 닫는 괄호가 나와야 하고, 괄호는 올바르게 중첩되어야 한다.

조건:
  1. 여는 괄호는 반드시 닫는 괄호와 짝을 이뤄야 한다.
  2. 괄호들은 올바르게 중첩되어야 한다.
  3. 괄호 외의 문자는 무시한다.

입력 형식:
  한 줄에 괄호 문자열이 주어지고, 문자열은 괄호 외에도 다른 문자를 포함.

출력 형식:
  괄호가 유효하면 "Valid"를, 유효하지 않으면 "Invalid"를 출력.
*/
import java.util.*;

class CStack2{
	private Character[] stk; // 스택용 캐릭터 배열
	private int capacity; // 스택의 전체 크기
	private int top; // 스택 꼭대기
	
	public CStack2(int maxlen) {
		capacity = maxlen;
		stk = new Character[capacity];
		top = 0;
	}
	
	public Character push(Character x){
		return stk[top++]=x;
	}
	
	public Character pop(Character x){
		top--;
		return x;
	}
	
	public boolean isEmpty(){
		if(top==0) return true;
		return false;
	}
	
	public Character isTop(){
		return stk[top];
	}

	@Override
	public String toString() {
		return Arrays.toString(stk);
	}
	
	
}

public class Train_ex04_08_assign_solution {

	public static boolean isValid(String s) {
		Stack<Character> stk = new Stack<Character>();
		for(char ch : s.toCharArray()) {
			//여는 괄호가 존재하면 stack에 push
			if(ch=='('|ch=='{'|ch=='['|ch=='<') {
				stk.push(ch);
			}
			//닫는 괄호를 만나면 stack에서 pop  //HashMap을 이용해서 이 부분을 키로 잡을 수도 있음
			if(ch==')'|ch=='}'|ch==']'|ch=='>') {
				//isEmpty로 비었는지 확인 
				if(!stk.isEmpty()) { //-> false면 pop
					//짝이 맞는지 비교 ex) <> -> false면 
					if()
				}
			}
		}
		//완전히 짝을 맞춰서 스택이 비면 true를 반환
		return stk.isEmpty();
    }

    public static void main(String[] args) {
    	CStack st = new CStack(500);
    	
        String[] cases1 = {
            "(12{as[33<1q2w3e>90]kkk}4r)fg", 
            "<111{ddd[4r(1q2w3e)44]77}jj>kk" ,
            "zz{w(a+b)*[c/d]-<q-e>1+2}w*t", 
            "dd[a+b+c(y*u[abstract]go{234}2w3e)444]ttt" , 
            "a+b<c-d<e%r{123{waste[go[stop(a+b+c(?)$)@]!]*}12}33>c-d>drop" 
        };

        String[] cases2 = {
            "a-b-c{1234[3.14(hello)kkk]1>d-w",  
            "a*b*c(121<good[days)gostop>q-w]t-1",  
            "123{hello[a-w-e(w/e/r]\n)\t}qq", 
            "q*t&w{12-34[a+b]*(c/d]-e}123", 
            "12<a/b/c/d{q-t-t[a=c(78::]23;)'8}sss>x+y+w",
        };
        
        System.out.println("예제1:");
        for (String test : cases1) {
            System.out.println(test + " → " + isValid(test));
        }

        System.out.println("\n예제2:");
        for (String test : cases2) {
            System.out.println(test + " → " + isValid(test));
        }
    }
}
