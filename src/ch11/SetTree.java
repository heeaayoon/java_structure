package ch11;

public class SetTree {
	
	//conected : 두 정점이 연결되었는지 유무를 확인하는 메서드
	
	public boolean connected(int i, int j) {
		return find(i) ==find(j);
	}

}
