package fectoryMethodEx01;

public class Client {
	public static void main(String[] args) {
		// 1. 공장 객체 생성(리스트)
		AbstractFactory[] factory = {
				new ConcreteFactoryA(),
				new ConcreteFactoryB()
		};
		
		IProduct productA = factory[0].createOperation();
	}
}
