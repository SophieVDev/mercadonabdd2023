package fr.mercadona.mercadona.repository;

import fr.mercadona.mercadona.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByLoginAndPassword(String login, String password);
    Optional<User> findFirstByLogin(String login);
    Optional<User> findByLogin(String login);

}
