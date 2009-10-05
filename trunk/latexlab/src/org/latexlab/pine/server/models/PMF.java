package org.latexlab.pine.server.models;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 * Exposes a common Persistence Manager Factory for data store
 * manipulation.
 */
public final class PMF {
    private static final PersistenceManagerFactory pmfInstance =
        JDOHelper.getPersistenceManagerFactory("transactions-optional");

    private PMF() {}

    /**
     * Retrieves the common instance of the Persistence Manager Factory.
     * @return
     */
    public static PersistenceManagerFactory get() {
        return pmfInstance;
    }
}