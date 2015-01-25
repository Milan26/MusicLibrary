package project.pa165.musiclibrary.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.inject.Inject;

/**
 * @author Milan
 */
public class RestAuthenticatorHelperImpl implements RestAuthenticatorHelper {

    private static final Logger logger = LoggerFactory.getLogger(RestAuthenticatorHelperImpl.class);
    @Inject
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;

    @Override
    public void authenticate() {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("rest", "rest");
        try {
            SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(auth));
        } catch (AuthenticationException authEx) {
            logger.warn("failed to authenticate:", authEx);
        }
    }
}
