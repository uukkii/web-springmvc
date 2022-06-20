package ru.netology.initializer;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;

public class ApplicationInitializer implements WebApplicationInitializer {

    private static final String PACKAGE = "ru.netology";
    private static final String SERV = "app";
    private static final String MAPPING_START = "/";

    @Override
    public void onStartup(ServletContext servletContext) {
        final var context = new AnnotationConfigWebApplicationContext();
        context.scan(PACKAGE);
        context.refresh();

        final var servlet = new DispatcherServlet(context);
        final var registration = servletContext.addServlet(SERV, servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping(MAPPING_START);
    }
}
