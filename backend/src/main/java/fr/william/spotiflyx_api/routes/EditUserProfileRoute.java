package fr.william.spotiflyx_api.routes;

import fr.william.spotiflyx_api.UserRoute;
import io.javalin.http.Context;
import org.bson.Document;

public class EditUserProfileRoute implements UserRoute {
    @Override
    public void handle(Context ctx) {
        Document document = Document.parse(ctx.body());

        if (!document.containsKey("email") || !document.containsKey("firstName") || !document.containsKey("lastName")) {
            ctx.status(400).json(new Document("message", "Missing email, firstName or lastName"));
            return;
        }

        String email = document.getString("email");
        String firstName = document.getString("firstName");
        String lastName = document.getString("lastName");


    }
}
