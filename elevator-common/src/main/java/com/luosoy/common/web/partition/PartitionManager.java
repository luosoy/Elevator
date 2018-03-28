/**
 * 
 */
package com.luosoy.common.web.partition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PartitionManager {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PartitionManager.class);

    /**
     * The Constant instanceThreadLocal.
     */
    private static final ThreadLocal<PartitionManager> instanceThreadLocal = new ThreadLocal<PartitionManager>();

    /**
     * The prepared.
     */
    private boolean prepared = false;

    /**
     * The partition id service.
     */
    private PartitionIdService partitionIdService;

    /**
     * Expected to be called only by a servlet aspect at the start and end of
     * each request.
     * 
     * @param partitionManager
     *            the new current instance
     */
    public static void setCurrentInstance(PartitionManager partitionManager) {
        if (partitionManager == null) {
            instanceThreadLocal.remove();
            return;
        }

        synchronized (partitionManager) {
            if (!partitionManager.prepared) {
                partitionManager.prepare();
            }
        }

        instanceThreadLocal.set(partitionManager);
    }

    /**
     * Return an object that implements the non-static methods of this abstract
     * class in a manner appropriate for whatever UI framework is handling the
     * current request.
     * 
     * @return the current instance
     */
    public static PartitionManager getCurrentInstance() {
        return (PartitionManager) instanceThreadLocal.get();
    }

    /**
     * This method must be called before any call to any method on this class
     * other than setters. Multiple calls to this method are safe; all except
     * the first one will be ignored. The setCurrentInstance method calls this
     * method automatically.
     */
    public void prepare() {
        if (partitionIdService == null) {
            partitionIdService = new PartitionIdService();
        }
        synchronized (this) {
            prepared = true;
        }
    }

    /**
     * This method should be invoked at the start of each JSF request cycle.
     * 
     */
    public void beginRequest() {
        LOGGER.debug("Beginning request");
        PartitionManager.setCurrentInstance(this);
    }

    /**
     * This method should be invoked at the end of each JSF request cycle.
     * 
     */
    public void endRequest() {
        LOGGER.debug("Ending request");
        PartitionManager.setCurrentInstance(null);
    }

    /**
     * Gets the partition id service.
     * 
     * @return the partition id service
     */
    public PartitionIdService getPartitionIdService() {
        return partitionIdService;
    }
}
