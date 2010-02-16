package org.latexlab.clsi.client;



import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ClsiServiceProxyAsync {

	void compile(String name, String contents, String serviceUrl, String clsiToken, String id,
		String instance, String compiler, String format, AsyncCallback<Boolean> callback);
	
	void compile(String name, String contents, String authsubToken, String serviceUrl, String clsiToken,
		String id, String instance, ResourceReference[] resources, String compiler,
		String format, AsyncCallback<Boolean> callback);
	
	void getCurrentUser(AsyncCallback<String> callback);
}
