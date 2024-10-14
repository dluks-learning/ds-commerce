package dev.dluks.dscommerce.dtos;

import dev.dluks.dscommerce.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private final Long id;
    private final String name;
    private final String email;
    private final String phone;
    private final String birthDate;

    private final List<String> roles = new ArrayList<>();

    public UserDTO(User entity) {
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
        phone = entity.getPhone();
        birthDate = entity.getBirthDate().toString();

        entity.getAuthorities().forEach(role -> this.roles.add(role.getAuthority()));
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public List<String> getRoles() {
        return roles;
    }
}
