package org.latexlab.docs.server.auth;

public class CertificateStore {
	
	public Certificate getCertificate(String purpose) {
	  return Certificate.getCertificate(purpose);
	}

}
