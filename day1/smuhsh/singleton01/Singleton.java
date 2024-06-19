package week01.day03.singleton01;

public class Singleton extends Object {
	
	//인스턴스를 공유하기 위해서 static 변수로 선언한다
	private static Singleton instance;
	private int msg;
	
	//constructor
	private Singleton(int msg) {
		//private 이유: public 이면 외부에서 접근이 가능해서 생성자 호출을 차단한다
		this.msg = msg;
	}

	public static Singleton getInstance(int msg) {
		if (instance == null) {
			instance = new Singleton(msg);
		}
		return instance;
	}
	
	public void printMsg() {
		System.out.println(msg);
	}

}
