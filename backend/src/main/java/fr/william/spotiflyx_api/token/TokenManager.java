package fr.william.spotiflyx_api.token;

import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TokenManager {

    private final static Map<String, String> tokens = new HashMap<>();
    private static final String SALT_ROUNDS = System.getenv("SALT_ROUNDS");

    public static String generateToken(String userId) {
        String token = BCrypt.hashpw(userId + System.currentTimeMillis() + UUID.randomUUID(), SALT_ROUNDS);
        tokens.put(token, userId);
        return token;
    }

    public static boolean isValidToken(String token) {
        return tokens.containsKey(token);
    }

    public static String getUserEmailFromToken(String token) {
        return tokens.get(token);
    }

    public static void removeToken(String token) {
        tokens.remove(token);
    }

}
