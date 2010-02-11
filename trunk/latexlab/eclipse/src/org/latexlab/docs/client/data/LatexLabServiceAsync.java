package org.latexlab.docs.client.data;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LatexLabServiceAsync {

	void compile(String name, String contents, String compiler, String format, AsyncCallback<CompilerOutput> callback);
	
	void getCurrentUser(AsyncCallback<String> callback);
}
