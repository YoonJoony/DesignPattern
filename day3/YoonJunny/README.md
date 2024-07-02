## 빌더 패턴

복잡한 객체의 생성 과정과 표현 방법을 분리하여 다양한 구성의 인스턴스를 만드는 생성 패턴

방식 : 생성자에 들어갈매개변수를 메서드 하나하나로 받아들이고 마지막에 빌드해서 객체를 생성하는 방식

**사례 : 수제 햄버거**
수제 햄버거를 주문할 때 빵이나 패티 등 속재료들은 주문하는 사람이 마음대로 결정된다. 어느 사람은 치즈를 빼달라고 할 수 있고, 어느 사람은 토마토를 빼달라고 할 수 있다.
이는 유연하게 선택적으로 속재료들을 받아 다양한 타입의 인스턴스를 생성할 수 있어, 클래스의 선택적 매개변수가 많은 상황에서 유연하게 사용.
#### ![image](https://github.com/YoonJoony/DesignPattern/assets/110625854/314a2fe9-3f2a-4279-98ff-04da1d6f5fe3)

### 빌더 패턴이 나오게 된 이유

점층적 생성자 패턴(Telescoping Constructor Pattern)은 

**🎨** 필수 매개변수와 함께 선택 매개변수를 0개, 1개, 2개 .. 받는 형태로, 우리가 다양한 매개변수를 입력받아 인스턴스를 생성하고 싶을때 사용하던 **생성자를 오버로딩** 하는 방식

```java
class Hamburger {
    // 필수 매개변수
    private int bun;
    private int patty;

    // 선택 매개변수
    private int cheese;
    private int lettuce;
    private int tomato;
    private int bacon;

    public Hamburger(int bun, int patty, int cheese, int lettuce, int tomato, int bacon) {
        this.bun = bun;
        this.patty = patty;
        this.cheese = cheese;
        this.lettuce = lettuce;
        this.tomato = tomato;
        this.bacon = bacon;
    }

    public Hamburger(int bun, int patty, int cheese, int lettuce, int tomato) {
        this.bun = bun;
        this.patty = patty;
        this.cheese = cheese;
        this.lettuce = lettuce;
        this.tomato = tomato;
    }
    ...
}
```

```java
public static void main(String[] args) {
    // 모든 재료가 있는 햄버거
    Hamburger hamburger1 = new Hamburger(2, 1, 2, 4, 6, 8);

    // 빵과 패티 치즈만 있는 햄버거
    Hamburger hamburger2 = new Hamburger(2, 1, 1);
}
```

- 이러한 방식은 클래스 인스턴스 필드들이 많으면 많을 수록 생성자에 들어갈 인자의 수가 늘어나
코드의 가독성이 안좋아진다.
- 매개변수의 특성 상 순서를 따라야 하기 때문에 매개변수 입력 원치 않을경우 0, null 을 입력해야 하는 경우가 생김
- 타입이 다양할 수록 생성자가 많아져 가독성이나 유지보수 측면에서 좋지 않음

## 위 생성자 패턴의 문제를 해결하기 위해 고안된 패턴들

### 1. Java Beans 패턴

흔이 하는 **`Setter 메소드`**가 이 패턴이다.

**🎨** 매개변수가 없는 생성자로 구성 후 **`Setter 메소드`**로 필드를 설정하는 방식

```java
class Hamburger {
    // 필수 매개변수
    private int bun;
    private int patty;

    // 선택 매개변수
    private int cheese;
    private int lettuce;
    private int tomato;
    private int bacon;
    
    public Hamburger() {}

    public void setBun(int bun) {
        this.bun = bun;
    }

    public void setPatty(int patty) {
        this.patty = patty;
    }

    public void setCheese(int cheese) {
        this.cheese = cheese;
    }
		...
}
```

```java
public static void main(String[] args) {
    // 모든 재료가 있는 햄버거
    Hamburger hamburger1 = new Hamburger();
    hamburger1.setBun(2);
    hamburger1.setPatty(1);
    hamburger1.setCheese(2);
    hamburger1.setLettuce(4);
    hamburger1.setTomato(6);
    hamburger1.setBacon(8);

    // 빵과 패티 치즈만 있는 햄버거
    Hamburger hamburger2 = new Hamburger();
    hamburger2.setBun(2);
    hamburger2.setPatty(1);
    hamburger2.setCheese(2);
}
```

⇒ 기존 생성자 오버로딩의 문제점이 사라지고 선택적인 파라미터인 Setter 메서드를 호출하여 유연성이 상승.

그러나 객체 생성 시점에 모든 값들을 주이하지 않아 일관성 문제와 불변성 문제가 나타남.

**일관성 문제**

