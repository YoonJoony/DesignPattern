# 빌더 패턴

- 복잡한 객체들을 단계별로 생성할 수 있도록 하는 생성 디자인 패턴
- 별도의 빌더클래스를 만들어 메소드를 통해 값을 입력받은 후 최종적으로 build() 메소드로 하나의 인스턴스를 생성하여 리턴하는 패턴

빌더 패턴 네이명 형식
1. 멤버이름()

```java
Student student = new StudentBuilder(2016120091)
.name("홍길동")
.grade("freshman")
.phoneNumber("010-5555-5555")
.build();
```
2. set멤버이름()

```
Student student = new StudentBuilder(2016120091)
        .setName("홍길동")
        .setGrade("freshman")
        .setPhoneNumber("010-5555-5555")
        .build();
```

3. with멤버이름()

```
Student student = new StudentBuilder(2016120091)
        .withName("홍길동")
        .withGrade("freshman")
        .withPhoneNumber("010-5555-5555")
        .build();
```
# 빌더 패턴 장단점

## 장점
1. 객체 생성 과정을 일관된 프로세스로 표현

```java
// 생성자 방식
Student student1 = new Student(2016120091, "홍길동", "freshman", "010-5555-5555");

// 빌더 방식
Student student2 = new StudentBuilder()
            .id(2016120091)
            .name("임꺽정")
            .grade("Senior")
            .phoneNumber("010-5555-5555")
            .build();
```

2. 디폴트 매개변수 생략을 간접적으로 지원

```java
public Student(int id, String name, String grade = "freshman", String phoneNumber) {
	this.id = id;
	this.name = name;
	this.grade = grade;
	this.phoneNumber = phoneNumber;
}
```

3. 필수 멤버와 선택적 멤버를 분리 가능

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
```

## 단점
1. 코드 복잡성 증가
2. 생성자보다는 성능은 떨어진다.

## 빌더 패턴 적용
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

```java
public class StudentBuilder {
	private int id;
	private String name;
	private String grade;
	private String phoneNumber;
	
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
	
	public Student build() {
		return new Student(id, name, grade, phoneNumber);
	}
	
	public static void main(String[] args) {
		Student student = new StudentBuilder()
				.id(2016120091)
				.grade("Senior")
				.phoneNumber("010-5555-5555")
				.build();
		
		System.out.println(student);
	}
}
```


## 빌더 디자인 패턴 종류
1. 이펙티브 자바의 빌더 패턴 : 생성시 지정해야 할 인자가 많을 때 사용. 객체의 일관성 불변성이 목적

```java
public class Person {
	private final String name;
	private final String age;
	private final String gender;
	private final String job;
	private final String birthday;
	private final String address;
	
	// 정적 내부 빌더 클래스
	public static class Builder {
		// 필수 파라미터
		private final String name;
		private final String age;
		
		private String gender;
		private String job;
		private String birthday;
		private String address;
		
		// 필수 파라미터는 빌더 생성자로 받게 한다.
		public Builder(String name, String age) {
			this.name = name;
			this.age = age;
		}
		
		public Builder gender(String gender) {
			this.gender = gender;
			return this;
		}
		
		public Builder job(String job) {
			this.job = job;
			return this;
		}
		
		public Builder birthday(String birthday) {
			this.birthday = birthday;
			return this;
		}
		
		public Builder address(String address) {
			this.address = address;
			return this;
		}
		
		// 대상 객체의 private 생성자를 호출하여 최종 인스턴스화 한다.
		public Person build() {
			return new Person(this);
		}
	}
	
	private Person(Builder builder) {
		this.name = builder.name;
		this.age = builder.age;
		this.gender = builder.gender;
		this.job = builder.job;
		this.birthday = builder.birthday;
		this.address = builder.address;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", gender=" + gender + ", job=" + job + ", birthday="
				+ birthday + ", address=" + address + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	public static void main(String[] args) {
		Person person = new Person
				.Builder("홍길동", "26")
				.gender("man")
				.job("Warrior")
				.birthday("2000.01.01")
				.address("한국")
				.build();
		
		System.out.println(person);
	}
}
```


1. 빌더 클래스를 Static Nested Class로 정의한다.
2. 빌더를 통해 인스턴스화 하기 때문에 대상 객체 생성자는 private로 정의한다.
3. 빌더클래스의 생성자는 public으로 하며 필수 파라미터에 대해 생성자의 파라미터로 받는다.
4. 선택적 파라미터에 대해서는 메소드로 제공한다. 이 때 메소드의 반환값은 빌더 객체 자신(this)이어야 한다.
5. 마지막으로 최종 객체를 생성하는 build() 메소드를 정의하여 클라이언트에게 최종 생성된 결과물의 제공한다.
6. 이때 생성자의 인수로 빌더 인스턴스 자기자신을 전달하고, 대상 객체 생성자에서 빌더 인스턴스의 필드를 각각 대입하여 최종 완성본이 나오게 된다.

