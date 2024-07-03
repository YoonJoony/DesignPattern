# 파사드 패턴
![facad](https://github.com/smuhsh/DesignPattern/assets/49484645/c3946608-9499-4099-b601-074fb4931faa)

# 정의
퍼사드(프랑스어: façade[fəˈsɑːd] 영어: facade) 패턴(외관 패턴)은 소프트웨어 공학 디자인 패턴 중 하나이다. 
객체 지향 프로그래밍 분야에서 자주 쓰인다. Facade (외관)는 "건물의 정면"을 의미한다.
퍼사드는 클래스 라이브러리 같은 어떤 소프트웨어의 다른 커다란 코드 부분에 대한 '간략화된 인터페이스'를 제공하는 객체이다.


# 왜? 어디에?

## 상황

### ex 1) 주문 프로세스
![order](https://github.com/smuhsh/DesignPattern/assets/49484645/e3c5b1ae-26be-442c-bec3-4d7a25be6a6f)

온라인 쇼핑몰 시스템을 예를들겠다. 이 시스템에는 사용자 인증, 제품 검색, 주문 처리, 결제 처리 등 여러 서브시스템이 있을 것이다. 
만약 이 모든 서브시스템을 직접 다루려면 굉장히 복잡하다. 
그래서 여기서 Facade pattern (퍼사드 패턴)을 사용하면, 이 모든 복잡한 과정들을 간단한 인터페이스 뒤에 숨길 수 있다. 
예를 들어 '주문하기'라는 하나의 메서드로 사용자 인증부터 결제까지 모든 과정을 간단하게 처리할 수 있게 되는 것이다.

### ex 2) 자동차 시동버튼
![carEngineButton](https://github.com/smuhsh/DesignPattern/assets/49484645/ecf95097-96d3-4052-a90a-23a48f042e1a)

자동차 엔진 시스템이 복잡한데, 운전자는 엔진의 모든 세부 사항을 알 필요 없이 
스타트 엔진 버튼만 누르면 엔진이 시동된다. 여기서 버튼은 운전자에게 복잡한 엔진 시스템의 간단한 인터페이스를 제공하는 퍼사드와 같은 역할을 한다. 
개발자 입장에서 퍼사드 패턴은 복잡한 시스템을 쉽게 사용할 수 있도록 해줘서, 개발 과정을 단순화하고 오류 가능성을 줄여주는 장점이 있다.

## 해결
![facad01](https://github.com/smuhsh/DesignPattern/assets/49484645/3c054fb8-ee81-4745-97f1-a1608a818861)

파사드는 복잡한 시스템에 대한 간단한 인터페이스를 제공하고 들어오는 요청을 시스템 내의 적절한 객체에 위임하는 클래스이다.
서브시스템은 시스템 기능을 구현하는 클래스 세트로써 파사드 클래스는 이러한 클래스를 처리한다.
클라이언트는 파사드를 사용하여 시스템에 액세스할 수 있다.

# 특징
## ※ 사용자의 사용성 향상

퍼사드 패턴은 특히 대규모 소프트웨어 프로젝트나 라이브러리, 프레임워크에서 유용하다.
복잡한 시스템의 세부 구현을 숨기고 사용자에게는 간단한 인터페이스를 제공함으로써, 시스템의 사용성을 향상시키고 유지보수를 용이하게 한다.
이러한 접근은 특히 다양한 하위 시스템들이 복잡하게 얽혀 있는 경우에 클라이언트의 작업을 대폭 간소화시킬 수 있다.


# 장점
### 단순해진다

① 복잡성 숨기기
 개발자는 복잡한 서브시스템의 내부 로직을 몰라도 되고, 단순화된 인터페이스를 통해서만 시스템과 상호작용할 수 있다. 이렇게 되면 개발 과정이 단순해지고, 오류 가능성도 줄어든다.

② 의존성 감소
 클라이언트 코드는 서브시스템의 구체적인 구현에 의존하지 않고, 단지 퍼사드 인터페이스에만 의존하게 된다. 이런 방식은 시스템의 결합도를 낮추어 유지보수와 확장성을 높이는 데 도움을 준다.

③유지보수 및 유연성 확장
 퍼사드는 좋게 작성되지 않은 API의 집합을 하나의 좋게 작성된 API로 감싸준다. 
 대부분의 바깥쪽의 코드가 퍼사드를 이용하기 때문에 시스템을 개발하는 데 있어 유연성이 향상된다.

# 단점
### 복잡성의 증가

① 퍼사드의 과도한 책임	
퍼사드가 서브시스템의 모든 요소를 추상화하려고 하면, 너무 많은 책임과 복잡성을 가지게 될 수 있다. 이는 퍼사드 자체가 복잡해지고 관리하기 어려워지는 원인이 될 수 있다.

② 서브시스템에 대한 의존성	
퍼사드는 서브시스템에 대해 어느 정도 의존성을 가질 수밖에 없다. 만약 서브시스템이 크게 변경된다면, 퍼사드 역시 이에 맞춰 수정되어야 하며, 이는 유지보수의 복잡성을 증가시킬 수 있다.

③ 유지보수의 복잡성	
퍼사드가 제공하는 추가적인 추상화 레이어는 코드 베이스에 추가적인 계층을 만든다. 이렇게 되면 간단한 작업조차도 퍼사드를 통해 이루어져야 하고, 이는 유지보수를 더 복잡하게 만들 수 있다. 특히, 시스템이 커지고 복잡해질수록 이러한 문제는 더 심각해질 수 있다.


# 간단한 예제

```java
package com.pattern.domain.facad;

public class Order {
	
    private Pay pay;
    private Product product;
    private Deliver deliver;
    
    public Order(){
        this.pay = new Pay();        
        this.product = new Product();
        this.deliver = new Deliver();
    }

    public void work(){
        this.pay.card();
        this.product.product();
        this.deliver.rider();
    }
}

```

```java
package com.pattern.domain.facad;

public class Pay {
	
    public void card() {
        System.out.println("결제 처리...");
    }

}

```


```java
package com.pattern.domain.facad;

public class Product {

    public void product() {
        System.out.println("상품 준비...");
    }

}
```

```java
package com.pattern.domain.facad;

public class Deliver {

    public void rider() {
        System.out.println("배송 준비...");
    }
    
}

```

```java
package com.pattern.domain.facad;

public class Order {
	
    private Pay pay;
    private Product product;
    private Deliver deliver;
    
    public Order(){
        this.pay = new Pay();        
        this.product = new Product();
        this.deliver = new Deliver();
    }

    public void work(){
        this.pay.card();
        this.product.product();
        this.deliver.rider();
    }
}

```
![facad01](https://github.com/smuhsh/DesignPattern/assets/49484645/df663fed-c1c6-4f46-961c-be75e1e28897)



우선, 사용자가 어플리케이션에서 사용할 카드사의 '주문한다' 라는 공통적인 기능을 Order class 에 정의했다.
그리고 주문 프로세스를 구현하기 위해 결제, 상품, 배송 클래스를 정의하였다.

![facad02](https://github.com/smuhsh/DesignPattern/assets/49484645/e9e8d905-3725-4d16-a3be-039cd49db969)


클라이언트가 '주문' 이라는 절차를 수행하기 위해서 내부 프로세스를 알 필요가 없다.
파사드패턴을 사용하면 이러한 서브시스템의 내부로직을 몰라도 된다.
퍼사드는 클래스 라이브러리 같은 어떤 소프트웨어의 다른 커다란 코드 부분에 대한 '간략화된 인터페이스'를 제공하는 객체이기 때문이다.

