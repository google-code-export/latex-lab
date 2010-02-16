package org.latexlab.clsi.client;



import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("clsi-rpc")
public interface ClsiServiceProxy extends RemoteService {

	Boolean compile(String name, String contents, String serviceUrl, String clsiToken, String id,
			String instance, String compiler, String format);
	
	Boolean compile(String name, String contents, String authsubToken, String serviceUrl, String clsiToken,
			String id, String instance, ResourceReference[] resources, String compiler, String format);
	
	String getCurrentUser();
}
