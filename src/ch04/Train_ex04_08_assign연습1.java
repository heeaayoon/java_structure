package ch04;
//괄호 짝 맞추기 연습
import java.util.*;

public class Train_ex04_08_assign연습1 {

	public static boolean isValid(String s) {
		Stack<Character> stk= new Stack<>();
		for(char ch : s.toCharArray()) {
			if(ch=='('||ch=='{'||ch=='['||ch=='<') stk.push(ch);
			else if(ch==')'||ch=='}'||ch==']'||ch=='>') {
				if(stk.isEmpty()) return false;
				char last = stk.pop();
				if((ch==')'&&last!='(')
						||(ch=='}'&&last!='{')
						||(ch==']'&&last!='[')
						||(ch=='>'&&last!='<')) return false;
			}
		}
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
