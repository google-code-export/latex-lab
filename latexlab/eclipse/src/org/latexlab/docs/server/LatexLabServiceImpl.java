package org.latexlab.docs.server;

import org.latexlab.docs.client.LatexLabService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * An RPC service for LaTeX Lab specific functions.
 * @TODO: add methods for saving user settings
 */
public class LatexLabServiceImpl extends RemoteServiceServlet implements LatexLabService {

	private static final long serialVersionUID = 7246863083594200339L;
	
	public LatexLabServiceImpl() {
	}
	
}
