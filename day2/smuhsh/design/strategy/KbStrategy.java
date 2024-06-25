package com.pattern.domain.strategy;

public class KbStrategy implements Account {

	private static final String KB = "kb국민";
	
	@Override
	public String account() {
        // 카드를 사용하는 기능 
        return KB;
		
	}

}
