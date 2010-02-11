package org.latexlab.docs.server;

import org.latex.clsi.ClsiServiceResponse;
import org.latexlab.docs.client.data.LatexLabService;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class LatexLabServiceImpl extends RemoteServiceServlet implements LatexLabService {

	@Override
	public String compile(String name, String contents, String compiler,
			String format) {
		org.latex.clsi.ClsiService svc = new org.latex.clsi.ClsiService(
				"http://clsi.scribtex.com/clsi/compile", "9cb69fe18fe33b7b3d4b7a26185247ed");
		ClsiServiceResponse resp;
		try {
			resp = svc.compile(name, contents, compiler, format);
			if (resp.getStatus().equalsIgnoreCase("success")) {
				return resp.getOutputFiles().get(0).getUrl();
			}
		} catch (Exception x) {
		}
		return null;
	}
	
	@Override
	public String getCurrentUser() {
      UserService userService = UserServiceFactory.getUserService();
	  if (userService.getCurrentUser() != null) {
	    return userService.getCurrentUser().getEmail();
	  }
	  return null;
	}

}
