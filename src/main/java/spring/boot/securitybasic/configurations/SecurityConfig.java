package spring.boot.securitybasic.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import spring.boot.securitybasic.controllers.UserController;

/**
 * Spring Security Form Login
 * https://www.baeldung.com/spring-security-login
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
  private final Logger logger = LoggerFactory.getLogger( SecurityConfig.class);

//  @Bean
  public PasswordEncoder passwordEncoder()
  {
    logger.info( "passwordEncoder()");

    return NoOpPasswordEncoder.getInstance(); //new BCryptPasswordEncoder();
  }

  @Override
  protected void configure( AuthenticationManagerBuilder auth) throws Exception
  {
    logger.info( "configure( AuthenticationManagerBuilder)");

    auth.inMemoryAuthentication()
        .withUser("user1").password( passwordEncoder().encode("xxxxx")).roles("USER")
        .and()
        .withUser("user2").password( passwordEncoder().encode("xxxxx")).roles("USER")
        .and()
        .withUser("admin").password( passwordEncoder().encode("xxxxx")).roles("ADMIN");
  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception
  {
    logger.info( "configure( HttpSecurity)");

    http.authorizeRequests().anyRequest().permitAll();
/*
    http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/admin/**").hasRole("ADMIN")
        .antMatchers("/anonymous*").anonymous()
        .antMatchers("/login*").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/login.html")
        .loginProcessingUrl("/perform_login")
        .defaultSuccessUrl("/homepage.html", true)
        //.failureUrl("/login.html?error=true")
//        .failureHandler(authenticationFailureHandler())
        .and()
        .logout()
        .logoutUrl("/perform_logout")
        .deleteCookies("JSESSIONID");
//        .logoutSuccessHandler(logoutSuccessHandler());
*/
  }
}