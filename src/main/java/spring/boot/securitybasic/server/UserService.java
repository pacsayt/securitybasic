package spring.boot.securitybasic.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


@Service
public class UserService
{
  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private UserRepository userRepository;

  // @PreAuthorize and @PostAuthorize, which allow you to write expressions containing references to method parameters
  // and return values respectively.
//  @Secured("ROLE_USER") // Nem tudom, ez milyen viszonyban van a tobbivel ... proxy ojektumot huz ra, ami ellenorzi
  public UserDetails loadUserByUsername( String username)
  {
    logger.info( "loadUserByUsername( {})", username);
    return userRepository.findUserByUsername( username).orElseThrow(() -> new UsernameNotFoundException( "User not found: " + username));
  }

  public List<User> listUsers()
  {
    Object user = ""; // SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    logger.info("listUsers() : Logged in user: {}", user);

    return userRepository.findAll(Sort.by("username"));
  }

  public void addUser( User newUser)
  {
    // save() : the data associated with the save operation will not be flushed to the DB unless and until an explicit call to flush() or commit() method is made.
    userRepository.saveAndFlush( newUser);
  }
}