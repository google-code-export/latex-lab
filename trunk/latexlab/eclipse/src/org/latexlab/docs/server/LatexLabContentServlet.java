package org.latexlab.docs.server;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.repackaged.com.google.common.util.Base64DecoderException;
import com.google.gwt.gdata.ext.server.documents.DocumentsServiceProxyImpl;

/**
 * Servlet exposing document content retrieval for LaTeX processing.
 */
public class LatexLabContentServlet extends HttpServlet {
  
  private static final long serialVersionUID = -8219215936905834146L;
  
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws IOException {
    try {
      DesEncrypter encrypter = new DesEncrypter();
      String q = req.getQueryString();
      if (q != null && q.startsWith("q=")) {
    	q = q.substring(2);
        q = encrypter.decrypt(q);
        String[] pars = q.split(",");
        if (pars.length == 3) {
    	  String token = pars[0];
    	  String docid = pars[1];
    	  long time = Long.parseLong(pars[2]);
    	  long elapsed = new Date().getTime() - time;
    	  if (elapsed < 1000 * 60 * 5) {
    	    DocumentsServiceProxyImpl svc = new DocumentsServiceProxyImpl();
    	    String contents = svc.getDocumentContents("LaTeX-Lab", token, docid);
            resp.setContentType("text/plain");
            resp.getWriter().write(contents);
    	  } else {
    	    throw new Exception("The content url has expired.");
    	  }
        } else {
          throw new Exception("Invalid request.");
        }
      } else {
    	throw new Exception("Invalid request.");
      }
    } catch (Exception e) {
      e.printStackTrace();
      resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }
  }
	
  public static String getDocumentContentsUrl(String token, String documentId) {
	try {
      DesEncrypter encrypter = new DesEncrypter();
	  String q = token + "," + documentId + "," + new Date().getTime();
      q = encrypter.encrypt(q);
      return "/fetch?q=" + URLEncoder.encode(q, "UTF-8");
	} catch(Exception x) {
	  x.printStackTrace();
	}
	return null;
  }
  
  private static class DesEncrypter {
	  
	private static SecretKey key;
    private Cipher ecipher;
    private Cipher dcipher;

    public DesEncrypter() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
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
    
    private static SecretKey getKey() throws NoSuchAlgorithmException {
      if (key == null) {
        key = KeyGenerator.getInstance("DES").generateKey();
      }
      return key;
    }
    
  }

}

