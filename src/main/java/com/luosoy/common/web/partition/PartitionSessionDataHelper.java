/**
 * 
 */
package com.luosoy.common.web.partition;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


public final class PartitionSessionDataHelper {

    /**
     * Instantiates a new partition session data helper.
     */
    private PartitionSessionDataHelper() {
    }

    /**
     * Gets the session data.
     * 
     * @param key
     *            the key
     * @return the session data
     */
    @SuppressWarnings("unchecked")
    public static Object getSessionData(String key) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        long partitionId = PartitionManager.getCurrentInstance().getPartitionIdService()
                .getPartitionId(attr.getRequest());
        Map<String, Object> attValue = (Map<String, Object>) session.getAttribute("partition-" + partitionId);
        if (attValue == null) {
            return null;
        } else {
            return attValue.get(key);
        }
    }

    /**
     * Sets the session data.
     * 
     * @param key
     *            the key
     * @param value
     *            the value
     */
    @SuppressWarnings("unchecked")
    public static void setSessionData(String key, Object value) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        long partitionId = PartitionManager.getCurrentInstance().getPartitionIdService()
                .getPartitionId(attr.getRequest());
        Map<String, Object> attValue = (Map<String, Object>) session.getAttribute("partition-" + partitionId);
        if (attValue == null) {
            attValue = new HashMap<String, Object>();
        }
        attValue.put(key, value);
        if (attValue != null) {
            session.setAttribute("partition-" + partitionId, attValue);
        }
    }

    /**
     * Sets the global session data.
     * 
     * @param key
     *            the key
     * @param value
     *            the value
     */
    public static void setGlobalSessionData(String key, Object value) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        if (value != null) {
            session.setAttribute(key, value);
        }
    }

    /**
     * Gets the global session data.
     * 
     * @param key
     *            the key
     * @return the global session data
     */
    public static Object getGlobalSessionData(String key) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        return session.getAttribute(key);
    }

    /**
     * Clear session data.
     */
    public static void clearSessionData() {

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        long partitionId = PartitionManager.getCurrentInstance().getPartitionIdService()
                .getPartitionId(attr.getRequest());
        session.setAttribute("partition-" + partitionId, new HashMap<String, Object>());
    }

    /**
     * Removes the session attribute.
     * 
     * @param key
     *            the key
     */
    @SuppressWarnings("unchecked")
    public static void removeSessionAttribute(String key) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        long partitionId = PartitionManager.getCurrentInstance().getPartitionIdService()
                .getPartitionId(attr.getRequest());
        Map<String, Object> attValue = (Map<String, Object>) session.getAttribute("partition-" + partitionId);
        if (attValue == null || !attValue.containsKey(key)) {
            session.removeAttribute(key);
        } else {
            attValue.remove(key);
            session.setAttribute("partition-" + partitionId, attValue);
        }
    }
}
