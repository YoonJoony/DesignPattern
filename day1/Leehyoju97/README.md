# 템플릿 메소드 패턴
특정 작업을 처리하는 일부분을 서브 클래스로 캡슐화하여 전체적인 구조는 바꾸지 않으면서 특정 단계에서 수행하는 내용을 바꾸는 패턴

## 장점

1. 중복코드를 줄일 수 있다. <br>
2. 하위 클래스의 역할을 줄여 핵심 로직의 관리가 용이하다.
3. 코드를 객체지향적으로 구성할 수 있다.

## 단점
1. 추상 메소드가 많아지면서 클래스 관리가 복잡해진다.
2. 클래스간의 관계와 코드가 꼬여버릴 염려가 있다.

```

abstract class Teacher{
	
    public void start_class() {
        inside();
        attendance();
        teach();
        outside();
    }
	
    // 공통 메서드
    public void inside() {
        System.out.println("선생님이 강의실로 들어옵니다.");
    }
    
    public void attendance() {
        System.out.println("선생님이 출석을 부릅니다.");
    }
    
    public void outside() {
        System.out.println("선생님이 강의실을 나갑니다.");
    }
    
    // 추상 메서드
    abstract void teach();
}
 

class Korean_Teacher extends Teacher{
    
    @Override
    public void teach() {
        System.out.println("선생님이 국어를 수업합니다.");
    }
}
 

class Math_Teacher extends Teacher{

    @Override
    public void teach() {
        System.out.println("선생님이 수학을 수업합니다.");
    }
}


class English_Teacher extends Teacher{

    @Override
    public void teach() {
        System.out.println("선생님이 영어를 수업합니다.");
    }
}

public class Main {
    public static void main(String[] args) {
        Korean_Teacher kr = new Korean_Teacher();
        Math_Teacher mt = new Math_Teacher();
        English_Teacher en = new English_Teacher();
        
        kr.start_class();
        System.out.println("----------------------------");
        mt.start_class();
        System.out.println("----------------------------");
        en.start_class();
    }
}
```

Korean_Teacher, Math_Teacher, English_Teacher 클래스의 공통적인 부분을 추상클래스 Teacher로 구현해주고 하위클래스에서 재정의가 필요한 메소드를 추상메소드로 정의 해준 후 하위클래스에서 오버라이딩 해주는 방식으로 사용하여 코드의 중복성을 최소화 할 수 있습니다.

