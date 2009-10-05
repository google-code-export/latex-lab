package org.latexlab.pine.server.auth;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gdata.client.http.AuthSubUtil;
import com.google.gdata.util.AuthenticationException;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import org.latexlab.pine.server.GDataServiceImpl;
import org.latexlab.pine.server.models.AuthenticationToken;

/**
 * Encapsulates AuthSub Authentication routines.
 */
@SuppressWarnings("serial")
public class Authentication extends HttpServlet {
  
  /**
   * Determines whether the current user is authenticated.
   * The current user is authenticated if:
   * 1. The user has logged in via the Google User service.
   * 2. The user has a valid AuthSub token in the data store.
   */
  public static boolean isAuthenticated() {
    UserService userService = UserServiceFactory.getUserService();
    if (userService.getCurrentUser() != null) {
      String userEmail = userService.getCurrentUser().getEmail();
      AuthenticationToken authToken = AuthenticationToken.getUserToken(userEmail);
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
   */
  public static AuthenticationToken autoPilot(HttpServletRequest req, HttpServletResponse resp, boolean passive)
          throws IOException {
    UserService userService = UserServiceFactory.getUserService();
    if (userService.getCurrentUser() != null) {
      String userEmail = userService.getCurrentUser().getEmail();
      AuthenticationToken authToken = AuthenticationToken.getUserToken(userEmail);
      if (authToken != null) {
        return authToken;
      } else {
        String token = null, qs = req.getQueryString();
        if (qs != null) {
          token = AuthSubUtil.getTokenFromReply(qs);
        }
        if (token != null && !token.equals("")) {
          try {
            token = AuthSubUtil.exchangeForSessionToken(token, null);
            AuthenticationToken.storeUserToken(req.getUserPrincipal().getName(), token);
            // remove the token from the url
            resp.sendRedirect(req.getRequestURI());
          } catch (AuthenticationException e) {
            e.printStackTrace();
          } catch (GeneralSecurityException e) {
            e.printStackTrace();
          }
        } else {
          String authUrl = AuthSubUtil.getRequestUrl(
              req.getRequestURL().toString(),
              GDataServiceImpl.DOCS_SCOPE, false, true);
          resp.sendRedirect(authUrl);
        }
      }
    } else {
      if (!passive) {
        String authUrl = userService.createLoginURL(req.getRequestURI());
        resp.sendRedirect(authUrl);
      }
    }
    return null;
  }
}
