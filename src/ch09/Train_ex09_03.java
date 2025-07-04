package ch09;
//집합 트리
//집합 원소를 제거하는 delete() 추가, 집합 세트를 출력하는 displaySets()를 추가함
//그래프 문제를 풀기 위해, set의 union, find 함수가 필요함 ->treeset api에는 union, find가 없음

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

class Sets3 {
    private int[] parent; //상대적 상위계층
    private int n;

    public Sets3(int sz) {
        n = sz;
        parent = new int[sz + 1]; // Don't want to use parent[0]
        Arrays.fill(parent, -1);  // Initialize with -1
    }
    
    public int simpleFind(int i) {
    	// Find "the root" of the tree containing element i
    	//i 번째 원소를 찾아줘 => 찾았으면 원소의 값을 보내주고, 못찾으면 i 번째 원소를 전달 
    	while(parent[i]>=0) {
    		i = parent[i]; 
    	}
    	return i;
    }

    public void simpleUnion(int i, int j) {
        // Replace the disjoint sets with roots i and j, i != j with their union
        i = simpleFind(i); //i가 속한 집함의 루트
        j = simpleFind(j); //j가 속한 집합의 루트
        if (i == j) return; //이미 같은 집합이면 종료
        else
        	parent[j]=i;
    }
    
    public int find(int i) {
    	if(parent[i]<0) { // i가 루트인지?
    		return i; 
    	}
    	return parent[i] = find(parent[i]); //경로압축 : 부모를 루트에 연결
    }
    
    public void displaySets() {
    	//{1,2,3} 등으로 set을 표시하기	
    	Map<Integer, List<Integer>> sets = new HashMap<>();
    	for(int i =1;i<n;i++) {
    		int root = find(i);
    		sets.computeIfAbsent(root, k-> new ArrayList<>()).add(i);
    	}
    	System.out.println("현재 집합 상태");
    	sets.forEach((root,element)->{
    		System.out.println("{"+element.stream().sorted()
    				.map(String::valueOf)
    				.collect(Collectors.joining(","))
    				+"}");
    	});
    	System.out.println("");   	
    }
    
    //출력 메서드 
    public void display() {
    	System.out.print("display:index=  ");
    	for (int i = 1; i <= n; i++) {
    		System.out.printf("%3d", i);
    	}
    	System.out.println();
    	
    	System.out.print("display:value= ");
    	for (int i = 1; i <= n; i++) {
    		System.out.printf("%3d", parent[i]);
    	}
    	System.out.println();
    }
    
    public void weightedUnion(int i, int j) {
        // Union sets with roots i and j, using the weighting rule.
        i = find(i);
        j = find(j);
        if (i == j) return;
        
        
        //더 작은 집합을 더 큰 집합 아래에 둔다
        if(parent[i]<parent[j]) { //i의 집합 갯수가 작을 때
        	parent[i] += parent[j]; //병합
        	parent[j] = i; // j를 i의 자식으로 만듦
        }else {
        	parent[j] += parent[i]; //병합
        	parent[i] = j; // i를 j의 자식으로 만듦
        }
    
    }
    //void difference() 차집합 -  이 문제는 disjoint set을 가정하므로 가정 변경이 필요
    //void intersection()교집합

    
    public void delete(int n) {
    	if(n<=0 ||n>this.n) {
    		System.out.println("잘못된 매개변수입니다");
    		return;
    	}
    	//root, non-leaf 아닌 경우 -> 독립 집합 분리
    	parent[n] = -1;
    }
}

//delete 에서 root, non-leaf 를 삭제할 때 문제 해결 필요


    
public class Train_ex09_03 {
	public static void main(String[] args) {
		TreeSet<Integer> set1 = new TreeSet<>();
		set1.add(1);
	
        Sets3 m = new Sets3(20);
        m.simpleUnion(7, 1);
        m.simpleUnion(2, 3);
        m.simpleUnion(4, 5);
        m.simpleUnion(6, 7);
        
        m.simpleUnion(4, 2);
        m.simpleUnion(5, 7);
        m.simpleUnion(8, 10);
        m.simpleUnion(13, 11);
        m.simpleUnion(12, 9);
        m.simpleUnion(14, 20);
        m.simpleUnion(16, 19);
        m.simpleUnion(17, 18);
        m.simpleUnion(12, 19);
        m.simpleUnion(13, 15);
        System.out.println("SimpleUnion() 실행 결과::");
        m.display();
        m.displaySets();
//        m.weightedUnion(1, 8);
//        m.weightedUnion(1, 4);
//        m.weightedUnion(3, 9);
//        m.weightedUnion(7, 15);
//        m.weightedUnion(12, 18);
//        m.weightedUnion(4, 16);
//        System.out.println("WeightedUnion() 실행 결과::");
//        m.display();
//        m.displaySets();
        
        if (m.simpleFind(2) == m.simpleFind(18))
        	System.out.println("2, 18은 같은 집합이다");
        else
        	System.out.println("2, 18은 다른 집합이다");

        System.out.println("\n***3를 집합에서 삭제한다***");
        m.delete(3); //root를 삭제할 때를 고민
        m.display();
        m.displaySets();
    }
}