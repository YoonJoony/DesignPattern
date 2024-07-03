package com.pattern.domain.facad;

public class Order {
	
    private Pay pay;
    private Product product;
    private Deliver deliver;
    
    public Order(){
        this.pay = new Pay();        
        this.product = new Product();
        this.deliver = new Deliver();
    }

    public void work(){
        this.pay.card();
        this.product.product();
        this.deliver.rider();
    }
}
