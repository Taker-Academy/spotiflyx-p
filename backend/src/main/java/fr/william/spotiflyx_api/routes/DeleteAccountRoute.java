package fr.william.spotiflyx_api.routes;

import fr.william.spotiflyx_api.Main;
import fr.william.spotiflyx_api.SpotiflyxRoute;
import fr.william.spotiflyx_api.response.Response;
import io.javalin.http.Context;
import org.bson.Document;

public class DeleteAccountRoute implements SpotiflyxRoute {
    @Override
    public void handle(Context ctx) {
        Document document = Document.parse(ctx.body());

        if (!document.containsKey("email") || !document.containsKey("password")) {
            ctx.status(400).json(new Document("message", "Missing email or password"));
            return;
        }

        String email = document.getString("email");
        String password = document.getString("password");

        Response response = Main.getUserService().deleteAccount(email, password);
        ctx.status(response.getStatus()).json(response.toJson());
    }
}
