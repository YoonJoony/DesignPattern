## Map

키와 값을 하나의 쌍으로 저장하는 방식

요소를 순차적으로 저장하지 않고 key를 통해 value를 얻어옴.

- key : 중복을 허용 X
- value : 중복을 허용 O

### HashMap

Map 중 가장 간단한 HashMap 방식

- Map 기능
    
    `put(key, value)` : Map 객체 값 추가
    
    ```java
    HashMap<String, String> map = new HashMap<>();
    map.put("people", "사람");
    map.put("baseball", "야구");
    
    ```
    
    `get(key)` : Map 객체 값 조회
    
    ```java
    System.out.println(map.get("people"));
    ```
    
    `containsKey(key)` : Map 에 해당하는 키가 있는지 boolean 타입으로 조회
    
    ```java
    System.out.println(map.containsKey("people"));
    ```
    
    `remove(key)` : key에 해당하는 아이템(key, value)을 삭제
    
    ```java
    map.remove("people");
    ```
    

## Lambda

**익명 클래스로 구현한 메소드를 람다식으로 줄이기**

```java
interface A {
	void abc();
}

public class OOPvsFP {
	public static void main(String[] args) {
		// 2. 객체지향 프로그래밍 문법 2(case2) : 익명 이너 클래스
		A a2 = new A() {
			@Override
			public void abc() {
				System.out.println("메서드 내용 2");
			};
		};
		a2.abc();
		
		// 3. 함수적 프로그래밍 문법 (람다식)
		A a3 = () -> {System.out.println("메서드 내용 3");};
		a3.abc();
	}
}
```

**매개변수가 존재하는 람다식**

```java
interface A {
	void abc(int a);
}

public class FunctionToLambdaExpression {
	public static void main(String[] args) {
		A a1 = (a) -> {System.out.println("람다 : " + a);};
		a1.abc(39);
	}
}
```
