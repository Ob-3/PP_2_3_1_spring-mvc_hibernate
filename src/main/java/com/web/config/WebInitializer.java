package com.web.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;

public class WebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class, WebConfig.class);
        context.setServletContext(servletContext);

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}


//package com.web.config;
//
//import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//
//public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
//
//    @Override
//    protected Class<?>[] getRootConfigClasses() {
//        return new Class[]{AppConfig.class};
//    }
//
//    @Override
//    protected Class<?>[] getServletConfigClasses() {
//        return new Class[]{WebConfig.class};
//    }
//
//    @Override
//    protected String[] getServletMappings() {
//        return new String[]{"/"};
//    }
//}


//
//package com.web.config;
//
//import org.springframework.web.WebApplicationInitializer;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.servlet.DispatcherServlet;
//import jakarta.servlet.ServletContext;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRegistration;
//
//public class WebInitializer implements WebApplicationInitializer {
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.register(AppConfig.class, WebConfig.class);
//        context.setServletContext(servletContext);
//
//        // Создаем DispatcherServlet и регистрируем его в контейнере
//        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
//        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", dispatcherServlet);
//
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping("/");
//    }
//}
//
