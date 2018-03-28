/**
 * 
 */
package com.luosoy.common.web.partition;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;


public class PartitionParameterProvider {

    /**
     * The Constant PAGE_LINK_SEP.
     */
    private static final String PAGE_LINK_SEP = "#";

    /**
     * The Constant SCHEMES_TO_EXCLUDE.
     */
    private static final Set<String> SCHEMES_TO_EXCLUDE = new HashSet<String>();

    /**
     * The Constant PAGE_PARAMETER_SEP.
     */
    private static final String PAGE_PARAMETER_SEP = "?";

    /**
     * The Constant PARAMETER_SEP.
     */
    private static final String PARAMETER_SEP = "&";

    static {
        SCHEMES_TO_EXCLUDE.add("javascript");
        SCHEMES_TO_EXCLUDE.add("ftp");
        SCHEMES_TO_EXCLUDE.add("mailto");
    }

    /**
     * Instantiates a new partition parameter provider.
     */
    private PartitionParameterProvider() {
    }

    /**
     * Encode and attach partition id.
     * 
     * @param url
     *            the url
     * @param request
     *            the request
     * @return the string
     */
    public static String encodeAndAttachPartitionId(String url, HttpServletRequest request) {
        if (url == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String firstSeparator = url.indexOf(PAGE_PARAMETER_SEP) == -1 ? PAGE_PARAMETER_SEP : PARAMETER_SEP;
        int pageLinkPos = url.indexOf(PAGE_LINK_SEP);
        if (pageLinkPos < 0) {
            sb.append(url);
        } else {
            sb.append(url.substring(0, pageLinkPos));
        }

        if (!url.contains(PartitionIdService.PARTITION_ID_NAME)) {
            sb.append(firstSeparator);
            sb.append(PartitionIdService.PARTITION_ID_NAME);
            sb.append("=");
            sb.append(PartitionManager.getCurrentInstance().getPartitionIdService().getPartitionId(request));

            if (pageLinkPos > -1) {
                sb.append(url.substring(pageLinkPos));
            }
        }
        return sb.toString();
    }

    /**
     * Encode and attach new partition id.
     * 
     * @param url
     *            the url
     * @return the string
     */
    public static String encodeAndAttachNewPartitionId(String url) {
        if (url == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        int nuofParams = -1;
        String firstSeparator = url.indexOf(PAGE_PARAMETER_SEP) == -1 ? PAGE_PARAMETER_SEP : PARAMETER_SEP;
        int pageLinkPos = url.indexOf(PAGE_LINK_SEP);
        if (pageLinkPos < 0) {
            sb.append(url);
        } else {
            sb.append(url.substring(0, pageLinkPos));
        }

        if (!url.contains(PartitionIdService.PARTITION_ID_NAME)) {
            sb.append(nuofParams == 0 ? firstSeparator : PARAMETER_SEP);
            sb.append(PartitionIdService.PARTITION_ID_NAME);
            sb.append("=");
            sb.append(PartitionIdService.nextPartitionId());

            if (pageLinkPos > -1) {
                sb.append(url.substring(pageLinkPos));
            }
        }
        return sb.toString();
    }
}
