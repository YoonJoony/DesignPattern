package com.pattern.domain.facad;

/*
 * 작성일:2024-07-02
 * 작성자:황석현
 * 개요: 파사드 패턴 적용
 * 		파사드(Facade) 패턴은 복잡한 시스템을 쉽게 사용할 수 있도록 도와주는 디자인 패턴.
 * 		클라이언트가 주문을 하면 작업절차인
 * 		결제, 상품, 배송 까지 하나의 행위로 간단히 처리한다.
 * 		
 */
public class Main {
    public static void main(String[] args){
        Order order = new Order();
        order.work();
    }
}
