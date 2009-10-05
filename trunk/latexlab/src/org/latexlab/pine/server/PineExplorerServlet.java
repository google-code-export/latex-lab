package org.latexlab.pine.server;

import org.latexlab.pine.server.auth.Authentication;

import java.io.FileReader;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The servlet corresponding to the document explorer.
 */
@SuppressWarnings("serial")
public class PineExplorerServlet extends HttpServlet {
  
  /**
   * Handles a GET request. Before writing out the html page, check for authentication
   * in passive mode. Passive mode means that, when unauthenticated, the user
   * will not be automatically redirected to the login or access control page and 
   * instead a splash page will be rendered which will allow the user to initiate
   * authentication.
   * In short, if the user has started authentication, continue with authentication,
   * otherwise display the splash page.
   */
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws IOException {
    Authentication.autoPilot(req, resp, true);
    if (Authentication.isAuthenticated()) {
      outputFile(resp, "pine-explorer.html");
    } else {
      outputFile(resp, "pine-splash.html");
    }
  }
  
  /**
   * Handles a POST request. All POST requests initiate authentication which redirects
   * the user first to the login page and then to the access control page as needed.
   */
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
          throws IOException {
    Authentication.autoPilot(req, resp, false);
    doGet(req, resp);
  }
  
  /**
   * Writes out a file to the client.
   * 
   * @param resp the HTTP response object
   * @param file the file which to write
   * @throws IOException if the file cannot be read or written
   */
  private void outputFile(HttpServletResponse resp, String file) throws IOException {
    resp.setContentType("text/html");
    char[] buffer = new char[1028];
    FileReader reader = new FileReader(file);
    int i = 0;
    while ((i = reader.read(buffer, 0, 1028)) > 0) {
      resp.getWriter().write(buffer, 0, i);
    }
  }
}
