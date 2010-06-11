package org.latexlab.docs.server.auth;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * Exposes methods for storing and retrieving authentication tokens
 * to and from the data store.
 */
public class AuthenticationTokenStore {

  /**
   * Clears a user's authentication token, by email.
   * 
   * @param email the user's email
   */
  public void clearUserToken(String email) {
    AuthenticationToken.clearUserToken(email);
  }

  /**
   * Retrieves the authentication token for the currently logged on user.
   * 
   * @return the authentication token for the currently logged on user
   */
  public AuthenticationToken getUserToken() {
    UserService userService = UserServiceFactory.getUserService();
    if (userService.getCurrentUser() != null) {
      String email = userService.getCurrentUser().getEmail();
      return AuthenticationToken.getUserToken(email);
    }
    return null;
  }
	
  /**
   * Retrieves a user's authentication token, by email.
   * 
   * @param email the user's email
   * @return the user's authentication token
   */
  public AuthenticationToken getUserToken(String email) {
    return AuthenticationToken.getUserToken(email);
  }

  /**
   * Stores a user's authentication token.
   * 
   * @param email the user's email
   * @param token the user's authentication token
   */
  public void setUserToken(String email, String token) {
    AuthenticationToken.storeUserToken(email, token);
  }

}
