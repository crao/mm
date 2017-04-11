package com.my.auth;

import com.my.model.Member;
import com.my.model.Token;

public interface AuthorizationService {
	
	public boolean authourize(Member member);
	
	public Token createAuthorizationToken(Member member);
	
	

}
