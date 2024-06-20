## 싱글톤패턴
![singleton](https://github.com/smuhsh/DesignPattern/assets/49484645/2b692803-5b1d-4041-8814-df7463193246)

## 정의
클래스의 인스턴스를 "딱 하나"만 생성하여 사용하는 패턴이다.

(즉, 객체를 딱 하나만 생성하도록 하는 디자인 패턴)

주로 특정 객체를 여러곳에서 공유해야할 때 사용한다. ex) DB Connection pool

![singleton-3x](https://github.com/smuhsh/DesignPattern/assets/49484645/1bc74f84-3191-4bbe-84bb-a407a7968488)

```java
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
```

![ezgif-6-abe5ed4bfe](https://github.com/smuhsh/DesignPattern/assets/49484645/017ba955-b4a0-485a-b1ab-cd26ae75a2f9)
