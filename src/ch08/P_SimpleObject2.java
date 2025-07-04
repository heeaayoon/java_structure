package ch08;

import java.util.Comparator;
import java.util.Scanner;

public class P_SimpleObject2 {
	static final int NO = 1; // 번호를 읽어 들일까요?
    static final int NAME = 2; // 이름을 읽어 들일까요?
    String no; // 회원번호
    String name; // 이름
    String expire;//  유효기간 필드를 추가

    public P_SimpleObject2() {
        this.no = null;
        this.name = null;
        this.expire = null;
    }

    public P_SimpleObject2(String sno, String sname, String expire) {
        this.no = sno;
        this.name = sname;
        this.expire = expire;
    }

    // --- 문자열 표현을 반환 ---//
    @Override
    public String toString() {
        return "(" + no + ") " + name;
    }

    // --- 데이터를 읽어 들임 ---//
    void scanData(String guide, int sw) {
        Scanner sc = new Scanner(System.in);
        System.out.println(guide + "할 데이터를 입력하세요." + sw);

        if ((sw & NO) == NO) { //& 는 bit 연산자임
            System.out.print("번호: ");
            no = sc.next();
        }
        if ((sw & NAME) == NAME) {
            System.out.print("이름: ");
            name = sc.next();
        }
    }

    // --- 회원번호로 순서를 매기는 comparator ---//
    public static final Comparator<SimpleObject2> NO_ORDER = new NoOrderComparator();

    private static class NoOrderComparator implements Comparator<SimpleObject2> {
        @Override
        public int compare(SimpleObject2 d1, SimpleObject2 d2) {
            return (d1.no.compareTo(d2.no) > 0) ? 1 : ((d1.no.compareTo(d2.no) < 0)) ? -1 : 0;
        }
    }

    // --- 이름으로 순서를 매기는 comparator ---//
    public static final Comparator<SimpleObject2> NAME_ORDER = new NameOrderComparator();

    private static class NameOrderComparator implements Comparator<SimpleObject2> {
        @Override
        public int compare(SimpleObject2 d1, SimpleObject2 d2) {
            return (d1.name.compareTo(d2.name) > 0) ? 1 : ((d1.name.compareTo(d2.name) < 0)) ? -1 : 0;
        }
    }
}
