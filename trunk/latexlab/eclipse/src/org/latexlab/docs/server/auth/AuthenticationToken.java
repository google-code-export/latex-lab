package org.latexlab.docs.server.auth;

import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import org.latexlab.docs.server.PMF;

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
    private String publicToken;

    @Persistent
    private Date obtained;

    @Persistent
    private Date activity;

    /**
     * Constructs a new Authentication Token.
     * 
     * @param email the user's email
     * @param token the AuthSub token
     * @param obtained the date the token was assigned
     */
    private AuthenticationToken(String email, String token, String publicToken, Date obtained, Date activity) {
        this.email = email;
        this.token = token;
        this.publicToken = publicToken;
        this.obtained = obtained;
        this.activity = activity;
    }
    
    /**
     * Determines if the token's age exceeds the set limit.
     * 
     * @return whether the token's age exceeds the age limit
     */
    public boolean isExpired() {
      Calendar cal = Calendar.getInstance();
      cal.add(Calendar.MONTH, -2);
      if (this.obtained.before(cal.getTime())) {
    	return true;
      }
      return false;
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
     * Retrieves the public token.
     * 
     * @return the public token
     */
    public String getPublicToken() {
        return publicToken;
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
     * Retrieves the date the token was last used.
     * 
     * @return the date the token was last used
     */
    public Date getActivity() {
      return activity;
    }
    
    /**
     * Sets the token's last activity date.
     * 
     * @param activity the token's last activity
     */
    public void setActivity(Date activity) {
      this.activity = activity;
      PersistenceManager pm = PMF.get().getPersistenceManager();
      try {
    	pm.makePersistent(this);
      } catch (JDOObjectNotFoundException e) {
      } finally {
        pm.close();
      }
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
      } catch (JDOObjectNotFoundException e) { }
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
          Cryptoid enc;
          String publicToken = token;
    	  try {
    	    enc = new Cryptoid();
            publicToken = enc.hash(email).replace("+", "_").replace("/", "-").replace("=", "_");
    	  } catch (Exception x) {
    	    Logger.getLogger(AuthenticationToken.class.getName()).log(Level.SEVERE, "Token encryption error: "  + x.getMessage(), x);
    	    x.printStackTrace();
    	  }
          at = new AuthenticationToken(email, token, publicToken, new Date(), new Date());
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