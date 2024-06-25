package fectoryMethodEx01;

// 공장 객체 A (ProductA를 생성하여 반환)
public class ConcreteFactoryA extends AbstractFactory {
	@Override
	protected IProduct createProduct() {
		System.out.println("ConcreateFactoruA");
		return new ConcreteProductA();
	}
}
