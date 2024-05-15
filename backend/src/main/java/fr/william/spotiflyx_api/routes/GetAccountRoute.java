package fr.william.spotiflyx_api.routes;

import fr.william.spotiflyx_api.AuthService;
import fr.william.spotiflyx_api.Main;
import fr.william.spotiflyx_api.SpotiflyxRoute;
import fr.william.spotiflyx_api.response.Response;
import fr.william.spotiflyx_api.token.TokenManager;
import io.javalin.http.Context;
import org.bson.Document;

public class GetAccountRoute implements SpotiflyxRoute {
    @Override
    public void handle(Context ctx) {
        String token = ctx.header("Authorization");

        if (!AuthService.isAuthorized(token)) {
            ctx.status(401);
            ctx.result(new Document("ok", false).append("message", "Unauthorized").toJson());
            return;
        }
        Response response = Main.getUserService().getAccountFromToken(AuthService.extractToken(token));
        ctx.status(response.getStatus());
        ctx.result(response.toJson());
    }
}
