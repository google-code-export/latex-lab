package org.latexlab.docs.server.auth;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthenticationKey {

  protected AuthenticationKey() { }
  
  private static PrivateKey authSubKey;
  private static final Logger log = Logger.getLogger(AuthenticationKey.class.getName());
  
  public static PrivateKey getAuthSubKey() {
	if (authSubKey == null) {
	  try {
		CertificateStore certStore = new CertificateStore();
		Certificate cert = certStore.getCertificate("gdata");
	    byte[] encodedKey = cert.getKey().getBytes();
	    byte[] decodedBuffer = com.google.appengine.repackaged.com.google.common.util.Base64.decode(encodedKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(decodedBuffer);
        authSubKey = keyFactory.generatePrivate(privateKeySpec);
	  } catch (Exception x) {
        log.log(Level.SEVERE, "AuthSub key generation failed: "  + x.getMessage(), x);
	  }
	}
	return authSubKey;
  }
  
}
