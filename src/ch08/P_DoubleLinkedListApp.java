package ch08;

import java.util.Scanner;

public class P_DoubleLinkedListApp {

	enum Menu {
        Add("삽입"), Delete("삭제"), Show("인쇄"), Search("검색"), Merge_NewList("병합-새리스트"), Merge_InPlace("병합-제자리"), Exit("종료");

        private final String message; // 표시할 문자열

        static Menu MenuAt(int idx) { // 순서가 idx번째인 열거를 반환
            for (Menu m : Menu.values())
                if (m.ordinal() == idx) return m;
            return null;
        }

        Menu(String string) { // 생성자(constructor)
            message = string;
        }

        String getMessage() { // 표시할 문자열을 반환
            return message;
        }
    }

    // --- 메뉴 선택 ---//
    static Menu SelectMenu() {
        Scanner sc1 = new Scanner(System.in);
        int key;
        do {
            for (Menu m : Menu.values()) {
                System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
                if ((m.ordinal() % 3) == 2 && m.ordinal() != Menu.Exit.ordinal()) System.out.println();
            }
            System.out.print(" : ");
            key = sc1.nextInt();
        } while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());
        return Menu.MenuAt(key);
    }

    public static void main(String[] args) {
        Menu menu; // 메뉴
        Scanner sc2 = new Scanner(System.in);
        DoubledLinkedList2 lst1 = new DoubledLinkedList2(), lst2 = new DoubledLinkedList2();
        DoubledLinkedList2 lst3 = new DoubledLinkedList2(), lst4 = new DoubledLinkedList2();
        String sno1 = null, sname1 = null;
        SimpleObject2 so;
        boolean result = false;
        int count = 3;
        do {
            switch (menu = SelectMenu()) {
                case Add: // 객체들의 올림차순으로 정렬되도록 구현
//				so =  new SimpleObject2();
//				so.scanData("입력", 3);
//				lst1.add(so, SimpleObject2.NO_ORDER);
                    SimpleObject2[] simpleobjects = new SimpleObject2[10];
                    makeSimpleObjects(simpleobjects);
                    for (int i = 0; i < simpleobjects.length; i++)
                        lst1.add(simpleobjects[i], SimpleObject2.NO_ORDER);
                    break;
                case Delete: // 임의 객체를 삭제
                    so = new SimpleObject2();
                    so.scanData("삭제", SimpleObject2.NO);
                    lst1.delete(so, SimpleObject2.NO_ORDER);
                    break;
                case Show: // 리스트 전체를 출력
                    lst1.show();
                    break;
                case Search: // 회원 번호 검색
                    so = new SimpleObject2();
                    so.scanData("탐색", SimpleObject2.NO);
                    result = lst1.search(so, P_SimpleObject2.NO_ORDER);
                    if (!result) System.out.println("검색 값 = " + so + "데이터가 없습니다.");
                    else System.out.println("검색 값 = " + so + "데이터가 존재합니다.");
                    break;
                case Merge_NewList://기존 2개의 리스트를 합병하여 새로운 리스트를 생성(새로운 노드를 생성하여 추가)
//				for (int i = 0; i < count; i++) {//3개의 객체를 연속으로 입력받아 l2 객체를 만든다
//					so = new SimpleObject2();
//					so.scanData("병합", 3);
//					lst2.add(so, SimpleObject2.NO_ORDER );
//				}
                    SimpleObject2[] simpleobjects2 = new SimpleObject2[10];
                    makeSimpleObjects2(simpleobjects2);
                    for (int i = 0; i < simpleobjects2.length; i++)
                        lst2.add(simpleobjects2[i], SimpleObject2.NO_ORDER);
                    System.out.println("리스트 lst1::");
                    lst1.show();
                    System.out.println("리스트 lst2::");
                    lst2.show();
                    lst3 = lst1.merge_NewList(lst2, SimpleObject2.NO_ORDER);
                    //merge 실행후 show로 결과 확인 - 새로운 노드를 만들지 않고 합병 - 난이도 상
                    System.out.println("병합 리스트 lst3::");
                    lst3.show();
                    break;
                case Merge_InPlace:
//				for (int i = 0; i < count; i++) {//3개의 객체를 연속으로 입력받아 l2 객체를 만든다
//					so = new SimpleObject2();
//					so.scanData("병합", 3);
//					lst4.add(so, SimpleObject2.NO_ORDER );
//				}
                    SimpleObject2[] simpleobjects3 = new SimpleObject2[10];
                    makeSimpleObjects3(simpleobjects3);
                    for (int i = 0; i < simpleobjects3.length; i++)
                        lst4.add(simpleobjects3[i], SimpleObject2.NO_ORDER);
                    System.out.println("리스트 lst2::");
                    lst2.show();
                    System.out.println("리스트 lst4::");
                    lst4.show();
                    lst4.mergeInPlace(lst2, SimpleObject2.NO_ORDER);
                    //merge 실행후 show로 결과 확인 - 새로운 노드를 만들지 않고 합병 - 난이도 상
                    System.out.println("병합 리스트 lst4::");
                    lst4.show();
                    break;
                case Exit: //
                    break;
            }
        } while (menu != Menu.Exit);
    }

    static void makeSimpleObjects(SimpleObject2[] simpleobjects) {
        simpleobjects[0] = new SimpleObject2("s8", "hong", "240618");
        simpleobjects[1] = new SimpleObject2("s2", "kim", "240619");
        simpleobjects[2] = new SimpleObject2("s3", "lee", "240601");
        simpleobjects[3] = new SimpleObject2("s1", "park", "240621");
        simpleobjects[4] = new SimpleObject2("s4", "choi", "240622");
        simpleobjects[5] = new SimpleObject2("s6", "jung", "240611");
        simpleobjects[6] = new SimpleObject2("s7", "kang", "240624");
        simpleobjects[7] = new SimpleObject2("s5", "jo", "240615");
        simpleobjects[8] = new SimpleObject2("s19", "oh", "240606");
        simpleobjects[9] = new SimpleObject2("s10", "jang", "240607");
    }

    static void makeSimpleObjects2(SimpleObject2[] simpleobjects) {
        simpleobjects[0] = new SimpleObject2("s5", "song", "240608");
        simpleobjects[1] = new SimpleObject2("s2", "Lim", "240609");
        simpleobjects[2] = new SimpleObject2("s3", "kee", "240601");
        simpleobjects[3] = new SimpleObject2("s1", "park", "240611");
        simpleobjects[4] = new SimpleObject2("s8", "choo", "240612");
        simpleobjects[5] = new SimpleObject2("s9", "jong", "240618");
        simpleobjects[6] = new SimpleObject2("s4", "jang", "240614");
        simpleobjects[7] = new SimpleObject2("s7", "go", "240605");
        simpleobjects[8] = new SimpleObject2("s11", "na", "240616");
        simpleobjects[9] = new SimpleObject2("s10", "you", "240617");
    }

    static void makeSimpleObjects3(SimpleObject2[] simpleobjects) {
        simpleobjects[0] = new SimpleObject2("s5", "song", "240608");
        simpleobjects[1] = new SimpleObject2("s2", "Lim", "240609");
        simpleobjects[2] = new SimpleObject2("s3", "kee", "240601");
        simpleobjects[3] = new SimpleObject2("s1", "park", "240611");
        simpleobjects[4] = new SimpleObject2("s8", "choo", "240612");
        simpleobjects[5] = new SimpleObject2("s9", "jong", "240618");
        simpleobjects[6] = new SimpleObject2("s4", "jang", "240614");
        simpleobjects[7] = new SimpleObject2("s7", "go", "240605");
        simpleobjects[8] = new SimpleObject2("s11", "na", "240616");
        simpleobjects[9] = new SimpleObject2("s10", "you", "240617");
    }
}
