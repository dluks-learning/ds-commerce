package dev.dluks.dscommerce.repositories;

import java.util.List;
import java.util.Optional;

import dev.dluks.dscommerce.models.User;
import dev.dluks.dscommerce.projections.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value = """
            	SELECT users.email AS username, users.password, roles.id AS roleId, roles.authority
            	FROM users
            	INNER JOIN users_roles ON users.id = users_roles.user_id
            	INNER JOIN roles ON roles.id = users_roles.role_id
            	WHERE users.email = :email
            """)
    List<UserDetailsProjection> searchUserAndRolesByEmail(String email);

    Optional<User> findByEmail(String email);
}
