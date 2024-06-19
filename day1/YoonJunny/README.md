### 질문 한 것

- 팩토리 메소드의 단점
- 템플릿 메소드의는 보통 어떠한 상황에서 적용되나

## 싱글톤 패턴

<aside>
💡 클래스에 인스턴스 하나만 있도록 하면서 이 인스턴스에 대한 
전역 접근지점을 제공하는 생성 디자인 패턴.

</aside>

### 특징

1. **클래스 인스턴스가 하나만 있도록 한다.** 
    - 클래스에 있는 있스턴스 수를 제어하려는 일반적인 이유는 일부 공유 리소스에 대한 접근을 제어하기 위함.반듯
        - 새 객체를 생성하는 대신 이미 만든 객체를 받게 한다.
        - 그러나 생성자 호출은 특성상 반드시 새 객체를 반환해야 하므로 싱글톤 패턴의 호출은 일반 생성자로 구현할 수 없다.
    - 해당 인스턴스에 대한 전역 접근 지점을 제공
        - 한 클래스에서 생성한 전역 변수는 접근하기에는 편할 지 몰라도, 잠재적으로 해당 변수가 다른 내용으로 덮어 쓸 위험성이 있다.
        - 싱글톤으로 사용하면 모든 클라이언트로부터 해당 변수/객체 하나로만 접근하게 할게 보호함.

### 구성 방법

- 다른 곳에서 싱글톤 클래스 객체에 접근할 때 new 연산자를 사용하지 못하도록 디폴트 생성자를 비공개 설정한다.
    - 이를 정적 생성자를 생성하여 외부 접근을 막는다.
        
        ```java
        	public class Singleton {
        		private Singleton sigleton = null;
        		private Singleton() { };
        ```
        
- 생성자 역할을 하는 정적 생성 메서드를 만든다. 내부적으로 객체를 생성한 뒤 객체를 정적 필드에 저장. 이 메서드에 대한 그 다음 호출은 모두 캐시된 객체를 반환
    
    ```java
    	public class Singleton {
    		private Singleton sigleton = null;
    		private Singleton() { };
    		public static Singleton getInstance() {
    			if(singleton == null) { singleton = new Singleton(); }
    			return singleton;
    		}
    ```
    

⇒ 인스턴스가 생성되어 있는지 확인하고 생성되어 있지 않으면 동기화.

### 장점

- 클래스가 하나의 인스턴스만 갖는다는 것을 확신할 수 있따.
- 이 인스턴스에 대한 전역 접근 지점을 얻는다.
- 싱글턴 객체는 처음 요청될 때만 초기화 된다.

### 단점

1. 단일 책임 원칙 위반. 한 번에 두 가지의 문제를 책임져야함
    - 사용자가 부여한 클래스 본래의 책임, 싱글톤으로 유지되어야 하는 책임
2. 다중 스레드 환경에서 여러 스레드가 싱글턴 객체를 여러 번 생성할 수 있음. 

### 단점 2 상황

싱글톤 객체를 생성하는 getInstance 메서드가 지연 초기화 하는 방식에서 발생

**지연 초기화 방식** → 위에 작성한 코드처럼 `일반적인 싱글톤 방식`

1. 스레드 A가 getInstance 메서드를 호출하고 객체가 null 인지 확인
2. 스레드 A가 아직 인스턴스를 생성하기 전에 컨텍스트 스위칭이 발생하여 스레드 B가 getInstance 메서드를 호출
3. 스레드 B도 instance가 null인지 확인하고 새로운 스레드 생성
4. 스레드 A로 다시 컨텍스트가 전환되어 인스턴스를 생성.

### 해결 방법

1. 메서드 전체를 동기화

```java
public class Singleton {
	private static Singleton instance;
	
	public Singleton() { }
	
	public static synchronized Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
}
```

- 간단하지만 성능 저하 초래할 수 있음.
- 모든 스레드가 getInsctance 메서드를 호출할 때 동기화 비용이 발생.

1. 이중 검사 잠금(Double-Checked Locking)

```java

```
