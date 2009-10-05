package org.latexlab.pine.server.models;

import java.util.Date;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Models and stores an AuthSub token.
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class AuthenticationToken {
    @PrimaryKey
    private String email;

    @Persistent
    private String token;

    @Persistent
    private Date obtained;

    /**
     * Constructs a new Authentication Token.
     * 
     * @param email the user's email
     * @param token the AuthSub token
     * @param obtained the date the token was assigned
     */
    private AuthenticationToken(String email, String token, Date obtained) {
        this.email = email;
        this.token = token;
        this.obtained = obtained;
    }

    /**
     * Retrieves the email of the user associated with the token.
     * 
     * @return the user's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retrieves the AuthSub token.
     * 
     * @return the AuthSub token
     */
    public String getToken() {
        return token;
    }
    
    /**
     * Retrieves the date the token was assigned.
     * 
     * @return the date the token was assigned
     */
    public Date getObtained() {
      return obtained;
    }
    
    /**
     * Sets the AuthSub token.
     * 
     * @param token the AuthSub token
     */
    private void setToken(String token) {
      this.token = token;
      this.obtained = new Date();
    }
    
    /**
     * Retrieves the Authentication Token for a given user, by email.
     * 
     * @param email the user's email address
     * @return the Authentication Token, if any
     */
    public static AuthenticationToken getUserToken(String email) {
      PersistenceManager pm = PMF.get().getPersistenceManager();
      AuthenticationToken token = null;
      try {
        token = pm.getObjectById(AuthenticationToken.class, email);
      } 
      catch (JDOObjectNotFoundException e) { }
      finally {
        pm.close();
      }
      return token;
    }
    
    /**
     * Stores a user token. If a token already exists for the specified
     * user, it's value is updated.
     * 
     * @param email the user's email address
     * @param token the AuthSub token
     * @return the new or updated Authentication Token
     */
    public static AuthenticationToken storeUserToken(String email, String token) {
      PersistenceManager pm = PMF.get().getPersistenceManager();
      AuthenticationToken at = null;
      try {
        try {
          at = pm.getObjectById(AuthenticationToken.class, email);
          at.setToken(token);
        } 
        catch (JDOObjectNotFoundException e) {
          at = new AuthenticationToken(email, token, new Date());
          pm.makePersistent(at);
        }
      } finally {
        pm.close();
      }
      return at;
    }
    
    /**
     * Clears a user token from the data store.
     * 
     * @param email the user's email address
     */
    public static void clearUserToken(String email) {
      PersistenceManager pm = PMF.get().getPersistenceManager();
      AuthenticationToken token = null;
      try {
        token = pm.getObjectById(AuthenticationToken.class, email);
        pm.deletePersistent(token);
      } 
      catch (JDOObjectNotFoundException e) { }
      finally {
        pm.close();
      }
    }
}