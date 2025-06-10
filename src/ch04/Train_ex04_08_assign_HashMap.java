package ch04;
//hashMap 추가
import java.util.*;

public class Train_ex04_08_assign_HashMap {

	public static boolean isValid(String s) {
		Stack<Character> stk = new Stack<Character>(); //자바의 내장 stack 사용
		//HashMap 사용
		//문자열의 각 문자를 순회하면서
		for(char ch : s.toCharArray()) {
			if(ch=='('||ch=='{'||ch=='['||ch=='<') { //여는 괄호를 만나면 stack에 push
				stk.push(ch);
			} else if(ch==')'||ch=='}'||ch==']'||ch=='>') { //닫는 괄호를 만나면 stack에서 두가지 검사를 진행  //HashMap을 이용해서 이 부분을 키로 잡을 수도 있음
				if(stk.isEmpty()) return false; //1. stack이 비었는지 isEmpty로 확인 -> 비면 유효하지 X
				char last = stk.pop(); //2. 가장 최근에 push 된 여는 괄호를 꺼내
				if((ch==')'&&last!='(')
						|| (ch=='}'&&last!='{')
						|| (ch==']'&&last!='[')
						|| (ch=='>'&&last!='<')) return false; //괄호의 짝이 맞지 않으면 -> 유효하지 X
			}
		}
		//순회 종료 후, 스택이 비면 -> 유효하게 처리된 것
		return stk.isEmpty();
    }

    public static void main(String[] args) {
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
            System.out.println(test + " → " + (isValid(test)?"Valid":"Invalid"));
        }

        System.out.println("\n예제2:");
        for (String test : cases2) {
            System.out.println(test + " → " + (isValid(test)?"Valid":"Invalid"));
        }
    }
}
