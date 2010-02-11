package org.latexlab.docs.client.data;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("rpc")
public interface LatexLabService extends RemoteService {

	String compile(String name, String contents, String compiler, String format);
	
	String getCurrentUser();
}
