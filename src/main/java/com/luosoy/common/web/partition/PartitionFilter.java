package com.luosoy.common.web.partition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;


public class PartitionFilter implements Filter {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PartitionFilter.class);

    /**
     * The url matcher.
     */
    private UrlMatcher urlMatcher;

    /* (non-Javadoc)
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        urlMatcher = new UrlMatcher(filterConfig);
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    public void doFilter(ServletRequest req, ServletResponse rsp, FilterChain filterChain) throws IOException,
            ServletException {

        if (!urlMatcher.accept(req)) {
            filterChain.doFilter(req, rsp);
            return;
        }
        PartitionManager manager = new PartitionManager();
        LOGGER.debug("Partition doFilter");
        try {
            manager.beginRequest();
            filterChain.doFilter(req, rsp);
        } finally {
            manager.endRequest();
        }
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#destroy()
     */
    public void destroy() {
        return;
    }
}
