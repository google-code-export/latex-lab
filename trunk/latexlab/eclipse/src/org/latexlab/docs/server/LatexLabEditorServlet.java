package org.latexlab.docs.server;

import java.io.FileReader;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * The servlet corresponding to the document editor.
 */
@SuppressWarnings("serial")
public class LatexLabEditorServlet extends HttpServlet {
  
  /**
   * Handles a GET request. Before writing out the html page, check for authentication
   * in non-passive mode. Non-passive mode means that, when unauthenticated, the user
   * will be automatically redirected to the login or access control page.
   */
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws IOException {
	UserService userService = UserServiceFactory.getUserService();
	if (userService.getCurrentUser() == null) {
      String authUrl = userService.createLoginURL(req.getRequestURI());
      resp.sendRedirect(authUrl);
    } else {
      resp.setContentType("text/html");
      char[] buffer = new char[1028];
      FileReader reader = new FileReader("editor.html");
      int i = 0;
      while ((i = reader.read(buffer, 0, 1028)) > 0) {
        resp.getWriter().write(buffer, 0, i);
      }
    }

  }
  
}

