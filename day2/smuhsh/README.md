# 전략패턴
![strategy-2x](https://github.com/smuhsh/DesignPattern/assets/49484645/5be09a5a-a2e9-4e02-8f34-9a5eaf2c6986)

# 정의
실행(런타임) 중 알고리즘 전략을 선택하여 객체 동작을 실시간으로 바뀌도록 할 수 있게 하는 행위 디자인 패턴이다.
'전략'이란 일종의 알고리즘이 될 수 도 있으며, 기능이나 동작이 될 수도 있는 특정한 목표를 수행하기 위한 행동 계획을 말한다.
즉, 어떤 일을 수행하는 알고리즘이 여러가지 일때, 동작들을 미리 전략으로 정의함으로써 손쉽게 전략을 교체할 수 있는, 
알고리즘 변형이 빈번하게 필요한 경우에 적합한 패턴이다.


# 왜? 어디에?

## 상황
![strategy-comic-1-ko-2x](https://github.com/smuhsh/DesignPattern/assets/49484645/e0db3fa4-32e6-4366-95c9-c09816bb39a2)

ex) 만일 당신이 공항에 가야 하는 상황이다. 당신은 버스를 탈 수도 있고, 택시나 자전거를 탈 수도 있다. 이것들이 바로 당신의 운송 전략들이다.
전략패턴은 예산이나 시간 제약 등을 고려하여 이러한 전략 알고리즘 중 하나를 선택할 수 있다.

## 해결
![solution-2x](https://github.com/smuhsh/DesignPattern/assets/49484645/2a80028d-190c-4c1f-a93e-0ba35a92f3c3)

전략 패턴은 특정 작업을 다양한 방식으로 수행하는 클래스를 선택한 후 모든 알고리즘을 전략들(strategies)​이라는 별도의 클래스들로 추출할 것을 제안한다.

콘텍스트(context)​라는 원래 클래스에는 전략 중 하나에 대한 참조를 저장하기 위한 필드가 있어야 한다. 콘텍스트는 작업을 자체적으로 실행하는 대신 연결된 전략 객체에 위임한다.

콘텍스트는 작업에 적합한 알고리즘을 선택할 책임이 없다. 대신 클라이언트가 원하는 전략을 콘텍스트에 전달한다. 
콘텍스트는 같은 일반 인터페이스를 통해 모든 전략과 함께 작동하며, 이 일반 인터페이스는 선택된 전략 내에 캡슐화된 알고리즘을 작동시킬 단일 메서드만 노출한다.
이렇게 하면 콘텍스트가 구상 전략들에 의존하지 않게 되므로 콘텍스트 또는 다른 전략들의 코드를 변경하지 않고도 새 알고리즘들을 추가하거나 기존 알고리즘들을 수정할 수 있다.

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
