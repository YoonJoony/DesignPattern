package fectoryMethodEx01;

// 공장 객체 B (ProductB를 생성하여 반환)
public class ConcreteFactoryB extends AbstractFactory {
	@Override
	protected IProduct createProduct() {
		System.out.println("ConcreateFactoruB");
		return new ConcreteProductB();
	}
}
