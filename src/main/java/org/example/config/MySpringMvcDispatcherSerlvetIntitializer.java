package org.example.config;
import org.springframework.web.filter.HiddenHttpMethodFilter; /* это фильтр, который позволяет обрабатывать
HTTP-запросы с методами, отличными от стандартных GET и POST, через добавление специальных параметров в запрос. */

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer; /*
это абстрактный класс, предоставляющий удобный способ настройки DispatcherServlet на основе Java-конфигураций. */

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/* нужные зависимости: "spring-web", "spring-webmvc", "javax.servlet-api". */

// этот класс нужен для инициализации и настройки DispatcherServlet в Spring MVC.

public class MySpringMvcDispatcherSerlvetIntitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    /* Этот метод указывает на классы конфигурации, связанные с конфигурацией Spring MVC. Здесь SpringConfig.class
    предполагается быть классом, содержащим настройки для Spring MVC.
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringConfig.class};
    }
    /* Этот метод определяет URL-шаблоны, которые будут обрабатываться DispatcherServlet.
     В данном случае, все запросы будут направляться на DispatcherServlet по корневому URL ("/").
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
    /* метод onStartup вызывает метод registerHiddenFieldFilter в контексте приложения,
     чтобы зарегистрировать фильтр и обеспечить поддержку нестандартных HTTP-методов.
     */
    @Override
    public void onStartup(ServletContext aServletContext) throws ServletException {
        super.onStartup(aServletContext);
        registerHiddenFieldFilter(aServletContext);
    }
    /*Этот метод регистрирует фильтр HiddenHttpMethodFilter в контексте сервлета. Фильтр будет применяться
     ко всем URL-шаблонам ("/*") и позволит обрабатывать скрытые параметры для HTTP-методов.
     обрабатывать запросы с использованием методов, таких как PUT, DELETE, PATCH и т.д.,
     */
    private void registerHiddenFieldFilter(ServletContext aContext) {
        aContext.setRequestCharacterEncoding("UTF-8");
        aContext.setResponseCharacterEncoding("UTF-8");
        aContext.addFilter("hiddenHttpMethodFilter",
                new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null ,true, "/*");
    }
}