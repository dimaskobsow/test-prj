package ru.tst.rest.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import ru.tst.rest.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final UserRepository repository;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        var login = request.getParameter("username").toLowerCase();
        repository.findOneByLogin(login).ifPresent(user -> {

            if (user.getLastFail() == null || Instant.now().isAfter(user.getLastFail().plus(1, ChronoUnit.HOURS))) {
                user.setLastFail(Instant.now());
                user.setBlocked(false);
                user.setLoginAttempt(0);
            }

            user.setLoginAttempt(user.getLoginAttempt() != null ? user.getLoginAttempt() + 1 : 1);
            if (user.getLoginAttempt() >= 10) {
                user.setBlocked(true);
            }

            repository.save(user);
        });
        super.onAuthenticationFailure(request, response, exception);
    }
}
