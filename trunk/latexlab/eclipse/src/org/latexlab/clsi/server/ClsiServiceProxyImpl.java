package org.latexlab.clsi.server;

import org.latex.clsi.ClsiLinkedInputResource;
import org.latex.clsi.ClsiLiteralInputResource;
import org.latex.clsi.ClsiOptions;
import org.latex.clsi.ClsiServiceCompileRequest;
import org.latexlab.clsi.client.ClsiServiceProxy;
import org.latexlab.clsi.client.ResourceReference;
import org.latexlab.docs.server.LatexLabContentServlet;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ClsiServiceProxyImpl extends RemoteServiceServlet implements ClsiServiceProxy {
	
	@Override
	public Boolean compile(String name, String contents, String serviceUrl, String clsiToken,
			String id, String instance, String compiler, String format) {
		org.latex.clsi.ClsiService svc = new org.latex.clsi.ClsiService(
				serviceUrl, clsiToken);
		try {
			ClsiServiceCompileRequest request = new ClsiServiceCompileRequest(clsiToken);
			request.setId(id);
			request.setInstance(instance);
			ClsiLiteralInputResource text = new ClsiLiteralInputResource(name, contents);
			request.getResources().add(text, true);
			ClsiOptions options = new ClsiOptions();
			options.setCompiler(compiler);
			options.setOutputFormat(format);
			request.setOptions(options);
			svc.compileAsync(request);
		} catch (Exception x) {
			x.printStackTrace();
		}
		return true;
	}
	
	@Override
	public Boolean compile(String name, String contents, String authsubToken, String serviceUrl, String clsiToken,
			String id, String instance, ResourceReference[] resources, String compiler, String format) {
		org.latex.clsi.ClsiService svc = new org.latex.clsi.ClsiService(
				serviceUrl, clsiToken);
		try {
			ClsiServiceCompileRequest request = new ClsiServiceCompileRequest(clsiToken);
			request.setId(id);
			request.setInstance(instance);
			ClsiLiteralInputResource text = new ClsiLiteralInputResource(name, contents);
			request.getResources().add(text, true);
			for (ResourceReference doc : resources) {
				String root = this.getThreadLocalRequest().getRequestURL().toString();
				int a = root.indexOf("/", "https://".length());
				if (a > 0) root = root.substring(0, a);
				if (root.contains("localhost")) {
					root = root.replace("localhost", "3.1.4.20");
				}
				String url = root + LatexLabContentServlet.getDocumentContentsUrl(
						authsubToken, doc.getDocumentId());
				ClsiLinkedInputResource link = new ClsiLinkedInputResource(
						doc.getName(), url, doc.getModified());
				request.getResources().add(link);
			}
			ClsiOptions options = new ClsiOptions();
			options.setCompiler(compiler);
			options.setOutputFormat(format);
			request.setOptions(options);
			svc.compileAsync(request);
		} catch (Exception x) {
			x.printStackTrace();
		}
		return true;
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
