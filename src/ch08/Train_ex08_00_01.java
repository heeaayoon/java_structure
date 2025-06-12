package ch08;
//스트링 배열 합병 정렬
import java.util.Arrays;
import java.util.List;

public class Train_ex08_00_01 {
	static void showList(String topic, String [] list) {
    	System.out.print(topic + "::");
	    for ( String city: list)
	    	System.out.print("  "+ city);
	    System.out.println();
    }
    static String[] mergeList(String[]s1, String[] s2) {
    	int i = 0, j = 0,k =0;
    	int s3_len = s1.length + s2.length;
    	String[] s3 = new String[s3_len];
    	while (i < s1.length && j < s2.length) {
    		if ((s1[i].compareTo(s2[j])) < 0) {
    			s3[k] = s1[i];k++; i++;
    		}
    		else if ((s1[i].compareTo(s2[j])) > 0) {
    			s3[k] = s2[j];k++; j++;
    		}else {
    			s3[k] = s1[i]; k++; i++; j++;
    		}
    	}
    	while ( i <s1.length) {
    		s3[k] = s1[i]; k++; i++;
    	}
    	while ( j <s2.length) {
    		s3[k] = s2[j]; k++; j++;
    	}
    	return s3;
    }
    public static void main(String[] args) {
	String[] s1 = {"홍길동", "강감찬", "을지문덕", "계백", "김유신", "최치원" };
	String[] s2 = {"독도", "울릉도", "한산도", "영도", "오륙도", "동백섬"};
	Arrays.sort(s1);
	Arrays.sort(s2);
	
	showList("s1배열 = ", s1);
	showList("s2배열 = ", s2);

	String[] s3 = mergeList(s1,s2);//정렬된 s1[], s2[]을 합병하여 다시 정렬된 결과를 만드는 함수 구현
	showList("스트링 배열 s3 = s1 + s2:: ", s3);
}
}
