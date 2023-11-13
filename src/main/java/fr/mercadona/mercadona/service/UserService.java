package fr.mercadona.mercadona.service;
import org.mindrot.jbcrypt.BCrypt;
import fr.mercadona.mercadona.model.User;
import fr.mercadona.mercadona.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
// NEW
private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String login, String password, String email) {
        if (login == null || password == null) {
            return null;
        } else {
            if (userRepository.findFirstByLogin(login).isPresent()) {
                System.out.println("Duplicate login");
                return null;
            }
            User user = new User();
            user.setLogin(login);

            // Hacher le mot de passe avant de le stocker
            String hashedPassword = hasherMotDePasse(password);
            user.setPassword(hashedPassword);

            user.setEmail(email);
            return userRepository.save(user);
        }
    }

    public User authenticate(String login, String password) {
        // Récupérer l'utilisateur depuis la base de données
        User user = userRepository.findByLogin(login).orElse(null);

        if (user != null) {
            // Utiliser BCrypt.checkpw pour vérifier le mot de passe
            if (BCrypt.checkpw(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    private String hasherMotDePasse(String motDePasse) {
        // Générer un sel aléatoire
        String sel = BCrypt.gensalt();

        // Hacher le mot de passe avec le sel
        return BCrypt.hashpw(motDePasse, sel);
    }
}


