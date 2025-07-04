package ch08;

import java.util.Comparator;

public class P_DoubledLinkedList2 {

	private Node4 first; // 머리 포인터(참조하는 곳은 더미노드)

    // --- 생성자(constructor) ---//
    public P_DoubledLinkedList2() {
        first = new Node4(); // dummy(first) 노드를 생성
    }
    
    // --- 리스트가 비어있는가? ---//
    public boolean isEmpty() {
        return first.getRlink() == first;
    }

    // --- 노드를 검색 ---//
    public boolean search(SimpleObject2 obj, Comparator<? super SimpleObject2> c) {
        Node4 current = first.getRlink();
        
        while (current != first) {
            if (c.compare(current.getData(), obj) == 0) {
                return true;
            }
            current = current.getRlink();
        }
        return false;
    }

    // --- 전체 노드 표시 ---//
    public void show() {
        Node4 current = first.getRlink();
        
        if (isEmpty()) {
            System.out.println("리스트가 비어있습니다.");
            return;
        }
        
        while (current != first) {
            System.out.print(current.getData() + " ");
            current = current.getRlink();
        }
        System.out.println();
    }

    // --- 올림차순으로 정렬이 되도록 insert ---//
    public void add(SimpleObject2 obj, Comparator<? super SimpleObject2> c) {
        Node4 newNode = new Node4(obj);
        Node4 current = first.getRlink();
        Node4 prev = first;
        
        // 올림차순으로 삽입할 위치 찾기
        while (current != first && c.compare(current.getData(), obj) < 0) {
            prev = current;
            current = current.getRlink();
        }
        
        // 새 노드 삽입
        newNode.setRlink(current);
        newNode.setLlink(prev);
        prev.setRlink(newNode);
        current.setLlink(newNode);
    }

    // --- list에 삭제할 데이터가 있으면 해당 노드를 삭제 ---//
    public void delete(SimpleObject2 obj, Comparator<? super SimpleObject2> c) {
        Node4 current = first.getRlink();
        
        while (current != first) {
            if (c.compare(current.getData(), obj) == 0) {
                // 노드 삭제
                current.getLlink().setRlink(current.getRlink());
                current.getRlink().setLlink(current.getLlink());
                return;
            }
            current = current.getRlink();
        }
        System.out.println("삭제할 데이터를 찾을 수 없습니다.");
    }

    public DoubledLinkedList2 mergeNewList(DoubledLinkedList2 lst2, Comparator<SimpleObject2> cc) {
        //l3 = l1.merge(l2); 실행하도록 리턴 값이 리스트임
        //l.add(객체)를 사용하여 구현
        //기존 리스트의 노드를 변경하지 않고 새로운 리스트의 노드들을 생성하여 구현
        DoubledLinkedList2 lst3 = new DoubledLinkedList2();
        Node4 ai = this.first.getRlink(), bi = lst2.first.getRlink();

        // 두 리스트의 노드들을 순서대로 새로운 리스트에 추가
        while (ai != this.first && bi != lst2.first) {
            if (cc.compare(ai.getData(), bi.getData()) <= 0) {
                lst3.add(ai.getData(), cc);
                ai = ai.getRlink();
            } else {
                lst3.add(bi.getData(), cc);
                bi = bi.getRlink();
            }
        }
        
        // 남은 노드들 처리
        while (ai != this.first) {
            lst3.add(ai.getData(), cc);
            ai = ai.getRlink();
        }
        
        while (bi != lst2.first) {
            lst3.add(bi.getData(), cc);
            bi = bi.getRlink();
        }

        return lst3;
    }

    void mergeInPlace(DoubledLinkedList2 b, Comparator<SimpleObject2> cc) {
        /*
         * 연결리스트 a,b에 대하여 a = a + b
         * merge하는 알고리즘 구현으로 in-place 방식으로 합병/이것은 새로운 노드를 만들지 않고 합병하는 알고리즘 구현
         * 난이도 등급: 최상급
         * 회원번호에 대하여 a = (3, 5, 7), b = (2,4,8,9)이면 a = (2,3,4,5,8,9)가 되도록 구현하는 코드
         */
        Node4 p = first.getRlink(), q = b.first.getRlink();
        Node4 temp = null;

        // b 리스트의 모든 노드를 a 리스트로 이동
        while (q != b.first) {
            temp = q.getRlink(); // 다음 노드 저장
            
            // q 노드를 b 리스트에서 분리
            q.getLlink().setRlink(q.getRlink());
            q.getRlink().setLlink(q.getLlink());
            
            // q 노드를 a 리스트에 올림차순으로 삽입
            Node4 current = first.getRlink();
            Node4 prev = first;
            
            while (current != first && cc.compare(current.getData(), q.getData()) < 0) {
                prev = current;
                current = current.getRlink();
            }
            
            // q 노드 삽입
            q.setRlink(current);
            q.setLlink(prev);
            prev.setRlink(q);
            current.setLlink(q);
            
            q = temp; // 다음 노드로 이동
        }
        
        // b 리스트 초기화
        b.first.setRlink(b.first);
        b.first.setLlink(b.first);
    }
}
