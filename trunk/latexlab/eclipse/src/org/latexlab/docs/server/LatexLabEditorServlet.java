package org.latexlab.docs.server;

import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.latexlab.docs.server.auth.AuthenticationKey;
import org.latexlab.docs.server.auth.AuthenticationManager;
import org.latexlab.docs.server.auth.AuthenticationTokenStore;

/**
 * The servlet corresponding to the document editor.
 */
public class LatexLabEditorServlet extends HttpServlet {
  
  private static final long serialVersionUID = -4184093752854675998L;
  private static final Logger log = Logger.getLogger(LatexLabEditorServlet.class.getName());

  /**
   * On GET, check and require authentication. If authenticated, write editor page.
   */
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
       throws IOException {
	try {
	  AuthenticationManager authManager = new AuthenticationManager(
	      new AuthenticationTokenStore(), AuthenticationKey.getAuthSubKey());
	  authManager.autoPilot(req, resp, false);
	} catch(Exception x) {
	  log.log(Level.SEVERE, "Auto-pilot authentication error: "  + x.getMessage(), x);
	}
    resp.setContentType("text/html");
    char[] buffer = new char[1028];
    FileReader reader = new FileReader("editor.html");
    int i = 0;
    while ((i = reader.read(buffer, 0, 1028)) > 0) {
      resp.getWriter().write(buffer, 0, i);
    }
  }
  
}

