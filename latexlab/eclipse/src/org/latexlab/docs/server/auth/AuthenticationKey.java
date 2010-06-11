package org.latexlab.docs.server.auth;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Exposes methods to retrieve an authentication keys from the data store.
 */
public class AuthenticationKey {

  protected AuthenticationKey() { }
  
  private static PrivateKey authSubKey;
  private static final Logger log = Logger.getLogger(AuthenticationKey.class.getName());
  
  /**
   * Retrieves the private key for use in secure AuthSub.
   * 
   * @return the private key for use in secure AuthSub
   */
  public static PrivateKey getAuthSubKey() {
	if (authSubKey == null) {
	  try {
		CertificateStore certStore = new CertificateStore();
		Certificate cert = certStore.getCertificate("gdata");
		if (cert == null) {
		  try {
			///*dev*/Certificate.putCertificate("gdata", "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANv+rKCE8z9sHPu2knH2g6jSQ8zhdV5Kyqn53s2Z+23XQCIQSJU35nbXj5QI/uqH/bZLtDLgZpLYxIrnBZNGr0CteSqP0E+dh8v+gKUeFtO8VS2XzD+stHimp6znCLYbeawuZeKZO1zmyoOjmQRxB7pTbqWgJQC2AqOIYbo2WwhTAgMBAAECgYAcDYSrz2hJTRsBCg3wlFPDolRc+t8BkB35wNAlfZ4vKoSWE2d+B6vOubwoT2FaPM8ggNTQrAbIcPXXFJCEcD4Gj8n7UXdfT8inlUbhZX+tXbLLyJ6xCdTV+DaztRp8UDb9FwADCeI+NHAaRnUOwIbUP0065lWapZr6/0VBIkqkKQJBAPU2Rc9LR72tX8g+s0kAfskaymt9bosa8aSelgIAPsrtsarRuH3kxOIC3vNiLPX8JxVXITlmqmIBAkH91w0gCR0CQQDlrGPtMSoWMns5ZgpTwJ/1dZYSh+1jtaeRvrcE8l3t1oer09GUouXd7YO1x3v+GyTGo6BaG3ZNdlqY0N3/fAwvAkB9Cjbg9OdZXq5oAykTQdBlJmcwFt8myg+MvV2LGarreffOPnFQqTaIdaRRAbAho3oelLaZebPKk+8dzZ460Co1AkEAiokooUsVktwSXfYB5rD7C4lI45agB5PsJsp+BPrY03yiy29yLJDxzKLW3pAcZ5Mh5LuQHqoP+vuX/DOIpPHDOwJADSKGwMW9DhmOwa1zCSQyKF2X9/KIyS5GAKwoKvjVgmSpFi2bUJl2JeL+Xr395E/sXj8DCJOUws13iOyNS7y2Yw=="); //for local testing
			///*prod*/Certificate.putCertificate("gdata", "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMMrw37SUqnovRKlUbtoJbJqhOUTpBOh4iDyak3cRySXbe+OkBIj5iJnJGkem6ZkZPfuP1EHIK9KnZ37zAqKBBFiRjlqBrL/I19651j2deCLJHWLtKo2NT9nxhbzJcraaiOwHeRSJfyy0iAWNteEx8y0NoqS8nD6tqHLVHs84ZoDAgMBAAECgYATbbvcWBuahmyr+oEUYt290RTBqR7gRXE5Tmh+r7r1TY3IFy0IYUJ7kkDxpfZgrgXPGIx9CHScfpUKMRKyyeJ1nA0JKuPj35aeC9h4K/L29ZMjWv4YhOYtWJqzVMdggFTR+9a0qOEt6nG4I7J88zNj9d2NF7JbQTfmA5gBZEULQQJBAOJ8SaOg7xSOdoL6YwG7IZ7jiwMRJ1IwtriHuyuR2vkwsByrc54XVX1OkNJ7vO2h2QJe90Z4z9B1Okc5XniBR9sCQQDcms0XCGxajXgEspJFSIXExktzZG7W4mK/kMO081Fc2y9uEFJFA97Jil1LyiWC4x7PIlQm+8ol5NQmywZPqQL5AkB91mqf5KIRvNnyCiaKZqvWeTzalmRPkUtkmd2P5rlQrRGczFIv620Pc2CZ4jBlpABMBfHEEQn+G7QmsKRhtgrzAkEAutpLmJTD4jctTx1Jk3GIDFHBGq4Yy93YslWVzrDBW5szo1Ajvt02WRLfTxCbkS96qwtyp8phQJN/tpQEdni7UQJBAMmmGamtl8i0SHKJHPvLA6bVo1nN9U4S7P2RZpoDdGZBeefiTU3EPnBA73z9P4hwkFcf0lozKeBJHc8vkklTgs8="); //for local testing
			//cert = certStore.getCertificate("gdata");
		  } catch (Exception x) { }
		}
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
