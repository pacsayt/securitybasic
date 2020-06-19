package spring.boot.securitybasic.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * https://spring.io/guides/gs/securing-web/
 * The web application is based on Spring MVC.
 * As a result, you need to configure Spring MVC and set up view controllers to expose these templates.
 */

@Configuration
public class MvcConfig implements WebMvcConfigurer
{
  public void addViewControllers(ViewControllerRegistry registry)
  {
    registry.addViewController("/home").setViewName("home");
    registry.addViewController("/").setViewName("home");
    registry.addViewController("/hello").setViewName("hello");
    registry.addViewController("/login").setViewName("login");
  }
}