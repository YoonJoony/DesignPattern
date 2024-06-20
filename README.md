# 싱글톤패턴
![singleton](https://github.com/smuhsh/DesignPattern/assets/49484645/2b692803-5b1d-4041-8814-df7463193246)

# 정의
클래스의 인스턴스를 "딱 하나"만 생성하여 사용하는 패턴이다.
(즉, 객체를 딱 하나만 생성하도록 하는 디자인 패턴)
주로 특정 객체를 여러곳에서 공유해야할 때 사용한다. ex) DB Connection pool

# 왜? 어디에?
![singleton-3x](https://github.com/smuhsh/DesignPattern/assets/49484645/1bc74f84-3191-4bbe-84bb-a407a7968488)

ex) 사무실에 여러 명의 사람이 프린터를 사용한다. 프린터를 사용하려는 사람들이 프린터를 각자 생성해서 사용하는 것은 불가능하고 1대만 존재하는 프린터를 여러 사람이 함께 공유하며 사용해야 한다. 이러한 상황은 현실에서도 존재하지만, 실제 구현하는 프로그램 안에서도 존재할 수 있다.

프로그램 내에서 단 1개만 존재해야 하는 객체가 있으며 이를 프로그램 내부의 여러 부분에서 호출하여 사용하는 경우이다. 
보통 공통된 객체를 여러 개 생성해서 사용하는 데이터베이스 연결 모듈에 싱글톤 패턴이 많이 사용한다.

# 특징
메모리에 인스턴스 하나를 등록해 여러 쓰레드에서 동시에 하나의 객체를 사용할 수 있다.
기본생성자를 private 접근제어자로 지정해서 직접 인스턴스를 생성할 수 없게하고, 사용자에게 인스턴스를 전달하는 static 메서드가 있다. static 이기 때문에 고정된 메모리 영역을 얻는다. 
모든 클래스에서 사용할 수 있어서 공통된 객체를 다수의 클라이언트에서 사용해야하는 상황에서 주로 사용된다.

# 장점
① 리소스 관리 및 접근 제어 :
싱글톤 패턴은 특정 자원이나 서비스에 대한 접근을 제어하는데 유용하다. 싱글톤은 한 번에 하나의 인스턴스만이 자원을 사용하도록 보장함으로써, 리소스의 과도한 사용을 방지할 수 있다.

② 메모리 효율성 :
싱글톤은 필요한 시점에만 인스턴스를 생성하고, 이후에는 동일 인스턴스를 재사용한다.
이는 메모리 사용을 줄이고 시스템의 전반적인 효율성을 높이는 데 도움 된다.

③ 공유 상태의 일관성 :
싱글톤 인스턴스는 애플리케이션에서 전역 상태를 유지한다. 이는 여러 컴포넌트 간에 상태를 공유하고 일관성을 유지하는 데 유용하다.

④ 해당 인스턴스를 다른 모듈들이 공유하며 사용하기 때문에 인스턴스를 생성할 때 드는 
비용이 줄어드는 장점이 있다.

# 단점
① 동기화(여러곳에서 동시에 접근해서 생기는 문제)가 발생할 수 있어 잘 파악하고 설계해야된다.
② 싱글톤 객체는 객체지향적인 설계에서 문제가 발생할 수 있다.

싱글톤 객체는 객체의 상태와 행위를 모두 정적(static)으로 구현하기 때문에, 객체지향적인 설계 원칙을 위반할 수 있다. 객체지향적인 설계에서는 객체의 상태와 행위를 캡슐화하여 응집도(cohesion)와 결합도(coupling)를 유지해야 한다. 테스트마다 ‘독립적인’ 인스턴스를 만들기가 어렵다. 이는 객체 지향 설계 원칙에 어긋난다.

# 간단한 예제

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
```java
package week01.day03.singleton01;
/*
 * 작성일:2024-06-19
 * 작성자:황석현
 * 개요: 싱글톤 패턴 연습
 */

public class Main {

	public static void main(String[] args) {
		Singleton instance1 = Singleton.getInstance(1);
		Singleton instance2 = Singleton.getInstance(2);
		
		System.out.println(instance1.hashCode());
		System.out.println(instance2.hashCode());
		
		instance1.printMsg();
		instance2.printMsg();
		
	}

}
```
우선, 싱글톤은 외부에서 생성자를 차단하여 불필요한 메모리 사용을 방지한다.
그래서 접근제어자를 public 이 아닌 private를 사용하여 new 인스턴스를 외부에서 사용할 수 없게 하였다.

![ezgif-6-abe5ed4bfe](https://github.com/smuhsh/DesignPattern/assets/49484645/017ba955-b4a0-485a-b1ab-cd26ae75a2f9)

그리고 싱글톤 객체의 인스턴스값을 호출하여도 결과는 같다.
메모리의 주소값과 파라미터에 int형 인자를 할당한 결과 모두 동일한 값이 출력됨을 확인할 수 있다.
