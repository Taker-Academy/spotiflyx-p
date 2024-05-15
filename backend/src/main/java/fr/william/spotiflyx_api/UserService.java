package fr.william.spotiflyx_api;

import fr.william.spotiflyx_api.database.MariaDBService;
import fr.william.spotiflyx_api.response.ErrorResponse;
import fr.william.spotiflyx_api.response.Response;
import fr.william.spotiflyx_api.response.SuccessResponse;
import fr.william.spotiflyx_api.token.TokenManager;
import org.bson.Document;
import org.mindrot.jbcrypt.BCrypt;

public class UserService {

    private final MariaDBService mariaDBService;

    public UserService(MariaDBService mariaDBService) {
        this.mariaDBService = mariaDBService;
    }

    public Response register(String email, String password, String firstName, String lastName) {
        if (mariaDBService.emailExists(email))
            return new ErrorResponse("Email already exists", 400);

        String hashedPassword = BCrypt.hashpw(password, System.getenv("SALT_ROUNDS"));
        mariaDBService.createUser(email, hashedPassword, firstName, lastName);
        String token = TokenManager.generateToken(email);
        AuthService authService = new AuthService(mariaDBService);
        authService.sendConfirmationEmail(email, token);
        return new SuccessResponse(new Document("token", token));
    }

    public Response login(String email, String password) {
        if (!mariaDBService.emailExists(email))
            return new ErrorResponse("Email not found", 404);

        String hashedPassword = mariaDBService.getPassword(email);
        if (!BCrypt.checkpw(password, hashedPassword))
            return new ErrorResponse("Invalid password", 400);

        String token = TokenManager.generateToken(email);
        return new SuccessResponse(new Document("token", token));
    }

    public Response logout(String token) {
        if (!AuthService.isAuthorized(token))
            return new ErrorResponse("Unauthorized", 401);
        TokenManager.removeToken(token);
        return new SuccessResponse(new Document("message", "Logged out successfully"));
    }

    public Response changePassword(String id, String oldPassword, String newPassword) {
        if (!mariaDBService.idExists(id))
            return new ErrorResponse("Account not found", 404);

        String hashedOldPassword = mariaDBService.getPassword(id);
        if (!BCrypt.checkpw(oldPassword, hashedOldPassword))
            return new ErrorResponse("Invalid old password", 400);

        String hashedNewPassword = BCrypt.hashpw(newPassword, System.getenv("SALT_ROUNDS"));
        mariaDBService.updatePassword(id, hashedNewPassword);
        return new SuccessResponse(new Document("message", "Password changed successfully"));
    }

    public Response deleteAccount(String email, String password) {
        if (!mariaDBService.emailExists(email))
            return new ErrorResponse("Email not found", 404);

        String hashedPassword = mariaDBService.getPassword(email);
        if (!BCrypt.checkpw(password, hashedPassword))
            return new ErrorResponse("Invalid password", 400);

        mariaDBService.deleteUser(email);
        return new SuccessResponse(new Document("message", "Account deleted successfully"));
    }
}
