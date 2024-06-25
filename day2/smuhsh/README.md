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
## ※ 캡슐화 (알고리즘 코드가 노출되어서는 안 되는 데이터에 액세스 하거나 데이터를 활용할 때)

같은 기능이지만 서로 다른 전략을 가진 클래스들을 각각 캡슐화 하여 상호교환 할 수 있도록 도와주는 패턴이다.
객체의 행위를 변경하고 싶은 경우 직접 수정하지 않고 전략이라 불리는 캡슐화한 알고리즘을 변경해줌으로써
유연하게 확장할 수 있다.

# 장점
유지보수하기 좋다(컨텍스트 코드의 변경 없이 새로운 전략을 추가할 수 있다)

예를 들어 새로운 전략인 중간 손님을 대폭할인하는 정책이 추가되었다고 가정하자. 그러면 아래와 같이 변할 것이다.

기존 코드 : else-if 문이 추가된다.
전략패턴이 적용된 코드 : 새로운 클래스가 추가된다.
즉, 요구사항이 변경되었을 때 기존의 코드를 변경하지 않아도 된다는 것이 전략패턴의 장점이며, 
새로운 전략에 대해서는 새로운 클래스를 통해 관리하기 때문에 OCP의 원칙을 준수할 수 있는 패턴이다.

# 단점
① 알고리즘이 많아질수록 관리해야할 객체의 수가 늘어난다.
만일 어플리케이션 특성이 알고리즘이 많지 않고 자주 변경되지 않는다면, 새로운 클래스와 인터페이스를 만들어 프로그램을 복잡하게 만들 이유가 없다.

② 적합한 전략을 선택하기 위해 전략간의 차이점을 파악해야한다. (복잡도 증가)

# 간단한 예제

```java
package com.pattern.domain.strategy;

/*
 * 작성일:2024-06-25
 * 작성자:황석현
 * 개요: 인터페이스 정의
 * 		현금인출을 위한 카드사 계좌 기능
 * 
 * */
public interface Account {

	String account();

}
```

```java
package com.pattern.domain.strategy;

public class AtmMachine {
	
    public String account(Account account) {
    	
        return account.account();
    }
}
```

```java
package com.pattern.domain.strategy;


public class IbkStrategy implements Account {
	
    private static final String IBK = "기업은행";
    
    @Override
    public String account() {
        // IBK기업은행에서 인출하는 기능
        return IBK;
    }
}
```
```java
package com.pattern.domain.strategy;

public class KbStrategy implements Account {

	private static final String KB = "kb국민";
	
	@Override
	public String account() {
        // 카드를 사용하는 기능 
        return KB;
		
	}

}
```
```java
package com.pattern.domain.strategy;

/*
 * 작성일:2024-06-25
 * 작성자:황석현
 * 개요: 전략패턴
 * 		카드를 통해 ATM기에서 현금을 인출한다
 * 
 */
public class Main {
	
	//클라이언트가 현금인출기에서 계좌를 선택하는 전략
	private static Account kbButton() {
		return new KbStrategy();
	}
	
	private static Account ibkButton() {
		return new IbkStrategy();
	}

    public static void main(String[] args) {
    	
    	System.out.println("ATM 현금인출기 카드목록");
    	
    	//#1. 현금인출기 생성
        AtmMachine atmMachine = new AtmMachine();
        
        //#2. KB국민은행 카드로 현금인출
        String KB = atmMachine.account(kbButton());
        System.out.println(atmMachine.account(kbButton()));
        
        //#3. IBK기업은행 카드로 현금인출
        String ibk = atmMachine.account(ibkButton());
        System.out.println(atmMachine.account(ibkButton()));

    }

}
```
![ezgif-4-3a62d56600](https://github.com/smuhsh/DesignPattern/assets/49484645/6cf33201-94ea-4479-b88b-bac62f311cc2)

우선, 현금인출기에서 사용할 카드사의 '계좌를 선택한다' 라는 공통적인 기능을 Account 인터페이스에 정의했다. 그리고 이를 구현하기 위해 IBK, KB 전략 클래스를 정의하였다.

![ezgif-4-8eb37c693f](https://github.com/smuhsh/DesignPattern/assets/49484645/361faef3-1ca3-415d-99d4-9816e1456c4a)

![ezgif-4-a2e45d6a77](https://github.com/smuhsh/DesignPattern/assets/49484645/c1a28617-6ea9-461a-9753-185d84fadcbb)

클라이언트가 ATM 으로 갔고 버튼을 누르는 행위를 하였다. 누르는 행위인 '버튼' 이벤트가 발생하면 전략을 다르게 부여하면 된다.
전략패턴을 사용하면 현금인출을 아주 간결하게 구현할 수 있다.
계좌 인터페이스를 받으면 클라이언트 쪽에서 주입하는 구현체에 따라서 전략이 결정되기 때문이다.

