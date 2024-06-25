package fectoryMethodEx01;

abstract class AbstractFactory {
	// 객체 생성 전처리 후처리 메소드 (final로 오버라이딩 방지, 템플릿화)
	final IProduct createOperation() {
		IProduct product = createProduct(); // 서브 클래스에서 구체화한 팩토리 메서드 실행
		product.setting(); // .. 이밖의 객체 생성에 가미할 로직 실행
		return product; // 제품 객체를 생성하고 추가 설정하고 완성된 제품을 반환
	}
	
	abstract protected IProduct createProduct();
}
