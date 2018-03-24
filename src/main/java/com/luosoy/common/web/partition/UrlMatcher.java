/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.luosoy.common.web.partition;

import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;


public class UrlMatcher {

    /**
     * The exclude prefix.
     */
    private final String excludePrefix;

    /**
     * The exclude suffix.
     */
    private final String excludeSuffix;

    /**
     * Instantiates a new url matcher.
     * 
     * @param config
     *            the config
     */
    public UrlMatcher(FilterConfig config) {
        this.excludePrefix = config.getInitParameter("excludePrefix");
        this.excludeSuffix = config.getInitParameter("excludeSuffix");
    }

    /**
     * Accept.
     * 
     * @param req
     *            the req
     * @return true, if successful
     */
    public boolean accept(ServletRequest req) {// NOSONAR

        if ((excludePrefix == null) && (excludeSuffix == null)) {
            return true;
        }

        if (req instanceof HttpServletRequest == false) {
            return true;
        }

        HttpServletRequest hreq = (HttpServletRequest) req;
        String pathInfo = hreq.getPathInfo();
        String servletPath = hreq.getServletPath();

        if (excludePrefix != null) {
            for (String prefix : excludePrefix.split(",")) {
                if (pathInfo == null && servletPath.startsWith(prefix.trim())) {
                    return false;
                }

                if (pathInfo != null && pathInfo.startsWith(prefix.trim())) {
                    return false;
                }
            }
        }

        if (excludeSuffix != null) {
            for (String suffix : excludeSuffix.split(",")) {
                if (pathInfo == null && servletPath.endsWith(suffix.trim())) {
                    return false;
                }

                if (pathInfo != null && pathInfo.endsWith(suffix.trim())) {
                    return false;
                }
            }
        }

        return true;
    }
}
