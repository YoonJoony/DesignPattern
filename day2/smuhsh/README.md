# 전략패턴
![strategy-2x](https://github.com/smuhsh/DesignPattern/assets/49484645/5be09a5a-a2e9-4e02-8f34-9a5eaf2c6986)

# 정의
실행(런타임) 중 알고리즘 전략을 선택하여 객체 동작을 실시간으로 바뀌도록 할 수 있게 하는 행위 디자인 패턴이다.
'전략'이란 일종의 알고리즘이 될 수 도 있으며, 기능이나 동작이 될 수도 있는 특정한 목표를 수행하기 위한 행동 계획을 말한다.
즉, 어떤 일을 수행하는 알고리즘이 여러가지 일때, 동작들을 미리 전략으로 정의함으로써 손쉽게 전략을 교체할 수 있는, 
알고리즘 변형이 빈번하게 필요한 경우에 적합한 패턴이다.


# 왜? 어디에?

## 상황

### ex 1) 네이게이션의 경로탐색
![strategy-comic-1-ko-2x](https://github.com/smuhsh/DesignPattern/assets/49484645/e0db3fa4-32e6-4366-95c9-c09816bb39a2)

만일 당신이 공항에 가야 하는 상황이다. 당신은 버스를 탈 수도 있고, 택시나 자전거를 탈 수도 있다. 이것들이 바로 당신의 운송 전략들이다.
전략패턴은 예산이나 시간 제약 등을 고려하여 이러한 전략 알고리즘 중 하나를 선택할 수 있다.

### ex 2) 커피머신기 주문
![스타벅스-‘사이렌-오더’-비회원도-서비스-가능](https://github.com/smuhsh/DesignPattern/assets/49484645/69962f9a-22dd-4420-80d2-950f78492a6e)

당신은 커미머신기에서 카페라떼, 아이스라떼, 핫초코 등 여러가지 음료를 주문 할 수 있다.
클라이언트에서 요청하는 여러 주문을 해결하기 위해 기호에 맞는 전략을 택할 수 있다.

## 해결
![solution-2x](https://github.com/smuhsh/DesignPattern/assets/49484645/2a80028d-190c-4c1f-a93e-0ba35a92f3c3)
![img](https://github.com/smuhsh/DesignPattern/assets/49484645/fad12649-88e4-49d1-a587-fd001bd36b57)

전략 패턴은 특정 작업을 다양한 방식으로 수행하는 클래스를 선택한 후 모든 알고리즘을 전략들(strategies)​이라는 별도의 클래스들로 추출할 것을 제안한다.

콘텍스트(context)​라는 원래 클래스에는 전략 중 하나에 대한 참조를 저장하기 위한 필드가 있어야 한다. 콘텍스트는 작업을 자체적으로 실행하는 대신 연결된 전략 객체에 위임한다.

콘텍스트는 작업에 적합한 알고리즘을 선택할 책임이 없다. 대신 클라이언트가 원하는 전략을 콘텍스트에 전달한다. 
콘텍스트는 같은 일반 인터페이스를 통해 모든 전략과 함께 작동하며, 이 일반 인터페이스는 선택된 전략 내에 캡슐화된 알고리즘을 작동시킬 단일 메서드만 노출한다.
이렇게 하면 콘텍스트가 구상 전략들에 의존하지 않게 되므로 콘텍스트 또는 다른 전략들의 코드를 변경하지 않고도 새 알고리즘들을 추가하거나 기존 알고리즘들을 수정할 수 있다.

# 특징
※ 알고리즘 코드가 노출되어서는 안 되는 데이터에 액세스 하거나 데이터를 활용할 때 (캡슐화)

같은 기능이지만 서로 다른 전략을 가진 클래스들을 각각 캡슐화 하여 상호교환 할 수 있도록 도와주는 패턴이다.
객체의 행위를 변경하고 싶은 경우 직접 수정하지 않고 전략이라 불리는 캡슐화한 알고리즘을 변경해줌으로써
유연하게 확장할 수 있다.

# 장점
① 유지보수하기 좋다 :
싱글톤 패턴은 특정 자원이나 서비스에 대한 접근을 제어하는데 유용하다. 싱글톤은 한 번에 하나의 인스턴스만이 자원을 사용하도록 보장함으로써, 리소스의 과도한 사용을 방지할 수 있다.

② 메모리 효율성 :
싱글톤은 필요한 시점에만 인스턴스를 생성하고, 이후에는 동일 인스턴스를 재사용한다.
이는 메모리 사용을 줄이고 시스템의 전반적인 효율성을 높이는 데 도움 된다.

③ 공유 상태의 일관성 :
싱글톤 인스턴스는 애플리케이션에서 전역 상태를 유지한다. 이는 여러 컴포넌트 간에 상태를 공유하고 일관성을 유지하는 데 유용하다.

④ 해당 인스턴스를 다른 모듈들이 공유하며 사용하기 때문에 인스턴스를 생성할 때 드는 
비용이 줄어드는 장점이 있다.

# 단점
① 알고리즘이 많아질수록 관리해야할 객체의 수가 늘어난다.
만일 어플리케이션 특성이 알고리즘이 많지 않고 자주 변경되지 않는다면, 새로운 클래스와 인터페이스를 만들어 프로그램을 복잡하게 만들 이유가 없다.

② 적합한 전략을 선택하기 위해 전략간의 차이점을 파악해야한다. (복잡도 증가)

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
