package fr.mercadona.mercadona.model;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PasswordHashes {
    public static String hasherMotDePasse(String motDePasse) {
        String sel = BCrypt.gensalt();
        return BCrypt.hashpw(motDePasse, sel);
    }

    public static void main(String[] args) {
        // Assurez-vous de configurer correctement votre connexion à la base de données
        String url = "jdbc:postgresql://postgresql-sophie88.alwaysdata.net/sophie88_mercadonabdd";
        String utilisateur = "${PGUSER}";
        String motDePasse = "${PGPASSWORD}";

        try (Connection connexion = DriverManager.getConnection(url, utilisateur, motDePasse)) {
            // Inscription d'un nouvel utilisateur
            String motDePasseAInscrire = "mot_de_passe_securise";
            String motDePasseHashe = hasherMotDePasse(motDePasseAInscrire);

            // Stocker motDePasseHashe dans la base de données avec le sel

            // Authentification d'un utilisateur existant
            String selectQuery = "SELECT id, password, sel FROM user_table";
            try (PreparedStatement preparedStatement = connexion.prepareStatement(selectQuery);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int userId = resultSet.getInt("id");
                    String storedPasswordHash = resultSet.getString("password");
                    String storedSalt = resultSet.getString("sel");

                    // Utilisation de BCrypt.checkpw pour vérifier le mot de passe
                    if (BCrypt.checkpw("mot_de_passe_entre_par_utilisateur", storedPasswordHash)) {
                        System.out.println("Mot de passe correct pour l'utilisateur avec l'ID : " + userId);
                    } else {
                        System.out.println("Mot de passe incorrect pour l'utilisateur avec l'ID : " + userId);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

