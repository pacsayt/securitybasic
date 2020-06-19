package spring.boot.securitybasic.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * https://www.baeldung.com/get-user-in-spring-security
 */
@Controller
public class GetUserWithHTTPServletRequestController
{
  private final Logger logger = LoggerFactory.getLogger( GetUserWithHTTPServletRequestController.class);

  @ResponseBody
  @RequestMapping( value = "/usernamehttp", method = RequestMethod.GET)
  public String currentUserNameSimple( HttpServletRequest request)
  {
    logger.info( "currentUserNameSimple()");
    Principal principal = request.getUserPrincipal();
    return principal.getName();
  }
}