package org.latexlab.docs.server.auth;

/**
 * Exposes methods to retrieve certificates from the data store.
 */
public class CertificateStore {

  /**
   * Retrieves a certificate from the data store, by purpose.
   * 
   * @param purpose the certificate's purpose
   * @return the certificate associated with the specified purpose
   */
  public Certificate getCertificate(String purpose) {
    return Certificate.getCertificate(purpose);
  }

}
