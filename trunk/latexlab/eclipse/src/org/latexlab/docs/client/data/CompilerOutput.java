package org.latexlab.docs.client.data;

import java.io.Serializable;

public class CompilerOutput implements Serializable {

	private static final long serialVersionUID = -7916518133469749551L;

	private String status;
	private String[] files, logs;
	
	public CompilerOutput() { }
	public CompilerOutput(String status, String[] files, String[] logs) {
		this.status = status;
		this.files = files;
		this.logs = logs;
	}

	public String getStatus() {
		return status;
	}

	public String[] getFiles() {
		return files;
	}

	public String[] getLogs() {
		return logs;
	}
	
}
