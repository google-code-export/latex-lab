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
	    String key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMMrw37SUqnovRKl\nUbtoJbJqhOUTpBOh4iDyak3cRySXbe+OkBIj5iJnJGkem6ZkZPfuP1EHIK9KnZ37\nzAqKBBFiRjlqBrL/I19651j2deCLJHWLtKo2NT9nxhbzJcraaiOwHeRSJfyy0iAW\nNteEx8y0NoqS8nD6tqHLVHs84ZoDAgMBAAECgYATbbvcWBuahmyr+oEUYt290RTB\nqR7gRXE5Tmh+r7r1TY3IFy0IYUJ7kkDxpfZgrgXPGIx9CHScfpUKMRKyyeJ1nA0J\nKuPj35aeC9h4K/L29ZMjWv4YhOYtWJqzVMdggFTR+9a0qOEt6nG4I7J88zNj9d2N\nF7JbQTfmA5gBZEULQQJBAOJ8SaOg7xSOdoL6YwG7IZ7jiwMRJ1IwtriHuyuR2vkw\nsByrc54XVX1OkNJ7vO2h2QJe90Z4z9B1Okc5XniBR9sCQQDcms0XCGxajXgEspJF\nSIXExktzZG7W4mK/kMO081Fc2y9uEFJFA97Jil1LyiWC4x7PIlQm+8ol5NQmywZP\nqQL5AkB91mqf5KIRvNnyCiaKZqvWeTzalmRPkUtkmd2P5rlQrRGczFIv620Pc2CZ\n4jBlpABMBfHEEQn+G7QmsKRhtgrzAkEAutpLmJTD4jctTx1Jk3GIDFHBGq4Yy93Y\nslWVzrDBW5szo1Ajvt02WRLfTxCbkS96qwtyp8phQJN/tpQEdni7UQJBAMmmGamt\nl8i0SHKJHPvLA6bVo1nN9U4S7P2RZpoDdGZBeefiTU3EPnBA73z9P4hwkFcf0loz\nKeBJHc8vkklTgs8=";
        byte[] encodedKey = key.getBytes();
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
