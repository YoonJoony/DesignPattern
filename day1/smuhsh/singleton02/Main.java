package week01.day03.singleton02;
/*
 * 작성일:2024-06-19
 * 작성자:황석현
 * 개요: 싱글톤 패턴 연습2
 * 		
 * 		동시성 문제 :
 * 		싱글톤 객체를 생성하고 동시에 여러개의 객체가 생성되는 것을 확인하기.
 * 		쓰레드를 실행하면 모든 쓰레드가 동시에 도착하여 서로 null 객체를 바라보기 때문에
 * 		쓰레드들이 객체를 생성하는 문제발생 → 동시성 문제 발생
 * 
 */

public class Main {
	
	public static int num = 1;
	
	public static void main(String[] args) {
		
		Runnable run = () -> {
			num++;
			Singleton singleton = Singleton.getInstance(num);
			System.out.println("instance :" + singleton.getMsg());
		};
		for (int i=0; i<10; i++) {
			Thread thread = new Thread(run);
			thread.start();
			//System.out.println("run : "+ run);
			//System.out.println("instance :"+Singleton.getInstance(i)); 
		}
		
	}

}
