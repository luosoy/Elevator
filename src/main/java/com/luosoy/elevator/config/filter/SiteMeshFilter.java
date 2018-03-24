package com.luosoy.elevator.config.filter;

import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns="/web/*",filterName = "sitemesh")
@Component
public class SiteMeshFilter extends ConfigurableSiteMeshFilter {

}
