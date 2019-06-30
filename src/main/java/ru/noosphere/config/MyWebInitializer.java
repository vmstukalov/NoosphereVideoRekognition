package ru.noosphere.config;

import com.github.ziplet.filter.compression.CompressingFilter;
import org.springframework.mobile.device.DeviceResolverRequestFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;


public class MyWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);

        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
        encodingFilter.setInitParameter("encoding", "UTF-8");
        encodingFilter.setInitParameter("forceEncoding", "true");
        encodingFilter.addMappingForUrlPatterns(null, true, "/*");

        //FilterRegistration.Dynamic multipartFilter = servletContext.addFilter("multipartFilter", MultipartFilter.class);
        //multipartFilter.addMappingForUrlPatterns(null, true, "/*");

        //FilterRegistration.Dynamic httpMethodFilter = servletContext.addFilter("HttpMethodFilter", HiddenHttpMethodFilter.class);
        //httpMethodFilter.addMappingForUrlPatterns(null, true, "/*");

        FilterRegistration.Dynamic securityFilter = servletContext.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class);
        securityFilter.addMappingForUrlPatterns(null, true, "/*");


        //gzip
        FilterRegistration.Dynamic compressingFilter = servletContext.addFilter("compressingFilter", CompressingFilter.class);
        compressingFilter.addMappingForUrlPatterns(null, true, "/*");

        FilterRegistration.Dynamic deviceResolverRequestFilter = servletContext.addFilter("deviceResolverRequestFilter", DeviceResolverRequestFilter.class);
        compressingFilter.addMappingForUrlPatterns(null, true, "/*");


    }

    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { SpringRootConfig.class };
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { App.class };
    }

    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

}