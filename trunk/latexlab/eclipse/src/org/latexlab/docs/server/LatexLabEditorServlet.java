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
public class LatexLabEditorServlet extends HttpServlet {
  
  private static final long serialVersionUID = -4184093752854675998L;

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

