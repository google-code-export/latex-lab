package org.latexlab.docs.server;

import org.latex.clsi.ClsiServiceResponse;
import org.latexlab.docs.client.data.CompilerOutput;
import org.latexlab.docs.client.data.LatexLabService;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class LatexLabServiceImpl extends RemoteServiceServlet implements LatexLabService {

	@Override
	public CompilerOutput compile(String name, String contents, String compiler,
			String format) {
		org.latex.clsi.ClsiService svc = new org.latex.clsi.ClsiService(
				"http://clsi.scribtex.com/clsi/compile", "9cb69fe18fe33b7b3d4b7a26185247ed");
		ClsiServiceResponse resp;
		try {
			resp = svc.compile(name, contents, compiler, format);
			String[] files = new String[resp.getOutputFiles().size()];
			String[] logs = new String[resp.getOutputLogs().size()];
			for (int i=0; i<resp.getOutputFiles().size(); i++) {
				files[i] = resp.getOutputFiles().get(i).getUrl();
			}
			for (int i=0; i<resp.getOutputLogs().size(); i++) {
				logs[i] = resp.getOutputLogs().get(i).getUrl();
			}
			CompilerOutput out = new CompilerOutput(resp.getStatus(), files, logs);
			return out;
		} catch (Exception x) {
			x.printStackTrace();
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
