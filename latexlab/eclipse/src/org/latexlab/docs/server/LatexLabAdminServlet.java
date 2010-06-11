package org.latexlab.docs.server;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.latexlab.docs.server.auth.AuthenticationToken;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * A servlet exposing some admin functions.
 */
public class LatexLabAdminServlet extends HttpServlet {
	
  private static final long serialVersionUID = 6668585697634422514L;
  private static final Logger log = Logger.getLogger(LatexLabEditorServlet.class.getName());

  /**
   * Allow AppEngine admins to flush tokens.
   * @TODO: make this alot better.
   */
  @SuppressWarnings("unchecked")
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
       throws IOException {
	UserService userService = UserServiceFactory.getUserService();
    if (userService.getCurrentUser() != null) {
      if (userService.isUserAdmin()) {
    	String user = userService.getCurrentUser().getEmail();
    	if (req.getParameter("action") != null) {
    	  String action = req.getParameter("action");
    	  if (action.equalsIgnoreCase("flushTokens")) {
    	    PersistenceManager pm = PMF.get().getPersistenceManager();
    	    try {
    	      Calendar cal = Calendar.getInstance();
    	      cal.add(Calendar.HOUR, -6);
    	      Query query = pm.newQuery(AuthenticationToken.class);
    	      query.setFilter("activity < activityParam");
    	      query.setRange(0, 1000);
    	      query.declareImports("import java.util.Date");
    	      query.declareParameters("Date activityParam");
	          List<AuthenticationToken> objs = (List<AuthenticationToken>) query.execute(cal.getTime());
              long c = objs.size();
              for (AuthenticationToken obj : objs) {
            	pm.deletePersistent(obj);
              }
              String msg = user + " - Flushed " + c + " tokens.";
      	      log.log(Level.INFO, msg);
              resp.getWriter().write(msg);
    	    } finally {
    	      pm.close();
    	    }
    	  } else if (action.equalsIgnoreCase("countTokens")) {
    	    PersistenceManager pm = PMF.get().getPersistenceManager();
    	    try {
    	      Query query = pm.newQuery(AuthenticationToken.class);
              List<AuthenticationToken> objs = (List<AuthenticationToken>) query.execute();
              int c = objs.size();
              String msg = user + " - There are " + c + " tokens.";
      	      log.log(Level.INFO, msg);
              resp.getWriter().write(msg);
    	    } finally {
    	      pm.close();
    	    }
	  	  } else if (action.equalsIgnoreCase("countFlushTokens")) {
	  	    PersistenceManager pm = PMF.get().getPersistenceManager();
	  	    try {
	  	      Calendar cal = Calendar.getInstance();
	  	      cal.add(Calendar.HOUR, -6);
    	      Query query = pm.newQuery(AuthenticationToken.class);
    	      query.setFilter("activity < activityParam");
    	      query.declareImports("import java.util.Date");
    	      query.declareParameters("Date activityParam");
	          List<AuthenticationToken> objs = (List<AuthenticationToken>) query.execute(cal.getTime());
	          int c = objs.size();
	          String msg = user + " - There are " + c + " tokens to flush.";
	    	  log.log(Level.INFO, msg);
	          resp.getWriter().write(msg);
	  	    } finally {
    	      pm.close();
	  	    }
	  	  }
    	}
      }
    }
  }
  
}

