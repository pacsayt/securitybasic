package spring.boot.securitybasic.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

import spring.boot.securitybasic.server.UserService;
import spring.boot.securitybasic.server.UsernameInUrlAuthenticationFailureHandler;
import spring.boot.securitybasic.server.User;

@Controller
public class UserController
{
  private final Logger logger = LoggerFactory.getLogger( UserController.class);

  private UserService userService;

  @GetMapping("/login")
  public ModelAndView login(HttpSession httpSession)
  {
    logger.debug( "login() : httpSession: {}", httpSession);

    String lastUsername = (String) httpSession.getAttribute( UsernameInUrlAuthenticationFailureHandler.LAST_USERNAME_KEY);

    if ( lastUsername != null )
    {
      httpSession.removeAttribute( UsernameInUrlAuthenticationFailureHandler.LAST_USERNAME_KEY);
    }

    return new ModelAndView("login", "lastUsername", lastUsername);
  }

  @GetMapping(value = "/")
  public ModelAndView index(/*@AuthenticationPrincipal*/ User user)
  {
    logger.debug( "index() : @AuthenticationPrincipal User user: {}", user);
    return new ModelAndView("index", Map.of("users", userService.listUsers(), "user", new User()));
  }

  @PostMapping(value = "/")
  public String addUser(@ModelAttribute User user)
  {
    logger.debug( "addUser() : @ModelAttribute User user: {}", user);

    userService.addUser(user);
    return "redirect:/";
  }

}

