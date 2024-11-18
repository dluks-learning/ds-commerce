package dev.dluks.dscommerce.services;

import dev.dluks.dscommerce.models.User;
import dev.dluks.dscommerce.services.exceptions.ForbiddenException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public void validateSelfOrAdmin(long userId) {

        User me = userService.authenticated();

        if (!me.getId().equals(userId) && !me.hasRole("ROLE_ADMIN")) {
            throw new ForbiddenException("Acesso negado");
        }

    }

}