필수 매개변수란 객체가 초기화될때 반드시 설정되어야 하는 값. 

그러나 Setter로 모든 메서드를 호출하지 않았다면 이 객체는 일관성이 무너진 상태가 된다. 

즉, 객체가 유효하지 않은 것이다. 만일 다른곳에서 햄버거 인스턴스를 사용하게 된다면 `런타임 예외가 발생할 수도 있다.`

**불변성 문제**

자바 빈 패턴에서는 객체를 생성한 후에 `setter` 메서드를 통해 필드를 설정. 

하지만 객체를 생성했음에도 여전히 외부적으로 Setter 메소드를 노출되어 누군가 Setter 메서드를 호출해 함부로 객체를 조작할수 있게 된다.

### 2. 빌더 패턴

자바 빈 패턴의 문제를 해결하기 위해 고안된 패턴.

별도의 Builder 클래스를 만들어 메소드를 통해 step-by-step으로 값을 이력받은 후 최종적으로 build() 메소드로 객체를 생성하는 패턴.

**🎨 main 함수의 객체 생성을 먼저 보면**

빌더 방식에서 build() 전까지는 setter와 비슷하게 메소드로 받지만 하**나의 체인 형태로 여러가지 메소드를 받으며** 결정적인 차이는 **마지막에 build() 메소드로 객체를 생성하여 객체 수정을 닫는 것**

```java
public static void main(String[] args) {

    // 생성자 방식
    Hamburger hamburger = new Hamburger(2, 3, 0, 3, 0, 0);

    // 빌더 방식
    Hamburger hamburger = new Hamburger.Builder(10)
        .bun(2)
        .patty(3)
        .lettuce(3)
        .build();
}
```

## 빌더 패턴 구조

코드 예시 → Student 클래스와 이 클래스 대한 객체 생성만을 담당하는 별도의 빌더 클래스 생성

**🎨 Student 클래스**

```java
class Student {
    private int id;
    private String name = "아무개";
    private String grade = "freshman";
    private String phoneNumber = "010-0000-0000";

    public Student(int id, String name, String grade, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.phoneNumber = phoneNumber;
    }
    
    @Override
    public String toString() {
        return "Student { " +
                "id='" + id + '\'' +
                ", name=" + name +
                ", grade=" + grade +
                ", phoneNumber=" + phoneNumber +
                " }";
    }
}
```

**🎨빌더 클래스 구현**

```java
class StudentBuilder {
// 만들고자 하는 클래스(Student)에 대한 필드와 똑같이 필드를 구성한 클래스 생성
    private int id;
    private String name;
    private String grade;
    private String phoneNumber;
// 그리고 각 맴버에대한 Setter 메서드를 구현
		public StudentBuilder id(int id) {
        this.id = id;
        return this;
    }

    public StudentBuilder name(String name) {
        this.name = name;
        return this;
    }

    public StudentBuilder grade(String grade) {
        this.grade = grade;
        return this;
    }

    public StudentBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
// 마지막으로 구현하고자 하는 클래스로 셋팅된 빌더 클래스 멤버를 그대로 넣어줌.
    public Student build() {
        return new Student(id, name, grade, phoneNumber); // Student 생성자 호출
    }
}
```

- 중요한 점은 setter 메소드 구현 시 return this를 호출함으로써 연속적으로 빌더 메서드를 체이닝 하여 호출할 수 있게 한다.
    - **`new StudentBuilder.id().name()…`**
- 마지막으로 build() 메서드를 구성하여 생성하고자 하는 클래스 생성자의 인자에 넣어 멤버 구성이 완료된 인스턴스를 얻게 한다.

### 빌더 패턴 장점

1. **객체 생성 과정을 일관된 프로세스로 표현**
    
    직관적으로 어떤 데이터에 어떤 값이 설정되는지 한눈에 파악 가능
    
2. 필수 멤버와 선택적 멤버 분리 가능.
    
    **🎨** 기존 생성자 방식은 이를 구분해주려면 객체 생성 시 null을 입력하는 방식으로 구성하거나 생성자 오버로딩을 해야 했음
    
    ```java
    class Student {
        // 초기화 필수 멤버
        private int id;
    
        // 초기화 선택적 멤버
        private String name;
        private String grade;
        private String phoneNumber;
    
        public Student(int id, String name, String grade, String phoneNumber) {
            this.id = id;
            this.name = name;
            this.grade = grade;
            this.phoneNumber = phoneNumber;
        }
    }
    =======================================================================================
    // main함수에서 객체 생성 시
    Student student = new Student(2010234455, null, null, null);
    ```
    
     ****
    
    **🎨 빌더패턴은 초기화가 필수 멤버를 생성자로 받게하여 필수 멤버 따로 지정 유도 가능**
    
    ```java
    class StudentBuilder {
        // 초기화 필수 멤버
        private int id;
    
        // 초기화 선택적 멤버
        private String name;
        private String grade;
        private String phoneNumber;
        
        // 필수 멤버는 빌더의 생성자를 통해 설정
        public StudentBuilder(int id) {
            this.id = id;
        }
    		...
    }
    =======================================================================================
    // main함수에서 객체 생성 시
    Student student1 = 
            new StudentBuilder(2016120091) // 필수 멤버
            .name("홍길동") // 선택 멤버
            .build();
    ```
    
