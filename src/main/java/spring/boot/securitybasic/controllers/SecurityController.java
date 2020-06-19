package spring.boot.securitybasic.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class SecurityController
{
  private final Logger logger = LoggerFactory.getLogger( SecurityController.class);

  @ResponseBody
  @RequestMapping( value = "/usernameprinc", method = RequestMethod.GET)
  public String currentUserName( Principal principal)
  {
    logger.info( "currentUserName() : principal={}", principal);
    return principal.getName();
  }
}