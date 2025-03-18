package ru.Akctucb.springmvc;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ru.Akctucb.springmvc.config.DatabaseConfig;
import ru.Akctucb.springmvc.config.WebConfig;
import ru.Akctucb.springmvc.model.User;
import ru.Akctucb.springmvc.service.UserService;

/**
 * TomCat я в итоге в ручную запускаю.
 */

import java.io.File;

public class AppMain {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        File webContentFolder = new File("src/main/webapp/");
        Context context = tomcat.addWebapp("", webContentFolder.getAbsolutePath());

        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
        webContext.register(DatabaseConfig.class, WebConfig.class);
        webContext.setServletContext(context.getServletContext());
        webContext.refresh();

        UserService userService = webContext.getBean(UserService.class);
        userService.addUser(new User("Иван Иванов", "ivan@example.com"));
        userService.addUser(new User("Пётр Петров", "petr@example.com"));

        DispatcherServlet dispatcherServlet = new DispatcherServlet(webContext);

        Tomcat.addServlet(context, "dispatcherServlet", dispatcherServlet);
        context.addServletMappingDecoded("/", "dispatcherServlet");

        tomcat.start();
        System.out.println(">>> Tomcat started on http://localhost:8080");
        tomcat.getServer().await();
    }
}


