package ch08;

public class P_Node4 {
	 private SimpleObject2 data; // 데이터
	    private Node4 llink; // 좌측포인터(앞쪽 노드에 대한 참조)
	    private Node4 rlink; // 우측포인터(뒤쪽 노드에 대한 참조)
	    
	    // 생성자
	    public P_Node4() {
	        this.data = null;
	        this.llink = llink;
	        this.rlink = rlink;
	    }
	    
	    public P_Node4(SimpleObject2 data) {
	        this.data = data;
	        this.llink = llink;
	        this.rlink = rlink;
	    }
	    
	    // getter와 setter
	    public SimpleObject2 getData() {
	        return data;
	    }
	    
	    public void setData(SimpleObject2 data) {
	        this.data = data;
	    }
	    
	    public Node4 getLlink() {
	        return llink;
	    }
	    
	    public void setLlink(Node4 llink) {
	        this.llink = llink;
	    }
	    
	    public Node4 getRlink() {
	        return rlink;
	    }
	    
	    public void setRlink(Node4 rlink) {
	        this.rlink = rlink;
	    }
	}
