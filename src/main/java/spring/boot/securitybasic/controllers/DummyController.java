package spring.boot.securitybasic.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import spring.boot.securitybasic.configurations.SecurityConfig;

/**
 * http://localhost:8080/root/eleresiutvonalvaltozo/?requestParam=keresparameter
 */
@RestController
public class DummyController
{
  private final Logger logger = LoggerFactory.getLogger( DummyController.class);

  @RequestMapping(value = "/root/{pathVariable}", method = RequestMethod.GET)
  public String rootMapping( @PathVariable("pathVariable") String pathVariable, @RequestParam("requestParam") String requestParam)
  {
    // HELLO WORLD ! pathVariable=eleresiutvonalvaltozo requestParam=keresparameter
    logger.info( "DummyController::rootMapping() : pathVariable={}", pathVariable);

    return "HELLO WORLD ! pathVariable=" + pathVariable + " requestParam=" + requestParam;
  }

  @RequestMapping( value="/error2")
  public String errorHandler()
  {
    logger.info( "DummyController::errorHandler() : ");

    return "SOME UNCLASSIFIED ERROR ;-)";
  }
/*
  @GetMapping("authtoken")
  public AuthenticationResponse gettAuthToken( JwtAuthenticationToken jwtAuthenticationToken)
  {
    System.out.println( "DummyController::gettAuthToken() : ");

    System.out.println( jwtAuthenticationToken.getName());
    System.out.println( jwtAuthenticationToken.getAuthorities());
    System.out.println( jwtAuthenticationToken.getTokenAttributes().get( StandardClaimNames.PREFERRED_USERNAME));

    return new AuthenticationResponse("========== Hello JWT! ==========");
  }
*/
}