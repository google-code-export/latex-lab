package org.latexlab.docs.server.auth;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import com.google.appengine.api.datastore.Text;

import org.latexlab.docs.server.PMF;

/**
 * Models and stores a certificate key.
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Certificate {
	
    @PrimaryKey
    private String purpose;

    @Persistent
    private Text key;

    /**
     * Constructs a new Authentication Token.
     * 
     * @param email the user's email
     * @param token the AuthSub token
     * @param obtained the date the token was assigned
     */
    private Certificate(String purpose, String key) {
        this.purpose = purpose;
        this.key = new Text(key);
    }

    /**
     * Retrieves the purpose of the certificate.
     * 
     * @return the certificate's purpose
     */
    public String getPurpose() {
        return purpose;
    }

    /**
     * Retrieves the certificate key.
     * 
     * @return the certificate key
     */
    public String getKey() {
        return key.getValue();
    }
    
    /**
     * Retrieves the Authentication Token for a given user, by email.
     * 
     * @param email the user's email address
     * @return the Authentication Token, if any
     */
    public static Certificate getCertificate(String purpose) {
      PersistenceManager pm = PMF.get().getPersistenceManager();
      Certificate cert = null;
      try {
        cert = pm.getObjectById(Certificate.class, purpose);
      } 
      catch (JDOObjectNotFoundException e) { }
      finally {
        pm.close();
      }
      return cert;
    }

}