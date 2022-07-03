package ru.tst.rest.service.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.tst.rest.domain.User;
import ru.tst.rest.repository.UserRepository;

import java.util.Collections;
import java.util.Locale;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);

        String lowercaseLogin = login.toLowerCase();
        return userRepository
                .findOneByLogin(lowercaseLogin)
                .filter(user -> !user.getBlocked())
                .map(this::createSpringSecurityUser)
                .orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database"));
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(User user) {
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ADMIN")));
    }
}
