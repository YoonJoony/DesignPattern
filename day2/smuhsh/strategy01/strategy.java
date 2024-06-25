package week01.day03.singleton02;

public class Singleton extends Object {
	
	private static Singleton instance;
	private int msg;
	
	
	private Singleton(int msg) {
		
		try {
			Thread.sleep(100);
			this.msg = msg;
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static Singleton getInstance(int msg) {
		if (instance == null) {
			instance = new Singleton(msg);
		}
		return instance;
	}
	
	public int getMsg() {
		return msg;
	}

}
