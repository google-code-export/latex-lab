package org.latexlab.docs.server.auth;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class AuthenticationTokenStore {

	public AuthenticationToken getUserToken() {
      UserService userService = UserServiceFactory.getUserService();
      if (userService.getCurrentUser() != null) {
        String email = userService.getCurrentUser().getEmail();
	    return AuthenticationToken.getUserToken(email);
      }
      return null;
	}
	
	public AuthenticationToken getUserToken(String email) {
	  return AuthenticationToken.getUserToken(email);
	}

	public void setUserToken(String email, String token) {
	  AuthenticationToken.storeUserToken(email, token);
	}

}
