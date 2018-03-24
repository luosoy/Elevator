package com.luosoy.common.web.partition;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


public class PartitionIdService {

    /**
     * The Constant PARTITION_ID_NAME.
     */
    public static final String PARTITION_ID_NAME = "partitionId";

    /**
     * The id.
     */
    private static long id = 1;

    /**
     * The partition id.
     */
    private Long partitionId = null;

    /**
     * Instantiates a new partition id service.
     */
    public PartitionIdService() {

    }

    /**
     * Next partition id.
     * 
     * @return the long
     */
    public static synchronized long nextPartitionId() {
        return ++id;
    }

    /**
     * Will return the current partition context id or create a new one if a new one is needed.
     * 
     * @param request
     *            the request
     * @return the current partition context id
     */
    public long getPartitionId(HttpServletRequest request) {
        if (partitionId == null) {
            @SuppressWarnings("rawtypes")
            Map map = request.getParameterMap();
            if (map.containsKey(PARTITION_ID_NAME)) {
                partitionId = Long.valueOf(request.getParameter(PARTITION_ID_NAME).toString());
            }
            if (partitionId == null) {
                synchronized (this) {
                    partitionId = ++id;
                }
            }
        }
        return partitionId;
    }

    /**
     * Gets the current partition id.
     * 
     * @return the current partition id
     */
    public long getCurrentPartitionId() {
        return partitionId;
    }
}
