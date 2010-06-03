package org.latexlab.docs.server.auth;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import com.google.appengine.repackaged.com.google.common.util.Base64DecoderException;

public class Cryptoid {

  private static SecretKey key;
  private Cipher ecipher;
  private Cipher dcipher;

  public Cryptoid() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
    ecipher = Cipher.getInstance("DES");
    dcipher = Cipher.getInstance("DES");
    ecipher.init(Cipher.ENCRYPT_MODE, getKey());
    dcipher.init(Cipher.DECRYPT_MODE, getKey());
  }

  public String encrypt(String str) throws IllegalBlockSizeException,
      BadPaddingException, UnsupportedEncodingException {
    byte[] utf8 = str.getBytes("UTF8");
    byte[] enc = ecipher.doFinal(utf8);
    return com.google.appengine.repackaged.com.google.common.util.Base64.encode(enc);
  }

  public String decrypt(String str) throws Base64DecoderException,
      IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
    byte[] dec = com.google.appengine.repackaged.com.google.common.util.Base64.decode(str);
    byte[] utf8 = dcipher.doFinal(dec);
    return new String(utf8, "UTF8");
  }
  
  public String hash(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
	MessageDigest digest = MessageDigest.getInstance("SHA-1");
	byte[] input = digest.digest(str.getBytes("UTF-8"));
	digest.reset();
	byte[] enc = digest.digest(input);
	return com.google.appengine.repackaged.com.google.common.util.Base64.encode(enc);
  }

  private static SecretKey getKey() throws NoSuchAlgorithmException {
    if (key == null) {
      key = KeyGenerator.getInstance("DES").generateKey();
    }
    return key;
  }

}

