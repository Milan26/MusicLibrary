package project.pa165.musiclibrary.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import project.pa165.musiclibrary.dto.UserDto;
import project.pa165.musiclibrary.entities.UserAuthority;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Milan
 */
@Named
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Inject
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDto user = userService.findUserByEmail(email);
        logger.debug("user exists={}, with email={}", user != null, email);
        return buildUserForAuthentication(user, buildUserAuthority(user.getUserAuthorities()));
    }

    private User buildUserForAuthentication(UserDto user, List<GrantedAuthority> authorities) {
        return new User(user.getEmail(), user.getPassword(), user.getEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<UserAuthority> userAuthorities) {
        Set<GrantedAuthority> authorities = new HashSet<>();

        for (UserAuthority userAuthority : userAuthorities) {
            authorities.add(new SimpleGrantedAuthority(userAuthority.getAuthority()));
        }

        return new ArrayList<>(authorities);
    }
}
