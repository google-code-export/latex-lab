package org.latexlab.docs.server.auth;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.latexlab.docs.server.gdocs.DocumentServiceImpl;

import com.google.gdata.client.http.AuthSubUtil;
import com.google.gdata.util.AuthenticationException;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.repackaged.com.google.common.util.Base64DecoderException;

/**
 * Encapsulates AuthSub Authentication routines.
 */
@SuppressWarnings("serial")
public class AuthenticationManager extends HttpServlet {
  
  private AuthenticationTokenStore store;
  private PrivateKey key;
  
  public AuthenticationManager(AuthenticationTokenStore store, PrivateKey key) {
	this.store = store;
	this.key = key;
  }
  
  /**
   * Determines whether the current user is authenticated.
   * The current user is authenticated if:
   * 1. The user has logged in via the Google User service.
   * 2. The user has a valid AuthSub token in the data store.
   */
  public boolean isAuthenticated() {
    UserService userService = UserServiceFactory.getUserService();
    if (userService.getCurrentUser() != null) {
      String userEmail = userService.getCurrentUser().getEmail();
      AuthenticationToken authToken = store.getUserToken(userEmail);
      if (authToken != null) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Automatically guides the user through authentication, redirecting as appropriate.
   * If the user is not authenticated and does not have a token then the auto-pilot
   * process will span three requests and two redirects:
   * 1. Load and redirect to the Google login page.
   * 2. Load and redirect to the AuthSub access control page.
   * 3. Load and obtain the AuthSub token in the URL querystring.
   * Other scenarios involve only steps 1 or steps 2 and 3 if, respectively, the user
   * is not logged on but an AuthSub token exists in the data store or if the user
   * is logged on but does not have a valid AuthSub token.
   * 
   * @param req the HTTP request object
   * @param resp the HTTP response object
   * @param passive whether to allow redirects
   * @return an authentication token containing an AuthSub token string
   * @throws IOException if a redirect error occurs
   * @throws GeneralSecurityException 
   * @throws AuthenticationException 
   * @throws Base64DecoderException 
   */
  public AuthenticationToken autoPilot(HttpServletRequest req, HttpServletResponse resp, boolean passive)
      throws IOException, AuthenticationException, GeneralSecurityException, Base64DecoderException {
    UserService userService = UserServiceFactory.getUserService();
    if (userService.getCurrentUser() != null) {
      String userEmail = userService.getCurrentUser().getEmail();
      AuthenticationToken authToken = store.getUserToken(userEmail);
      //check token age and clear if token is too old
      if (authToken != null && authToken.isExpired()) {
    	try {
    	  AuthSubUtil.revokeToken(authToken.getToken(), key);
    	} catch (Exception x) { }
    	store.clearUserToken(authToken.getEmail());
    	authToken = null;
      }
      //check token validity to ensure that the token is still valid
      if (authToken != null) {
    	try {
    	  Map<String, String> info = AuthSubUtil.getTokenInfo(authToken.getToken(), key);
    	  if (info == null || info.size() == 0) {
	    	store.clearUserToken(authToken.getEmail());
	    	authToken = null;
    	  }
    	} catch (Exception x) {
	      store.clearUserToken(authToken.getEmail());
	      authToken = null;
    	}
      }
      if (authToken != null) {
    	authToken.setActivity(new Date());
        return authToken;
      } else {
        String token = null, qs = req.getQueryString();
        if (qs != null) {
          token = AuthSubUtil.getTokenFromReply(qs);
        }
        if (token != null && !token.equals("")) {
          token = URLDecoder.decode(token, "UTF-8");
          token = AuthSubUtil.exchangeForSessionToken(token, key);
          store.setUserToken(req.getUserPrincipal().getName(), token);
          resp.sendRedirect(getFullUrl(req));
        } else {
          if (!passive) {
            String authUrl = AuthSubUtil.getRequestUrl(
                getFullUrl(req),
                DocumentServiceImpl.AUTH_SCOPES, true, true);
            resp.sendRedirect(authUrl);
          }
        }
      }
    } else {
      if (!passive) {
        String authUrl = userService.createLoginURL(getFullUrl(req));
        resp.sendRedirect(authUrl);
      }
    }
    return null;
  }
  
  /**
   * Obtains the full URL for the current request, eliminating
   * the token parameter, if it is present. The token removal isn't
   * required but helps protect the user from by minimizing its
   * visibility.
   * 
   * @param req
   * @return
   */
  @SuppressWarnings("unchecked")
  private String getFullUrl(HttpServletRequest req) {
    String url = req.getRequestURL().toString();
    String qs = "";
    Enumeration<String> pars = req.getParameterNames();
    while(pars.hasMoreElements()) {
      String name = pars.nextElement();
      if (!name.equals("token")) {
        String value = req.getParameter(name);
        if (value != null) {
          try {
            qs += name + "=" + URLEncoder.encode(value, "UTF-8") + "&";
          } catch (UnsupportedEncodingException e) {
          }
        }
      }
    }
    if (!qs.equals("")) {
      url += "?" + qs;
    }
    return url;
  }
  
}