3. **객체 생성 단게 지연**
    
    객체 생성을 단계별로 구성하거나 구성 단계를 지연하여 재귀적으로 생성을 처리할 수 있다.
    
    즉, 빌더를 재사용하거나 미리 만들어 놓아 나중에 객체를 생성할 수 있게 함.
    
    ```java
    // 1. 빌더 클래스 전용 리스트 생성
    List<StudentBuilder> builders = new ArrayList<>();
    
    // 2. 객체를 최종 생성 하지말고 초깃값만 세팅한 빌더만 생성
    builders.add(
        new StudentBuilder(2016120091)
        .name("홍길동")
    );
    
    builders.add(
        new StudentBuilder(2016120092)
        .name("임꺽정")
        .grade("senior")
    );
    
    builders.add(
        new StudentBuilder(2016120093)
        .name("박혁거세")
        .grade("sophomore")
        .phoneNumber("010-5555-5555")
    );
    
    // 3. 나중에 빌더 리스트를 순회하여 최종 객체 생성을 주도
    for(StudentBuilder b : builders) {
        Student student = b.build();
        System.out.println(student);
    }
    ```
    

### 빌더 패턴의 단점

- 코드의 복잡성 증가 → 클래스 수 많아짐
- 생성자 보다는 성능이 떨어짐 → 내부 or 외부 클래스와 메소드를 거치고 생성하므로 당연히 떨어짐
    - 지나친 빌더 남용은 차라리 정적 팩토리 메소드로 사용하는 것이 더 좋을 수 있음.

## 빌더 패턴의 종류

### **심플 빌더 패턴(Effective java)**

- 일반적으로 정의되는 빌더 패턴.
- 생성자가 많을 경우 or 변경 불가능한 불변 객체가 필요할 경우에 중점을 둠.

```java
class Person {
	String name;
	int age;
	// 정적 내부 빌더 클래스
	public static class Builder {
		String name;
		int age;
		
		Builder name(String name) {
			this.name = name;
			return this;
		}
		
		Builder age (int age) {
			this.age = age;
			return this;
		}
	}
	// private 생성자
	private Person(Builder builder) {
		this.name = builder.name;
		this.age = builder.age;
	}
}
```

빌더 클래스가 static inner class로 구현되는 이유

1. **두 클래스를 물리적으로 그룹핑**
    
    ```java
    Student st = new Student.Builder()
    				.name("miler")
    				.gender("man")
    				...
    ```
    
    위 처럼 `new 생성 목적 클래스.빌더 클래스`  로 물리적으로 분리하여 관계 파악이 가능
    
2. **대상 객체는 오로지 빌더 객체에 의해 초기화.**
    
    위에서 처음 설명한 두 클래스로 나눴던 빌더 패턴의 경우
    
    - 초기화된 빌더 클래스를 **생성하고자 하는** 클래스(Student)가 받아야 하므로 `생성자를 public으로 설정해야 됐다.`
        
        ```java
        class StudentBuilder {
            private int id;
            private String name;
            private String grade;
            private String phoneNumber;
            
        		public StudentBuilder id(int id) {
                this.id = id;
                return this;
            }
        
        		...
            public Student build() {
                return new Student(id, name, grade, phoneNumber); // 이 부분 =====================================
            }
        }
        ```
        
    
     
    
    이너 빌더 클래스로 구현하여 `생성자를 private로 숨길 수 있게 됨.`
    
3. 왜 굳이 이너 빌더 클래스를 static으로 생성하냐면 당연히 빌더 클래스에서 먼저 필드를 초기화 하고 생성된 빌더 객체를 외부 클래스에 넘겨줘야 하기 떄문.
    ```java
    Student st = new Student.Builder()....
    ```

[참고 사이트](https://inpa.tistory.com/entry/GOF-%F0%9F%92%A0-%EB%B9%8C%EB%8D%94Builder-%ED%8C%A8%ED%84%B4-%EB%81%9D%ED%8C%90%EC%99%95-%EC%A0%95%EB%A6%AC)
