## 팩토리 메서드 패턴

- 객체 생성을 팩토리 클래스로 캡슐화 처리하여 대신 생성하게 하는 디자인 패턴
- 클라이언트에서 직접 new 연산자를 통해 제품 객체를 생성하는 것이 아니고 제품 객체들을 맡아 생성하는 공장 클래스를 만들고, 이를 상속하는 서브 공장 클래스의 메서드에서 여러가지 제품 객체 생성을 각각 책임 지는 것이다.

![클래스 다이어그램](https://github.com/YoonJoony/DesignPattern/assets/83864280/19dbe260-6631-4bcf-8060-b92125e8d4c0)

### 예제

- 팩토리 메서드 패턴을 적용한 예제

```
package designpattern.factory;

public class Ship {
	String name;
	String color;
	String capacity;
	
	@Override
	public String toString() {
		return "Ship [name=" + name + ", color=" + color + ", capacity=" + capacity + "]";
	}
	
	public static Ship orderShip(String name, String email) {
		if (name == null) throw new IllegalArgumentException("배 이름을 지어주세요.");
		if (email == null) throw new IllegalArgumentException("이메일을 남겨주세요.");
		
		Ship ship = new Ship();
		ship.name = name;
		
		if (name.equalsIgnoreCase("ContainerShip")) ship.capacity = "20t";
		else if (name.equalsIgnoreCase("OilTankerShip")) ship.capacity = "15t";
		
		if (name.equalsIgnoreCase("ContainerShip")) ship.color = "green";
		else if (name.equalsIgnoreCase("OilTankerShip")) ship.color = "blue";
		
		System.out.println(ship.name + " 다 만들었다고 " + email + "로 메일 보냈습니다.");
		
		return ship;
	}
	
	public static void main(String[] args) {
		Ship containerShip = orderShip("ContainerShip", "dlgywn2420@gmail.com");
		System.out.println(containerShip);
		
		Ship oilTankerShip = orderShip("OilTankerShip", "dlgywn2420@gmail.com");
		System.out.println(oilTankerShip);
	}
}
```

- 단순히 Ship 객체를 만들면 간단하게 선박을 만드는 프로그램을 작성할 수 있습니다.
- 클린하지 않은 코드
	- 다른 종류의 선박이 추가 될 경우 Ship클래스 구성 자체가 복잡해질수 있다.
	
### 팩토리 메서드 패턴 적용

```
package designpattern.factory03;

public class Ship {
	String name;
	String color;
	String capacity;
	@Override
	public String toString() {
		return "Ship [name=" + name + ", color=" + color + ", capacity=" + capacity + "]";
	}
}
```

```
package designpattern.factory03;

public class ContainerShip extends Ship {
	public ContainerShip(String name, String capacity, String color) {
		this.name = name;
		this.capacity = capacity;
		this.color = color;
	}
}

```

```
package designpattern.factory03;

public class OilTankerShip extends Ship {
	OilTankerShip(String name, String capacity, String color) {
		this.name = name;
		this.capacity = capacity;
		this.color = color;
	}
}
```

```
package designpattern.factory03;

public abstract class ShipFactory {
	final Ship orderShip(String email) {
		validate(email);
		
		Ship ship = createShip();
		
		sendEmailTo(email, ship);
		
		return ship;
	}
	
	abstract protected Ship createShip();
	
	private void validate(String email) {
		if (email == null) throw new IllegalArgumentException("이메일을 남겨주세요");
	}
	
	private void sendEmailTo(String email, Ship ship) {
		System.out.println(ship.name + " 다 만들었다고 " + email + "로 메일을 보냈습니다.");
	}
}
```

```
package designpattern.factory03;

public class ContainerShipFactory extends ShipFactory {

	@Override
	protected Ship createShip() {
		return new ContainerShip("ContainerShip", "20t", "green");
	}

}
```

```
package designpattern.factory03;

public class OilTankerShipFactory extends ShipFactory {

	@Override
	protected Ship createShip() {
		return new OilTankerShip("OilTankerShip", "15t", "blue");
	}
	
}
```

```
package designpattern.factory03;

public class Client {
	public static void main(String[] args) {
		Ship containerShip = new ContainerShipFactory().orderShip("dlgywn2420@gmail.com");
		System.out.println(containerShip);
		
		Ship oilTankerShip  = new OilTankerShipFactory().orderShip("dlgywn2420@gmail.com");
		System.out.println(oilTankerShip);
	}
}
```

```
ShipFactory 클래스를 추상클래스로 만든 후 Ship의 공통된 기능을 메소드로 만들고 Ship 
인스턴스를 생성을 담당하는 createShip() 메소드를 추상메소드로 선언한 한 후 ShipFactory의 
하위 클래스에서 ShipFactory 클래스를 상속받은 후 재정의를 통해 각 객체의 특성에 맞게 재정의를 합니다.
이렇게 만들면 또 다른 Ship이 생길 경우 ShipFactory를 상속받은 후 재정의를 하면 되기 
때문에 기존코드를 건들 필요가 없다는 장점이 있습니다.
```

### 팩토리 메서드 패턴 특징

#### 패턴 사용 시기
1. 클래스 생성과 사용의 처릴 로직을 분리하여 결합도를 낮추고자 할 때
2. 코드가 동작해야 하는 객체의 유형과 종속성을 캡슐화를 통해 정보 은닉 처리 할 경우
3. 라이브러리 혹은 프레임워크 사용자에게 구성 요소를 확장하는 방법을 제공하려는 경우
4. 기존 객체를 재구성하는 대신 기존 객체를 재사용하여 리소스를 절약하고자 하는 경우

#### 장점
1. 생성자와 구현 객체의 강한 결합을 피할 수 있다.
2. 팩토리 메소드를 통해 객체의 생성 후 공통으로 할 일을 수행하도록 지정해줄 수 있다.
3. 캡슐화, 추상화를 통해 생성되는 객체의 구체적인 타입을 감출 수 있다.
4. 단일 책임 원칙 준수
5. 개방/ 폐쇄 원칙 준수
6. 생성에 대한 인터페이스 부분과 생성에 대한 구현 부분을 따로 나뉘었기 때문에 패키지 분리하여 개별로 여러 개발자가 협업을 통해 개발

#### 단점
1. 각 제품 구현체마다 팩토리 객체들을 모두 구현해주어야 하기 때문에, 구현제가 늘어날 때마다 팩토리 클래스가 증가하여 서블 클래스 수가 폭발한다.
2. 코드 복잡성이 증가한다.
