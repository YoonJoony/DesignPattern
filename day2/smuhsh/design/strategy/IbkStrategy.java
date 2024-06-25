package com.pattern.domain.strategy;


public class IbkStrategy implements Account {
	
    private static final String IBK = "기업은행";
    
    @Override
    public String account() {
        // IBK기업은행에서 인출하는 기능
        return IBK;
    }
}
